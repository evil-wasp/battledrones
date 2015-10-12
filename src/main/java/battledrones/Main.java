package battledrones;

import battledrones.MyMath.MyMath;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import static java.lang.Math.*;

/**
 * Created by evilwasp on 11/10/15.
 */
public class Main {

    public static void main(String [] args) {

        FileWriter sin = null;
        FileWriter cos = null;
        try {
            sin = new FileWriter("/users/evilwasp/projects/battledrones/src/main/resources/SinTable");
            cos = new FileWriter("/users/evilwasp/projects/battledrones/src/main/resources/CosTable");


            double dx = 0.001;

            for (double i = 0; i <= MyMath.roundTo(2 * Math.PI + 0.02, 3); i += dx) {
                sin.write(String.valueOf(Math.sin(i)) + "\n");
                cos.write(String.valueOf(Math.cos(i)) + "\n");
                sin.flush();
                cos.flush();

                System.out.println("dx = "+ dx + "  i = "+ i + " MyMath.roundTo(2 * Math.PI, 3) = "+ MyMath.roundTo(2 * Math.PI, 3));

            }
        } catch (Throwable exeption){
            if (sin == null){
                System.out.println("no file here");
            }

        }
    }
}
