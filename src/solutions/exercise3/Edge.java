package solutions.exercise3;


import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.CapacityEdge;

/**
 * Describes the edges of a weighted graph.
 *
 * @author G10T01
 * @version 3.1.0
 * @since 01-12-2020
 */

abstract class Edge <V> implements CapacityEdge <V>, ExerciseSubmission {

    protected V startNode;
    protected V endNode;
    protected int capacity;



    /**
     * The constructor initialises a the start and the end of an edge.
     *
     * @param startNode the start node of the edge
     * @param endNode   the end node of the edge
     * @param capacity  the capacity og the edge
     */

    public Edge (V startNode, V endNode, int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;

    }

    /**
     * Gets the start node of this edge.
     *
     * @return the start node of this edge
     */


    @Override
    public V getStart() {
        return this.startNode;
    }


    /**
     * Gets the end node of this edge.
     *
     * @return the end node of this edge
     */

    @Override
    public V getEnd() {
        return this.endNode;
    }


    /**
     * Gets the weight of this edge. Most likely, this weight will be a powerline's capacity.
     *
     * @return the capacity of this edge (edge's weight)
     */

    @Override
    public int getCapacity() {
        return this.capacity;
    }


    /**
     * Sets the weight of this edge.
     *
     * @param capacity of this edge (edge's weight)
     * @throws java.lang.IllegalArgumentException -  if value is negative
     */

    @Override
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The value of the capacity should not be negative ");

        } else {
            this.capacity = capacity;
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
