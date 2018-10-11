/**
 * 
 */
package com.janyworks.features.se8.methodreference;

/**
 * Method reference is alternative to lambda expression
 * 
 * The method referenced and referrer should have the same argument.
 * If the the referrer has void return type then the referenced can have any return type
 * but if the referrer has return type other than void then the reference return type should also match
 * 
 * The target method can be static or instance
 * 
 * @author Jany
 *
 */
public class MethodReference {

	public static void main(String[] args) {
		learnStaticMethodReference();
		learnInstanceMethodReference();
	}
	
	public static void testRun()  {
		for(int i=0;i<10;i++) {
			System.out.println("testRunning "+i);
		}
	}
	
	public static int testRunAndSum() {
		int s=0;
		for(int i=0;i<10;i++) {
			s=s+i;
			System.out.println("testRunAndSumming "+s);
		}
		return s;
	}
	
	public void testMultiply()  {
		int m = 1;
		for(int i=1;i<11;i++) {
			System.out.println("testMultiply "+(m=m*i));
		}
	}
	
	public int testRunAndAvg() {
		int s=0;
		for(int i=0;i<10;i++) {
			s=s+i;
			System.out.println("Sum "+s);
		}
		return s/10;
	}
	
	public static void learnStaticMethodReference() {
		System.out.println("learnStaticMethodReference");
		Thread t = new Thread(MethodReference::testRun);
		t.start();
		
		t = new Thread(MethodReference::testRunAndSum);//Note testRunAndSum returns int but still works because the referrer return type is void
		t.start();
		
		ITestMethRef iMethRef = MethodReference::getPerson;
		iMethRef.getName(1111);
		
		//iMethRef = MethodReference::getPersonAge;//Cannot do this, should be same type
	}
	
	public static void learnInstanceMethodReference() {
		System.out.println("learnInstanceMethodReference");
		MethodReference mr = new MethodReference();
		Thread t = new Thread(mr::testMultiply);
		t.start();
		
		t = new Thread(mr::testRunAndAvg);
		t.start();
	}
	
	public static String getPerson(int id) {
		System.out.println("getPerson");
		return ""+id;
	}
	
	public static Integer getPersonAge(int id) {
		System.out.println("getPersonAge");
		return Integer.valueOf(id);
	}
}

interface ITestMethRef{
	public String getName(int id);
}
