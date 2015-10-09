package battledrones.devices;

/**
 * Created by evilwasp on 30/09/15.
 */
public interface Engine extends Device {
    /**
     * Sets engine thrust level in percents.
     * @param level any values &lt; 0 will set thrust to minimum value 0, any values &gt; 100 will set thrust to maximum value 1
     */
    void setThrust(int level);
    /**
     * Sets engine thrust level.
     * @param level any values &lt; 0 will set thrust to minimum value 0, any values &gt; 1 will set thrust to maximum value 1
     */
    void setThrust(float level);

    /**
     * Returns engine thrust level.
     */
    float getThrust();

    /**
     * Return maximum power of engine.
     */
    float getPower();
}
