package battledrones.devices;

import battledrones.Main;
import org.apache.commons.configuration.HierarchicalConfiguration;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evilwasp on 10/10/15.
 */
public class DefaultDrone extends DefaultDevice implements Drone {
    List<Device> devices;


    double orientation;

    public DefaultDrone(HierarchicalConfiguration conf) {
        super(conf);
    }
    //PolVector

    void Drone() {
        devices = new ArrayList<Device>();
    }


    public List<Device> getDevices() {

        return devices;
    }

    public double getOrientation() {
        return 0;
    }

    public Point getLocation() {
        return null;
    }

    public double getSpeed() {
        return 0;
    }


    // public DecVector getCenterLocation(){

    //     return ;
    //}

}
