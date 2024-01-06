//211788625 Evyatar Paz
package Game;

import Animation.AnimationRunner;
import Animation.EndScreen;
import Animation.KeyPressStoppableAnimation;
import Base.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this class manages the game .
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private double widthBound;
    private double heightBound;
    private boolean win;

    /**
     * Instantiates a new Game flow.
     *
     * @param animationRunner the animation runner
     * @param keyboardSensor  the keyboard sensor
     * @param widthBound      the width bound
     * @param heightBound     the height bound
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor,
                    double widthBound, double heightBound) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.widthBound = widthBound;
        this.heightBound = heightBound;
        this.score = new Counter(0);
        this.win = true;
    }

    /**
     * runLevels
     * gets a list of levelInformation objects
     * and runs the appropriate levels.
     * @param levelsList the given list.
     */
    public void runLevels(List<LevelInformation> levelsList) {
        for (LevelInformation levelInfo : levelsList) {
            GameLevel level = new GameLevel(levelInfo, this.score, this.keyboardSensor,
                    this.animationRunner, widthBound, heightBound);
            level.initialize();
            ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
            NameIndicator nameIndicator = new NameIndicator(levelInfo.levelName());
            level.addSprite(scoreIndicator);
            level.addSprite(nameIndicator);
            while (level.getNumberOfBlocks().getValue() != 0 && level.getNumberOfBalls().getValue() != 0) {
                level.run();
            }
            if (level.getNumberOfBalls().getValue() == 0) {
                this.win = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(this.score, this.win)));
    }
}