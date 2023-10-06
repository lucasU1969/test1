package nemo;

import java.util.ArrayList;


public class Nemo {
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate = 0;
	private String direction;
	public Nemo(int newXCoordinate, int newYCoordinate, String newDirection) {
		xCoordinate = newXCoordinate;
		yCoordinate = newYCoordinate;
		direction = newDirection;

	}

	public void moveUpward() {
		if (zCoordinate != 0) {
			zCoordinate++;
		}
	}
	public void moveDownward(){
		zCoordinate--;
	}

	public void moveForward() {
		if (direction.equals("North")) {
			yCoordinate++;
		}
		if (direction.equals("South")) {
			yCoordinate--;
		}
		if (direction.equals("East")) {
			xCoordinate++;
		}
		if (direction.equals("West")) {
			xCoordinate--;
		}
	}

	public void turnLeft() {
		if (direction.equals("North")) {
			direction = "West";
		} else if (direction.equals("South")) {
			direction = "East";
		} else if (direction.equals("East")) {
			direction = "North";
		} else if (direction.equals("West")) {
			direction = "South";
		}
	}
	public void turnRight(){
		if (direction.equals("North")) {
			direction = "East";
		} else if (direction.equals("South")) {
			direction = "West";
		} else if (direction.equals("East")) {
			direction = "South";
		} else if (direction.equals("West")) {
			direction = "North";
		}
	}
	public void launchCapsule(){

	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public int getZCoordinate() {
		return zCoordinate;
	}

	public String getDirection() {
		return direction;
	}
	
	public boolean isOnTheSurface() {
		return zCoordinate == 0;
	}

	public void command(String commands) {
		for (int i = 0; i < commands.length(); i++) {
			this.executeAction(commands.substring(i, i+1));
		}
	}

	public void executeAction(String command) {
		if (command.equals("u")) {
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
}
