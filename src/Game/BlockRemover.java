//211788625 Evyatar Paz
package Game;
import Collidable.Block;
import Sprites.Ball;
import Base.HitListener;
/**
 * a Game.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;


    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel            the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }
}