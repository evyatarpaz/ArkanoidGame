//211788625 Evyatar Paz
package Game;

import Animation.Green3;
import Base.LevelInformation;
import Collidable.Block;
import Geometry.Point;
import Base.Sprite;
import Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the third level.
 */
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(45, 4);
        Velocity v2 = Velocity.fromAngleAndSpeed(-45, 4);
        velocityList.add(v1);
        velocityList.add(v2);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 126;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[]{Color.gray, Color.red, Color.yellow, Color.blue, Color.white};
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Point(725 - (j * 50), 150 + (i * 25)), 50, 25, colors[i]);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
