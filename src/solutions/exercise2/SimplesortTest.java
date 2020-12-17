package solutions.exercise2;


import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractSimplesortTest;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests all the methods of the class SimplesortImpl and the validity of its parameter.
 *
 * @author G10T01
 * @version 2.3.1
 * @since 16-11-2020
 */

public class SimplesortTest extends AbstractSimplesortTest implements ExerciseSubmission {


    /**
     * Tests whether an IllegalArgumentException is thrown for the method Simplesort.
     */

    @Override
    @Test
    public void testSimplesort_Parameters() {

        Integer[] x =  { 0, 1, 2, 3, 4, 5 };    // length is 6

        try {
            sut.simplesort(null, 1, 3);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.simplesort(x, -1, 3);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.simplesort(x,  3, -1);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.simplesort(x, 2, 7);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

        try {
            sut.simplesort(x, 7, 2);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }

    }


    /**
     *Tests the implementation of the method simplesort.
     */

    @Override
    @Test
    public void testSimplesort() {

        Integer[] input0 = new Integer[] { -1 }; // just one element in the array
        Integer[] expected0 = new Integer[] { -1 };
        //sut.simplesort(input0, 0, 1);
        assertThat("Array is not sorted.", input0, equalTo(expected0));

        Integer[] input1 = new Integer[]{ 0, -1 };
        Integer[] expected1 = new Integer[] { -1, 0 };
        sut.simplesort(input1, 0, input1.length - 1);
        assertThat("Array is not sorted.", input1, equalTo(expected1));

        Integer[] input2 = new Integer[] { 2, 5, 92, 1, 1 };
        Integer[] expected2 = new Integer[] { 1, 1, 2, 5, 92 };
        sut.simplesort(input2, 0, input2.length - 1);
        assertThat("Array is not sorted.", input2, equalTo(expected2));

        Integer[] input3 = new Integer[] { 0, 0, 0, 1, 1, -1, -1, 2, 2, 3, 4 };
        Integer[] expected3 = new Integer[] { -1, -1, 0, 0, 0, 1, 1, 2, 2, 3, 4 };
        sut.simplesort(input3, 0, input3.length - 1);
        assertThat("Array is not sorted.", input3, equalTo(expected3));

        Integer[] input4 = new Integer[] { 5, 4, 3, 2, 1, 0 };
        Integer[] expected4 = new Integer[] { 0, 1, 2, 3, 4, 5 };
        sut.simplesort(input4, 0, input4.length - 1);
        assertThat("Array is not sorted.", input4, equalTo(expected4));

        Integer[] input5 = new Integer[] { 3, 5, 0, 26, -6, -9, 1, 1, 0 };
        Integer[] expected5 = new Integer[] { -9, -6, 0, 0, 1, 1, 3, 5, 26};
        sut.simplesort(input5, 0, input5.length - 1);
        assertThat("Array is not sorted.", input5, equalTo(expected5));

        Integer[] input6 = new Integer[] { -9, -6, 0, 26, 5, 3, 1, 1, 0 };
        Integer[] expected6 = new Integer[] { -9, -6, 0, 0, 1, 1, 3, 5, 26};
        sut.simplesort(input6, 2, input6.length - 1);
        assertThat("Array is not sorted.", input6, equalTo(expected6));

        //only the use of "assertThat" is possible!!

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