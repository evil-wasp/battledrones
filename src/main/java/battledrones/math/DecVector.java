package battledrones.math;

import java.awt.geom.Point2D;

/**
 * Created by evilwasp on 10/10/15.
 */
public class DecVector extends Point2D {
    double x;
    double y;

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    DecVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    DecVector(DecVector a) {
        this.x = a.getX();
        this.y = a.getY();
    }

    DecVector() {
        this.x = 0;
        this.y = 0;
    }

    DecVector addHere(DecVector a) {
        this.x += a.getX();
        this.y += a.getY();
        return this;
    }

    static DecVector add(DecVector a, DecVector b) {
        DecVector c = new DecVector(a);
        c.addHere(b);
        return c;
    }

    DecVector subtractHere(DecVector a) {
        this.x -= a.getX();
        this.y -= a.getY();
        return this;
    }

    static DecVector subtract(DecVector a, DecVector b) {
        DecVector c = new DecVector(a);
        c.subtractHere(b);
        return c;
    }

    DecVector multiplyBy(double k) {
        this.x *= k;
        this.y *= k;
        return this;
    }

    static DecVector multiply(DecVector a, double k) {
        DecVector b = new DecVector(a);
        b.multiplyBy(k);
        return b;
    }

    DecVector divideBy(double k) {
        this.x /= k;
        this.y /= k;
        return this;
    }

    static DecVector divide(DecVector a, double k) {
        DecVector b = new DecVector(a);
        b.divideBy(k);
        return b;
    }

    double abs() {
        return Math.sqrt(x * x + y * y);
    }

    DecVector normalize() {
        double k = this.abs();
        this.divideBy(k);
        return this;
    }

    PolVector toPolar() {
        return new PolVector(Math.atan(this.x / this.y), this.abs());
    }

    double getAngle() {
        return Math.atan(this.x / this.y);
    }


}
