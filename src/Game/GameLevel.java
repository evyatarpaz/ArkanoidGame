//211788625 Evyatar Paz
package Game;
import Animation.CountdownAnimation;
import Base.Animation;
import Animation.AnimationRunner;
import Animation.PauseScreen;
import Animation.KeyPressStoppableAnimation;
import Base.LevelInformation;
import Collidable.Block;
import Base.Collidable;
import Collidable.Paddle;
import Geometry.Point;
import Sprites.Ball;
import Base.HitListener;
import Base.Sprite;
import Sprites.SpriteCollection;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Game.Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter numberOfBlocks;
    private Counter numberOfBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private int sizeOfBlock;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    private double width;
    private double height;

    /**
     * Constructor.
     * Instantiates a new Game.Game.
     *
     * @param level          the level
     * @param score          the score
     * @param keyboardSensor the keyboard sensor
     * @param runner         the runner
     * @param width          the width
     * @param height         the height
     */
    public GameLevel(LevelInformation level, Counter score, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, double width, double height) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.numberOfBlocks = new Counter(level.numberOfBlocksToRemove());
        this.numberOfBalls = new Counter(level.numberOfBalls());
        this.score = score;
        this.runner = runner;
        this.keyboard = keyboardSensor;
        this.sizeOfBlock = 25;
        this.level = level;
        this.height = height;
        this.width = width;
    }

    /**
     * Add a collidable to the environment of the game.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the Sprites.SpriteCollection.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Initialize border blocks.
     */
    public void initializeBorderBlocks() {
        HitListener ballRemover = new BallRemover(this, this.numberOfBalls);
        Block leftBorder = new Block(0, this.sizeOfBlock, this.sizeOfBlock, this.height, Color.gray);
        Block rightBorder = new Block(this.width - this.sizeOfBlock,
                this.sizeOfBlock, this.sizeOfBlock, this.height, Color.gray);
        Block topBorder = new Block(0, this.sizeOfBlock, this.width, this.sizeOfBlock, Color.gray);
        Block bottomBorder = new Block(0, this.height, this.width, this.sizeOfBlock, Color.gray);
        bottomBorder.addHitListener(ballRemover);
        Block[] blocks = new Block[]{leftBorder, rightBorder, topBorder, bottomBorder};
        for (Block block : blocks) {
            block.addToGame(this);
        }
    }

    /**
     * Initialize screen block.
     */
    public void initializeScreenBlock() {
        this.level.getBackground().addToGame(this);
    }

    /**
     * Instantiates all the game blocks.
     */
    public void initializeGameBlocks() {
        HitListener blockRemover = new BlockRemover(this, this.numberOfBlocks);
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (Block block : (this.level.blocks())) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Initialize the game balls.
     */
    public void initializeBalls() {
        for (Velocity i : this.level.initialBallVelocities()) {
            Ball ball = new Ball(this.width / 2,
                    this.height - this.sizeOfBlock - 30,
                    5, Color.white, this.environment);
            ball.setVelocity(i);
            ball.addToGame(this);
        }
    }

    /**
     * Initialize the game paddle.
     */
    public void initializePaddle() {
        Paddle paddle = new Paddle(new Point((this.width - this.level.paddleWidth()) / 2,
                this.height - 50), this.level.paddleWidth(), this.sizeOfBlock,
                this.sizeOfBlock, Color.ORANGE, this.keyboard, this.width);
        paddle.addToGame(this);
        paddle.setVelocity(this.level.paddleSpeed());
    }

    /**
     * Initialize a new game: create the Blocks and Sprites.Sprites.Ball (and Base.Collidable.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initializeScreenBlock();
        initializeBorderBlocks();
        initializeGameBlocks();
        initializeBalls();
        initializePaddle();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.numberOfBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.numberOfBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets number of balls.
     *
     * @return the number of balls
     */
    public Counter getNumberOfBalls() {
        return numberOfBalls;
    }

    /**
     * Gets number of blocks.
     *
     * @return the number of blocks
     */
    public Counter getNumberOfBlocks() {
        return numberOfBlocks;
    }
}
