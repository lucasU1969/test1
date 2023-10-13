package nemo;

public class TurnRight extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'r';
	}

	@Override
	public Runnable getAction() {
		// TODO Auto-generated method stub
		return () -> turnRight();
	}

}
