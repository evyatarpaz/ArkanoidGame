//211788625 Evyatar Paz
package Collidable;

import Base.Collidable;
import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Base.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Base.Collidable.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private double borderSize;
    private Color color;
    private int velocity;
    private double widthBound;

    /**
     * Instantiates a new Base.Collidable.Paddle.
     *
     * @param rectangle      the rectangle
     * @param border         the border
     * @param color          the color
     * @param keyboardSensor the keyboard sensor
     * @param widthBound     the width bound
     */
    public Paddle(Rectangle rectangle, int border, Color color, KeyboardSensor keyboardSensor, double widthBound) {
        this.rectangle = rectangle;
        this.keyboard = keyboardSensor;
        this.borderSize = border;
        this.widthBound = widthBound;
        this.color = color;
        this.velocity = 10;
    }

    /**
     * Instantiates a new Base.Collidable.Paddle.
     *
     * @param upperLeft      the upper left
     * @param wight          the wight
     * @param height         the height
     * @param border         the border
     * @param color          the color
     * @param keyboardSensor the keyboard sensor
     * @param widthBound     the width bound
     */
    public Paddle(Point upperLeft, double wight, double height, int border,
                  Color color, KeyboardSensor keyboardSensor, double widthBound) {
        this.rectangle = new Rectangle(upperLeft, wight, height);
        this.keyboard = keyboardSensor;
        this.widthBound = widthBound;
        this.borderSize = border;
        this.color = color;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        Rectangle nextRec = new Rectangle(new Point(rectangle.getUpperLeft().getX() - this.velocity,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        if (nextRec.getUpperLeft().getX() >= borderSize) {
            this.rectangle = nextRec;
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        Rectangle nextRec = new Rectangle(new Point(rectangle.getUpperLeft().getX() + this.velocity,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        if (nextRec.getUpperLeft().getX() + nextRec.getWidth()
                <= (this.widthBound - this.borderSize)) {
            this.rectangle = nextRec;
        }
    }

    /**
     * Sets velocity.
     *
     * @param velocity the velocity
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    // Base.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Base.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Calculate the position of each of the five regions in the block.
        double upperLeftX = rectangle.getUpperLeft().getX();
        double regionWidth = this.rectangle.getWidth() / 5;
        double region1 = upperLeftX + 1 * regionWidth;
        double region2 = upperLeftX + 2 * regionWidth;
        double region3 = upperLeftX + 3 * regionWidth;
        double region4 = upperLeftX + 4 * regionWidth;
        double region5 = upperLeftX + 5 * regionWidth;
        // Check which region the collision point is in and return a new velocity accordingly.
        if (collisionPoint.getX() >= upperLeftX && collisionPoint.getX() <= region1) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() >= region1 && collisionPoint.getX() <= region2) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() >= region2 && collisionPoint.getX() <= region3) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionPoint.getX() >= region3 && collisionPoint.getX() <= region4) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() >= region4 && collisionPoint.getX() <= region5) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        // Check if the collision point is on the left or right side
        // of the block, and return a new velocity accordingly.
        if (this.rectangle.getLeftLine().onLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        }
        if (this.rectangle.getRightLine().onLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }


    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}