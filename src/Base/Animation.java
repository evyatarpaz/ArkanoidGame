//211788625 Evyatar Paz
package Base;

import biuoop.DrawSurface;

/**
 * The interface Base.Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}