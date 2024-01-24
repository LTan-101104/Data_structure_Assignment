/*
 * Copyright 2021 Marc Liberatore.
 */

package sets;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class SetUtilitiesTest {
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private Set<Integer> emptyInts;
	private Set<Integer> evens;
	private Set<Integer> odds;
	private Set<Integer> zeroToNine;
	
	private Set<String> emptyStrings;
	private Set<String> jacksonFive;
	private Set<String> godfatherActors;

	@Before
	public void setup() {
		emptyInts = new HashSet<Integer>();

		evens = new HashSet<Integer>();
		for (int i = 0; i < 10; i += 2) {
			evens.add(i);
		}

		odds = new TreeSet<Integer>();
		for (int i = 1; i < 10; i += 2) {
			odds.add(i);
		}

		zeroToNine = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			zeroToNine.add(i);
		}
		
		emptyStrings = new TreeSet<String>();
		
		jacksonFive = new HashSet<String>();
		jacksonFive.addAll(Arrays.asList("jackie", "tito", "marlon", "michael", "jermaine", "randy"));
		
		godfatherActors = new TreeSet<String>();
		godfatherActors.addAll(Arrays.asList("marlon", "al", "james", "robert", "richard"));
	}

	@Test
	public void testUnionBothEmpty() {
		assertEquals(emptyStrings, SetUtilities.union(emptyStrings, emptyStrings));
	}

	@Test
	public void testUnionLeftEmpty() {
		assertEquals(jacksonFive, SetUtilities.union(new HashSet<String>(), jacksonFive));
	}

	@Test
	public void testUnionRightEmpty() {
		assertEquals(jacksonFive, SetUtilities.union(jacksonFive, new HashSet<String>()));
	}
	
	@Test
	public void testUnionLeftEmptyAndUnmodified() {
		Set<String> copyLeft = new HashSet<String>(emptyStrings);
		Set<String> copyRight = new HashSet<String>(jacksonFive);
		SetUtilities.union(emptyStrings, jacksonFive);
		assertEquals(copyLeft, emptyStrings);
		assertEquals(copyRight, jacksonFive);
	}

	@Test
	public void testUnionRightEmptyAndUnmodified() {
		Set<String> copyLeft = new HashSet<String>(jacksonFive);
		Set<String> copyRight = new HashSet<String>(emptyStrings);
		SetUtilities.union(jacksonFive, emptyStrings);
		assertEquals(copyLeft, jacksonFive);
		assertEquals(copyRight, emptyStrings);
	}

	@Test
	public void testUnionDisjoint() {
		assertEquals(jacksonFive, 
				SetUtilities.union(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon")), 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}

	@Test
	public void testUnionOverlapping() {
		assertEquals(jacksonFive, 
				SetUtilities.union(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon", "randy")), 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}
	
	@Test
	public void testUnionSubset() {
		assertEquals(zeroToNine, 
				SetUtilities.union(zeroToNine, odds));
		assertEquals(zeroToNine, 
				SetUtilities.union(evens, zeroToNine));
	}
	
	@Test
	public void testUnionSameValues() {
		assertEquals(zeroToNine, 
				SetUtilities.union(zeroToNine, zeroToNine));
	}

	@Test
	public void testIntersectionBothEmpty() {
		assertEquals(emptyStrings, SetUtilities.intersection(emptyStrings, emptyStrings));
	}

	@Test
	public void testIntersectionLeftEmpty() {
		assertEquals(emptyStrings, SetUtilities.intersection(new HashSet<String>(), jacksonFive));
	}

	@Test
	public void testIntersectionRightEmpty() {
		assertEquals(emptyStrings, SetUtilities.intersection(jacksonFive, new HashSet<String>()));
	}
	
	@Test
	public void testIntersectionDisjoint() {
		assertEquals(emptyStrings, 
				SetUtilities.intersection(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon")), 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}

	@Test
	public void testIntersectionOverlapping() {
		assertEquals(new HashSet<String>(Arrays.asList("randy")), 
				SetUtilities.intersection(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon", "randy")), 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}
	
	@Test
	public void testIntersectionSubset() {
		assertEquals(odds, 
				SetUtilities.intersection(zeroToNine, odds));
		assertEquals(evens, 
				SetUtilities.intersection(evens, zeroToNine));
	}
	
	@Test
	public void testIntersectionSubsetsUnmodified() {
		Set<Integer> copy = new HashSet<Integer>(zeroToNine);
		SetUtilities.intersection(zeroToNine, odds);
		SetUtilities.intersection(evens, zeroToNine);
		assertEquals(copy, zeroToNine);
	}

	@Test
	public void testIntersectionSameValues() {
		assertEquals(zeroToNine, 
				SetUtilities.intersection(zeroToNine, zeroToNine));
	}
	
	@Test
	public void testSetDifferenceBothEmpty() {
		assertEquals(emptyStrings, SetUtilities.setDifference(emptyStrings, emptyStrings));
	}

	@Test
	public void testSetDifferenceLeftEmpty() {
		assertEquals(emptyStrings, SetUtilities.setDifference(new HashSet<String>(), jacksonFive));
	}

	@Test
	public void testSetDifferenceRightEmpty() {
		assertEquals(jacksonFive, SetUtilities.setDifference(jacksonFive, new HashSet<String>()));
	}
	
	@Test
	public void testSetDifferenceDisjoint() {
		Set<String> left = new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon"));
		assertEquals(left, 
				SetUtilities.setDifference(left, 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}

	@Test
	public void testSetDifferenceOverlapping() {
		assertEquals(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon")), 
				SetUtilities.setDifference(new HashSet<String>(Arrays.asList("jackie", "jermaine", "marlon", "randy")), 
									new HashSet<String>(Arrays.asList("michael", "tito", "randy"))));
	}
	
	@Test
	public void testSetDifferenceSubset() {
		assertEquals(evens, 
				SetUtilities.setDifference(zeroToNine, odds));
		assertEquals(emptyInts, 
				SetUtilities.setDifference(evens, zeroToNine));
	}
	
	@Test
	public void testSetDifferenceSubsetsUnmodified() {
		Set<Integer> copy = new HashSet<Integer>(zeroToNine);
		SetUtilities.setDifference(zeroToNine, odds);
		SetUtilities.setDifference(evens, zeroToNine);
		assertEquals(copy, zeroToNine);
	}

	@Test
	public void testSetDifferenceSameValues() {
		assertEquals(emptyInts, 
				SetUtilities.setDifference(zeroToNine, zeroToNine));
	}
	
	@Test
	public void testJaccardBothEmpty() {
		assertEquals(1.0, SetUtilities.jaccardIndex(emptyInts, emptyInts), 0.0);
	}
	
	@Test
	public void testJaccardOneEmpty() {
		assertEquals(0.0, SetUtilities.jaccardIndex(emptyInts, zeroToNine), 0.0);
	}

	@Test
	public void testJaccardHalf() {
		assertEquals(0.5, SetUtilities.jaccardIndex(evens, zeroToNine), 0.0);
	}

	@Test
	public void testJaccardPartial() {
		assertEquals(0.1, SetUtilities.jaccardIndex(godfatherActors, jacksonFive), 0.0);
	}
}
