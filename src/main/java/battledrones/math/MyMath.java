package battledrones.math;

import battledrones.Main;

/**
 * Created by evilwasp on 11/10/15.
 */
public class MyMath {
    public static final String TABLE_SIZE_KEY = "trigonometry.tablesize";
    public static final int TABLE_SIZE = Main.configuration.getInt(TABLE_SIZE_KEY, 10000);
    private static double[] sinTable;
    private static double[] cosTable;
    private static double[] tanTable;

    static {
        fillTables();
    }

    public static void main(String[] args) {
    }

    public static double sin(double angle) {
        angle = angle % (Math.PI * 2);
        return sinTable[(int) (angle / (Math.PI * 2) * TABLE_SIZE)];
    }

    public static double cos(double angle) {
        angle = angle % (Math.PI * 2);
        return cosTable[(int) (angle / (Math.PI * 2) * TABLE_SIZE)];
    }

    public static double tan(double angle) {
        angle = angle % (Math.PI * 2);
        return tanTable[(int) (angle / (Math.PI * 2) * TABLE_SIZE)];
    }

    public static double sinOfDeg(double angle) {
        angle = angle % 360;
        return sin(angle / 180 * Math.PI);
    }

    public static double cosOfDeg(double angle) {
        angle = angle % 360;
        return cos(angle / 180 * Math.PI);
    }
    public static double tanOfDeg(double angle) {
        angle = angle % 360;
        return tan(angle / 180 * Math.PI);
    }

    public static double roundTo(double value, int places) {
        double a = value * 10 * places;
        a = Math.round(a);
        a /= 10 * places;
        return a;
    }

    private static void fillTables() {
        sinTable = new double[TABLE_SIZE];
        cosTable = new double[TABLE_SIZE];
        tanTable = new double[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            double d = Math.PI * 2 * i / TABLE_SIZE;
            sinTable[i] = Math.sin(d);
            cosTable[i] = Math.cos(d);
            tanTable[i] = Math.tan(d);
        }
    }
}
