package solutions.exercise4;


import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;
import solutions.exercise3.ResidualGraphImpl;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Tests the functionality all the methods of the class FordFulkersonImpl and the validity of its parameter.
 *
 * @author G10T01
 * @version 4.2.0
 * @since 17-12-2020
 */

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {


    @Override
    @Test
    public void test_findPath1() {

    }

    @Override
    @Test
    public void test_findPath2() {

    }

    @Override
    @Test  //PASSED
    public void test_findPath_IsNull() {
        ResidualGraph<String> residual_Graph3 = new ResidualGraphImpl<String>(flowGraph3);
        assertNull("The path is null !!", sut.findPath("s","t", residual_Graph3));

    }

    @Override
    @Test  //PASSED
    public void test_findPath_ParameterStartIsNull() {
        ResidualGraph<String> residual_Graph1 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath(null, "t", residual_Graph1);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    @Override
    @Test  //PASS
    public void test_findPath_ParameterTargetIsNull() {
        ResidualGraph<String> residual_Graph1 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath("s", null, residual_Graph1);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    @Override
    @Test  //PASSED
    public void test_findPath_ParameterGraphIsNull() {
        try {
            sut.findPath("s", "t", null);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    //extra Test
    @Test   //PASS
    public void test_findPath_InvalidParameter() {
        ResidualGraph<String> residual_Graph1 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath("x", "y", residual_Graph1);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }

        try {
            sut.findPath("hi", "x", residual_Graph1);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }


    }






    @Override
    @Test
    public void test_augmentPath1() {

    }

    @Override
    @Test
    public void test_augmentPath2() {

    }

    @Override
    @Test  //PASSED
    public void test_augmentPath_ParameterNull() {
        try {
            sut.augmentPath(null);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }






    @Override
    @Test
    public void test_findMaxFlow_flowGraphA() {
    }


    @Override
    @Test
    public void test_findMaxFlow_flowGraphB() {

    }

    @Override
    @Test
    public void test_findMaxFlow_flowGraphC() {

    }

    @Override
    @Test  //PASS
    public void test_findMaxFlow_ParameterGraphIsNull() {
        try {
            sut.findPath("s", "t", null);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    @Override
    @Test  //PASS
    public void test_findMaxFlow_ParameterStartIsNull() {
        try {
            sut.findMaxFlow(flowGraph1, null, "t");
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    @Override
    @Test  //PASS
    public void test_findMaxFlow_ParameterTargetIsNull() {
        try {
            sut.findMaxFlow(flowGraph1, "s", null);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
    }

    @Override
    @Test  //PASSED
    public void test_findMaxFlow_ParameterTargetNotInGraph() {
        try {
            sut.findMaxFlow(flowGraph1, "x", "x");
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
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