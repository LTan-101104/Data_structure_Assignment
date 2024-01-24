/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LinkedListTest {
    // @Rule
    // public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

    @Test
    public void testLinkedListConstructor() {
        List<Integer> l = new LinkedList<>();
    }

    @Test
    public void testLinkedListEmptyEquals() {
        List<Integer> l = new LinkedList<>();
        List<Integer> m = new LinkedList<>();
        assertTrue(l.equals(m));
    }

    @Test
    public void testLinkedListOneEmptyUnequal() {
        List<Integer> l = new LinkedList<>();
        List<Integer> m = new LinkedList<>();
        l.add(43);
        assertFalse(l.equals(m));
    }

    @Test
    public void testLinkedListOneEqual() {
        List<Integer> l = new LinkedList<>();
        List<Integer> m = new LinkedList<>();
        l.add(43);
        m.add(43);
        assertTrue(l.equals(m));
    }

    @Test
    public void testLinkedListOneUnequal() {
        List<Integer> l = new LinkedList<>();
        List<Integer> m = new LinkedList<>();
        l.add(43);
        m.add(17);
        assertFalse(l.equals(m));
    }

    @Test
    public void testLinkedListThreeEqual() {
        List<String> l = new LinkedList<>();
        List<String> m = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            l.add("testLinkedList" + i);
            m.add("testLinkedList" + i);
            assertTrue(l.equals(m));
        }
    }

    @Test
    public void testLinkedListThreeUnequal() {
        List<String> l = new LinkedList<>();
        List<String> m = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            l.add("testLinkedList" + i);
            m.add("testLinkedList" + i);
            assertTrue(l.equals(m));
        }
        l.add("foo");
        m.add("bar");
        assertFalse(l.equals(m));
    }

    @Test
    public void testLinkedListEmptySize() {
        List<Integer> l = new LinkedList<>();
        assertEquals(0, l.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListAddLowerBound() {
        List<Integer> l = new LinkedList<>();
        l.add(78);
        l.add(-1, 77);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListAddEmptyUpperBound() {
        List<Integer> l = new LinkedList<>();
        l.add(1, 77);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListAddUpperBound() {
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            l.add(i);
        }
        l.add(6, 6);
    }

    @Test
    public void testLinkedListAppend() {
        List<String> l = new LinkedList<>();
        l.add(0, "a");
        l.add(1, "b");
        l.add(2, "c");
        assertEquals("a", l.get(0));
        assertEquals("b", l.get(1));
        assertEquals("c", l.get(2));
    }

    @Test
    public void testLinkedListAddToEmptySize() {
        List<Integer> l = new LinkedList<>();
        l.add(42);
        assertEquals(1, l.size());
    }

    @Test
    public void testLinkedListAddToEmptyThenGet() {
        List<String> l = new LinkedList<>();
        l.add("foo");
        assertEquals("foo", l.get(0));
    }

    @Test
    public void testLinkedListAddLots() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            l.add("testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(i));
        }
    }

    @Test
    public void testLinkedListAddAtFrontEmpty() {
        List<String> l = new LinkedList<>();
        l.add(0, "foo");
        assertEquals("foo", l.get(0));
    }

    @Test
    public void testLinkedListAddLotsAtFront() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            l.add(0, "testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(0));
        }
    }

    @Test
    public void testLinkedListAddThenRemove() {
        List<String> l = new LinkedList<>();
        l.add("foo");
        assertEquals("foo", l.get(0));
        assertEquals(1, l.size());

        String result = l.remove(0);
        assertEquals("foo", result);
        assertEquals(0, l.size());
    }

    @Test
    public void testLinkedListAddThenRemoveTwo() {
        List<String> l = new LinkedList<>();
        l.add("foo");
        assertEquals("foo", l.get(0));
        assertEquals(1, l.size());

        l.add("bar");
        assertEquals("foo", l.get(0));
        assertEquals("bar", l.get(1));
        assertEquals(2, l.size());

        String result = l.remove(1);
        assertEquals("bar", result);
        assertEquals(1, l.size());

        result = l.remove(0);
        assertEquals("foo", result);
        assertEquals(0, l.size());
    }

    @Test
    public void testLinkedListAddThenRemoveTwoFrontFirst() {
        List<String> l = new LinkedList<>();
        l.add("foo");
        assertEquals("foo", l.get(0));
        assertEquals(1, l.size());

        l.add("bar");
        assertEquals("foo", l.get(0));
        assertEquals("bar", l.get(1));
        assertEquals(2, l.size());

        String result = l.remove(0);
        assertEquals("foo", result);
        assertEquals(1, l.size());

        result = l.remove(0);
        assertEquals("bar", result);
        assertEquals(0, l.size());
    }

    @Test
    public void testLinkedListEqualityAfterRemove() {
        List<Integer> l = new LinkedList<>();
        List<Integer> m = new LinkedList<>();
        l.add(43);
        m.add(43);
        assertTrue(l.equals(m));

        l.add(100);
        m.add(-55);
        assertFalse(l.equals(m));

        l.remove(1);
        m.remove(1);
        assertTrue(l.equals(m));
    }

    @Test
    public void testLinkedListAddAndRemoveMany() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            l.add("testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(i));
        }

        assertEquals(1000, l.size());

        for (int i = 999; i >= 0; i--) {
            String removed = l.remove(i);
            assertEquals("testLinkedList" + i, removed);
        }

        assertEquals(0, l.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListRemoveLowerBound() {
        List<Integer> l = new LinkedList<>();
        l.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListRemoveUpperBoundEmpty() {
        List<Integer> l = new LinkedList<>();
        l.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListRemoveUpperBound() {
        List<Integer> l = new LinkedList<>();
        l.add(1999);
        l.remove(1);
    }

    @Test
    public void testLinkedListSetOne() {
        List<String> l = new LinkedList<>();
        l.add("foo");
        assertEquals("foo", l.get(0));

        l.set(0, "bar");
        assertEquals("bar", l.get(0));
    }

    @Test
    public void testLinkedListSetFirst() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            l.add("testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(i));
        }
        l.set(0, "banana");
        assertEquals("banana", l.get(0));
        for (int i = 1; i < 100; i++) {
            assertEquals("testLinkedList" + i, l.get(i));
        }
    }

    @Test
    public void testLinkedListSetMiddle() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            l.add("testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(i));
        }
        l.set(49, "banana");
        for (int i = 0; i < 100; i++) {
            if (i == 49) {
                assertEquals("banana", l.get(i));
            } else {
                assertEquals("testLinkedList" + i, l.get(i));
            }
        }
    }

    @Test
    public void testLinkedListSetEnd() {
        List<String> l = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            l.add("testLinkedList" + i);
            assertEquals("testLinkedList" + i, l.get(i));
        }
        l.set(99, "banana");
        for (int i = 0; i < 99; i++) {
            assertEquals("testLinkedList" + i, l.get(i));
        }
        assertEquals("banana", l.get(99));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListSetLowerBound() {
        List<Integer> l = new LinkedList<>();
        l.set(-1, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListSetUpperBoundEmpty() {
        List<Integer> l = new LinkedList<>();
        l.set(0, 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListSetUpperBound() {
        List<Integer> l = new LinkedList<>();
        l.add(1999);
        l.set(1, 2000);
    }

    @Test
    public void testLinkedListIndexOfEmpty() {
        List<Integer> l = new LinkedList<>();
        assertEquals(-1, l.indexOf(1337));
    }

    @Test
    public void testLinkedListIndexOfFirst() {
        List<Integer> l = new LinkedList<>();
        l.add(1337);
        l.add(1337);
        assertEquals(0, l.indexOf(1337));
    }

    @Test
    public void testLinkedListIndexOfLast() {
        List<Integer> l = new LinkedList<>();
        l.add(1335);
        l.add(1336);
        l.add(1337);
        assertEquals(2, l.indexOf(1337));
    }

    @Test
    public void testLinkedListIndexOfMissing() {
        List<Integer> l = new LinkedList<>();
        l.add(1335);
        l.add(1336);
        l.add(1337);
        assertEquals(-1, l.indexOf(1338));
    }
}
