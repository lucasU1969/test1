package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {
	private int[] pos;
	private Directions direction;
	private ArrayList<Depth> depth = new ArrayList<Depth>();
	private List<Directions> cardinalPoints = List.of(Directions.north(), Directions.south(), Directions.east(), Directions.west());
	private List<Commands> commands = List.of( Commands.moveUpwards(), Commands.moveDownwards(), Commands.moveForward(), Commands.turnLeft(), Commands.turnRight(), Commands.launchCapsule());
	private List<Runnable> moves = List.of( () -> moveUpwards(), ()-> moveDownwards(), () -> moveForward(), () -> turnLeft() , () -> turnRight(), () -> launchCapsule());
	private CapsuleLauncher capsuleLauncher = new LoadedCapsuleLauncher();
	
	public Nemo(int newXCoordinate, int newYCoordinate, String newDirection) {
		direction = cardinalPoints.stream().filter(each -> each.toString().equals(newDirection)).findFirst().get();
		pos = new int[] {newXCoordinate, newYCoordinate};
		depth.add(new Surface());
	}

	public void moveUpwards() {
		depth.remove( getCurrentDepthState().moveUpwards() );
	}
	
	public void moveDownwards(){
		depth.add( getCurrentDepthState().moveDownwards() );
	}
	
	public void moveForward() {
		pos = new int[] { getXCoordinate() + direction.changeInXAxis(), getYCoordinate() + direction.changeInYAxis() };
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	public void turnRight() {
		direction = direction.turnRight();
	}

	public int getXCoordinate() {
		return pos[0];
	}

	public int getYCoordinate() {
		return pos[1];
	}

	public int getZCoordinate() {
		return (depth.size() - 1) * (-1);
	}

	public String getDirection() {
		return direction.toString();
	}
	
	public boolean isOnTheSurface() {
		return depth.size()  ==  1;
	}

	public void command(String commands) {
		commands.chars().forEach(command -> executeThisCommand((char) command));
	}
	
	public void executeThisCommand(char command) {
		moves.get(commands.indexOf(commands.stream().filter(each -> each.getCommandAsChar() == command).findFirst().get())).run();
//		commands.stream().filter(each -> (each.getCommandAsChar()) == command).findFirst().get().getAction().run();
	}
	
	public boolean isCapsuleInNemo() {
		return capsuleLauncher.isLoaded();
	}

	public void launchCapsule() {
		getCurrentDepthState().launchCapsule();
		capsuleLauncher.launch();
		capsuleLauncher = new EmptyCapsuleLaucher();
	}

	private Depth getCurrentDepthState() {
		return depth.get(depth.size() - 1);
	}

}
