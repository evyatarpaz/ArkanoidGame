//211788625 Evyatar Paz
package Animation;
import Base.Animation;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a count-down animation object.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private int countFrom;
    private boolean running;
    private double numOfMillis;
    private int numberCounting;
    private long initiationTime;

    /**
     * constructor.
     * @param numOfSeconds the number of seconds
     * @param countFrom  from which number to count
     * @param gameScreen  the sprites list
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.initiationTime = System.currentTimeMillis();
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.numberCounting = countFrom;
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (countFrom == 1) {
            this.running = false;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(117, 82, 46));
        d.drawText(380, 450, Integer.toString(countFrom), 80);
        if (System.currentTimeMillis() - this.initiationTime
                > this.numOfMillis / this.numberCounting) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}