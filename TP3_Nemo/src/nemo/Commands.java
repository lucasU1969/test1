package nemo;

import java.util.List;

public abstract class Commands {
	public static List<Commands> availableCommands = List.of( Commands.moveUpwards(), Commands.moveDownwards(), Commands.moveForward(), Commands.turnLeft(), Commands.turnRight(), Commands.launchCapsule());

	public static Commands moveUpwards() {
		return new MoveUpwards();
	}

	public static Commands moveDownwards() {
		return new MoveDownwards();
	}

	public static Commands turnLeft() {
		return new TurnLeft();
	}

	public static Commands turnRight() {
		return new TurnRight();
	}

	public static Commands launchCapsule() {
		return new LaunchCapsule();
	}

	public static Commands moveForward() {
		return new MoveForward() ;
	}
	
	public abstract char getCommandAsChar();
	public abstract void exeucuteAction(Nemo submarine);

}
