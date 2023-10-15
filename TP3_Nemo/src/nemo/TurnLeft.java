package nemo;

public class TurnLeft extends Commands {

	public char getCommandAsChar() {
		return 'l';
	}

	public Runnable getAction() {
		return () -> turnLeft();
	}

}
