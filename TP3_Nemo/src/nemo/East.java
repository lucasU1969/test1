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
}
