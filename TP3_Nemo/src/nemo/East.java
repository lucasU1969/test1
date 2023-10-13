package nemo;

public class East extends Directions {

    public Directions turnLeft(){
        return Directions.north();
    }

    public Directions turnRight(){
        return Directions.south();
    }

    public String toString(){
        return "East";
    }

    public int changeInXAxis() {
    	return 1;
    }
    
    public int changeInYAxis() {
    	return 0;
    }
}
