//211788625 Evyatar Paz
package Animation;
import Base.Animation;
import Game.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean win;
    private boolean close;

    /**
     * Instantiates a new End screen.
     *
     * @param score          the score
     * @param win            the win
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score.getValue();
        this.win = win;
        this.close = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0x145565));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        if (this.win) {
            d.drawText(130, 200, "Congratulations!", 80);
            d.setColor(new Color(0x8131F9));
            d.drawText(134, 196, "Congratulations!", 80);

            d.setColor(Color.BLACK);
            d.drawText(210, 280, "You Won :)", 60);
            d.setColor(new Color(0x944B72));
            d.drawText(214, 276, "You Won :)", 60);
        } else {
            d.drawText(150, 200, "Game Over", 100);
            d.setColor(new Color(0x42C1F3));
            d.drawText(154, 196, "Game Over ", 100);

            d.setColor(Color.BLACK);
            d.drawText(180, 280, "You Lost :(", 60);
            d.setColor(new Color(0x2B985F));
            d.drawText(184, 276, "You Lost :(", 60);
        }
        d.setColor(Color.BLACK);
        d.drawText(250, 370, "Press space to continue", 25);
        d.setColor(Color.WHITE);
        d.drawText(320, 430, "Final score: " + this.score, 20);
    }

    @Override
    public boolean shouldStop() {
        return this.close;
    }
}
