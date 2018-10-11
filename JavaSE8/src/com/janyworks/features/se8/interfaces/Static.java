package com.janyworks.features.se8.interfaces;

/**
 * This class show how and how not to use interface static methods
 * 
 * @author Jany
 *
 */
public class Static {

	public static void main(String[] args) {

		InterfaceF.displayMessage1();
		InterfaceF.displayMessage2();
		
		ClassF f = new ClassF();
		//f.displayMessage1(); //Cannot do this
		//ClassF.displayMessage1(); //Cannot do this
	}

}
interface InterfaceF{
	static void displayMessage1() {
		System.out.println("Static InterfaceA displayMessage1");
	}
	static void displayMessage2() {
		System.out.println("Static InterfaceA displayMessage2");
	}
}
class ClassF implements InterfaceF{
	
	static void displayMessage3() {
		 //displayMessage1(); //Cannot do this
	}
}

//The main method in the interface can be run using the java cmd (as a standalone program)
interface InterfaceG{
	static void main(String[] args) {
		System.out.println("Running InterfaceG main method");
	}
}