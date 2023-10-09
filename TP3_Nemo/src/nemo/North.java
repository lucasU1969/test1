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
    
    public int changeInXAxis() {
    	return 0;
    }
    
    public int changeInYAxis() {
    	return 1;
    }
}
