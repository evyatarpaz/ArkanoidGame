//211788625 Evyatar Paz
package Game;

import Collidable.CollisionInfo;
import Base.Collidable;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Instantiates a new Game.Game environment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> newCollidableList = new ArrayList<>(this.collidableList);
        int size = newCollidableList.size();
        // If the collection of collidables is empty, return null
        if (size == 0) {
            return null;
        }
        // Initialize variables for collision information and the collision point
        CollisionInfo collisionInfo = null;
        Point collision = null;
        // Iterate through the collection of collidables and find the closest collision
        int i = 0;
        for (; i < size; i++) {
            // Find the closest intersection point between the trajectory and the current collidable
            collision = trajectory.closestIntersectionToStartOfLine(newCollidableList.get(i).getCollisionRectangle());
            // If a collision occurs, create a new Collidable.CollisionInfo object and set collisionInfo to it
            if (collision != null) {
                collisionInfo = new CollisionInfo(collision, newCollidableList.get(i));
                break;
            }
        }
        // If no collision was found, return null
        if (collision == null) {
            return null;
        }
        // Calculate the distance between the collision point and the start of the trajectory
        double distance = collision.distance(trajectory.start());
        // Iterate through the remaining collidables and find the closest collision
        for (; i < size; i++) {
            // Find the closest intersection point between the trajectory and the current collidable
            collision = trajectory.closestIntersectionToStartOfLine(newCollidableList.get(i).getCollisionRectangle());
            // If a collision occurs, and it is closer than the previous closest collision,
            // update the collisionInfo and distance variables
            if (collision != null) {
                if (collision.distance(trajectory.start()) < distance) {
                    collisionInfo = new CollisionInfo(collision, newCollidableList.get(i));
                }
            }
        }
        // Return the collisionInfo variable, which contains information about the closest collision
        return collisionInfo;
    }

    /**
     * Gets collidable list.
     *
     * @return the collidable list
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }
}