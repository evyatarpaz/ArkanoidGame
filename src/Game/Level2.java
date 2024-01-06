//211788625 Evyatar Paz
package Game;

import Animation.WideEasy;
import Base.LevelInformation;
import Collidable.Block;
import Geometry.Point;
import Base.Sprite;
import Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the second level.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int j = -50; j <= 50; j += 10) {
            if (j == 0) {
                continue;
            }
            velocityList.add(Velocity.fromAngleAndSpeed(j, 4));
        }
        return velocityList;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasy();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[]{Color.RED, Color.RED, Color.ORANGE,
                Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK, Color.cyan, Color.cyan};
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            Block b = new Block(new Point(25 + (i * 50), 280), 50, 20, colors[i]);
            blockList.add(b);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
