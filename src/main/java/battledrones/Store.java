package battledrones;

import battledrones.devices.Device;
import battledrones.devices.Drone;
import battledrones.devices.Engine;

import java.util.List;

/**
 * Created by evilwasp on 30/09/15.
 */
public interface Store {
    /**
     * Returns list of all devices available.
     */
    List<Device> showDevices();

    /**
     * Returns list of all engines available.
     */
    List<Engine> showEngines();

    /**
     * Returns list of all drones available.
     */
    List<Drone> showDrones();

    enum DroneType{
        DefaultDrone;
    }

    enum EngineType{
        DefaultEngine;
    }

    enum RadarType{
        DefaulRadar;
    }

    Drone getDrone(String droneType);
}
