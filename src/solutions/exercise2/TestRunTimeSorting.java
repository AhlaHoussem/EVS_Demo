package solutions.exercise2;

import com.google.common.base.Stopwatch;
import org.junit.Before;
import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractTestRunTimeSorting;
import org.sopra.api.exercises.exercise2.Quicksort;
import org.sopra.api.exercises.exercise2.Simplesort;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests and compares the duration of excution of both the classes SimplesortImpl and QuicksortImpl.
 *
 * @author G10T01
 * @version 2.6.3
 * @since 17-11-2020
 */


public class TestRunTimeSorting extends AbstractTestRunTimeSorting implements ExerciseSubmission {

    private Quicksort<Integer> sutquick;
    private Simplesort<Integer> sutsimple;
    private Integer[] sorted;
    private Integer[] unsorted;
    private Stopwatch stopwatch ;


    /**
     * Sets the frame of test.
     */

    @Override
    @Before
    public void setup() {

        int length = 10;
        this.sutquick = new QuicksortImpl<>(comparator);
        this.sutsimple = new SimplesortImpl<>(comparator);
        this.sorted = createOrderedIntArray(length);
        this.unsorted = createShuffledIntArray(length);
        this.stopwatch = Stopwatch.createUnstarted();

    }


    /**
     * calculates the Runtime of the simplesort methods.
     */

    @Override
    @Test
    public void simplesortRunTimeTest() {

        stopwatch.start();
        sutsimple.simplesort(unsorted, 0, unsorted.length-1);
        stopwatch.stop();
        long timeElapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
        System.out.println(" Time elapsed for sorting unsorted array with SimpleSort = " + timeElapsed + " ms ");
        assertArrayEquals(unsorted, sorted);

        //by length 10, the value of the time elapsed is : 82ms
        //by length 50, the value of the time elapsed is : 583ms
        //by length 100, the value of the time elapsed is : 784ms
        //by length 1000, the value of the time elapsed is : 22724ms

    }


    /**
     * calculates the Runtime of the quicksort methods.
     */

    @Override
    @Test
    public void quicksortRunTimeTest() {

        stopwatch.start();
        sutquick.quicksort(unsorted, 0, unsorted.length-1);
        stopwatch.stop();
        long timeElapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
        System.out.println(" Time elapsed for sorting unsorted array with QuickSort = " + timeElapsed + " ms ");
        assertArrayEquals(sorted, unsorted);

        //by length 10, the value of the time elapsed is : 68ms
        //by length 50, the value of the time elapsed is : 241ms
        //by length 100, the value of the time elapsed is : 458ms
        //by length 1000, the value of the time elapsed is : 2388ms

        //  an obviuos conclusion : Quicksorts methode is obviously faster than the simplesort its inner loop
        //  can be efficiently implemented on most architectures, and in most real-world data,
        //  it is possible to make design choices that minimize the probability of requiring quadratic time
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
