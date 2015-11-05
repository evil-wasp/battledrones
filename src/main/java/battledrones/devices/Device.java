package battledrones.devices;

/**
 * Created by evilwasp on 30/09/15.
 */
public interface Device {
    /**
     * Return device name.<br>
     * Ex: <code>iBluster 6s</code>
     */
    String getName();

    double getMass();

    int getPrice();

    /**
     * Returns human friendly description of device.<br>
     * Ex: <i>This is such a nice gadget. Looks posh. Keep it dry and safe. Made from recyclable materials.</i>
     */
    String getHumanDescription();

    /**
     * Returns technical description of device.<br>
     * Ex: <code>Gadget-123. 750 kg, $180.</code>
     */
    String getTechDescription();
}
