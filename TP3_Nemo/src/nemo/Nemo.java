package nemo;

public class Nemo {
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	private String direction;
	public Nemo(int newXCoordinate, int newYCoordinate, int newZCoordinate, String newDirection) {
//
		xCoordinate = newXCoordinate;
		yCoordinate = newYCoordinate;
		zCoordinate = newZCoordinate;
		direction = newDirection;

	}

	public void moveUpward() {
		if (zCoordinate == 0) {
			throw new RuntimeException("Nemo cannot move upward in the surface");
		}
		zCoordinate++;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public int getZCoordinate() {
		return zCoordinate;
	}

	public String getDirection() {
		return direction;
	}
}
