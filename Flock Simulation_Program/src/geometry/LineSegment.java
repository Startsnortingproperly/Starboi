package geometry;


//Class for line segment
public class LineSegment {
    private CartesianCoordinate startPoint;
    private CartesianCoordinate endPoint;

    public LineSegment(CartesianCoordinate start, CartesianCoordinate end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    @Override
    public String toString() {
        return "Start point " + startPoint + "End point" + endPoint;
    }

    public void setEndPoint(CartesianCoordinate endPoint) {
        this.endPoint = endPoint;
    }

    public CartesianCoordinate getEndPoint() {
        return endPoint;
    }

    public void setStartPoint(CartesianCoordinate startPoint) {
        this.startPoint = startPoint;
    }

    public CartesianCoordinate getStartPoint() {
        return startPoint;
    }
    
    public double length() {
        double diffX = this.getEndPoint().getX() - this.getStartPoint().getX();
        double diffY = this.getEndPoint().getY() - this.getStartPoint().getY();
        
        diffX *= diffX;
        diffY *= diffY;
        return Math.sqrt(diffX + diffY);
    }
    
}
