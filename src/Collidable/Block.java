//211788625 Evyatar Paz
package Collidable;
import Base.Collidable;
import Base.HitNotifier;
import Sprites.Ball;
import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import Base.HitListener;
import Base.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Base.Collidable.Block class represents a rectangular block with a color,
 * which can collide with other collidables and be drawn on a surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor
     * Instantiates a new Base.Collidable.Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor
     * Instantiates a new Base.Collidable.Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * Instantiates a new Base.Collidable.Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }


    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove the block from the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Hit listeners list.
     *
     * @return the list
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }


    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        if (this.rectangle.getUpperLine().onLine(collisionPoint)
                || this.rectangle.getBottomLine().onLine(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        if (this.rectangle.getLeftLine().onLine(collisionPoint)
                || this.rectangle.getRightLine().onLine(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }
}
