package battledrones.devices;

/**
 * Created by evilwasp on 30/09/15.
 */
public class DefaultDevice implements Device {
    private float mass;
    private int price;

    public DefaultDevice(float mass, int price) {
        this.mass = mass;
        this.price = price;
    }

    public String getName() {
        return "UnknownDevice";
    }

    public float getMass() {
        return mass;
    }

    public int getPrice() {
        return price;
    }

    public String getHumanDescription() {
        return "This is such a nice gadget. Looks posh. Keep it dry and safe. Made from recyclable materials.";
    }

    public String getTechDescription() {
        return getName() + mass + " kg, $" + price;
    }
}
