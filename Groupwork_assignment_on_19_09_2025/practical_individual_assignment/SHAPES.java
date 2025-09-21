package COM.ENTITY;

public abstract class SHAPES 
{
	public abstract double calculateArea();
	public abstract double calculatePerimeter();

	@Override
	public String toString() {
		return "Area = " + calculateArea() + ", Perimeter = " + calculatePerimeter();
	}
 }
