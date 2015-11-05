package battledrones.test;

import battledrones.Store;
import battledrones.TheStore;
import battledrones.devices.DeviceFactory;
import battledrones.devices.Drone;
import battledrones.devices.Engine;
import junit.framework.TestCase;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by evilwasp on 27/10/15.
 */
public class DeviceFactoryTest extends TestCase {

    @Test
    public void testDefaultDevices() throws Exception {
        Drone drone = DeviceFactory.getDefaultDrone();
        Engine engine = DeviceFactory.getDefaultEngine();
        List<Engine> engines = DeviceFactory.getEngines();
        List<Drone> drones = DeviceFactory.getDrones();
        Drone drone2 = DeviceFactory.getDrone("Default Drone");
        System.out.println();
    }
}
