package nemo;

import java.util.ArrayList;

public class Nemo {
	private int[] pos;
	private Directions direction;
	private ArrayList<Depth> depth = new ArrayList<Depth>();
	private CapsuleLauncher capsuleLauncher = new LoadedCapsuleLauncher();
	
	public Nemo(int newXCoordinate, int newYCoordinate, Directions newDirection) {
		pos = new int[] {newXCoordinate, newYCoordinate};
		depth.add(new Surface());
		direction = newDirection;
	}

	public void moveUpward() {
		depth.remove( depth.get(depth.size() - 1 ).moveUpwards());
	}
	
	public void moveDownward(){
		depth.add(depth.get(depth.size() - 1).moveDownwards());
	}
	
	public Depth getCurrentDepthState() {
		return depth.get(depth.size() - 1);
	}
	public void moveForward() {
		pos = new int[] {getXCoordinate() + direction.changeInXAxis(), getYCoordinate() + direction.changeInYAxis()};
		
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

	public Directions getDirection() {
		return direction;
	}
	
	public boolean isOnTheSurface() {
		return depth.size()  ==  1;
	}

	public void command(String commands) {
		commands.chars().forEach(command -> this.executeAction(String.valueOf((char) command)));
	}

	public void executeAction(String command) {
		if (command.equals("u") ) {
			moveUpward();
		}
		if (command.equals("d")) {
			moveDownward();
		}
		if (command.equals("l")) {
			turnLeft();
		}
		if (command.equals("r")) {
			turnRight();
		}
		if (command.equals("f")) {
			moveForward();
		}
		if (command.equals("m")) {
			launchCapsule();
		}
	}
	
	public boolean isCapsuleInNemo() {
		return capsuleLauncher.isLoaded();
	}

	public void launchCapsule() {
		depth.get(depth.size() -1).launchCapsule();
		capsuleLauncher.launch();
		capsuleLauncher = new EmptyCapsuleLaucher();
	}

}
