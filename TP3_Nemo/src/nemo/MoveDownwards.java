package nemo;

public class MoveDownwards extends Commands {

	public char getCommandAsChar() {
		return 'd';
	}

	public Runnable getAction() {
		return () -> moveDownwards();
	}

}
