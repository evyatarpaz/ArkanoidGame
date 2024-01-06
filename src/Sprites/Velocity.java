//211788625 Evyatar Paz
package Sprites;

import Geometry.Point;

/**
 * The Sprites.Velocity.
 *
 * @author evyatar paz evyatarpaz@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor
     * Instantiates a new Sprites.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Sets dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }


    /**
     * Sets dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert the angle from degrees to radians
        double radians = Math.toRadians(angle);

        // Calculate the x and y components of the velocity using trigonometry
        double dx = speed * Math.sin(radians);
        double dy = speed * -Math.cos(radians);

        // Create and return a new Sprites.Velocity object with the calculated components
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}