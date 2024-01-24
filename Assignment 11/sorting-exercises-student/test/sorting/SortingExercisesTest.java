/*
 * Copyright 2023 Marc Liberatore.
 */

package sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SortingExercisesTest {
    //  @Rule
    //  public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

    @Before
    public void setup() {
    }

    @Test
    public void testInsertionEmpty() throws Exception {
        double[] a = {};
        SortingExercises.insertionSort(a);
    }

    @Test
    public void testInsertionOne() throws Exception {
        double[] a = { 0.0 };
        double[] b = { 0.0 };
        SortingExercises.insertionSort(a);
        assertArrayEquals(b, a, 0);
    }

    @Test
    public void testInsertionTwoInOrder() throws Exception {
        double[] a = { 0.0 , 1.0};
        double[] b = { 0.0 , 1.0};
        SortingExercises.insertionSort(a);
        assertArrayEquals(b, a, 0);
    }

    @Test
    public void testInsertionTwo() throws Exception {
        double[] a = { 0.0, 1.0 };
        double[] b = { 0.0, 1.0 };
        SortingExercises.insertionSort(a);
        assertArrayEquals(b, a, 0);
    }

    @Test
    public void testInsertionThreeInOrder() throws Exception {
        double[] a = { 0.0, 1.0, 2.0 };
        double[] b = { 0.0, 1.0, 2.0 };
        SortingExercises.insertionSort(a);
        assertArrayEquals(b, a, 0);
    }


    @Test
    public void testInsertionThree() throws Exception {
        double[] a = { 0.0, 2.0, 1.0 };
        double[] b = { 0.0, 1.0, 2.0 };
        SortingExercises.insertionSort(a);
        assertArrayEquals(b, a, 0);
    }
    
    @Test
    public void testInsertion1000Random() throws Exception {
        double[] a = new double[10];
        double[] b = new double[10];
        Random r = new Random(0);

        for (int j = 0; j < 1000; j++) {
            for (int i = 0; i < a.length; i++) {
                a[i] = r.nextInt(100);
                b[i] = a[i];
            }
            SortingExercises.insertionSort(a);
            Arrays.sort(b);

            assertArrayEquals(b, a, 0);
        }
    }

    // @Test
    // public void testInsertionRangeAndOthers() throws Exception {
    //     fail("This test is intended to fail. Read the comment and then comment out this test.");
    //     // We did not provide you with tests for the ranged insertion sort (insertionSort(a, start, end)).
    //     // It would be a Good Idea to write tests for it, especially if your insertion-sort-using
    //     // mergesort or quicksort exhibits problems.
    //     // Similarly, we've given you only a small test for mergesort and none for quicksort.
    //     // Not a bad idea to practice and write some for those, too.
    //     // It's *fine* to copy/paste a test and change it into another test!
    // }

    @Test
    public void testMerge10() throws Exception {
        double[] a = { 1.0, 4.0, 6.0, 2.0, 0.0, 5.0, 7.0, 8.0, 9.0, 3.0 };
        double[] b = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 };

        SortingExercises.mergeSort(a);
        Arrays.sort(b);

        assertArrayEquals(b, a, 0);
    }

    @Test
    public void testQuickSort(){
        double[] a = { 1.0, 4.0, 6.0, 2.0, 0.0, 5.0, 7.0, 8.0, 9.0, 3.0 };
        double[] b = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 };

        SortingExercises.quickSort(a);
        Arrays.sort(b);

        assertArrayEquals(b, a, 0);
    }
    
}
