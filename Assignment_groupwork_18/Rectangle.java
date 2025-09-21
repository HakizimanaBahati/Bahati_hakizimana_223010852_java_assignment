
//1. Bahati HAKIZIMANA 		223010852
//2. Olivier HABINGABIRE	223007847
//3. Federance TUYIZERE 	223003521
//4. Christine KIRABO		223008741
//5. Dieudonne IRADUKUNDA	223015105
//6. Felicite NYIRANZIZA	223005134
//7. Hidaya UMUTONI			222020222





package com.case1;

public class Rectangle {
	private  double length;
	private double width;
	
	
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double calcualtearea(){
		return this.length*this.width;

	}
}

