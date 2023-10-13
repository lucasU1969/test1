package nemo;

public class Underwater extends Depth {

    public void launchCapsule() {}
	
	public int getDepth() {
		return -1;
	}

	public Depth moveDownwards() {
		return new Deep(-2);
	}

	public Depth moveUpwards() {
		return this;
	}
}
