package nemo;

public class MoveUpwards extends Commands {

	public char getCommandAsChar() {
		return 'u';
	}
	
	public Runnable getAction() {
		return () -> moveUpwards();
	}

}
