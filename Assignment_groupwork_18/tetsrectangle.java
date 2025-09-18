//1. Bahati HAKIZIMANA 		223010852
//2. Olivier HABINGABIRE	223007847
//3. Federance TUYIZERE 	223003521
//4. Christine KIRABO		223008741
//5. Dieudonne IRADUKUNDA	223015105
//6. Felicite NYIRANZIZA	223005134
//7. Hidaya UMUTONI			222020222


package com.case1;

import java.util.Scanner;

public class tetsrectangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input= new Scanner(System.in);
		Rectangle bjc= new Rectangle();
		bjc.calcualtearea();
	
		System.out.println("enter the length:");
		bjc.setLength(input.nextDouble());
		System.out.println("enter the width:");
		bjc.setWidth(input.nextDouble());
		
		if(bjc.getLength() > bjc.getWidth()){
			System.out.println("The shape is Rectangle: then the area is:"+bjc.calcualtearea());
					
			
		}
		else{
			System.out.println("the shape is square");
		}
		
		
	

	}

}
