package com.janyworks.features.se8.functionalinterfaces;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Predicate
 * -----------
 * 1. Predicate is a functional interface, 
 * it has one abstract method test which takes one argument and returns boolean
 * 2. Predicate has three default methods - and, or, negate
 * 3. Predicate has one static method isEqual
 * 4. BiPredicate is a functional interface, 
 * it has one abstract method test which takes two arguments and returns boolean
 * 5. IntPredicate, LongPredicate, DoublePredicate are used for primitive arguments
 * 
 * 
 * 
 * Function
 * -----------
 * 1. Function is a functional interface,
 * it has one abstract method apply which takes one argument and returns any type.
 * 2. Function has two default methods - andThen, compose
 * 3. Function has one static method identity
 * 4. BiFunction is a functional interface, 
 * it has one abstract method apply which takes two arguments and returns any type.
 * 5. IntFunction, LongFunction, DoubleFunction are used for primitive arguments
 * 
 * 
 * @author Jany
 *
 */
public class FunctionalInterfaceExamples {

	public static void main(String[] args) {
		
		learnPredicate();
		learnFunction();
		learnConsumer();
		learnSupplier();
	}

	public static void learnPredicate() {
		Predicate<String> pNotEmpty = s -> !s.isEmpty(); 
		Predicate<String> pLength5 = s -> s.length() > 5; 
		Predicate<String> pCharH = s -> s.charAt(0) == 'H'; 

		System.out.println("1. "+pNotEmpty.and(pLength5).test("Hello World"));
		System.out.println("2. "+pLength5.or(pCharH).test("Bye"));
		
		System.out.println("3. "+pLength5.or(pCharH).or(pNotEmpty).test("Bye"));
		System.out.println("4. "+pNotEmpty.and(pCharH).or(pLength5).test("Fellow"));
		
		System.out.println("5. "+pNotEmpty.negate().test("Hello World"));
		
		System.out.println("6. "+Predicate.isEqual("Hello").test("World"));
		System.out.println("7. "+Predicate.isEqual("Hello").test("Hello"));
		
		BiPredicate<String, String> bp;
		IntPredicate ip;
		LongPredicate lp;
		DoublePredicate dp;
	}
	
	public static void learnFunction() {
		Function<Integer, String> twiceF = i -> ""+i*2;
		
		Function<String, String> twiceConcatF = s -> s+s;
		
		System.out.println("Function 1. "+twiceF.apply(5));
		System.out.println("Function 2. "+twiceF.andThen(twiceConcatF).apply(7));
		System.out.println("Function 3. "+twiceConcatF.compose(twiceF).apply(12));
		
		//TODO identity?
		
		BiFunction<Integer, Integer, String> bif;
		IntFunction<String> intf;
		LongFunction<String> lf;
		DoubleFunction<String> df;
		
		IntToLongFunction itlf;
		IntToDoubleFunction idf;
		LongToIntFunction lif;
		LongToDoubleFunction ltdf;
		DoubleToIntFunction dtif;
		DoubleToLongFunction dtlf;
		
		
		
	}
	
	public static void learnConsumer() {
		Consumer<String> printC = s -> System.out.println(s);
		
		printC.accept("Consumer Accepts");
		
		BiConsumer<String, String> biC;
		IntConsumer ic;
		LongConsumer lc;
		DoubleConsumer dc;
		
	}
	
	public static void learnSupplier() {
		Supplier<Integer> randomNum = ()-> (int)(Math.random()*10);
		
		System.out.println(randomNum.get());
		System.out.println(randomNum.get());
		System.out.println(randomNum.get());
		
		IntSupplier is;
		LongSupplier ls;
		DoubleSupplier ds;
		BooleanSupplier bs;
		
	}
	
}
