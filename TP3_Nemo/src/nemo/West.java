package nemo;

public class West extends Directions {

    public Directions turnLeft(){
        return Directions.south();
    }

    public Directions turnRight(){
        return Directions.north();
    }

    public String toString(){
        return "West";
    }
    
    public Point2D directionVector() {
    	return new Point2D(-1, 0);
    }

}
