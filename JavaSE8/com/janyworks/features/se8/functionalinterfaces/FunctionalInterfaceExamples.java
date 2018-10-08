package com.janyworks.features.se8.functionalinterfaces;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
 * Consumer
 * ----------
 * 1. Consumer is a functional interface,
 * it has one abstract method accept which takes one argument and returns void
 * 2. Consumer has one default method - andThen
 * 3. BiConsumer is a functional interface, which takes two arguments and returns void
 * 4. IntConsumer, LongConsumer, DoubleConsumer are used for primitive arguments
 * 
 * 
 * Supplier
 * ----------
 * 1. Supplier is a functional interface,
 * it has one abstract method get which doesn't take any argument but returns the specified type.
 * 2. IntSupplier, LongSupplier, DoubleSupplier, BooleanSupplier bs;
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

		System.out.println("Predicate 1. "+pNotEmpty.and(pLength5).test("Hello World"));
		System.out.println("Predicate 2. "+pLength5.or(pCharH).test("Bye"));
		
		System.out.println("Predicate 3. "+pLength5.or(pCharH).or(pNotEmpty).test("Bye"));
		System.out.println("Predicate 4. "+pNotEmpty.and(pCharH).or(pLength5).test("Fellow"));
		
		System.out.println("Predicate 5. "+pNotEmpty.negate().test("Hello World"));
		
		System.out.println("Predicate 6. "+Predicate.isEqual("Hello").test("World"));
		System.out.println("Predicate 7. "+Predicate.isEqual("Hello").test("Hello"));
		
		BiPredicate<String, String> bp;
		IntPredicate ip;//int
		LongPredicate lp;//long
		DoublePredicate dp;//double
	}
	
	public static void learnFunction() {
		Function<Integer, String> twiceF = i -> ""+i*2;
		
		Function<String, String> twiceConcatF = s -> s+s;
		
		System.out.println("Function 1. "+twiceF.apply(5));
		System.out.println("Function 2. "+twiceF.andThen(twiceConcatF).apply(7));
		System.out.println("Function 3. "+twiceConcatF.compose(twiceF).apply(12));
		
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
		
		//Input and output same type
		UnaryOperator<Integer> squareUO = i -> i*i;
		System.out.println("Function 4. "+squareUO.apply(5));
		
		IntUnaryOperator iuo;
		LongUnaryOperator luo;
		DoubleUnaryOperator duo;
		
		//Input and output same type
		BinaryOperator<Integer> multiplyBO = (i,j) -> i*j;
		System.out.println("Function 5. "+multiplyBO.apply(5,4));
		
		IntBinaryOperator ibo;
		LongBinaryOperator lbo;
		DoubleBinaryOperator dbo;
	}
	
	public static void learnConsumer() {
		Consumer<String> printC = s -> System.out.println("Consumer 1. "+s);
		
		Consumer<String> printTwiceC = s -> System.out.println("Consumer 2. "+s+s);
		
		printC.andThen(printTwiceC).accept("learnConsumer");
		
		BiConsumer<String, String> biC;
		IntConsumer ic;
		LongConsumer lc;
		DoubleConsumer dc;
	}
	
	public static void learnSupplier() {
		Supplier<Integer> randomNum = ()-> (int)(Math.random()*10);
		
		System.out.println("Supplier 1. "+randomNum.get());
		System.out.println("Supplier 2. "+randomNum.get());
		System.out.println("Supplier 3. "+randomNum.get());
		
		IntSupplier is;
		LongSupplier ls;
		DoubleSupplier ds;
		BooleanSupplier bs;
	}
}
