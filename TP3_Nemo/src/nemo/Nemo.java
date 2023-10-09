package nemo;



public class Nemo {
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate = 0;
	private Directions direction;
	private CapsuleLauncher capsuleLauncher = new LoadedCapsuleLauncher();
	public Nemo(int newXCoordinate, int newYCoordinate, Directions newDirection) {
		xCoordinate = newXCoordinate;
		yCoordinate = newYCoordinate;
		direction = newDirection;
	}

	public void moveUpward() {
		if (zCoordinate != 0) {
			zCoordinate++;
		} else {
			zCoordinate = 0;
		}
	}
	public void moveDownward(){
		zCoordinate--;
	}

	public void moveForward() {
		if (direction.toString().equals(Directions.north().toString())) {
			yCoordinate++;
		} else if (direction.toString().equals(Directions.south().toString())) {
			yCoordinate--;
		} else if (direction.toString().equals(Directions.east().toString())) {
			xCoordinate++;
		} else if (direction.toString().equals(Directions.west().toString())) {
			xCoordinate--;
		}
	}

	public void turnLeft() {
		direction = direction.turnLeft();
	}

	public void turnRight() {
		direction = direction.turnRight();
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

	public Directions getDirection() {
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
	
	public boolean isCapsuleInNemo() {
		return capsuleLauncher.isLoaded();
	}

	public void launchCapsule() {
		if (zCoordinate <-1) {
			throw new RuntimeException("Nemo cannot launch the capsule this deep.");
		}
		capsuleLauncher.launch();
		capsuleLauncher = new EmptyCapsuleLaucher();
	}

}
