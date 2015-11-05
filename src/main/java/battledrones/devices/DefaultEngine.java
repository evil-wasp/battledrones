package battledrones.devices;

import org.apache.commons.configuration.HierarchicalConfiguration;

import java.util.HashMap;

/**
 * Created by evilwasp on 30/09/15.
 */
public class DefaultEngine extends DefaultDevice implements Engine {
    public static final String PARAM_MAX_THRUST = "maxThrust";
    private double thrust = 0d;
    private double maxThrust;

    /**
     * Creates engine with given {@link #maxThrust}, {@link DefaultDevice#mass mass} and {@link DefaultDevice#price price}.
     */
    public DefaultEngine(HierarchicalConfiguration conf) {
        super(conf);
        this.maxThrust = conf.getDouble(PARAM_MAX_THRUST);
    }

    public void setThrust(int level) {
        this.thrust = maxThrust * ((double) level) / 100;
    }

    public void setThrust(double level) {
        if (level < 0)
            this.thrust = level;
    }

    public double getThrust() {
        return thrust;
    }

    public double getMaxThrust() {
        return maxThrust;
    }

    public String getName() {
        return name;
    }

    public String getHumanDescription() {
        return humanDescription;
    }

    public String getTechDescription() {
        return getName() + ": " + getMass() + " kg, " + getMaxThrust() + " MN, $" + getPrice();
    }
}
