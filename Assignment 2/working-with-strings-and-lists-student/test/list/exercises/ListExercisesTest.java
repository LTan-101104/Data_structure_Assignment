/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;



public class ListExercisesTest {

	// Uncomment these two lines if you want to catch infinite loops
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testCountCharactersEmpty() {
		assertEquals(0, ListExercises.countCharacters(new ArrayList<String>()));
	}
	
	@Test
	public void testCountCharactersEmptyString() {
		assertEquals(0, ListExercises.countCharacters(Arrays.asList("")));
	}

	@Test
	public void testCountCharactersEmptyStrings() {
		assertEquals(0, ListExercises.countCharacters(Arrays.asList("", "", "")));
	}
	
	@Test
	public void testCountCharactersOneString() {
		assertEquals(9, ListExercises.countCharacters(Arrays.asList("asdf jkl;")));
	}

	@Test
	public void testCountCharactersThreeStrings() {
		assertEquals(15, ListExercises.countCharacters(Arrays.asList("asdf jkl;", "", "123456")));
	}
	
	@Test
	public void testSplitZero() {
		assertEquals(Arrays.asList(""), 
				ListExercises.split(""));
	}

	@Test
	public void testSplitOne() {
		assertEquals(Arrays.asList("banana"),
				ListExercises.split("banana"));
	}

	@Test
	public void testSplitThree() {
		assertEquals(Arrays.asList("Larry", "Moe", "Curly"), 
				ListExercises.split("Larry   Moe\nCurly "));
	}

	@Test
	public void testUppercasedEmptyList() {
		assertEquals(Arrays.asList(), ListExercises.uppercased(Arrays.asList()));
	}
	
	@Test
	public void testUppercasedEmpty() {
		assertEquals(Arrays.asList(""), ListExercises.uppercased(Arrays.asList("")));
	}
	
	@Test
	public void testUppercasedList() {
		assertEquals(Arrays.asList("ASDF", "JKL;", "!@#$"), 
				ListExercises.uppercased(Arrays.asList("asdf", "jkl;", "!@#$")));
	}

	@Test
	public void testUppercasedParameterUnchanged() {
		List<String> parameter = Arrays.asList("asdf", "jkl;", "!@#$");
		ListExercises.uppercased(parameter);
		assertEquals(Arrays.asList("asdf", "jkl;", "!@#$"), 
				parameter);
	}
	
	@Test
	public void testAllCapitalizedWordsEmptyList() {
		assertFalse(ListExercises.allCapitalizedWords(Arrays.asList()));
	}

	@Test
	public void testAllCapitalizedWordsEmptyString() {
		assertFalse(ListExercises.allCapitalizedWords(Arrays.asList("")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse1() {
		assertFalse(ListExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "qwer")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse2() {
		assertFalse(ListExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "!@#$")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsFalse3() {
		assertFalse(ListExercises.allCapitalizedWords(Arrays.asList("Asdf", "", "Jkl;")));
	}

	@Test
	public void testAllCapitalizedWordsOneStringTrue() {
		assertTrue(ListExercises.allCapitalizedWords(Arrays.asList("Asdf")));
	}

	@Test
	public void testAllCapitalizedWordsThreeStringsTrue() {
		assertTrue(ListExercises.allCapitalizedWords(Arrays.asList("Asdf", "Jkl;", "Qwer")));
	}

	@Test
	public void testFilterContainingEmptyList() {
		assertEquals(Arrays.asList(), ListExercises.filterContaining(Arrays.asList(), 'a'));
	}

	@Test
	public void testFilterContainingEmptyStrings() {
		assertEquals(Arrays.asList(), ListExercises.filterContaining(Arrays.asList("", ""), 'b'));
	}
	
	@Test
	public void testFilterContainingEmptyAndMatchingStrings() {
		assertEquals(Arrays.asList("Marc"), 
				ListExercises.filterContaining(Arrays.asList("Marc", "", "banana"), 'c'));
	}

	@Test
	public void testFilterContainingMultipleMatchingStrings() {
		assertEquals(Arrays.asList("David", "Ted", "Bundy"), 
				ListExercises.filterContaining(Arrays.asList("Marc", "David", "Ted", "Bundy"), 'd'));
	}
	
	@Test
	public void testFilterContainingParameterUnchanged() {
		List<String> parameter = Arrays.asList("Marc", "David", "Ted", "Bundy"); 
		ListExercises.filterContaining(parameter, 'e');
		assertEquals(Arrays.asList("Marc", "David", "Ted", "Bundy"), parameter);
	}
	
	@Test
	public void testInsertInOrderEmpty() {		
		List<String> l = new ArrayList<String>();;
		ListExercises.insertInOrder("Josh", l);
		assertEquals(Arrays.asList("Josh"), l);
	}	

	@Test
	public void testInsertInOrderFirst() {		
		List<String> l = new ArrayList<String>();
		l.add("Marc");
		ListExercises.insertInOrder("Josh", l);
		assertEquals(Arrays.asList("Josh", "Marc"), l);
	}	

	@Test
	public void testInsertInOrderMiddle() {		
		List<String> l = new ArrayList<String>();
		l.add("Josh");
		l.add("Marc");
		ListExercises.insertInOrder("Lisa", l);
		assertEquals(Arrays.asList("Josh", "Lisa", "Marc"), l);
	}
	
	@Test
	public void testInsertInOrderEnd() {		
		List<String> l = new ArrayList<String>();
		l.add("Josh");
		l.add("Lisa");
		ListExercises.insertInOrder("Marc", l);
		assertEquals(Arrays.asList("Josh", "Lisa", "Marc"), l);
	}
	
	
}
