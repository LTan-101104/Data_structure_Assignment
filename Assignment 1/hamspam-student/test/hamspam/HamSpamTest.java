/*
 * Copyright 2021 Marc Liberatore.
 */

package hamspam;

import static org.junit.Assert.*;

import org.junit.Test;

//!This is basically where we will keep all the test file

public class HamSpamTest {
	private final HamSpam hamspamThreeFour = new HamSpam(3, 4);
	private final HamSpam hamspamTwoFive = new HamSpam(2, 5);
	private final HamSpam hamspamThreeSix = new HamSpam(6, 3);

	@Test
	public void testNotHamOrSpamGetValue() {
		assertEquals("getValue returns incorrect value", "2",
				hamspamThreeFour.getValue(2));
	}

	@Test
	public void testNotHamOrSpamGetValueAlt() {
		assertEquals("getValue returns incorrect value", "3",
				hamspamTwoFive.getValue(3));
	}

	@Test
	public void testHamOrSpamGetValue() {
		assertEquals("getValue returns incorrect value", "ham",
				hamspamThreeFour.getValue(3));
		assertEquals("getValue returns incorrect value", "spam",
				hamspamThreeFour.getValue(4));
	}

	@Test
	public void testHamOrSpamMultipleGetValue() {
		assertEquals("getValue returns incorrect value", "ham",
				hamspamThreeFour.getValue(9));
		assertEquals("getValue returns incorrect value", "spam",
				hamspamThreeFour.getValue(8));
	}

	@Test
	public void testHamAndSpamGetValue() {
		assertEquals("getValue returns incorrect value",
				"hamspam", hamspamThreeFour.getValue(12));
	}

	@Test
	public void testGetValuesSimple() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"1", "2", "ham", "spam" }, hamspamThreeFour.getValues(1, 4));
	}

	@Test
	public void testGetValuesOffset() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"2", "ham", "spam", "5", "ham" },
				hamspamThreeFour.getValues(2, 6));
	}

	@Test
	public void testNotHamOrSpamGetValueShared() {
		assertEquals("getValue returns incorrect value", "7",
				hamspamThreeSix.getValue(7));
	}

	@Test
	public void testHamOrSpamMultipleGetValueShared() {
		assertEquals("getValue returns incorrect value", "spam",
				hamspamThreeSix.getValue(9));
	}

	@Test
	public void testHamAndSpamGetValueShared() {
		assertEquals("getValue returns incorrect value",
				"hamspam", hamspamThreeSix.getValue(6));
	}

	@Test
	public void testHamOrSpamGetValueAlt() {
		assertEquals("getValue returns incorrect value", "ham",
				hamspamTwoFive.getValue(2));
		assertEquals("getValue returns incorrect value", "ham",
				hamspamTwoFive.getValue(4));
		assertEquals("getValue returns incorrect value", "spam",
				hamspamTwoFive.getValue(5));
	}

	@Test
	public void testHamAndSpamGetValueAlt() {
		assertEquals("getValue returns incorrect value", "hamspam",
				hamspamTwoFive.getValue(10));
	}

	@Test
	public void testGetValuesAlt() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"1", "ham", "3", "ham", "spam" },
				hamspamTwoFive.getValues(1, 5));

		assertArrayEquals("getValues returns incorrect values", new String[] {
				"7", "ham", "9", "hamspam", "11" },
				hamspamTwoFive.getValues(7, 11));
	}

	@Test
	public void testGetValuesShared() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"spam", "4", "5", "hamspam" }, hamspamThreeSix.getValues(3, 6));
	}
}
