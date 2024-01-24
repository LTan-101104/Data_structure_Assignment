/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {
    /**
     * Returns true iff the subtree of a starting at index i is a max-heap.
     * @param a an array representing a mostly-complete tree, possibly a heap
     * @param i an index into that array representing a subtree rooted at i
     * @return true iff the subtree of a starting at index i is a max-heap
     */
    static boolean isHeap(double[] a, int i) {
        if (2*i + 1 > a.length - 1 && 2*i + 2 > a.length - 1) return true;

        return (a[i] > a[2*i + 1] && (2*i + 2 <= a.length - 1 && a[i] > a[2*i + 2]) && isHeap(a, 2*i + 1) && isHeap(a, 2*i + 2));
    }

    /**
     * Perform the heap siftdown operation on index i of the array a. 
     * 
     * This method assumes the subtrees of i are already valid max-heaps.
     * 
     * This operation is bounded by n (exclusive)! In a regular heap, 
     * n = a.length, but in some cases (for example, heapsort), you will 
     * want to stop the sifting at a particular position in the array. 
     * siftDown should stop before n, in other words, it should not 
     * sift down into any index great than (n-1).
     * 
     * @param a the array being sifted
     * @param i the index of the element to sift down
     * @param n the bound on the array (that is, where to stop sifting)
     */
    static void siftDown(double[] a, int i, int n) {
        
        int breakPoint = n - 1;

        while ( i <= breakPoint){
            int tempMax = i;

            if ( 2*i + 1 < n && a[2*i + 1] > a[tempMax]) tempMax = 2*i + 1;

            if (2*i + 2 < n && a[2*i + 2] > a[tempMax]) tempMax = 2*i + 2;

            if (tempMax == i) return; // position is valid and we return

            swap(a, tempMax, i);

            i = tempMax; 

        }

        
    }

    static void swap(double[] arr, int a, int b){
        double temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Heapify the array a in-place in linear time as a max-heap.
     * @param a an array of values
     */
    static void heapify(double[] a) {

        int i = 1, n = a.length;

        while (true){
            int curNonLeaf = (n/2) - i;
            if (curNonLeaf < 0) break; 
            siftDown(a, curNonLeaf, n);
            i ++;
        }
    }

    /**
     * Heapsort the array a in-place, resulting in the elements of
     * a being in ascending order.
     * @param a
     */
    static void heapSort(double[] a) {
        //first we gotta heapify this guy
        //remove the top element (max), replace by the last element (just like the remove method)
        //heapify the array again, then add the max to the end of the array

       heapify(a);

       int breakpoint = a.length - 1;

       while(breakpoint > 0){
        //from breakpoint to breakpoint - 1 is the sorted part
    
           swap(a, 0, breakpoint);
    
           siftDown(a, 0, breakpoint);

           breakpoint --;
       }

    }
    
    public static void main(String[] args) {
        Random r = new Random(0);
        int length = 15;
        double[] l = new double[length];
        for (int i = 0; i < length; i++) {
            l[i] = r.nextInt(20);
        }
        System.out.println(Arrays.toString(l));

        heapify(l);

        System.out.println(Arrays.toString(l));

        heapSort(l);

        System.out.println(Arrays.toString(l));
    }
}
