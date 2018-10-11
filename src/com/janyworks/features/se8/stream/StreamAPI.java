/**
 * 
 */
package com.janyworks.features.se8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Stream is a way of dealing data in a collection as a whole
 * Parallel Stream, splits the task across multiple CPUs
 * 
 * Some important concepts are
 * 1. Various methods of creating Stream Object
 * 2. filter
 * 3. map
 * 4. collect - groupby, partition
 * 5. count
 * 6. forEach
 * 7. toArray
 * 8. sorted
 * 9. min
 * 10 max
 * 11. reduce
 * A reduction operation (also called a fold) takes a sequence of input elements 
 * and combines them into a single summary result by repeated application of a combining operation
 * 12. peek
 * 13. flatMap
 * 13. Closing streams 
 * - https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * Streams have a BaseStream.close() method and implement AutoCloseable, 
 * but nearly all stream instances do not actually need to be closed after use. Generally, 
 * only streams whose source is an IO channel (such as those returned by Files.lines(Path, Charset)) will require closing. 
 * Most streams are backed by collections, arrays, or generating functions, 
 * which require no special resource management. 
 * (If a stream does require closing, it can be declared as a resource in a try-with-resources statement.)
 * 
 * 
 * @author Jany
 *
 */
public class StreamAPI {

	public static void main(String[] args) {
		learnStreamForEach();
		learnStreamFilter();
		learnStreamCollect();
		learnStreamMethods();
		learnStreamParallel();
		learnStreamForArray();
	}
	
	private static List<Person> persons = new ArrayList<Person>();
	private static Person[] personsArray = {
			new Person("Joy", 33, "Male"),
			new Person("John", 55, "Male"),
			new Person("Sara", 25, "Female"),
			new Person("Smith", 60, "Male"),
			new Person("Bob", 20, "Male"),
			new Person("Grace", 35, "Female")
	};
	
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
		try(Stream<Person> stream = persons.stream()){//The try-with-resources is not required for collection streams
			stream.forEach((p)->System.out.println(p.getName()));
		}
	}
	public static void learnStreamFilter() {
		System.out.println("learnStreamFilter");
		persons.stream().filter((p)->p.getAge()>30).forEach((p)->System.out.println(p.getName()));
	}
	
	public static void learnStreamMethods() {
		System.out.println("learnStreamMethods");
		
		System.out.println("learnStreamMethods reduce");
		Person person = Stream.of(personsArray).reduce(new Person("FirstOne",10000,"Female"), (r,e)->{r.setAge(r.getAge()+e.getAge());return r;});
		System.out.printf("Reduced Person: Name:%s Age:%d Gender:%s\n",person.getName(),person.getAge(),person.getGender());
	
		System.out.println("learnStreamMethods flatmap");
		Stream<String> personDetails = Stream.of(personsArray).flatMap((p)->Stream.of(p.getName()+" "+p.getGender()));
		personDetails.forEach(p->System.out.println(p));
		
		System.out.println("learnStreamMethods peek");
		
		Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());
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
		
		System.out.println("learnStreamCollect - partition");
		Map<Boolean,List<Person>> partitionedMap = persons.stream().collect(Collectors.partitioningBy(p->p.getAge()>30));
		List<Person> result = partitionedMap.get(true);
		System.out.println("partition Age > 30 ");
		if(result != null) {
			result.stream().forEach(p->System.out.printf("Name: %s Age: %d\n",p.getName(),p.getAge()));
		}
		System.out.println("partition Age <= 30 ");
		result = partitionedMap.get(false);
		if(result != null) {
			result.stream().forEach(p->System.out.printf("Name: %s Age: %d\n",p.getName(),p.getAge()));
		}
	}
	
	
	
	public static void learnStreamParallel() {
		System.out.println("learnStreamParallel");
		
		persons.parallelStream().forEach((p)->System.out.println(p.getName()));

		//Another way of getting Parallel Streams
		//persons.stream().parallel()
		
		//Parallel Streams are beneficial while doing aggregating
		List<Person> lotOfPerson = new ArrayList<Person>(10000);
		for(int i=0;i<10000;i++) {
			String gender = Math.round((Math.random() * 1)) == 0? "Male":"Female";
			lotOfPerson.add(new Person("1",(int)Math.round(Math.random() * 200),gender));
		}
		double averageAge = persons.stream().parallel().collect(Collectors.averagingInt((p)->p.getAge()));
		System.out.println("Average calculated parallely: "+averageAge);
		
		System.out.println("learnStreamParallel - mapping");
		//mapToInt converts the Stream<Person> to IntStream
		long totalAges = persons.parallelStream().mapToInt(p->p.getAge()).sum();
		System.out.println("Sum calculated parallel: "+totalAges);
		
		OptionalDouble optionalAvg = persons.parallelStream().mapToInt(p->p.getAge()).average();
		System.out.println("OptionalDouble calculated parallel: "+(optionalAvg.isPresent()?optionalAvg.getAsDouble():"No Avg"));
		
		optionalAvg = new ArrayList<Person>().parallelStream().mapToInt(p->p.getAge()).average();
		System.out.println("OptionalDouble calculated parallel: "+(optionalAvg.isPresent()?optionalAvg.getAsDouble():"No Avg"));
		
	}
	
	public static void learnStreamForArray() {
		System.out.println("learnStreamForArray");
		Stream.of(personsArray).forEach((p)->System.out.println(p.getName()));
		
		//Another way of getting stream
		Arrays.stream(personsArray);
		
		//Another way of getting stream
		Stream.of(1,2,3,4,5);
		
		//toArray - copies elements to another array
		Person[] anotherArray = Stream.of(personsArray).toArray(Person[]::new);
		
		//Same as above
		IntFunction<Person[]> personsStream = i->new Person[i];
		anotherArray = Stream.of(personsArray).toArray(personsStream);
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
