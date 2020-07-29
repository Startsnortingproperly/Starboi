package turtle;

//Basic methods for 2D vectors
import static java.lang.Math.acos;
import static java.lang.Math.atan2;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Driver {
    double x, y;
 
    Driver() {
    }
 
    Driver(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    void add(Driver d) {
        x += d.x;
        y += d.y;
    }
 
    void sub(Driver d) {
        x -= d.x;
        y -= d.y;
    }
 
    void div(double val) {
        x /= val;
        y /= val;
    }
 
    void mult(double val) {
        x *= val;
        y *= val;
    }
 
    double mag() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
 
    double dot(Driver d) {
        return x * d.x + y * d.y;
    }
 
    void normalize() {
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }
 
    void limit(double lim) {
        double mag = mag();
        if (mag != 0 && mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }
 
    double heading() {
        return atan2(y, x);
    }
 
    static Driver sub(Driver d1, Driver d2) {
        return new Driver(d1.x - d2.x, d1.y - d2.y);
    }
 
    static double dist(Driver d1, Driver d2) {
        return sqrt(pow(d1.x - d2.x, 2) + pow(d1.y - d2.y, 2));
    }
 
    static double angleBetween(Driver d1, Driver d2) {
        return acos(d1.dot(d2) / (d1.mag() * d2.mag()));
    }
}