package nemo;

public abstract class Commands {

	public static Commands moveUpwards() {
		// TODO Auto-generated method stub
		return new MoveUpwards();
	}

	public static Commands moveDownwards() {
		// TODO Auto-generated method stub
		return new MoveDownwards();
	}

	public static Commands turnLeft() {
		// TODO Auto-generated method stub
		return new TurnLeft();
	}

	public static Commands turnRight() {
		// TODO Auto-generated method stub
		return new TurnRight();
	}

	public static Commands launchCapsule() {
		// TODO Auto-generated method stub
		return new LaunchCapsule();
	}

	public static Commands moveForward() {
		// TODO Auto-generated method stub
		return new MoveForward() ;
	}
	
	public abstract char getCommandAsChar();
	public abstract Runnable getAction();

}
