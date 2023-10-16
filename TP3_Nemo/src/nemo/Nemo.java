package nemo;

import java.util.ArrayList;

public class Nemo {
	
	private Point2D position;
	private Directions direction;
	private ArrayList<Depth> depth = new ArrayList<Depth>();
	private CapsuleLauncher capsuleLauncher = new LoadedCapsuleLauncher();
	
	public Nemo(int newXCoordinate, int newYCoordinate, String newDirection) {
		direction = Directions.cardinalPoints.stream()
											 .filter( each -> each.toString().equals( newDirection ) )
											 .findFirst()
											 .get();
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
		position.sum( direction.directionVector() );
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	public void turnRight() {
		direction = direction.turnRight();
	}
	
	public void launchCapsule() {
		getCurrentDepthState().launchCapsule();
		capsuleLauncher.launch();
		capsuleLauncher = new EmptyCapsuleLaucher();
	}

	public void command(String commands) {
		commands.chars()
				.forEach( command -> executeThisCommand( ( char ) command ) );
	}
	
	public void executeThisCommand(char command) {
		Commands.availableCommands.stream()
								  .filter( each -> ( each.getCommandAsChar() ) == command )
								  .findFirst()
								  .get()
								  .exeucuteAction( this );
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
	
	public boolean isCapsuleLauncherLoaded() {
		return capsuleLauncher.isLoaded();
	}

	private Depth getCurrentDepthState() {
		return depth.get( depth.size() - 1 );
	}
}
