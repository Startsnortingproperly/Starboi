package turtle;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import static java.lang.Math.PI;
import java.util.List;
import java.util.Random;


//turtle class methods of speration, allignment and cohession(The main funtionlities of Flock simulation)
public class Turtle {
    static final Random r = new Random();
    static final Driver migrate = new Driver(0.02, 0);
    static final int size = 3;
    static final Path2D shape = new Path2D.Double();
    
    static {
        shape.moveTo(0, -size * 2);
        shape.lineTo(-size, size * 2);
        shape.lineTo(size, size * 2);
        shape.closePath();
    }
    
    final double maxForce, maxSpeed;
 
    Driver location, velocity, acceleration;
    private boolean included = true;
 

    // Initiallizng a turtle
    Turtle(double x, double y) {
        acceleration = new Driver();
        velocity = new Driver(r.nextInt(3) + 1, r.nextInt(3) - 1);
        location = new Driver(x, y);
        maxSpeed = 3.0;
        maxForce = 0.05;
    }
 

    void update() {
        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        location.add(velocity);
        acceleration.mult(0);
    }
 
    void applyForce(Driver force) {
        acceleration.add(force);
    }
 
    Driver seek(Driver target) {
        Driver steer = Driver.sub(target, location);
        steer.normalize();
        steer.mult(maxSpeed);
        steer.sub(velocity);
        steer.limit(maxForce);
        return steer;
    }
 
    void flock(Graphics2D g, List<Turtle> turtles) {
        view(g, turtles);
 
        
        Driver rule1 = separation(turtles);
        Driver rule2 = alignment(turtles);
        Driver rule3 = cohesion(turtles);
 
        rule1.mult(2.5);
        rule2.mult(1.5);
        rule3.mult(1.3);
 
        applyForce(rule1);
        applyForce(rule2);
        applyForce(rule3);
        applyForce(migrate);
    }
 
    void view(Graphics2D g, List<Turtle> turtles) {
        double sightDistance = 100;
        double peripheryAngle = PI * 0.85;
 
        for (Turtle b : turtles) {
            b.included = false;
 
            if (b == this)
                continue;
 
            double d = Driver.dist(location, b.location);
            if (d <= 0 || d > sightDistance)
                continue;
 
            Driver lineOfSight = Driver.sub(b.location, location);
 
            double angle = Driver.angleBetween(lineOfSight, velocity);
            if (angle < peripheryAngle)
                b.included = true;
        }
    }
 
    Driver separation(List<Turtle> turtles) {
        double desiredSeparation = 25;
 
        Driver steer = new Driver(0, 0);
        int count = 0;
        for (Turtle b : turtles) {
            if (!b.included)
                continue;
 
            double d = Driver.dist(location, b.location);
            if ((d > 0) && (d < desiredSeparation)) {
                Driver diff = Driver.sub(location, b.location);
                diff.normalize();
                diff.div(d);        // weight by distance
                steer.add(diff);
                count++;
            }
        }
        if (count > 0) {
            steer.div(count);
        }
 
        if (steer.mag() > 0) {
            steer.normalize();
            steer.mult(maxSpeed);
            steer.sub(velocity);
            steer.limit(maxForce);
            return steer;
        }
        return new Driver(0, 0);
    }
 
    Driver alignment(List<Turtle> turtles) {
        double preferredDist = 50;
 
        Driver steer = new Driver(0, 0);
        int count = 0;
 
        for (Turtle b : turtles) {
            if (!b.included)
                continue;
 
            double d = Driver.dist(location, b.location);
            if ((d > 0) && (d < preferredDist)) {
                steer.add(b.velocity);
                count++;
            }
        }
 
        if (count > 0) {
            steer.div(count);
            steer.normalize();
            steer.mult(maxSpeed);
            steer.sub(velocity);
            steer.limit(maxForce);
        }
        return steer;
    }
 
    Driver cohesion(List<Turtle> turtles) {
        double preferredDist = 50;
 
        Driver target = new Driver(0, 0);
        int count = 0;
 
        for (Turtle b : turtles) {
            if (!b.included)
                continue;
 
            double d = Driver.dist(location, b.location);
            if ((d > 0) && (d < preferredDist)) {
                target.add(b.location);
                count++;
            }
        }
        if (count > 0) {
            target.div(count);
            return seek(target);
        }
        return target;
    }
 
    void draw(Graphics2D g) {
        AffineTransform save = g.getTransform();
 
        g.translate(location.x, location.y);
        g.rotate(velocity.heading() + PI / 2);
        g.setColor(Color.white);
        g.fill(shape);
        g.setColor(Color.black);
        g.draw(shape);
 
        g.setTransform(save);
    }
 
    void run(Graphics2D g, List<Turtle> turtles, int w, int h) {
        flock(g, turtles);
        update();
        draw(g);
    }
}
