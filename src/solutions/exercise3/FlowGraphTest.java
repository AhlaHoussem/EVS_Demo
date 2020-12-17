package solutions.exercise3;


import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests the functionality all the methods of the class FlowGraphImpl and the validity of its parameter.
 *
 * @author G10T01
 * @version 3.3.4
 * @since 02-12-2020
 */

public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission {


    /**
     * Sets a test case
     */
    //  @Before
    public void setup() {
        // initialise/implements the sut
        // setting the nodes of the graph

        sut.addNode(nodes[0]); //s
        sut.addNode(nodes[1]);//u
        sut.addNode(nodes[2]);//v
        sut.addNode(nodes[3]);//t

        sut.addEdge(nodes[0], nodes[1], edges[0].getCapacity());
        sut.addEdge(nodes[1], nodes[0], edges[1].getCapacity());

        sut.addEdge(nodes[2], nodes[0], edges[2].getCapacity());
        sut.addEdge(nodes[0], nodes[2], edges[3].getCapacity());

        sut.addEdge(nodes[1], nodes[2], edges[4].getCapacity());
        sut.addEdge(nodes[2], nodes[1], edges[5].getCapacity());

        sut.addEdge(nodes[3], nodes[1], edges[6].getCapacity());
        sut.addEdge(nodes[1], nodes[3], edges[7].getCapacity());

        sut.addEdge(nodes[3], nodes[2], edges[8].getCapacity());
        sut.addEdge(nodes[2], nodes[3], edges[9].getCapacity());


       // internal_setUp();
/*
        sut.addNode("s");
        sut.addNode("u");
        sut.addNode("v");
        sut.addNode("t");

        sut.addEdge("s", "u", 5);
        sut.addEdge("u", "s", 5);
        sut.addEdge("v", "s", 7);
        sut.addEdge("s", "v", 7);
        sut.addEdge("u", "v", 9);
        sut.addEdge("v", "u", 9);
        sut.addEdge("t", "u", 4);
        sut.addEdge("u", "t", 4);
        sut.addEdge("t", "v", 6);
        sut.addEdge("v", "t", 6);*/

    }


    /**
     * tests the method addNode and if its parameter are valid.
     */

    @Test
    public void test_addNode() {

        // PASSED
        assertTrue("The node S can not be added to the graph", sut.addNode("s"));
        assertTrue("The node U can not be added to the graph", sut.addNode("u"));
        assertTrue("The node T can not be added to the graph", sut.addNode("t"));
        assertTrue("The node V can not be added to the graph", sut.addNode("v"));
        assertFalse("The node S is duplicated in the graph", sut.addNode("s"));
        assertFalse("a Null is added to the graph", sut.addNode(null));


    }

    /**
     * tests the method getNodes.
     */

    @Test
    public void test_getNodes() {
        // assertNull("The Set is not equal to null", sut.getNodes());
        assertNotNull("The Set is not equal to null", sut.getNodes());
        assertTrue("The list is empty", sut.getNodes().isEmpty()); //test if the list is empty


        setup();
        //DONE
        assertTrue("There is no node S ", sut.getNodes().contains("s"));
        assertTrue("There is no node U ", sut.getNodes().contains("u"));
        assertTrue("There is no node T ", sut.getNodes().contains("t"));
        assertTrue("There is no node V ", sut.getNodes().contains("v"));
        assertFalse("There is no node O ", sut.getNodes().contains("O"));
        assertFalse("There is a null ", sut.getNodes().contains(null));

        assertNotNull("The Set is equal to null", sut.getNodes());
        assertFalse("The list is empty", sut.getNodes().isEmpty()); //test if the list is empty
        assertEquals("The Set's size is not equal 4", 4, sut.getNodes().size()); // test if the there is only 4 nodes


    }


    /**
     * tests the method containsNode and if its parameter are valid.
     */

    @Test
    public void test_containsNode() {
        setup();
        //PASSED
        assertTrue("There is no node S ", sut.containsNode("s"));
        assertTrue("There is no node U ", sut.containsNode("u"));
        assertTrue("There is no node T ", sut.containsNode("v"));
        assertTrue("There is no node V ", sut.containsNode("t"));
        assertFalse("There is no node O ", sut.containsNode("O"));
        assertFalse("There is a null ", sut.containsNode(null));
    }


    /**
     * tests the method addEdge and if its parameter are valid.
     */

    @Test
    public void test_addEdge() {
        setup();

        // PASSED


        // test adding start/end node which doesn't exist in the graph

        try {
            sut.addEdge("s", "A", 10);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }

        try {
            sut.addEdge("a", "s", 10);
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            //Expected NoSuchElementException
        }

        // test adding null as start/end node

/*       try {
            sut.addEdge("s", null, 10);
            fail(" No NullPointerException was not thrown.");
        } catch (NullPointerException e) {
            //Expected NullPointerException
        }

        try {
            sut.addEdge(null, "s", 10);
            fail(" No NullPointerException was not thrown.");
        } catch (NullPointerException e) {
            //Expected NullPointerException
        }

        // test adding a negative capacity

        try {
            sut.addEdge("v", "t", -10);
            fail(" No IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            //Expected IllegalArgumentException
        }*/


        //add node U,V. Initialise a flow between those two nodes with a positive capacity
        // sut.addNode("s");
        // sut.addNode("u");
        FlowEdge<String> edge1 = sut.addEdge("s", "u", 5);

        // test adding a new edge and check its components. This should returns the edge itself
        FlowEdge<String> edge2 = sut.addEdge("s", "u", 5);
        assertEquals("Adding an existing edge was failed ", edge1, edge2);

        assertNotEquals("The added edge is null", null, edge1);
        assertEquals("The Start node isn't S", "s", edge1.getStart());
        assertEquals("The End node isn't U", "u", edge1.getEnd());
        assertEquals("The capacity isn't 5 ", 5, edge1.getCapacity());
        assertEquals("The flow isn't 0", 0, edge1.getFlow());

        // test if the edge is added to the list of edges
        assertTrue("The edge1 was not added to the collection", sut.getEdges().contains(edge1));
    }


    /**
     * tests the method getEdge and implements all possible testcases.
     */

    @Test
    public void test_getEdge() {

        // PASSED

        setup();

        assertNull("The returned edge is null", sut.getEdge("s", null));
        assertNull("The returned edge is null", sut.getEdge(null, "s"));
        assertNull("The returned edge is null", sut.getEdge("h", "i"));
        assertNull("The returned edge is null", sut.getEdge("d", "s"));

        List<FlowEdge<String>> edgesList = sut.getEdges();

        assertEquals("The edges number is not equal to 10 ", 10, edges.length);

        FlowEdge<String> edge0 = sut.getEdge("s", "u");
        assertNotNull("The returned edge is null", edge0);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge0, edgesList.get(0)));

        FlowEdge<String> edge1 = sut.getEdge("u", "s");
        assertNotNull("The returned edge is null", edge1);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge1, edges[1]));

        FlowEdge<String> edge2 = sut.getEdge("v", "s");
        assertNotNull("The returned edge is null", edge2);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge2, edges[2]));


        FlowEdge<String> edge3 = sut.getEdge("s", "v");
        assertNotNull("The returned edge is null", edge3);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge3, edges[3]));


        FlowEdge<String> edge4 = sut.getEdge("u", "v");
        assertNotNull("The returned edge is null", edge4);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge4, edges[4]));


        FlowEdge<String> edge5 = sut.getEdge("v", "u");
        assertNotNull("The returned edge is null", edge5);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge5, edges[5]));

        FlowEdge<String> edge6 = sut.getEdge("t", "u");
        assertNotNull("The returned edge is null", edge6);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge6, edges[6]));

        FlowEdge<String> edge7 = sut.getEdge("u", "t");
        assertNotNull("The returned edge is null", edge7);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge7, edges[7]));

        FlowEdge<String> edge8 = sut.getEdge("t", "v");
        assertNotNull("The returned edge is null", edge8);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge8, edges[8]));

        FlowEdge<String> edge9 = sut.getEdge("v", "t");
        assertNotNull("The returned edge is null", edge9);
        assertTrue(" The returned edge is not equal to the one in the lis", equal(edge9, edges[9]));


    }


    /**
     * compares two flow edges and returns true if they are equal.
     *
     * @param edge1 the first flow edge
     * @param edge2 the second flow edge
     * @return true if the flow edges are equal.False otherwise
     */

    private boolean equal(FlowEdge<String> edge1, FlowEdge<String> edge2) {
        return (edge1.getStart().equals(edge2.getStart())) &&
                (edge1.getEnd().equals(edge2.getEnd())) &&
                (edge1.getCapacity() == edge2.getCapacity());

    }


    /**
     * tests the method edgesFrom and implements all possible testcases.
     */

    @Test
    public void test_edgesFrom() {

        setup();
        // PASSED


        // test adding node which doesn't exist in the graph
        try {
            sut.edgesFrom("a");
            fail(" No NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // Expected NoSuchElementException
        }


        assertNotNull("There is no edges leaving the node S", sut.edgesFrom("s"));
        assertEquals("The number of edges leaving S is not right", 2, sut.edgesFrom("s").size());

        assertNotNull("There is no edges leaving the node U", sut.edgesFrom("u"));
        assertEquals("The number of edges leaving U is not right", 3, sut.edgesFrom("u").size());

        assertNotNull("There is no edges leaving the node V", sut.edgesFrom("v"));
        assertEquals("The number of edges leaving V is not right", 3, sut.edgesFrom("v").size());

        assertNotNull("There is no edges leaving the node T", sut.edgesFrom("t"));
        assertEquals("The number of edges leaving T is not right", 2, sut.edgesFrom("t").size());


        // check the conection between the nodes

        // the start nodes
        for (String start : nodes) {
            assertNotNull("The start node is null ", sut.edgesFrom(start));

            // the start nodes
            for (FlowEdge<String> edge : sut.edgesFrom(start)) {
                assertNotNull("The corresponding edge is null ", edge);

                switch (start) {
                    case "s" -> assertTrue("The arrival node is not correct ", (edge.getEnd().equals("u")) || (edge.getEnd().equals("v")));
                    case "u" -> assertTrue("The arrival node is not correct ", (edge.getEnd().equals("s")) || (edge.getEnd().equals("t")) || (edge.getEnd().equals("v")));
                    case "t" -> assertTrue("The arrival node is not correct ", (edge.getEnd().equals("v")) || (edge.getEnd().equals("u")));
                    case "v" -> assertTrue("The arrival node is not correct ", (edge.getEnd().equals("s")) || (edge.getEnd().equals("t")) || (edge.getEnd().equals("u")));

                }

            }

        }

    }

    /**
     * tests the method getEdges and implements all possible testcases.
     */

    @Test
    public void test_getEdges() {

        //PASSED

        assertNotNull("The list is equal to null", sut.getEdges());
        assertTrue("The list is empty", sut.getEdges().isEmpty()); //test if the list is empty


        setup();

        // the edge in the graph are 10
        // check the content of the list of edges
        for (FlowEdge<String> returnedEdge : sut.getEdges()) {
            boolean tmp = (equal(returnedEdge, edges[0]) ||
                    equal(returnedEdge, edges[1]) ||
                    equal(returnedEdge, edges[2]) ||
                    equal(returnedEdge, edges[3]) ||
                    equal(returnedEdge, edges[4]) ||
                    equal(returnedEdge, edges[5]) ||
                    equal(returnedEdge, edges[6]) ||
                    equal(returnedEdge, edges[7]) ||
                    equal(returnedEdge, edges[8]) ||
                    equal(returnedEdge, edges[9]));
            assertTrue("The given edges list and the returned list arE not equal", tmp);

        }

        assertNotNull("The list is equal to null", sut.getEdges());
        assertFalse("The list is empty", sut.getEdges().isEmpty()); //test if the list is empty
        assertEquals("The list's size is not equal 10", 10, sut.getEdges().size()); // test if the there is only 10 eges
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

