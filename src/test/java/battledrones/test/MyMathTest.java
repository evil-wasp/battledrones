package battledrones.test;

import battledrones.math.MyMath;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by dron on 15.10.2015.
 */
public class MyMathTest extends TestCase {
    public static double ERROR = 1.0E-15;
    double[][] angles;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        angles = new double[][]{
                {0, Math.PI / 2, Math.PI, Math.PI / 2 * 3, Math.PI / 2 * 4, Math.PI / 2 * 5, Math.PI / 2 * 10}
                , {0, 90, 180, 270, 360, 450, 900}
        };
    }

    @Test
    public void testSinCosTan() throws Exception {
        for (double d : angles[0]) {
            if (Math.sin(d) - MyMath.sin(d) > ERROR)
                fail("sin(" + d + ") differs from Math.sin(" + d + ") for " + (Math.sin(d) - MyMath.sin(d)) + " more than max error " + ERROR);
            if (Math.cos(d) - MyMath.cos(d) > ERROR)
                fail("cos(" + d + ") differs from Math.cos(" + d + ") for " + (Math.cos(d) - MyMath.cos(d)) + " more than max error " + ERROR);
            if (Math.tan(d) - MyMath.tan(d) > ERROR)
                fail("tan(" + d + ") differs from Math.tan(" + d + ") for " + (Math.tan(d) - MyMath.tan(d)) + " more than max error " + ERROR);
        }
    }

    @Test
    public void testSinCosTanForDegrees() throws Exception {
        for (int i = 0; i < angles[0].length; i++) {
            if (MyMath.sin(angles[0][i]) != MyMath.sinOfDeg(angles[1][i]))
                fail("sin(" + angles[0][i] + ") != sinDeg(" + angles[1][i] + ")");
            if (MyMath.cos(angles[0][i]) != MyMath.cosOfDeg(angles[1][i]))
                fail("cos(" + angles[0][i] + ") != cosDeg(" + angles[1][i] + ")");
            if (MyMath.tan(angles[0][i]) != MyMath.tanOfDeg(angles[1][i]))
                fail("tan(" + angles[0][i] + ") != tanDeg(" + angles[1][i] + ")");
        }
    }
}