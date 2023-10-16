package nemo;

public class Point2D {
	private int xCoordinate;
	private int yCoordinate;
	
	public Point2D(int newXCoordinate,int newYCoordinate) {
		xCoordinate = newXCoordinate;
		yCoordinate = newYCoordinate;
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
	public void sum(Point2D adderPoint) {
		xCoordinate += adderPoint.getXCoordinate();
		yCoordinate += adderPoint.getYCoordinate();
	}
}
