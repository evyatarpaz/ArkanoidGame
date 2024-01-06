//211788625 Evyatar Paz
package Base;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;

/**
 * The interface Base.Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at a collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}