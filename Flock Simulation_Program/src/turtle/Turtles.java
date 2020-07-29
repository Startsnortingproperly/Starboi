package turtle;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


// Class of group of turtles
public class Turtles {
    List<Turtle> turtles;
    
    Turtles() {
        turtles = new ArrayList<>();
    }
 
    public void run(Graphics2D g,  int w, int h) {
        for (Turtle t : turtles) {
            t.run(g, turtles, w, h);
        }
    }

    //If the turtle has started
    public boolean hasLeftTheBuilding(int w) {
        int count = 0;
        for (Turtle t : turtles) {
            if (t.location.x + Turtle.size > w)
                count++;
        }
        return turtles.size() == count;
    }
 
    // to add new turtle
    public void addTurtle(Turtle t) {
        turtles.add(t);
    }
 

    //Adding turtle to group and setting its value
    public static Turtles spawn(double w, double h, int numTurtles) {
        Turtles group = new Turtles();
        for (int i = 0; i < numTurtles; i++)
            group.addTurtle(new Turtle(w, h));
        return group;
    }
}