package nemo;

public class MoveUpwards extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'u';
	}
	
	public Runnable getAction() {
		return () -> moveUpwards();
	}

}
