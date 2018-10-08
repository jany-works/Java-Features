/**
 * 
 */
package com.janyworks.features.se8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A Stream is a way of dealing data in a collection as a whole
 * Parallel Stream, splits the task across multiple CPUs
 * 
 * @author Jany
 *
 */
public class StreamAPI {

	public static void main(String[] args) {
		learnStreamForEach();
		learnStreamFilter();
		learnStreamCollect();
		learnStreamParallel();
	}
	
	private static List<Person> persons = new ArrayList<Person>();
	static {
		persons.add(new Person("Joy", 33, "Male"));
		persons.add(new Person("John", 55, "Male"));
		persons.add(new Person("Sara", 25, "Female"));
		persons.add(new Person("Smith", 60, "Male"));
		persons.add(new Person("Bob", 20, "Male"));
		persons.add(new Person("Grace", 35, "Female"));
	}
	public static void learnStreamForEach() {
		System.out.println("learnStreamForEach");
		persons.stream().forEach((p)->System.out.println(p.getName()));
	}
	public static void learnStreamFilter() {
		System.out.println("learnStreamFilter");
		persons.stream().filter((p)->p.getAge()>30).forEach((p)->System.out.println(p.getName()));
	}
	public static void learnStreamCollect() {
		System.out.println("learnStreamCollect - groupingBy");
		Map<String,List<Person>> personsByGender = persons.stream().collect(Collectors.groupingBy(Person::getGender));
		System.out.println(personsByGender);
		
		//Equivalent expression below
		Map<String,List<Person>> personsByGender2 = persons.stream().collect(Collectors.groupingBy((p)->p.getGender()));
		System.out.println(personsByGender2);
		
		//Equivalent expression below
		Function<Person,String> f = (p)->p.getGender();//Function<Person,String> f2 = Person::getGender;//Both expressions are same
		Collector<Person,?,Map<String,List<Person>>> c = Collectors.groupingBy(f);
		Map<String,List<Person>> personsByGender3 = persons.stream().collect(c);
		System.out.println(personsByGender3);
		
		System.out.println("learnStreamCollect - summingInt");
		
		int total = persons.stream().collect(Collectors.summingInt((p)->p.getAge()));
		System.out.println(total);
		
		System.out.println("learnStreamCollect - reducing");
		
		System.out.println("learnStreamCollect - partition");
	}
	public static void learnStreamParallel() {
		System.out.println("learnStreamParallel");
		persons.parallelStream().forEach((p)->System.out.println(p.getName()));
		
		//Another way of getting Parallel Streams
		//persons.stream().parallel()
	}

}

class Person{
	
	public Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	private String name;
	private int age;
	private String gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return this.name+this.age+this.gender;
	}
}
