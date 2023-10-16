package nemo;

public class North extends Directions {

    public Directions turnLeft() {
        return Directions.west();
    }

    public Directions turnRight() {
        return Directions.east();
    }

    public String toString(){
        return "North";
    }

	public Point2D directionVector() {
			return new Point2D(0,1);
	}
    
}
