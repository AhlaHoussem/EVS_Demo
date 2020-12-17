package solutions.exercise3;

//import org.sopra.api.exercises.ExerciseSubmission;

import org.sopra.api.exercises.exercise3.FlowEdge;


/**
 * Represents an edge in a flow network ( in weighted graph) .
 *
 * @author G10T01
 * @version 3.1.0
 * @since 01-12-2020
 */

public class FlowEdgeImpl<V> extends Edge<V> implements FlowEdge<V> {


    private int flow; // the flow of the edge


    /**
     * The constructor initialises the flow across an edge and initialise the given paramaters.
     *
     * @param startNode the start node of the edge
     * @param endNode   the end node of the edge
     * @param capacity  the capacity og the edge
     */

    public FlowEdgeImpl(V startNode, V endNode, int capacity) {
        super(startNode, endNode, capacity);  // initialise the given parameters from the super class Edge

        // test the case of start == end
        if (startNode.equals(endNode)) {
            throw new IllegalArgumentException("The start node should not be equal to the end node");
        }
        flow = 0;
        this.capacity = capacity;
    }


    /**
     * Gets the flow across this edge.
     *
     * @return flow across this edge
     */

    @Override
    public int getFlow() {
        return this.flow;
    }


    /**
     * Sets the flow across this edge.
     *
     * @param flow new flow across this edge.
     * @throws java.lang.IllegalArgumentException -  if value is negative
     */

    @Override
    public void setFlow(int flow) {
        if (flow < 0) {
            throw new IllegalArgumentException("The value of the flow should not be negative ");

        } else {
            this.flow = flow;
        }

    }

    /*

     */
/**
 * Gets the start node of this edge.
 *
 * @return the start node of this edge
 *//*



    @Override
    public V getStarT() {
        return this.startNode;
    }


    */
/**
 * Gets the end node of this edge.
 *
 * @return the end node of this edge
 *//*


    @Override
    public V getEnd() {
        return this.endNode;
    }


    */
/**
 * Gets the weight of this edge. Most likely, this weight will be a powerline's capacity.
 *
 * @return the capacity of this edge (edge's weight)
 *//*


    @Override
    public int getCapacity() {
        return this.capacity;
    }


    */
/**
 * Sets the weight of this edge.
 *
 * @param capacity of this edge (edge's weight)
 * @throws java.lang.IllegalArgumentException -  if value is negative
 *//*


    @Override
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The value of the capacity should not be negative ");

        } else {
            this.capacity = capacity;
        }
    }

*/

}
