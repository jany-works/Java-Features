/**
 * 
 */
package com.janyworks.features.se8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1. Only Functional Interfaces can be implemented by lambda expression
 * 2. Functional Interfaces are interfaces with (only) one abstract method
 * 3. Interfaces like Runnable, Comparator are Functional Interfaces
 * 
 * @author Jany
 */
public class Lambda {

	public static void main(String[] args) {
		funAdd();
		funSquare();
		funRun();
		funCompare();
	}
	
	interface IFunAdd{
		int add(int a, int b);
	}
	/**
	 * This method shows different ways of implementing IFunAdd using lambda expressions
	 */
	public static void funAdd() {
		//If only one statement inside lambda, then curly braces not required
		//If no curly braces used, then return keyword is not required
		IFunAdd funAdd = (a,b) -> a+b;
		System.out.println(funAdd.add(10, 20));
		
		//return keyword is required if curly braces used inside lambda
		IFunAdd funAdd2 = (a,b) -> { return a+b;};
		System.out.println(funAdd2.add(10, 20));
		
		//Curly braces required if multiple statement inside lambda
		IFunAdd funAdd3 = (a,b) -> { int r = a+b; return r;};
		System.out.println(funAdd3.add(10, 20));
	}
	
	interface IFunSquare{
		int square(int s);
	}
	/**
	 * This method shows different ways of implementing one argument method using lambda expressions
	 */
	public static void funSquare() {
		
		IFunSquare funSquare = (a)->a*a;
		System.out.println(funSquare.square(10));
		
		//If only one argument, then parenthesis is not required
		IFunSquare funSquare2 = a->a*a;
		System.out.println(funSquare2.square(5));
	}
	
	/**
	 * This method shows lambda expression implementation of Runnable
	 */
	public static void funRun() {
		
		Runnable funRun = ()->{for(int i=0;i<10;i++) {System.out.println("Child Thread1 Running");}};
		
		Thread t = new Thread(funRun);
		Thread t2 = new Thread(()->{for(int i=0;i<15;i++) {System.out.println("Child Thread2 Running");}});
		
		t.start();
		t2.start();
		for(int i=0;i<10;i++) {
			System.out.println("Main Thread Running");
		}
	}
	
	/**
	 * This method lambda expression implementation of Comparator
	 */
	public static void funCompare() {
		
		List<String> names = Arrays.asList("Apple","Ball","Cat","Dog");
		Collections.sort(names, (a,b)->b.compareTo(a));
		System.out.println(names);
	}
}
