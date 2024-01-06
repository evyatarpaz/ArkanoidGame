//211788625 Evyatar Paz
package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Geometry.Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line upperLine;
    private Line leftLine;
    private Line rightLine;
    private Line bottomLine;

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double x = upperLeft.getX(), y = upperLeft.getY();
        Point bottomRight = new Point(x + width, y + height);
        this.upperLine = new Line(upperLeft, new Point(x + width, y));
        this.leftLine = new Line(upperLeft, new Point(x, y + height));
        this.rightLine = new Line(bottomRight, new Point(x + width, y));
        this.bottomLine = new Line(bottomRight, new Point(x, y + height));
    }

    /**
     * Instantiates a new Geometry.Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        Point bottomRight = new Point(x + width, y + height);
        this.upperLine = new Line(upperLeft, new Point(x + width, y));
        this.leftLine = new Line(upperLeft, new Point(x, y + height));
        this.rightLine = new Line(bottomRight, new Point(x + width, y));
        this.bottomLine = new Line(bottomRight, new Point(x, y + height));
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = new Line[]{this.upperLine, this.leftLine, this.rightLine, this.bottomLine};
        List<Point> intersectionPoints = new ArrayList<>();
        for (Line value : lines) {
            Point intersctionPoint = line.intersectionWith(value);
            if (intersctionPoint != null) {
                intersectionPoints.add(intersctionPoint);
            }
        }
        return intersectionPoints;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper line.
     *
     * @return the upper line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Gets bottom line.
     *
     * @return the bottom line
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
}