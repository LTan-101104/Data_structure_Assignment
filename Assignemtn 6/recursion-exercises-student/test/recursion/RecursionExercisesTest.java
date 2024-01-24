package recursion;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


import static recursion.RecursionExercises.*;

public class RecursionExercisesTest {
    // @Rule
    // public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

    @Test
    public void testSumOfDigitsIterative() {
        assertEquals(0, sumOfDigitsIterative(0));
        assertEquals(1, sumOfDigitsIterative(1));
        assertEquals(3, sumOfDigitsIterative(12));
        assertEquals(6, sumOfDigitsIterative(123));
        assertEquals(10, sumOfDigitsIterative(1234));
        assertEquals(1, sumOfDigitsIterative(-1));
        assertEquals(3, sumOfDigitsIterative(-21));
        assertEquals(6, sumOfDigitsIterative(-321));
        assertEquals(10, sumOfDigitsIterative(-1234));
    }

    @Test
    public void testSumOfDigitsRecursive() {
        assertEquals(0, sumOfDigitsRecursive(0));
        assertEquals(1, sumOfDigitsRecursive(1));
        assertEquals(3, sumOfDigitsRecursive(12));
        assertEquals(6, sumOfDigitsRecursive(123));
        assertEquals(10, sumOfDigitsRecursive(1234));
        assertEquals(1, sumOfDigitsRecursive(-1));
        assertEquals(3, sumOfDigitsRecursive(-21));
        assertEquals(6, sumOfDigitsRecursive(-321));
        assertEquals(10, sumOfDigitsRecursive(-1234));
    }

    @Test
    public void testSize() {
        final Node<Character> a = new Node<>('a');
        final Node<Character> b = new Node<>('b', a);
        final Node<Character> c = new Node<>('c', b);
        final Node<Character> d = new Node<>('d', c);

        assertEquals(0, size(a.next));
        assertEquals(1, size(a));
        assertEquals(2, size(b));
        assertEquals(3, size(c));
        assertEquals(4, size(d));
    }

    @Test
    public void testGetNode() {
        final Node<Character> a = new Node<>('a');
        final Node<Character> b = new Node<>('b', a);
        final Node<Character> c = new Node<>('c', b);
        final Node<Character> d = new Node<>('d', c);

        assertEquals(null, getNode(null, 0));
        assertEquals(null, getNode(a, 1));
        assertEquals(a, getNode(a, 0));

        assertEquals(b, getNode(b, 0));
        assertEquals(a, getNode(b, 1));
        assertEquals(null, getNode(b, 2));

        assertEquals(c, getNode(c, 0));
        assertEquals(b, getNode(c, 1));
        assertEquals(a, getNode(c, 2));
        assertEquals(null, getNode(c, 3));

        assertEquals(d, getNode(d, 0));
        assertEquals(c, getNode(d, 1));
        assertEquals(b, getNode(d, 2));
        assertEquals(a, getNode(d, 3));
        assertEquals(null, getNode(d, 4));
    }
}
