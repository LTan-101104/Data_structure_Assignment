/*
 * Copyright 2023 Marc Liberatore.
 */

package sorting;

import java.util.Arrays;
import java.util.Random;

public class SortingExercises {

    /**
     * Swap the values at a[i] and a[j].
     */
    static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Perform an in-place insertion sort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; (j > 0 && a[j - 1] > a[j]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Perform an in-place insertion sort of the range start (inclusive) 
     * through end (exclusive) on array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a, int start, int end) {
        for (int i = start + 1; i < end; i ++){
            for (int j = i; (j > start && a[j-1] > a[j]); j --){
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Perform a destructive mergesort on the array a.
    
     * The array will be in ascending order (least-to-greatest) after sorting; the original
     * values will be overwritten.
     * Additional array space will be allocated by this method.
     * 
     * For efficiency, this method will *insertion sort* any array of length less than 10.
     */
    public static void mergeSort(double[] a) {
        if (a.length <= 1) {
            return;
        }

        int mid = a.length / 2;

        double[] left = Arrays.copyOfRange(a, 0, mid);

        double[] right = Arrays.copyOfRange(a, mid, a.length);

        mergeSort(left);
        mergeSort(right);
    
        merge(a, left, right);

    }

    /**
     * Merge the sorted arrays l and r into the array a (overwriting its previous contents).
     */
    static void merge(double[] a, double[] l, double[] r) {
        // a is the result

        int i = 0, j = 0;

        while(i < l.length && j < r.length){
            if (l[i] < r[j]) {
                a[i + j] = l[i];
                i ++;
            }

            else {
                a[i + j] = r[j];
                j ++;
            }

        }

        if (i < l.length ){
            for (int temp = i; temp < l.length; temp ++){
                a[temp + j] = l[temp];
            }
        }

        if (j < r.length){
            for(int temp = j; temp < r.length;temp ++){
                a[temp + i] = r[temp];
            }
        }
    }

    /**
     * Perform an in-place quicksort on the array a.
     */
    static void quickSort(double[] a) {
        
        quickSort(a, 0, a.length);
    }

    /**
     * Perform an in-place quicksort on the range i (inclusive) to j 
     * (exclusive) of the array a.
     * 
     * For efficiency, this method will *insertion sort* any range of 
     * length less than 10.
     */
    static void quickSort(double[] a, int i, int j) {

        //base case, where the subarray is only 1 or zero, we have nothing to sort
        if(j - i <= 1) {
            return;
        }

        int curPivot = partition(a, i, j);
        quickSort(a, i, curPivot);
        quickSort(a, curPivot + 1, j);

    }

    /**
     * Perform an in-place partition on the  range i (inclusive) to j 
     * (exclusive) of the array a, returning the index of the pivot
     * after partitioning.
     * 
     * To cut down on worst case choices, this method will chose the 
     * pivot value at random from among the values in the range.
     * 
     * @return the index of the pivot after the partition
     */

    static int partition(double[] a, int i, int j) {
        
        // int first = i;
        // double pivot = a[i];
        // j = j - 1;
        // do {
        // while ((i < j) && (pivot >= a[i])) {
        //     i++;
        // }
        // while (pivot < a[j]) {
        //     j--;
        // }
        // if (i < j) {
        //     swap(a, i, j);
        // }
        // } while (i < j);
        //     swap(a, first, j);
        //     return j;


        double pivot = a[i];
        int first = i;
        j --;

        while (i < j){
            while ( a[i] <= pivot ){
                i ++;
            }

            while( a[j] > pivot){
                j --;
            }

            if (i < j) swap(a, i, j);

        }

        swap(a, first, j);

        return j;
        
    }
}