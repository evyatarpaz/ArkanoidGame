//211788625 Evyatar Paz
package Game;

import Collidable.Block;
import Base.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Block scoreBlock;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.scoreBlock = new Block(0, 0, 800, 25, Color.white);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.scoreBlock.drawOn(d);
        d.setColor(Color.black);
        String stringScore = "Score: " + this.score.getValue();
        d.drawText((int) (this.scoreBlock.getRectangle().getUpperLeft().getX()
                        + this.scoreBlock.getRectangle().getWidth() / 2 - 20),
                (int) (this.scoreBlock.getRectangle().getUpperLeft().getY()
                        + this.scoreBlock.getRectangle().getHeight() / 2 + 5), stringScore, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
