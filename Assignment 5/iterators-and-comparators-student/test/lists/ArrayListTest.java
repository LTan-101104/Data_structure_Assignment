/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class ArrayListTest {
	// @Rule
	// public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testArrayListEmptyIterator() {
		List<String> l = new ArrayList<>();
		Iterator<String> i = l.iterator();
		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListIteratorOne() {
		List<String> l = new ArrayList<>();
		l.add("one");
		Iterator<String> i = l.iterator();

		assertTrue(i.hasNext());
		assertEquals("one", i.next());

		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListIteratorTwo() {
		List<String> l = new ArrayList<>();
		l.add("one");
		l.add("two");
		Iterator<String> i = l.iterator();

		assertTrue(i.hasNext());
		assertEquals("one", i.next());

		assertTrue(i.hasNext());
		assertEquals("two", i.next());

		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListEmptyIteratorForEach() {
		List<String> l = new ArrayList<>();
		for (String s : l) {
			fail(); // should never get here, as the list is empty!
		}
	}

	@Test
	public void testArrayListForEachTwo() {
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);

		Integer c = 1;
		for (Integer i : l) {
			assertEquals(c, i);
			c += 1;
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorException() {
		List<Integer> l = new ArrayList<>();
		Iterator<Integer> i = l.iterator();

		i.next();
	}
}
