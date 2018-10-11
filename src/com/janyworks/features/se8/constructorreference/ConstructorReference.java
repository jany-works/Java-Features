/**
 * 
 */
package com.janyworks.features.se8.constructorreference;

/**
 * Constructor reference can be done for no argument constructors only
 * 
 * @author Jany
 *
 */
public class ConstructorReference {

	public static void main(String[] args) {
		learnConstructorReference();
	}
	
	public static void learnConstructorReference() {
		System.out.println("learnConstructorReference");
		ITestConstRef iConstRef = Learner::new;
		System.out.println(iConstRef.getDetail().getName());
	}
	
}

interface ITestConstRef{
	public Learner getDetail();
}

interface ITestConstRefWithArg{
	public Learner getDetail(String name);
}

 class Learner{
	
	private String name;
	
	public Learner() {
		name = "DefaultLearner";
	}
	
	public Learner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
