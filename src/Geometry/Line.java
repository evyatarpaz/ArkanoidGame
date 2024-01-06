// 211788625 Evyatar Paz
package Geometry;

import java.util.List;


/**
 * The Geometry.Line class represents a line in 2D space with start and end points.
 *
 * @author evyatar paz evyatarpaz@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class Line {
    // start point of the line
    private final Point start;
    // end point of the line
    private final Point end;
    /**
     * The Epsilon.
     */
    static final double EPSILON = 0.0000001;

    /**
     * Constructs a new Geometry.Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Geometry.Line with the specified coordinates of the start and end points.
     *
     * @param x1 the x coordinate of the start point of the line
     * @param y1 the y coordinate of the start point of the line
     * @param x2 the x coordinate of the end point of the line
     * @param y2 the y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates and returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates and returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the slope of a line given its start and end points.
     *
     * @return The slope of the line as a double.
     */
    public double slopeLine() {
        // Obtain the x and y coordinates of the start and end points of the line.
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // Calculate the slope of the line using the formula: (y1 - y2) / (x1 - x2)
        return ((y1 - y2) / (x1 - x2));
    }

    /**
     * Returns true if the given point is on the line, false otherwise.
     *
     * @param point The point to check.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean onLine(Point point) {
        // Get the x and y values of the point.
        double x = point.getX();
        double y = point.getY();
        // Get the slope of the line.
        double slope = this.slopeLine();
        // If the slope is 0, the line is horizontal.
        if (slope == 0) {
            if (!this.start.equalsY(y)) {
                return false;
            }
            return (x >= Math.min(this.start.getX(), this.end.getX()) - EPSILON
                    && x <= Math.max(this.start.getX(), this.end.getX()) + EPSILON);
        }
        if (slope == Double.NEGATIVE_INFINITY || slope == Double.POSITIVE_INFINITY) {
            if (!this.start.equalsX(x)) {
                return false;
            }
            return (y >= Math.min(this.start.getY(), this.end.getY()) - EPSILON
                    && y <= Math.max(this.start.getY(), this.end.getY()) + EPSILON);
        }
        // Calculate the y value of the point on the line using the slope-intercept formula.
        double yOnLine = slope * (x - this.start.getX()) + this.start.getY();
        // Check if the y value of the point is equal to the calculated y value.
        return (y >= yOnLine - EPSILON && y <= yOnLine + EPSILON);
    }

    /**
     * Returns true if the intersection point is between values of the line, false otherwise.
     *
     * @param x     the x parameter of the intersection point
     * @param y     the y parameter of the intersection point
     * @param other the other line
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isBetweenValues(double x, double y, Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        return (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                && x >= Math.min(x3, x4) && x <= Math.max(x3, x4)
                && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)
                && y >= Math.min(y3, y4) && y <= Math.max(y3, y4));
    }

    /**
     * Is y between values boolean.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean isYBetweenValues(Line other) {
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = other.start.getY();
        double y4 = other.end.getY();
        return (y1 < Math.max(y3, y4) && y1 > Math.min(y3, y4)
                || y2 < Math.max(y3, y4) && y2 > Math.min(y3, y4)
                || y3 < Math.max(y1, y2) && y3 > Math.min(y1, y2)
                || y4 < Math.max(y1, y2) && y4 > Math.min(y1, y2));
    }

    /**
     * Share edge point point.
     *
     * @param other the other
     * @return the point
     */
    public Point shareEgdePoint(Line other) {
        if (this.start.equals(other.end) || this.start.equals(other.start)) {
            return start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    /**
     * Is X between values boolean.
     *
     * @param other the other line
     * @return the boolean
     */
    public boolean isXBetweenValues(Line other) {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        return (x1 < Math.max(x3, x4) && x1 > Math.min(x3, x4)
                || x2 < Math.max(x3, x4) && x2 > Math.min(x3, x4)
                || x3 < Math.max(x1, x2) && x3 > Math.min(x1, x2)
                || x4 < Math.max(x1, x2) && x4 > Math.min(x1, x2));
    }

    /**
     * this methode calls to the intersectionWith methode if we get a point return true
     * if we don't get a point check if the lines overlaps.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            if (this.equals(other)) {
                return true;
            }
            return this.onLine(other.start) || this.onLine(other.end)
                    || other.onLine(this.start) || other.onLine(this.end);
        }
        return true;
    }

    /**
     * Returns the intersection point of this line and another given line, if such
     * an intersection exists. If the lines are identical, returns null. If the
     * lines are parallel but not identical, returns null. If the lines intersect
     * at exactly one point, returns that point.
     *
     * @param other the other line to check for intersection with this line
     * @return the intersection point, if one exists, or null if there is no intersection
     */
    public Point intersectionWith(Line other) {
        // Get the start and end points of this line
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // Get the start and end points of the other line
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Check if this line and the other line are identical
        if (this.equals(other)) {
            return null;
        }
        // Check if both lines are vertical (i.e. have infinite slope) and have the same x coordinate
        if (x1 == x2 && x3 == x4) {
            // If the lines are not on the same x coordinate, they cannot intersect
            if (x2 != x3) {
                return null;
            } else {
                // If the lines overlap without the edges return null
                if (this.isYBetweenValues(other)) {
                    return null;
                } else {
                    // if the lines share only one point the edges return the corresponding edge point
                    return this.shareEgdePoint(other);
                }
            }
        }
        // Check if this line is vertical (i.e. has infinite slope)
        if (x1 == x2) {
            // Check if the other line is horizontal (i.e. has zero slope)
            if (other.slopeLine() == 0) {
                // Check if the intersection point is within the bounds of both lines
                if (x1 <= Math.max(x3, x4) && x1 >= Math.min(x3, x4)
                        && y3 >= Math.min(y1, y2) && y3 <= Math.max(y1, y2)) {
                    return new Point(x1, y3);
                }
            } else {
                // Calculate the intersection point using the slope of the other line
                double slopeOtherLine = other.slopeLine();
                double x, y;
                x = x1;
                y = slopeOtherLine * (x - x3) + y3;
                // Check if the intersection point is within the bounds of both lines
                if (isBetweenValues(x, y, other)) {
                    return new Point(x, y);
                }
            }
            return null;
        }
        // Check if the other line is vertical (i.e. has infinite slope)
        if (x3 == x4) {
            // Check if this line is horizontal (i.e. has zero slope)
            if (this.slopeLine() == 0) {
                // Check if the intersection point is within the bounds of both lines
                if (x3 <= Math.max(x1, x2) && x3 >= Math.min(x1, x2)
                        && y1 >= Math.min(y3, y4) && y1 <= Math.max(y3, y4)) {
                    return new Point(x3, y1);
                }
            } else {
                // Calculate the intersection point using the slope of the other line
                double x, y;
                x = x3;
                y = this.slopeLine() * (x - x1) + y1;
                // Check if the intersection point is within the bounds of both lines
                if (isBetweenValues(x, y, other)) {
                    return new Point(x, y);
                }
            }
            return null;
        }
        // Check if both lines are horizontal (i.e. has zero slope) and have the same y coordinate
        if (this.slopeLine() == 0 && other.slopeLine() == 0) {
            // If the lines are not on the same y coordinate, they cannot intersect
            if (y1 != y3) {
                return null;
            }
            // If the lines overlap without the edges return null
            if (this.isXBetweenValues(other)) {
                return null;
            }
            // if the lines share only one point the edges return the corresponding edge point
            return this.shareEgdePoint(other);
        }
        // Check if this line is horizontal (i.e. has zero slope)
        if (this.slopeLine() == 0) {
            // Calculate the intersection point using the slope of the other line
            double x, y;
            y = y1;
            x = ((y - y3) / (other.slopeLine())) + x3;
            if (isBetweenValues(x, y, other)) {
                return new Point(x, y);
            }
            return null;
        }
        // Check if the other line is horizontal (i.e. has zero slope)
        if (other.slopeLine() == 0) {
            // Calculate the intersection point using the slope of this line
            double x, y;
            y = y3;
            x = ((y - y1) / (this.slopeLine())) + x1;
            // Check if the intersection point is within the bounds of both lines
            if (isBetweenValues(x, y, other)) {
                return new Point(x, y);
            }
            return null;
        }
        // Check if both lines are parallels
        if (this.slopeLine() == other.slopeLine()) {
            // If the lines overlap without the edges return null
            if (x1 < Math.max(x3, x4) && x1 > Math.min(x3, x4)
                    && y1 < Math.max(y3, y4) && y1 > Math.min(y3, y4)
                    || x2 < Math.max(x3, x4) && x2 > Math.min(x3, x4)
                    && y2 < Math.max(y3, y4) && y2 > Math.min(y3, y4)
                    || x3 < Math.max(x1, x2) && x3 > Math.min(x1, x2)
                    && y3 < Math.max(y1, y2) && y3 > Math.min(y1, y2)
                    || x4 < Math.max(x1, x2) && x4 > Math.min(x1, x2)
                    && y4 < Math.max(y1, y2) && y4 > Math.min(y1, y2)) {
                return null;
            }
            // if the lines share only one point the edges return the corresponding edge point
            return this.shareEgdePoint(other);
        }
        // Calculate the intersection point using the slope of this and other line
        double x, y;
        x = ((this.slopeLine() * x1 - y1) - (other.slopeLine() * x3 - y3))
                / (this.slopeLine() - other.slopeLine());
        y = this.slopeLine() * (x - x1) + y1;
        // Check if the intersection point is within the bounds of both lines
        if (isBetweenValues(x, y, other)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * Compares this Geometry.Line object with another Geometry.Line object to see if they are equal.
     *
     * @param other the other Geometry.Line object to compare to
     * @return true if the two Geometry.Line objects are equal, false otherwise
     */
    public boolean equals(Line other) {
        // get the start and end points of both lines
        Point startLine = this.start();
        Point endLine = this.end();
        Point startOtherLine = other.start();
        Point endOtherLine = other.end();

        // check if the two lines have the same start and end points, in any order
        return startLine.equals(endOtherLine) && endLine.equals(startOtherLine)
                || startLine.equals(startOtherLine) && endLine.equals(endOtherLine);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get a list of all intersection points between the line and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        // If there are no intersection points, return null
        if (intersectionPoints.size() == 0) {
            return null;
        }
        // Initialize an array to hold the distance between each intersection point and the start of the line
        double[] distances = new double[intersectionPoints.size()];
        // Iterate through each intersection point and calculate its distance to the start of the line
        for (int i = 0; i < intersectionPoints.size(); i++) {
            distances[i] = this.start.distance(intersectionPoints.get(i));
        }
        // Find the index of the intersection point with the smallest distance to the start of the line
        int index = 0;
        double min = distances[0];
        for (int i = 1; i < distances.length; i++) {
            if (min > distances[i]) {
                min = distances[i];
                index = i;
            }
        }
        // Return the intersection point with the smallest distance to the start of the line
        return intersectionPoints.get(index);
    }
}