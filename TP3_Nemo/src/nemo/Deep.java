package nemo;

public class Deep extends Depth {
	private int value;

	public Deep(int newValue) {
		value = newValue;
	}

	public Depth moveDownwards() {
		return new Deep(value - 1);
	}

	public Depth moveUpwards() {
		return this;
	}

	public void launchCapsule(Nemo submarine) {
		submarine = null;
		throw new RuntimeException("Nemo cannot launch the capsule this deep.");
	}
	
	public int getDepth() {
		return value;
	}
}
