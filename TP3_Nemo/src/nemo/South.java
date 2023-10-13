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

    public int changeInXAxis() {
    	return 0;
    }
    
    public int changeInYAxis() {
    	return -1;
    }

}
