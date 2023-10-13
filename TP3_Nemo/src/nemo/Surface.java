package nemo;

public class Surface extends Depth {

	public Depth moveDownwards() {
		return new Underwater();
	}
	
	public void launchCapsule() {}
	
	public int getDepth() {
		return 0;
	}
	
	public Depth moveUpwards() {
		return null;
	}
	
}
