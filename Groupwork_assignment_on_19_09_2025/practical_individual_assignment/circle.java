package COM.ENTITY;

public class circle extends SHAPES 

{
	private double radius;
	public circle() { this.radius = 7;}
	@Override
	public double calculateArea() { return Math.PI * radius * radius; }

	@Override
	public double calculatePerimeter() { return 2 * Math.PI * radius; }
}
