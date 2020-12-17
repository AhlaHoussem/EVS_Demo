package solutions.exercise2;


import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

import java.util.Comparator;

/**
 * sorts an unsorted Array by using the "Teile & Herrsche" principe (Quicksort) .
 *
 * @author G10T01
 * @version 2.4.1
 * @since 16-11-2020
 */

public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission {

    private Comparator<T> comparator;

    /**
     * The constructor initialises a QuickSort object.
     *
     * @param comparator the comparator
     */

    public QuicksortImpl(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is not allowed to be null.");
        }

        this.comparator = comparator;

    }


    /**
     * This function takes the median element as pivot, place the pivot element at its correct position in sorted
     * array, and places all smaller elements (smaller than pivot) to left of pivot and all greater elements to right
     *  of pivot
     *
     * @param arr   the unsorted Array
     * @param left  the position of the first element
     * @param right the position of the last element
     * @return the index of the pivot in the array
     */

    @Override
    public int partition(T[] arr, int left, int right) {

        if (arr == null) {
            throw new IllegalArgumentException("Array is not allowed to be null.");
        }

        if ((left < 0) || (left > right) || (right > arr.length -1 )) {
            throw new IllegalArgumentException("the positioning numbers are not valid");
        } else {


            //inspired by the slides of the frontal event page 3
            int x = left;
            int y = right;

            T pivot = arr[( right + left ) / 2];

            while (x <= y) {
                while (comparator.compare(arr[x], pivot) < 0) {
                    x++;
                }
                while (comparator.compare(arr[y], pivot) > 0) {
                    y--;
                }
                if (x <= y) {
                    T tmp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = tmp;
                    x++;
                    y--;
                }
            }
        return x;
        }

    }


    /**
     * sorts an scrambled Array
     *
     * @param arr   the unsorted Array
     * @param left  the position of the first element
     * @param right the position of the last element
     */

    @Override
    public void quicksort(T[] arr, int left, int right) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is not allowed to be null.");
        }
        if ((left < 0) || (left > right) || (right > arr.length -1 ) ) {
            throw new IllegalArgumentException("the positioning numbers are not valid");
        }else {

            //inspired by the slides of the frontal event page 3

            if (left < right) {

                int position = partition(arr, left, right);
                quicksort(arr, left, position - 1);  // sorting the left side of the array
                quicksort(arr, position, right);          // sorting the right side of the array

            }
        }

    }


    /**
     * Identifies the group
     *
     * @return the group's number and its specific team
     */

    @Override
    public String getTeamIdentifier() {
        return "G10T01";
    }

}


