//211788625 Evyatar Paz
package Sprites;

import Base.Sprite;
import Collidable.CollisionInfo;
import Game.GameLevel;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The Sprites.Ball class represents a ball object with a center point, a size,  a color and boundaries.
 *
 * @author evyatar paz evyatarpaz@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class Ball implements Sprite {
    private Point center;
    private final int size;
    private final Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;


    /**
     * Constructor that creates a Sprites.Ball object.
     *
     * @param x               the x-coordinate of the center point of the ball
     * @param y               the y-coordinate of the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        double speed = 50.0 / r;
        this.velocity = new Velocity(speed, speed);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new Sprites.Ball.
     *
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.size = r;
        this.color = color;
        double speed = 50.0 / r;
        this.velocity = new Velocity(speed, speed);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball to the given dx and dy values.
     *
     * @param dx the change in x position of the ball per step
     * @param dy the change in y position of the ball per step
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gets the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }


    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * the method moves the ball's center according to its velocity.
     * if the ball hit the boundaries changes the speed accordingly
     * take into a count the size of the ball
     */
    public void moveOneStep() {
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory());
        // there is no collision with any obstacle
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // check if the point is in the obstacle
            if (isInRec(info.collisionObject().getCollisionRectangle())) {
                this.center = new Point(this.center.getX(), this.center.getY() - 50);
                this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
            } else {
                // move the ball to "almost" the hit point, but just slightly before it.
                Point collision = info.collisionPoint();
                Rectangle rec = info.collisionObject().getCollisionRectangle();
                if (collision.getX() == (rec.getUpperLeft().getX())) {
                    this.center = new Point(collision.getX() - this.size, collision.getY());
                }
                if (collision.getX() == (rec.getUpperLeft().getX() + rec.getWidth())) {
                    this.center = new Point(collision.getX() + this.size, collision.getY());
                }
                if (collision.getY() == (rec.getUpperLeft().getY())) {
                    this.center = new Point(collision.getX(), collision.getY() - this.size);
                }
                if (collision.getY() == (rec.getUpperLeft().getY() + rec.getHeight())) {
                    this.center = new Point(collision.getX(), collision.getY() + this.size);
                }
            }
            // update the ball speed
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * calculate the trajectory of the ball.
     *
     * @return the line
     */
    public Line trajectory() {
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);
        return trajectory;
    }

    /**
     * check if point is in the given rectangle.
     *
     * @param rec - a rectangle
     * @return - true if yes and false otherwise.
     */
    private boolean isInRec(Rectangle rec) {
        double x = this.center.getX();
        double y = this.center.getY();
        double upperLeftX = rec.getUpperLeft().getX();
        double upperLeftY = rec.getUpperLeft().getY();
        return (x >= upperLeftX && x <= upperLeftX + rec.getWidth() && y >= upperLeftY
                && y <= upperLeftY + rec.getHeight());
    }
}
