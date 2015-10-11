package battledrones.MyMath;

/**
 * Created by evilwasp on 11/10/15.
 */
public class MyMath {

    public static double roundTo(double value, int places){
        double a = value * 10*places;
        a = Math.round(a);
        a *= 10*places;
        return a;

    }
}
