package geometry;


// position on cartesian plane
public class CartesianCoordinate {
    private double xPosition;
    private double yPosition;

    public CartesianCoordinate(double x, double y) {
        this.xPosition = x;
        this.yPosition = y;
    }


    // Getters
    public double getX() {
        return this.xPosition;
    }
    
    public double getY() {
        return this.yPosition;
    }


    //Setters
    public void setX(double x) {
        this.yPosition = x;
    }
    
    public void setY(double y) {
        this.yPosition = y;
    }

    @Override
    public String toString() {
        return "(" + xPosition + "," + yPosition + ')';
    }
    
}
