package solutions.exercise1;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractPairFinderTest;


import static org.junit.Assert.fail;


/**
 * Tests the methods of the class PairFinderImpl and the validity of its parameter.
 * @author G10T01
 * @version 1.1
 * @since 11-11-2020
 */
public class PairFinderTest extends AbstractPairFinderTest implements ExerciseSubmission {

    public PairFinderImpl sut;

    /**
     * Sets a test case
     */
    @Before
    public void setup(){
       sut = new PairFinderImpl(); // set a new instance from the class PairFinderImpl
    }


    /**
     *Tests the functionality of the method findConsumerProducerpairs
     */
    @Test
    public void testFindConsumerProducerPairs () {
            sut.findConsumerProducerPairs(graph1);
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