package battledrones.devices;

import org.apache.commons.configuration.HierarchicalConfiguration;

/**
 * Created by evilwasp on 30/09/15.
 */
public class DefaultDevice implements Device {
    public static final String PARAM_NAME = "name";
    public static final String PARAM_MASS = "mass";
    public static final String PARAM_PRICE = "price";
    public static final String PARAM_HUMAN_DESCRIPTION = "humanDescription";
    protected double mass;
    protected int price;
    String name;
    String humanDescription;

    public DefaultDevice(HierarchicalConfiguration conf) {
        this.name = conf.getString(PARAM_NAME);
        this.mass = conf.getDouble(PARAM_MASS);
        this.price = conf.getInt(PARAM_PRICE);
        this.humanDescription = conf.getString(PARAM_HUMAN_DESCRIPTION);
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public int getPrice() {
        return price;
    }

    public String getHumanDescription() {
        return humanDescription;
    }

    public String getTechDescription() {
        return getName() + ": " + +mass + " kg, $" + price;
    }
}
