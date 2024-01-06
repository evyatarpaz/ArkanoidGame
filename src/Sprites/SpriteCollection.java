//211788625 Evyatar Paz
package Sprites;

import Base.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Base.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Base.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> newSpriteList = new ArrayList<>(this.sprites);
        for (Sprite s : newSpriteList) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}