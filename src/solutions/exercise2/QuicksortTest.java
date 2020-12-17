package solutions.exercise2;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;


/**
 * Tests all the methods of the class QuicksortImpl and the validity of its parameter.
 *
 * @author G10T01
 * @version 2.5.4
 * @since 17-11-2020
 */

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {


    /**
     *Tests the functionality of the method partition
     */

    @Override
    @Test
    public void testPartition() {

        subTestPartition(new Integer[] { 0, 0 }, 0, 1, 1);
        subTestPartition(new Integer[] { 2, 1, -1 }, 0, 2, 2);
        subTestPartition(new Integer[] { 0, 1, 2, -2 }, 0, 3, 2);
        subTestPartition(new Integer[] { 1, 5, 2, -5, 0 }, 0, 4, 3);
        subTestPartition(new Integer[] { 1, 1, 2, 2, 0, 0, 0 }, 0, 6, 5);
        subTestPartition(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 1, 7, 5);
        subTestPartition(new Integer[] { 5, 4, 3, 2, 1, 0, -1, -2, -3 }, 2, 4, 4);
        subTestPartition(new Integer[] { 1, 1, 4, 4, 2, 2, 3, 3, 0, 0, 50 }, 0, 10, 5);
        subTestPartition(new Integer[] { -1, 0, 1, 2, -3, -2, 26, 33, 100, 22 }, 0, 9, 1);
        subTestPartition(new Integer[] { 1, 2, 2, -1, 2, 2, 2, 3, 4, 5, 8, 8, 8, 9 }, 0, 13, 5);


    }

    /**
     * checks if the pivot is in the right position in the new sorted array.
     * checks if the elements in left side are less than the pivot and the elements in the right side greater than the pivot.
     *
     * @param input     a given array
     * @param left      The left boundary
     * @param right     The right boundary
     * @param expected  the result of calling the partition method
     */

    private void subTestPartition (Integer[] input, int left, int right, int expected) {

        int pivot = input[(left + right)/2];

        int current = sut.partition(input, left, right);
        assertTrue("the current results doesn't match with the expected one", current == expected );
        // check if the pivot is in the right position in the new sorted array

       // int pivot = input[(left + right)/2];

        //check if the elements on the left side of the array are less than the pivot
        for (int i = left; i < current; i++) {
            assertTrue("The left side contains values greater than the pivot.", input[i] <= (pivot));

        }

        //check if the elements on the right side of the array are greater than the pivot
        for (int j = current; j > right; j++) {
            assertTrue("The right side contains values less than the pivot.", input[j] >= (pivot));

        }

    }


    /**
     * Tests whether an IllegalArgumentException is thrown for the method partition.
     */

    @Override
    @Test
    public void testPartition_Parameters() {

        Integer[] x =  { 10, 1, 2, 3, 4, 5 };

        try {
            sut.partition(null, 1, 3);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.partition(x, -1, 3);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.partition(x, 3, -1);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.partition(x, 2, 7);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.partition(x, 7, 2);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

       try {
            sut.partition(x, 7, 9);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    /**
     * Tests the functionality of the method Quicksort.
     */

    @Override
    @Test
    public void testQuicksort() {

        Integer[] input0 = new Integer[]{ -1 };
        Integer[] expected0 = new Integer[] { -1 };
        //sut.quicksort(input0, 0, input0.length - 1);
        assertThat("Array is not sorted.", input0, equalTo(expected0) );

        Integer[] input1 = new Integer[]{ 0, -1 };
        Integer[] expected1 = new Integer[] { -1, 0 };
        sut.quicksort(input1, 0, input1.length - 1);
        assertThat("Array is not sorted.", input1, equalTo(expected1) );

        Integer[] input2 = new Integer[] { 2, 5, 92, 1, 1 };
        Integer[] expected2 = new Integer[] { 1, 1, 2, 5, 92 };
        sut.quicksort(input2, 0, input2.length - 1);
        assertThat("Array is not sorted.", input2, equalTo(expected2) );

        Integer[] input3 = new Integer[] { 0, 0, 0, 1, 1, -1, -1, 2, 2, 3, 4 };
        Integer[] expected3 = new Integer[] { -1, -1, 0, 0, 0, 1, 1, 2, 2, 3, 4 };
        sut.quicksort(input3, 0, input3.length - 1);
        assertThat("Array is not sorted.", input3, equalTo(expected3) );

        Integer[] input4 = new Integer[] { 5, 4, 3, 2, 1, 0 };
        Integer[] expected4 = new Integer[] { 0, 1, 2, 3, 4, 5 };
        sut.quicksort(input4, 0, input4.length - 1);
        assertThat("Array is not sorted.", input4, equalTo(expected4) );

        Integer[] input5 = new Integer[] { 3, 5, 0, 26, -6, -9, 1, 1, 0 };
        Integer[] expected5 = new Integer[] { -9, -6, 0, 0, 1, 1, 3, 5, 26};
        sut.quicksort(input5, 0, input5.length - 1);
        assertThat("Array is not sorted.", input5, equalTo(expected5) );

        Integer[] input6 = new Integer[] { -9, -6, 0, 26, 5, 3, 1, 1, 0 };
        Integer[] expected6 = new Integer[] { -9, -6, 0, 0, 1, 1, 3, 5, 26};
        sut.quicksort(input6, 3, input6.length - 1);
        assertThat("Array is not sorted.", input6, equalTo(expected6) );

        //only the use of "assertThat" is possible!!

    }

    /**
     * Tests whether an IllegalArgumentException is thrown for the method Quicksort.
     */

    @Override
    @Test
    public void testQuicksort_Parameters() {

        Integer[] x =  { 0, 1, 2, 3, 4, 5 };

        try {
            sut.quicksort(null, 1, 3);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.quicksort(x, -1, 3);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.quicksort(x, 3, -1);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.quicksort(x, 2, 7);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.quicksort(x, 4, 2);
            fail("No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
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
