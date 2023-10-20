package nemo;

import java.util.ArrayList;

public class Nemo {
	
	private Point2D position;
	private Directions direction;
	private ArrayList<Depth> depth = new ArrayList<Depth>();
	
	public Nemo(int newXCoordinate, int newYCoordinate, String newDirection) {
		direction = Directions.getStringAsADirection(newDirection);
		position = new Point2D(newXCoordinate, newYCoordinate);
		depth.add(new Surface());
	}

	public void moveUpwards() {
		depth.remove( getCurrentDepthState().moveUpwards() );
	}
	
	public void moveDownwards(){
		depth.add( getCurrentDepthState().moveDownwards() );
	}
	
	public void moveForward() {
		position = position.sum( direction.directionVector() );
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	public void turnRight() {
		direction = direction.turnRight();
	}
	
	public void launchCapsule() {
		getCurrentDepthState().launchCapsule( this );
	}

	public void command(String commands) {
		commands.chars()
				.forEach( command -> executeThisCommand( ( char ) command ) );
	}
	
	public void executeThisCommand( char commandAsChar ) {
		Commands.getCommandFor(commandAsChar).exeucuteAction(this);
	}
	
	public int getXCoordinate() {
		return position.getXCoordinate();
	}
	
	public int getYCoordinate() {
		return position.getYCoordinate();
	}
	
	public int getZCoordinate() {
		return getCurrentDepthState().getDepth();
	}
	
	public String getDirection() {
		return direction.toString();
	}
	
	public boolean isOnTheSurface() {
		return getZCoordinate() == 0;
	}

	private Depth getCurrentDepthState() {
		return depth.get( depth.size() - 1 );
	}
}
