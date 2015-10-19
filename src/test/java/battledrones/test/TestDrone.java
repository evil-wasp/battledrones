package battledrones.test;

import battledrones.Player;
import battledrones.Store;
import battledrones.TheStore;
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
        List<Engine> engines = store.showEngines();
        Engine e = engines.get(0);
        System.out.println(e.toString());
        System.out.println("Price: " + e.getPrice() + " bukazoids");
        System.out.println(e.getTechDescription());
        System.out.println(e.getHumanDescription());

    }
}
