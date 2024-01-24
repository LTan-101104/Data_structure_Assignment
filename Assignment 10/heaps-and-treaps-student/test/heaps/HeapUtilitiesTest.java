/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class HeapUtilitiesTest {

    //  @Rule
    //  public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds
    @Before
    public void setup() {
    }

    @Test
    public void testSiftDownOne() throws Exception {
        double[] a = new double[1];
        double[] t = new double[1];
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownTwo() throws Exception {
        double[] a = { 0.0, 1.0 };
        double[] t = { 1.0, 0.0 };
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownTwoNoop() throws Exception {
        double[] a = { 1.0, 0.0 };
        double[] t = { 1.0, 0.0 };
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeLeft() throws Exception {
        double[] a = { 0.0, 2.0, 1.0 };
        double[] t = { 2.0, 0.0, 1.0 };
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeRight() throws Exception {
        double[] a = { 0.0, 1.0, 2.0 };
        double[] t = { 2.0, 1.0, 0.0 };
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeNoop() throws Exception {
        double[] a = { 2.0, 1.0, 0.0 };
        double[] t = { 2.0, 1.0, 0.0 };
        HeapUtilities.siftDown(a, 0, a.length);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeLeftBounded() throws Exception {
        double[] a = { 0.0, 2.0, 1.0, 3.0 };
        double[] t = { 2.0, 0.0, 1.0, 3.0 };
        HeapUtilities.siftDown(a, 0, 3);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeRightBounded() throws Exception {
        double[] a = { 0.0, 1.0, 2.0, 3.0 };
        double[] t = { 2.0, 1.0, 0.0, 3.0 };
        HeapUtilities.siftDown(a, 0, 3);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testSiftDownThreeNoopBounded() throws Exception {
        double[] a = { 2.0, 1.0, 0.0, 3.0 };
        double[] t = { 2.0, 1.0, 0.0, 3.0 };
        HeapUtilities.siftDown(a, 0, 3);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testHeapifyOne() throws Exception {
        double[] a = new double[1];
        double[] t = new double[1];
        HeapUtilities.heapify(a);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testHeapifyTwo() throws Exception {
        double[] a = { 0.0, 1.0 };
        double[] t = { 1.0, 0.0 };
        HeapUtilities.heapify(a);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testHeapifyTwoNoop() throws Exception {
        double[] a = { 1.0, 0.0 };
        double[] t = { 1.0, 0.0 };
        HeapUtilities.heapify(a);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testHeapifyThree() throws Exception {
        double[] a = { 0.0, 1.0, 2.0 };
        double[] t = { 2.0, 1.0, 0.0 };
        HeapUtilities.heapify(a);
        assertArrayEquals(t, a, 0);
    }

    @Test
    public void testHeapifySeven() throws Exception {
        double[] a = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
        double[] t = { 6.0, 4.0, 5.0, 3.0, 1.0, 0.0, 2.0 };
        HeapUtilities.heapify(a);
        assertArrayEquals(t, a, 0);
    }

    // @Test
    // public void testIsHeapWarning() throws Exception {        
    //     fail("This test is intended to fail. Read the comment and then comment out this test.");
    //     // We are not providing you with exhaustive tests for your isHeap code.
    //     // Nor do you *have to* implement it. But your local version of testHeapify100Randomized
    //     // won't pass until you do. (The Gradescope test will pass if your heapify code
    //     // is correct -- it doesn't use your version of isHeap.)
    // }

    @Test
    public void testHeapify100Randomized() throws Exception {
        final int length = 15;
        for (int seed = 0; seed < 100; seed++) {
            // the seed controls the rest of the random choices in this method
            Random r = new Random(seed);

            List<Integer> l = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                l.add(i);
            }
            Collections.shuffle(l, r);
            double[] a = new double[length];
            for (int i = 0; i < length; i++) {
                a[i] = l.get(i);
            }
            a[0] = -1; // force a non-heap to start

            assertFalse(HeapUtilities.isHeap(a, 0));

            HeapUtilities.heapify(a);

            assertTrue(HeapUtilities.isHeap(a, 0));
        }
    }

    private static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testHeapsort100Randomized() throws Exception {
        final int length = 15;
        for (int seed = 0; seed < 100; seed++) {
            // the seed controls the rest of the random choices in this method
            Random r = new Random(seed);

            List<Integer> l = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                l.add(i);
            }
            Collections.shuffle(l, r);
            double[] a = new double[length];
            for (int i = 0; i < length; i++) {
                a[i] = l.get(i);
            }
            a[0] = length; // force unsorted to start

            assertFalse(isSorted(a));

            HeapUtilities.heapSort(a);

            assertTrue(isSorted(a));
        }
    }

}