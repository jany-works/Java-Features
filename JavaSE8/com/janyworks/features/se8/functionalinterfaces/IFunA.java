/**
 * 
 */
package com.janyworks.features.se8.functionalinterfaces;

/**
 * 1. Functional interfaces must have (only) one abstract method
 * 2. Functional interfaces can have any number of default methods
 * 3. Functional interfaces can have any number of static methods
 * 4. Interfaces like Runnable, Comparator are Functional Interfaces
 * 
 * @author Jany
 */

@FunctionalInterface
interface IFunA {

	void funMethod1();
}

@FunctionalInterface
interface IFunB {

	void funMethod2();
	
	default void defMethod1() {
		System.out.println("Default Method 1");
	}
	
	static void statMethod1() {
		System.out.println("Static Method 1");
	}
}

@FunctionalInterface
interface IFunC extends IFunA {

}

@FunctionalInterface
interface IFunD extends IFunA {
	void funMethod1();
}

//IFunE is also functional interface
interface IFunE  {
	void funMethod3();
}

//IFunF is not a functional interface
interface IFunF extends IFunA {
	void funMethod2();
}
