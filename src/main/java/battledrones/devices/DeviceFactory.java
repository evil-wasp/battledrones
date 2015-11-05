package battledrones.devices;

import org.apache.commons.configuration.*;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by evilwasp on 24/10/15.
 */
public class DeviceFactory {
    private static HierarchicalConfiguration deviceConf;

    static {
        try {
            deviceConf = getConfig("./conf/devices.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static HierarchicalConfiguration getConfig(String path) throws ConfigurationException {
        File confFile = new File(path);
        if (confFile.isFile())
            return new XMLConfiguration(confFile);
        else
            return new XMLConfiguration(ClassLoader.getSystemClassLoader().getResource(path));
    }



    public static Drone getDefaultDrone() {
        return new DefaultDrone(deviceConf.configurationAt("DefaultDrone"));
    }

    public static Engine getDefaultEngine() {
        return new DefaultEngine(deviceConf.configurationAt("DefaultEngine"));
    }

    public static List<Engine> getEngines() {
        List<Engine> engines = new LinkedList<>();
        for (HierarchicalConfiguration conf : deviceConf.configurationsAt("engine")) {
            engines.add(new DefaultEngine(conf));
        }
        return engines;
    }

    public static List<Drone> getDrones() {
        List<Drone> drones = new LinkedList<>();
        for (HierarchicalConfiguration conf : deviceConf.configurationsAt("drone")) {
            drones.add(new DefaultDrone(conf));
        }
        return drones;
    }

    public static Engine getEngine(String engineType){
        getEngines();
        for(Engine engine : getEngines()){
            if(engine.getName() == engineType){
                return engine;
            }
        }
        return null;
    }

    public static Drone getDrone(String droneType){
        getDrones();
        for(Drone drone : getDrones()){
            if(drone.getName().equals(droneType)){
                return drone;
            }
        }
        return null;
    }
}
