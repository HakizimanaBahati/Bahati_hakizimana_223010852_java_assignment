package COM.ENTITY;

public class Rectangle extends SHAPES {

	private double length;
	private double width;


	public Rectangle() {
		this.length = 5;
		this.width = 10;
	}

	@Override
	public double calculateArea() {
		return length * width;
	}

	@Override
	public double calculatePerimeter() {
		return 2 * (length + width);
	}

}
