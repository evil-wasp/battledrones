package battledrones.devices;

import java.awt.*;
import java.util.List;

/**
 * Created by evilwasp on 30/09/15.
 */
public interface Drone {
    List<Device> getDevices();

    double getOrientation();

    Point getLocation();

    double getSpeed();




}
