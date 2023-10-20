package nemo;

import java.util.List;

public abstract class Directions {
//	public static List<Directions> cardinalPoints = List.of(Directions.north(), Directions.south(), Directions.east(), Directions.west());
	private static List<Directions> cardinalpoints = List.of(new North(), new South(), new East(), new West());
	
	public static Directions getStringAsADirection( String directionAsText ) {
		return cardinalpoints.stream().filter((each) -> each.toString() == directionAsText).findFirst().get();
	}

    public abstract Directions turnLeft();
    public abstract Directions turnRight();
    public abstract Point2D directionVector();
}
