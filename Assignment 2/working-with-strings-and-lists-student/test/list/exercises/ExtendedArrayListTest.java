/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class ExtendedArrayListTest {

	// Uncomment these two lines out if you want to catch infinite loops
//	@Rule
//	public Timeout globalTime= Timeout.seconds(10); // 10 seconds

	ExtendedArrayList<Object> empty;
	ExtendedArrayList<Integer> one;
	ExtendedArrayList<Integer> oneTwo;
	ExtendedArrayList<String> names;

	/** 
	 * each of the ExtendedArrayLists declared above are reset before each test 
	 */
	@Before
	public void setup() {

		//! this is a way to set things up and resue all of the instance
		//! Before notation will let Java run this method each time before a Test
		//! Note that we have to save these things to class atrtibutes because it should be shared among all Testing method
		empty = new ExtendedArrayList<Object>();
		
		one = new ExtendedArrayList<Integer>();
		one.add(1);
		
		oneTwo = new ExtendedArrayList<Integer>();
		oneTwo.add(1);
		oneTwo.add(2);
		
		names = new ExtendedArrayList<String>();
		names.add("John");
		names.add("Justine");
		names.add("Jingleheimer");
		names.add("Suzanne");
		names.add("John");
		names.add("Patrice");
		names.add("Washington");
		names.add("Kelly");
	}
	
	@Test
	public void testCountEmpty() {		
		assertEquals(0, empty.count(new Object()));
	}

	@Test
	public void testCountOne() {		
		assertEquals(1, one.count(1));
	}

	@Test
	public void testCountOneFirst() {		
		assertEquals(1, oneTwo.count(1));
	}

	@Test
	public void testCountOneTwoLast() {		
		assertEquals(1, oneTwo.count(2));
	}
	
	@Test
	public void testCountTwo() {		
		assertEquals(2, names.count("John"));
	}

	@Test
	public void testCountNone() {		
		assertEquals(0, names.count("Marc"));
	}
	
	@Test
	public void testRotateRightEmpty() {		
		empty.rotateRight(1);
		assertEquals(Arrays.asList(), empty);
	}
	
	@Test
	public void testRotateRightOne() {		
		one.rotateRight(1);
		assertEquals(Arrays.asList(1), one);
		one.rotateRight(2);
		assertEquals(Arrays.asList(1), one);
	}

	@Test
	public void testRotateRightOneTwo() {		
		oneTwo.rotateRight(1);
		assertEquals(Arrays.asList(2,1), oneTwo);
		oneTwo.rotateRight(1);
		assertEquals(Arrays.asList(1,2), oneTwo);
		oneTwo.rotateRight(2);
		assertEquals(Arrays.asList(1,2), oneTwo);
	}
	
	@Test
	public void testRotateRightNames() {		
		names.rotateRight(6);
		assertEquals(Arrays.asList("Jingleheimer","Suzanne","John","Patrice",
				"Washington","Kelly","John","Justine"), 
				names);
	}

	@Test
	public void testRotateRightNamesFurther() {		
		names.rotateRight(14);
		assertEquals(Arrays.asList("Jingleheimer","Suzanne","John","Patrice",
				"Washington","Kelly","John","Justine"), 
				names);
	}

	@Test
	public void testIntersperseEmpty() {	
		empty.intersperse(0);
		assertEquals(Arrays.asList(), empty);
	}

	@Test
	public void testIntersperseOne() {	
		one.intersperse(0);
		assertEquals(Arrays.asList(1), one);
	}

	@Test
	public void testIntersperseOneTwo() {	
		oneTwo.intersperse(0);
		assertEquals(Arrays.asList(1, 0, 2), oneTwo);
	}
	
	@Test
	public void testIntersperseNames() {	
		names.intersperse("yeah");
		assertEquals(Arrays.asList("John","yeah","Justine","yeah","Jingleheimer","yeah","Suzanne",
				"yeah","John","yeah","Patrice","yeah","Washington","yeah","Kelly"), 
				names);
	}

	@Test
	public void testReversedEmpty() {
		assertEquals(Arrays.asList(), empty.reversed());
	}

	@Test
	public void testReversedOne() {
		assertEquals(Arrays.asList(1), one.reversed());
	}

	@Test
	public void testReversedTwo() {
		assertEquals(Arrays.asList(2,1), oneTwo.reversed());
	}
	
	@Test
	public void testReversedNames() {
		assertEquals(Arrays.asList("Kelly", "Washington", "Patrice", "John", "Suzanne", 
				"Jingleheimer", "Justine", "John"), 
				names.reversed());
	}

	@Test
	public void testReversedParameterUnchanged() {
		List<String> copy = new ArrayList<String>(names);
		names.reversed();
		assertEquals(copy, names);
	}
	
}
