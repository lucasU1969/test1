package nemo;

public class TurnRight extends Commands {

	public char getCommandAsChar() {
		return 'r';
	}

	public Runnable getAction() {
		return () -> turnRight();
	}

}
