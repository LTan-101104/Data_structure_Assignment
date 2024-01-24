package sorting;

import java.util.Arrays;

public class mainProgram {
    public static void main(String[] args){
        double[] a = { 1.0, 2.0 };

        SortingExercises.mergeSort(a);


        for (double var : a) {
            System.out.println(var);
        }

    }
}
