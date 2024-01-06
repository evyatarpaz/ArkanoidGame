package Animation;
import Base.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean close;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
        this.close = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
            animation.doOneFrame(d);
        } else {
            if (!isAlreadyPressed) {
                this.close = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.close;
    }
}
