package nemo;


public class Nemo {
	private int zCoordinate = 0;
	private int[] pos;
	private Directions direction;
	private CapsuleLauncher capsuleLauncher = new LoadedCapsuleLauncher();
	
	public Nemo(int newXCoordinate, int newYCoordinate, Directions newDirection) {
		pos = new int[] {newXCoordinate, newYCoordinate};
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
		return zCoordinate;
	}

	public Directions getDirection() {
		return direction;
	}
	
	public boolean isOnTheSurface() {
		return zCoordinate == 0;
	}

	public void command(String commands) {
//		Arrays.asList(commands).forEach(command -> executeAction(command));
		for (int i = 0; i < commands.length(); i++) {
			this.executeAction(commands.substring(i, i+1));
		}
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
		if (zCoordinate <-1) {
			throw new RuntimeException("Nemo cannot launch the capsule this deep.");
		}
		capsuleLauncher.launch();
		capsuleLauncher = new EmptyCapsuleLaucher();
	}

}
