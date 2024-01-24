/*
 * Copyright 2021 Marc Liberatore.
 */

package string.exercises;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class StringExercisesTest {

	// Uncomment these two lines if you want to catch infinite loops
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testFindMarcEmpty() {
		assertEquals(-1, StringExercises.findMarc(""));
	}

	@Test
	public void testFindMarcExact() {
		assertEquals(0, StringExercises.findMarc("Marc"));
	}

	@Test
	public void testFindMarc() {
		assertEquals(0, StringExercises.findMarc("Marc "));
	}

	@Test
	public void testFindMarc2() {
		assertEquals(2, StringExercises.findMarc("  Marc  "));
	}
	
	@Test
	public void testFindSubstringEmpty() {
		assertEquals(-1, StringExercises.findSubstring("", "banana"));
	}

	@Test
	public void testFindSubstringExact() {
		assertEquals(0, StringExercises.findSubstring("banana", "banana"));
	}

	@Test
	public void testFindSubstring() {
		assertEquals(0, StringExercises.findSubstring("banana ", "banana"));
	}

	@Test
	public void testFindSubstring2() {
		assertEquals(2, StringExercises.findSubstring("  banana  ", "banana"));
	}
	
	@Test
	public void testFindSubstringBananas() {
		assertEquals(2, StringExercises.findSubstring("  bananabanana  ", "banana"));
	}
	
	@Test
	public void testContainsEmpty() {
		assertFalse(StringExercises.contains("", "banana"));
	}

	@Test
	public void testContainsExact() {
		assertTrue(StringExercises.contains("foo", "foo"));
	}

	@Test
	public void testContains() {
		assertTrue(StringExercises.contains("foo ", "foo"));
	}

	@Test
	public void testContainsEmbedded() {
		assertTrue(StringExercises.contains("aasdfsdf", "asdf"));
	}
	
	@Test
	public void testNotContainsEmbedded() {
		assertFalse(StringExercises.contains("aasdfsdf", "adsf"));
	}
	
	@Test
	public void testSplitZero() {
		assertArrayEquals(new String[] {""},
				StringExercises.splitIntoWords(""));
	}

	@Test
	public void testSplitOne() {
		assertArrayEquals(new String[] {"banana"},
				StringExercises.splitIntoWords("banana"));
	}

	@Test
	public void testSplitThree() {
		assertArrayEquals(new String[] {"Larry", "Moe", "Curly"}, 
				StringExercises.splitIntoWords("Larry   Moe\nCurly "));
	}

	@Test
	public void testFirstFourExact() {
		assertEquals("Kate", StringExercises.firstFour("Kate"));
	}

	@Test
	public void testFirstFour() {
		assertEquals("Kate", StringExercises.firstFour("Katelyn"));
	}
	
	@Test
	public void testFirstN() {
		assertEquals("", StringExercises.firstN("Katelyn", 0));
		assertEquals("K", StringExercises.firstN("Katelyn", 1));
		assertEquals("Ka", StringExercises.firstN("Katelyn", 2));
		assertEquals("Kat", StringExercises.firstN("Katelyn", 3));
		assertEquals("Kate", StringExercises.firstN("Katelyn", 4));
		assertEquals("Katel", StringExercises.firstN("Katelyn", 5));
		assertEquals("Kately", StringExercises.firstN("Katelyn", 6));
		assertEquals("Katelyn", StringExercises.firstN("Katelyn", 7));
	}

	@Test
	public void testLastFourExact() {
		assertEquals("Kate", StringExercises.lastFour("Kate"));
	}

	@Test
	public void testLastFour() {
		assertEquals("elyn", StringExercises.lastFour("Katelyn"));
	}
	
	@Test
	public void testLastN() {
		assertEquals("", StringExercises.lastN("Katelyn", 0));
		assertEquals("n", StringExercises.lastN("Katelyn", 1));
		assertEquals("yn", StringExercises.lastN("Katelyn", 2));
		assertEquals("lyn", StringExercises.lastN("Katelyn", 3));
		assertEquals("elyn", StringExercises.lastN("Katelyn", 4));
		assertEquals("telyn", StringExercises.lastN("Katelyn", 5));
		assertEquals("atelyn", StringExercises.lastN("Katelyn", 6));
		assertEquals("Katelyn", StringExercises.lastN("Katelyn", 7));
	}}
