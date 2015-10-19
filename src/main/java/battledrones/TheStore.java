package battledrones;

import battledrones.devices.DefaultEngine;
import battledrones.devices.Device;
import battledrones.devices.Drone;
import battledrones.devices.Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evilwasp on 17/10/15.
 */
public class TheStore implements Store {
    private static Store instance = new TheStore();

    private List<Engine> engines;

    public static Store getInstance() {
        return instance;
    }

    private TheStore() {
    }

    public List<Device> showDevices() {
        return null;
    }

    public List<Engine> showEngines() {
        if (engines == null) {
            this.engines = new ArrayList<Engine>();
            engines.add(new DefaultEngine(100, 50, 200));
            engines.add(new DefaultEngine(100, 50, 200));
            this.engines = Collections.unmodifiableList(this.engines);
        }
        return engines;
    }

    public Drone getDrone() {
        return null;
    }
}
