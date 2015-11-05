package battledrones;

import battledrones.devices.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evilwasp on 17/10/15.
 */
public class TheStore implements Store {
    private static Store instance = new TheStore();

    private List<Engine> engines;
    private List<Radar> radars;
    private List<Drone> drones;

    public static Store getInstance() {
        return instance;
    }

    private TheStore() {
        if (engines == null) {
            this.engines = new ArrayList<>();
            engines.add(DeviceFactory.getDefaultEngine());
            this.engines = Collections.unmodifiableList(this.engines);
        }

        if (drones == null) {
            this.drones = new ArrayList<>();
            drones.add(DeviceFactory.getDefaultDrone());
            this.drones = Collections.unmodifiableList(this.drones);
        }

    }

    public List<Device> showDevices() {
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(DeviceFactory.getDrones());
        devices.addAll(DeviceFactory.getEngines());

        return devices;
    }

    public List<Engine> showEngines() {
        return engines;
    }

    public List<Drone> showDrones() {
        return drones;
    }


    public Drone getDrone(String droneType) {

        return DeviceFactory.getDrone(droneType);
    }
}
