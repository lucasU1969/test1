package nemo;

public class MoveDownwards extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'd';
	}

	@Override
	public Runnable getAction() {
		// TODO Auto-generated method stub
		return () -> moveDownwards();
	}

}
