package nemo;

public class MoveForward extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'f';
	}

	@Override
	public Runnable getAction() {
		// TODO Auto-generated method stub
		return () -> moveForward();
	}

}
