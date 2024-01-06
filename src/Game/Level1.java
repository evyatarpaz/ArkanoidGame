//211788625 Evyatar Paz
package Game;

import Animation.DirectHit;
import Base.LevelInformation;
import Collidable.Block;
import Geometry.Point;
import Base.Sprite;
import Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *  represents the first level.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(0, 4);
        listVelocity.add(v1);
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHit();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Block b = new Block(new Point(387.5, 180), 25, 25, Color.red);
        blockList.add(b);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
