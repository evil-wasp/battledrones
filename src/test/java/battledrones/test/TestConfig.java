package battledrones.test;

import junit.framework.TestCase;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Test;

/**
 * Created by evilwasp on 28/10/15.
 */
public class TestConfig extends TestCase {
    @Test
    public void testHierarchicalConfig() throws Exception {
        XMLConfiguration conf = new XMLConfiguration(ClassLoader.getSystemClassLoader().getResource("./conf/devices.xml"));
        Object o1 = conf.getProperty("DefaultDrone");
        Object o2 = conf.getProperty("DefaultDrone.name");
        Object o3 = conf.getProperty("DefaultDrone.price");
        Object o4 = conf.getProperty("DefaultDrone.valueList");
        Object o5 = conf.getProperty("DefaultDrone.humanDescription");
        HierarchicalConfiguration o6 = conf.configurationAt("DefaultDrone");
        Object o61 = o6.getProperty("name");
        Object o62 = o6.getProperty("humanDescription");

        Object o7 = conf.getProperties("DefaultDrone");
        System.out.println();
    }
}