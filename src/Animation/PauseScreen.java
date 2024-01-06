package Animation;

import Base.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0x2C2278));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(0x040D25));

        d.setColor(Color.BLACK);
        d.drawText(211, 211, "paused", 100);
        d.setColor(new Color(0x94167F));
        d.drawText(215, 206, "paused", 100);
        d.setColor(Color.BLACK);
        d.drawText(151, 291, "press space to continue", 50);
        d.setColor(new Color(0x754440));
        d.drawText(155, 284, "press space to continue", 50);

        d.setColor(new Color(0x2D77DD));
        d.fillCircle(200, 450, 90);
        d.setColor(new Color(0x040D07));
        d.fillRectangle(171, 396, 20, 110);
        d.fillRectangle(211, 396, 20, 110);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}