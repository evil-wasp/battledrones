package battledrones.test;

import battledrones.Player;
import battledrones.Store;
import battledrones.TheStore;
import battledrones.devices.Device;
import battledrones.devices.Engine;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by evilwasp on 17/10/15.
 */
public class TestStore extends TestCase {
    @Test
    public void testStore() {
        Store store = TheStore.getInstance();
        List<Device> devices = store.showDevices();
        devices.forEach((d) -> System.out.println(d.getClass().getSimpleName() + ": " + d.getTechDescription()));

    }
}
