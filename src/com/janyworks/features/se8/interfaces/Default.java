/**
 * 
 */
package com.janyworks.features.se8.interfaces;

/**
 * This class shows how the default method works 
 * when implementing multiple interfaces with the same default method
 * @author Jany
 */
public class Default {

	public static void main(String[] args) {
		
		ClassC c = new ClassC();
		c.displayMessage();
		
		ClassD d = new ClassD();
		d.displayMessage();
		
		ClassE e = new ClassE();
		e.displayMessage();
		e.displayOtherMessage();
	}

}

interface InterfaceA{
	default void displayMessage() {
		System.out.println("InterfaceA.default.displayMesasge");
	}
}

interface InterfaceB{
	default void displayMessage() {
		System.out.println("InterfaceB.default.displayMesasge");
	}
}

interface InterfaceC extends InterfaceA{
	
}

class ClassC implements InterfaceC{
	
}

/*
 * InterfaceD has to override displayMesasge because InterfaceA and InterfaceB
 * has the same default method, there is ambiguity in which method to use.
 * Use the super keyword to call the required implementation
 */
interface InterfaceD extends InterfaceA,InterfaceB{

	default void displayMessage() {
		System.out.println("InterfaceD.default.displayMesasge");
		InterfaceA.super.displayMessage();
		InterfaceB.super.displayMessage();
	}
	
}

class ClassD implements InterfaceD{
	
}

/*
 * ClassE has to override displayMesasge because InterfaceA and InterfaceB
 * has the same default method, there is ambiguity in which method to use.
 * Use the super keyword to call the required implementation
 */
class ClassE implements InterfaceA,InterfaceB{

	public void displayMessage() {
		System.out.println("ClassE.default.displayMesasge");
		InterfaceA.super.displayMessage();
		InterfaceB.super.displayMessage();
	}

	
	public void displayOtherMessage()
	{
		InterfaceA.super.displayMessage();
	}
}
