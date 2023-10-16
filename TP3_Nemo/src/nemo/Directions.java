package nemo;

import java.util.List;

public abstract class Directions {
	public static List<Directions> cardinalPoints = List.of(Directions.north(), Directions.south(), Directions.east(), Directions.west());

    public static Directions west() {
        return new West();
    }

    public static Directions east() {
        return new East();
    }

    public static Directions north() {
        return new North();
    }

    public static Directions south() {
        return new South();
    }

    public abstract Directions turnLeft();
    public abstract Directions turnRight();
    public abstract Point2D directionVector();
}
