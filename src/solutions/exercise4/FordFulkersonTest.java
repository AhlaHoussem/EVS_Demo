package solutions.exercise4;


import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;
import solutions.exercise3.ResidualGraphImpl;

import java.util.*;

import static org.junit.Assert.*;


/**
 * Tests the functionality all the methods of the class FordFulkersonImpl and the validity of its parameter.
 *
 * @author G10T01
 * @version 4.2.4
 * @since 17-12-2020
 */

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {


    @Override
    @Test
    public void test_findPath1() {

        ResidualGraph<String> residual_Graph1 = new ResidualGraphImpl<String>(flowGraph1);
        assertNotNull("The graph is null ", residual_Graph1);

        Deque<ResidualEdge<String>> firstpath = sut.findPath("s", "t", residual_Graph1);
        assertNotNull("The path is null ", firstpath);
        assertTrue("It's not the shortest path", firstpath.size() == 3);


        // 4) d->t, a->d, s->a;

        List<ResidualEdge<String>> shortestPath = new LinkedList<>();
        shortestPath.add(residual_Graph1.getEdge("d", "t"));
        shortestPath.add(residual_Graph1.getEdge("a", "d"));
        shortestPath.add(residual_Graph1.getEdge("s", "a"));

        for (int i = 0; i < shortestPath.size(); i++) {
            assertNotNull(firstpath.peekFirst()); // not null
            assertEquals(shortestPath.get(i), firstpath.pollFirst()); // equal the expected
        }

    }


    @Override
    @Test
    public void test_findPath2() {

        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph2);
        assertNotNull("The graph is null ", residual_Graph2);

        Deque<ResidualEdge<String>> firstpath = sut.findPath("s", "t", residual_Graph2);
        assertNotNull("The path is null ", firstpath);
        assertTrue("It's not the shortest path", firstpath.size() == 3);


        // 4) e->t, b->e, s->b;

        List<ResidualEdge<String>> shortestPath = new LinkedList<>();
        shortestPath.add(residual_Graph2.getEdge("e", "t"));
        shortestPath.add(residual_Graph2.getEdge("b", "e"));
        shortestPath.add(residual_Graph2.getEdge("s", "b"));

        for (int i = 0; i < shortestPath.size(); i++) {
            assertNotNull(firstpath.peekFirst()); // not null
            assertEquals(shortestPath.get(i), firstpath.pollFirst()); // equal the expected path
        }


    }

    @Override
    @Test  //PASSED
    public void test_findPath_IsNull() {
        ResidualGraph<String> residual_Graph3 = new ResidualGraphImpl<String>(flowGraph3);
        assertNull("The path is null !!", sut.findPath("s", "t", residual_Graph3));

    }

    @Override
    @Test  //PASSED
    public void test_findPath_ParameterStartIsNull() {
        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath(null, "t", residual_Graph2);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }
        // assertThrows("",IllegalArgumentException.class, sut.findPath(null, "t", residual_Graph2));
    }

    @Override
    @Test  //PASS
    public void test_findPath_ParameterTargetIsNull() {
        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath("s", null, residual_Graph2);
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
        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph1);
        try {
            sut.findPath("x", "y", residual_Graph2);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }

        try {
            sut.findPath("hi", "x", residual_Graph2);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }


    }


    @Override
    @Test //PASSED
    public void test_augmentPath1() {

        // s-b-a-d-t
        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph1);
        assertNotNull("The graph is null ", residual_Graph2);

        Deque<ResidualEdge<String>> firstpath = new ArrayDeque<>();
        assertNotNull("The path is null", firstpath);
        assertEquals(0, firstpath.size());

        firstpath.add(residual_Graph2.getEdge("s", "b"));
        firstpath.add(residual_Graph2.getEdge("b", "a"));
        firstpath.add(residual_Graph2.getEdge("a", "d"));
        firstpath.add(residual_Graph2.getEdge("d", "t"));

        sut.augmentPath(firstpath); //capacity = 3

        assertEquals(residual_Graph2.getEdge("s", "b").getCapacity(), 4);
        assertNotNull(residual_Graph2.getEdge("s", "b"));
        assertEquals(residual_Graph2.getEdge("b", "s").getCapacity(), 10);
        assertNotNull(residual_Graph2.getEdge("b", "s"));

        assertEquals(residual_Graph2.getEdge("s", "a").getCapacity(), 10);
        assertNotNull(residual_Graph2.getEdge("s", "a"));
        assertEquals(residual_Graph2.getEdge("a", "s").getCapacity(), 10);
        assertNotNull(residual_Graph2.getEdge("a", "s"));

        assertEquals(residual_Graph2.getEdge("a", "b").getCapacity(), 6);
        assertNotNull(residual_Graph2.getEdge("a", "b"));
        assertEquals(residual_Graph2.getEdge("b", "a").getCapacity(), 0);
        assertNotNull(residual_Graph2.getEdge("b", "a"));

        assertEquals(residual_Graph2.getEdge("a", "d").getCapacity(), 5);
        assertNotNull(residual_Graph2.getEdge("a", "d"));
        assertEquals(residual_Graph2.getEdge("d", "a").getCapacity(), 11);
        assertNotNull(residual_Graph2.getEdge("d", "a"));

        assertEquals(residual_Graph2.getEdge("d", "c").getCapacity(), 11);
        assertNotNull(residual_Graph2.getEdge("d", "c"));
        assertEquals(residual_Graph2.getEdge("c", "d").getCapacity(), 11);
        assertNotNull(residual_Graph2.getEdge("c", "d"));

        assertEquals(residual_Graph2.getEdge("d", "t").getCapacity(), 3);
        assertNotNull(residual_Graph2.getEdge("d", "t"));
        assertEquals(residual_Graph2.getEdge("t", "d").getCapacity(), 9);
        assertNotNull(residual_Graph2.getEdge("t", "d"));

        assertEquals(residual_Graph2.getEdge("c", "t").getCapacity(), 13);
        assertNotNull(residual_Graph2.getEdge("c", "t"));
        assertEquals(residual_Graph2.getEdge("t", "c").getCapacity(), 13);
        assertNotNull(residual_Graph2.getEdge("t", "c"));


    }

    @Override
    @Test //PASSED
    public void test_augmentPath2() {

        // s-a-c-b-e-t
        ResidualGraph<String> residual_Graph2 = new ResidualGraphImpl<String>(flowGraph2);
        assertNotNull("The graph is null ", residual_Graph2);

        Deque<ResidualEdge<String>> secondpath = new ArrayDeque<>();
        assertNotNull("The path is null", secondpath);
        assertEquals(0, secondpath.size());

        secondpath.add(residual_Graph2.getEdge("s", "a"));
        secondpath.add(residual_Graph2.getEdge("a", "c"));
        secondpath.add(residual_Graph2.getEdge("c", "b"));
        secondpath.add(residual_Graph2.getEdge("b", "e"));
        secondpath.add(residual_Graph2.getEdge("e", "t"));

        sut.augmentPath(secondpath); //capacity = 5

        assertEquals(residual_Graph2.getEdge("s", "b").getCapacity(), 11);
        assertNotNull(residual_Graph2.getEdge("s", "b"));
        assertEquals(residual_Graph2.getEdge("b", "s").getCapacity(), 11);
        assertNotNull(residual_Graph2.getEdge("b", "s"));

        assertEquals(residual_Graph2.getEdge("s", "a").getCapacity(), 7);
        assertNotNull(residual_Graph2.getEdge("s", "a"));
        assertEquals(residual_Graph2.getEdge("a", "s").getCapacity(), 17);
        assertNotNull(residual_Graph2.getEdge("a", "s"));

        assertEquals(residual_Graph2.getEdge("b", "c").getCapacity(), 10);
        assertNotNull(residual_Graph2.getEdge("b", "c"));
        assertEquals(residual_Graph2.getEdge("c", "b").getCapacity(), 0);
        assertNotNull(residual_Graph2.getEdge("c", "b"));

        assertEquals(residual_Graph2.getEdge("b", "e").getCapacity(), 4);
        assertNotNull(residual_Graph2.getEdge("b", "e"));
        assertEquals(residual_Graph2.getEdge("e", "b").getCapacity(), 14);
        assertNotNull(residual_Graph2.getEdge("e", "b"));

        assertEquals(residual_Graph2.getEdge("a", "c").getCapacity(), 2);
        assertNotNull(residual_Graph2.getEdge("a", "c"));
        assertEquals(residual_Graph2.getEdge("c", "a").getCapacity(), 12);
        assertNotNull(residual_Graph2.getEdge("c", "a"));

        assertEquals(residual_Graph2.getEdge("a", "d").getCapacity(), 0);
        assertNotNull(residual_Graph2.getEdge("a", "d"));
        assertEquals(residual_Graph2.getEdge("d", "a").getCapacity(), 0);
        assertNotNull(residual_Graph2.getEdge("d", "a"));

        assertEquals(residual_Graph2.getEdge("e", "t").getCapacity(), 2);
        assertNotNull(residual_Graph2.getEdge("e", "t"));
        assertEquals(residual_Graph2.getEdge("t", "e").getCapacity(), 12);
        assertNotNull(residual_Graph2.getEdge("t", "e"));

        assertEquals(residual_Graph2.getEdge("e", "d").getCapacity(), 3);
        assertNotNull(residual_Graph2.getEdge("e", "d"));
        assertEquals(residual_Graph2.getEdge("d", "e").getCapacity(), 3);
        assertNotNull(residual_Graph2.getEdge("d", "e"));

        assertEquals(residual_Graph2.getEdge("d", "c").getCapacity(), 4);
        assertNotNull(residual_Graph2.getEdge("d", "c"));
        assertEquals(residual_Graph2.getEdge("c", "d").getCapacity(), 4);
        assertNotNull(residual_Graph2.getEdge("c", "d"));

        assertEquals(residual_Graph2.getEdge("d", "t").getCapacity(), 13);
        assertNotNull(residual_Graph2.getEdge("d", "t"));
        assertEquals(residual_Graph2.getEdge("t", "d").getCapacity(), 13);
        assertNotNull(residual_Graph2.getEdge("t", "d"));


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

        //init Max flow = 17
        // Summe of the flows leaving the start node is 17 ( by beiden edges)
        // Summe of the flows entering the end node is 17 ( by beiden edges)

        sut.findMaxFlow(flowGraphA, "s", "t");

        int Max_Flow = 17;

        int input = 0;
        FlowEdge<String> edge_1 = flowGraphA.getEdge("s", "a");
        assertNotNull(edge_1);
        assertTrue(flowGraphA.getNodes().contains(edge_1.getEnd()));
        input += edge_1.getFlow();
        FlowEdge<String> edge_2 = flowGraphA.getEdge("s", "b");
        assertNotNull(edge_2);
        assertTrue(flowGraphA.getNodes().contains(edge_1.getEnd()));
        input += edge_2.getFlow();

        int output = 0;
        FlowEdge<String> edge_3 = flowGraphA.getEdge("d", "t");
        assertNotNull(edge_3);
        assertTrue(flowGraphA.getNodes().contains(edge_1.getEnd()));
        output += edge_3.getFlow();
        FlowEdge<String> edge_4 = flowGraphA.getEdge("e", "t");
        assertNotNull(edge_4);
        assertTrue(flowGraphA.getNodes().contains(edge_1.getEnd()));
        output += edge_4.getFlow();

        assertTrue("There is an overloading", input == Max_Flow);
        assertEquals("There is a waste of flow during the transfer ", input, output);


    }


    @Override
    @Test
    public void test_findMaxFlow_flowGraphB() {
        sut.findMaxFlow(flowGraphB, "s", "t");

        int Max_Flow = 24;

        int input = 0;
        FlowEdge<String> edge_1 = flowGraphB.getEdge("s", "a");
        assertNotNull(edge_1);
        assertTrue(flowGraphB.getNodes().contains(edge_1.getEnd()));
        input += edge_1.getFlow();
        FlowEdge<String> edge_2 = flowGraphB.getEdge("s", "b");
        assertNotNull(edge_2);
        assertTrue(flowGraphB.getNodes().contains(edge_2.getEnd()));
        input += edge_2.getFlow();

        int output = 0;
        FlowEdge<String> edge_3 = flowGraphB.getEdge("d", "t");
        assertNotNull(edge_3);
        assertTrue(flowGraphB.getNodes().contains(edge_3.getEnd()));
        output += edge_3.getFlow();
        FlowEdge<String> edge_4 = flowGraphB.getEdge("e", "t");
        assertNotNull(edge_4);
        assertTrue(flowGraphB.getNodes().contains(edge_4.getEnd()));
        output += edge_4.getFlow();

        assertTrue("There is an overloading", input == Max_Flow);
        assertEquals("There is a waste of flow during the transfer ", input, output);
    }

    @Override
    @Test
    public void test_findMaxFlow_flowGraphC() {

        sut.findMaxFlow(flowGraphC, "s", "t");

        int Max_Flow = 10;

        int input = 0;
        FlowEdge<String> edge_1 = flowGraphC.getEdge("s", "a");
        assertNotNull(edge_1);
        assertTrue(flowGraphC.getNodes().contains(edge_1.getEnd()));
        input += edge_1.getFlow();
        FlowEdge<String> edge_2 = flowGraphC.getEdge("s", "b");
        assertNotNull(edge_2);
        assertTrue(flowGraphC.getNodes().contains(edge_2.getEnd()));
        input += edge_2.getFlow();

        int output = 0;
        FlowEdge<String> edge_3 = flowGraphC.getEdge("d", "t");
        assertNotNull(edge_3);
        assertTrue(flowGraphC.getNodes().contains(edge_1.getEnd()));
        output += edge_3.getFlow();
        FlowEdge<String> edge_4 = flowGraphC.getEdge("e", "t");
        assertNotNull(edge_4);
        assertTrue(flowGraphC.getNodes().contains(edge_1.getEnd()));
        output += edge_4.getFlow();

        assertTrue("There is an overloading", input == Max_Flow);
        assertEquals("There is a waste of flow during the transfer ", input, output);

    }

    @Override
    @Test  //PASS
    public void test_findMaxFlow_ParameterGraphIsNull() {
        try {
            sut.findMaxFlow(null, "s", "t");
            fail(" No IllegalArgumentException was thrown.");
        }   catch (IllegalArgumentException ex) {
            //Expected IllegalArgumentException

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    @Override
    @Test
    public void test_findMaxFlow_ParameterStartIsNull() {
        try {
            sut.findMaxFlow(flowGraph1, null, "t");
            fail(" No IllegalArgumentException was thrown.");
        }   catch (IllegalArgumentException ex) {
            //Expected IllegalArgumentException

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    @Override
    @Test  //PASS
    public void test_findMaxFlow_ParameterTargetIsNull() {
        try {
            sut.findMaxFlow(flowGraph1, "s", null);
            fail(" No IllegalArgumentException was thrown.");
        }  catch (IllegalArgumentException ex) {
            //Expected IllegalArgumentException

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    @Override
    @Test  //PASSED
    public void test_findMaxFlow_ParameterTargetNotInGraph() {
        try {
            sut.findMaxFlow(flowGraph1, "xy", "xy");
            fail(" No NoSuchElementException was thrown.");
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
