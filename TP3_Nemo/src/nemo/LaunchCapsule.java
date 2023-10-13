package nemo;

public class LaunchCapsule extends Commands {

	@Override
	public char getCommandAsChar() {
		// TODO Auto-generated method stub
		return 'm';
	}

	@Override
	public Runnable getAction() {
		// TODO Auto-generated method stub
		return () -> launchCapsule();
	}

}
