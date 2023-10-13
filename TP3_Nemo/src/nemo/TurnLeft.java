package nemo;

public class TurnLeft extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'l';
	}

	@Override
	public Runnable getAction() {
		// TODO Auto-generated method stub
		return () -> turnLeft();
	}

}
