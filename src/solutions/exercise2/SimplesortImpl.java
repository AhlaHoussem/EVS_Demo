package solutions.exercise2;


import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Simplesort;

import java.util.Comparator;

/**
 * sorts an unsorted Array by using the "simple sort" algorithm.
 *
 * @author G10T01
 * @version 2.2.1
 * @since 16-11-2020
 */


public class SimplesortImpl<T> implements Simplesort<T>, ExerciseSubmission {

    private Comparator<T> comparator;


    /**
     * The constructor initialises a SimpleSort object.
     *
     * @param comparator the given comparator
     */

    public SimplesortImpl(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is not allowed to be null.");
        }

        this.comparator = comparator;
    }


    /**
     * sorts an scrambled Array.
     *
     * @param arr the unsorted Array
     * @param left the position of the first element
     * @param right the position of the last element
     */

    @Override
    public void simplesort(T[] arr, int left, int right){
        if (arr == null) {
            throw new IllegalArgumentException("Array is not allowed to be null.");
        }
        if ((left < 0) || (left > right) || (right > arr.length -1 )) {
            throw new IllegalArgumentException("the positioning numbers are not valid");
        } else {

            if (arr.length > 1) {      // more than one element in the array
                for (int i = right - 1; i >= left; i--) {
                    for (int j = left; j <= i - 1; j++) {
                        if (comparator.compare(arr[i], arr[j]) < 0) {
                            Object tmp = arr[i]; // swipe arr[i] and arr[j]
                            arr[i] = arr[j];
                            arr[j] = (T) tmp;
                        }
                    }
                }
            }
        }

    }


    /**
     * Identifies the group.
     *
     * @return the group's number and its specific team
     */

    @Override
    public String getTeamIdentifier() {
        return "G10T01";
    }

}

