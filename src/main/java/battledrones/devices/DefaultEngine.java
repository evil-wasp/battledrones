package battledrones.devices;

/**
 * Created by evilwasp on 30/09/15.
 */
public class DefaultEngine extends DefaultDevice implements Engine {
    private float thrust = 0f;
    private float power;

    /**
     * Creates engine with given {@link #power}, {@link DefaultDevice#mass mass} and {@link DefaultDevice#price price}.
     */
    public DefaultEngine(float power, float mass, int price) {
        super(mass, price);
        this.power = power;
    }

    public void setThrust(int level) {
        this.thrust = ((float) level) / 100;
    }

    public void setThrust(float level) {
        if (level < 0)
            this.thrust = level;
    }

    public float getThrust() {
        return thrust;
    }

    public float getPower() {
        return power;
    }

    public String getName() {
        return "ProtoEngine";
    }

    public String getHumanDescription() {
        return "Unknown Engine prototype. No one have idea how to use it.";
    }

    public String getTechDescription() {
        return getName() + getMass() + " kg, " + getPower() + " MW, $" + getPrice();
    }
}
