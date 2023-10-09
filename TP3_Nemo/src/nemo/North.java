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
}
