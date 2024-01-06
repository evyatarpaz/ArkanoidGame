//211788625 Evyatar Paz
package Geometry;

/**
 * The Geometry.Point class represents a point in 2D space with x and y coordinates.
 *
 * @author evyatar paz evyatarpaz@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class Point {

    private final double x; // x coordinate of the point
    private final double y; // y coordinate of the point

    /**
     * Constructs a new Geometry.Point with the specified x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the Euclidean distance between this point and the other point
     */
    public double distance(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();
        return Math.sqrt((this.x - x1) * (this.x - x1) + (this.y - y1) * (this.y - y1));
    }

    /**
     * Checks whether this point is equal to another point.
     *
     * @param other the other point to compare to
     * @return true if the points are equal (have the same x and y coordinates), false otherwise
     */
    public boolean equals(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();
        return (Math.abs(this.getX() - x1) < Math.pow(10, -5)
                && Math.abs(this.getY() - y1) < Math.pow(10, -5));
    }

    /**
     * Equals x boolean.
     *
     * @param x the x
     * @return the boolean
     */
    public boolean equalsX(double x) {
        return (Math.abs(this.getX() - x) < Math.pow(10, -5));
    }

    /**
     * Equals y boolean.
     *
     * @param y the y
     * @return the boolean
     */
    public boolean equalsY(double y) {
        return (Math.abs(this.getY() - y) < Math.pow(10, -5));
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}