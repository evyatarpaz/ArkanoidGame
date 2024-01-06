//211788625 Evyatar Paz
package Animation;

import Base.Sprite;
import Game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * represents the background of the first level.
 */
public class DirectHit implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 192, 70);
        d.drawCircle(400, 192, 90);
        d.drawCircle(400, 192, 110);
        d.drawLine(377, 192, 270, 192);
        d.drawLine(420, 192, 540, 192);
        d.drawLine(400, 59, 400, 170);
        d.drawLine(400, 332, 400, 215);


    }

    @Override
    public void timePassed() {
    }
    /**
     * add the background to game.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
