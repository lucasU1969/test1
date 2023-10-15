package nemo;

public class MoveForward extends Commands {

	public char getCommandAsChar() {
		return 'f';
	}

	public Runnable getAction() {
		return () -> moveForward();
	}

}
