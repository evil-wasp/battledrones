package battledrones.MyMath;

/**
 * Created by evilwasp on 10/10/15.
 */
public class PolVector {
    double absAngle;
    double radius;

    PolVector(double absAngle, double radius){
        this.absAngle = absAngle;
        this.radius = radius;
    }

    PolVector(PolVector a){
        this.absAngle = a.absAngle;
        this.radius = a.radius;
    }

    double getAbsAngle(){
        return absAngle;
    }

    double getAngle(){
        return absAngle - (int)(absAngle/(2*Math.PI))*2*Math.PI;
    }

    double getRadius(){
        return radius;
    }

    PolVector trun(double a){
        absAngle += a;
        return this;
    }

    PolVector extend(double r){
        radius += r;
        return this;
    }



}
