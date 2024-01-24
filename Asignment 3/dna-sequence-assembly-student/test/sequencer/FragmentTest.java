/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class FragmentTest {

	// @Rule
	// public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testValidConstructor() {
		Fragment f = new Fragment("GCAT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConstructor1() {
		Fragment f = new Fragment("MARC");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConstructor2() {
		Fragment f = new Fragment("G C A T");
	}

	@Test
	public void testLength1() {
		Fragment f = new Fragment("G");
		assertEquals(1, f.length());
	}

	@Test
	public void testLength5() {
		Fragment f = new Fragment("GGCAT");
		assertEquals(5, f.length());
	}

	@Test
	public void testToString() {
		Fragment f = new Fragment("GCAT");
		assertEquals("GCAT", f.toString());
	}

	@Test
	public void testEqualsTrue1() {
		Fragment f = new Fragment("GCAT");
		assertTrue(f.equals(f));
	}

	@Test
	public void testEqualsTrue2() {
		Fragment f = new Fragment(new String("GCAT"));
		Fragment g = new Fragment(new String("GCAT"));
		assertTrue(f.equals(g));
		assertTrue(g.equals(f));
	}

	@Test
	public void testNotEqualsNull() {
		Fragment f = new Fragment("GCAT");
		assertFalse(f.equals(null));
	}

	@Test
	public void testNotEqualsObject() {
		Fragment f = new Fragment("GCAT");
		assertFalse(f.equals(new Object()));
	}

	@Test
	public void testNotEqual() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("TACG");
		assertFalse(f.equals(g));
	}

	@Test
	public void testNoOverlap() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("CGTA");
		assertEquals(0, f.calculateOverlap(g));
	}

	@Test
	public void testOneOverlap() {
		Fragment f = new Fragment("GGGA");
		Fragment g = new Fragment("AGGG");
		assertEquals(1, f.calculateOverlap(g));
	}

	@Test
	public void testTwoOverlap() {
		Fragment f = new Fragment("GGAC");
		Fragment g = new Fragment("ACGG");
		assertEquals(2, f.calculateOverlap(g));
	}

	@Test
	public void testTwoOverlapBounds() {
		Fragment f = new Fragment("TAC");
		Fragment g = new Fragment("ACAGAT");
		assertEquals(2, f.calculateOverlap(g));
	}

	@Test
	public void testTwoOverlapReverseBounds() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("AT");
		assertEquals(2, f.calculateOverlap(g));
	}

	@Test
	public void testThreeOverlap() {
		Fragment f = new Fragment("GGAAC");
		Fragment g = new Fragment("AACGG");
		assertEquals(3, f.calculateOverlap(g));
	}

	@Test
	public void testOverlapPastBounds() {
		Fragment f = new Fragment("GGAA");
		Fragment g = new Fragment("AAGGAA");
		assertEquals(2, f.calculateOverlap(g));
	}

	@Test
	public void testMultipleOverlap() {
		// i reach the end before j
		Fragment f = new Fragment("GGAACA");
		Fragment g = new Fragment("AACAGG");
		assertEquals(4, f.calculateOverlap(g));
	}

	@Test
	public void testNoMerge() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("CGTA");
		assertEquals(new Fragment("GCATCGTA"), f.mergedWith(g));
	}

	@Test
	public void testOneMerge() {
		Fragment f = new Fragment("GGGA");
		Fragment g = new Fragment("AGGG");
		assertEquals(new Fragment("GGGAGGG"), f.mergedWith(g));
	}

	@Test
	public void testTwoMerge() {
		Fragment f = new Fragment("GGAC");
		Fragment g = new Fragment("ACGG");
		assertEquals(new Fragment("GGACGG"), f.mergedWith(g));
	}

	@Test
	public void testSameMergeLength() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("GCAT");
		assertEquals(4, f.calculateOverlap(g));
	}

	@Test
	public void testSameMerge() {
		Fragment f = new Fragment("GCAT");
		Fragment g = new Fragment("GCAT");
		assertEquals(new Fragment("GCAT"), f.mergedWith(g));
	}

	@Test
	public void testThreeMerge() {
		Fragment f = new Fragment("GGAAC");
		Fragment g = new Fragment("AACGG");
		assertEquals(new Fragment("GGAACGG"), f.mergedWith(g));
	}

	@Test
	public void testMultipleMerge() {
		Fragment f = new Fragment("GGAACA");
		Fragment g = new Fragment("AACAGG");
		assertEquals(new Fragment("GGAACAGG"), f.mergedWith(g));
	}
}
