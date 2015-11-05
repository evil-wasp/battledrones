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
    public static Configuration calcConf;

    static {
        try {
            calcConf = getConfig("./conf/config.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    private static Configuration getConfig(String path) throws ConfigurationException {
        File calculationConfFile = new File(path);
        if (calculationConfFile.isFile())
            return new PropertiesConfiguration(calculationConfFile);
        else
            return new PropertiesConfiguration(ClassLoader.getSystemClassLoader().getResource(path));
    }


}
