//211788625 Evyatar Paz
package Base;

import Game.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Base.Sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * add the object to the gameLevel.
     * @param gameLevel - the gameLevel.
     */
    void addToGame(GameLevel gameLevel);

}

