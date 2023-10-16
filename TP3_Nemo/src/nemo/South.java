package nemo;

public class South extends Directions {

    public Directions turnLeft() {
        return Directions.east();
    }

    public Directions turnRight() {
        return Directions.west();
    }

    public String toString(){
        return "South";
    }

	public Point2D directionVector() {
		return new Point2D(0,-1);
	}

}
