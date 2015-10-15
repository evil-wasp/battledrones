package battledrones;

import battledrones.math.MyMath;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by evilwasp on 11/10/15.
 */
public class Main {
    public static Configuration configuration;

    static {
        try {
            configuration = getConfig();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    private static Configuration getConfig() throws ConfigurationException {
        File confFile = new File("./conf/config.properties");
        if (confFile.isFile())
            return new PropertiesConfiguration(confFile);
        else
            return new PropertiesConfiguration(ClassLoader.getSystemClassLoader().getResource("./conf/config.properties"));
    }
}
