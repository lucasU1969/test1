package nemo;

public class LaunchCapsule extends Commands {

	public char getCommandAsChar() {
		return 'm';
	}

	public Runnable getAction() {
		return () -> launchCapsule();
	}

}
