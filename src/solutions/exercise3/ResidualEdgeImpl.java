package solutions.exercise3;


import org.sopra.api.exercises.exercise3.ResidualEdge;

/**
 * Represents an edge in a residual network.
 * <p>
 * Each edge keeps track of its start- & end-point, its capacity,
 * and its reverse residual edge, if in existence.
 * Adding or removing a unit of flow from an edge works by decreasing or increasing the capacity of this edge as appropriate,
 * then increasing or decreasing the capacity of its residual edge appropriately.
 *
 * @author G10T01
 * @version 3.4.0
 * @since 03-12-2020
 */

public class ResidualEdgeImpl<V> extends Edge<V> implements ResidualEdge<V> {


    private ResidualEdge<V> reverse;


    /**
     * The constructor initialises the flow across an edge and initialise the given paramaters.
     *
     * @param startNode the start node of the edge
     * @param endNode   the end node of the edge
     * @param capacity  the capacity og the edge
     */


    public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
        super(startNode, endNode, capacity);
    }


    /**
     * Adds or subtracts the indicated number of flow units across this edge.
     * Adding or removing a unit of flow from an edge works by decreasing or increasing the capacity of this edge as appropriate,
     * then increasing or decreasing the capacity of its residual edge appropriately.
     *
     * @param amount of flow to be added or subtracted
     * @throws java.lang.IllegalArgumentException if the amount of flow added or removed from this edge exceeds the capacity of this edge
     */

    @Override
    public void addFlow(int amount) {
        if (amount > capacity) {
            throw new IllegalArgumentException("the amount flow should not exceed the capacity value !!");
        }
        if (Math.abs(amount) <= capacity) {

            if (amount >= 0) {
                capacity -= amount;
            } else {
                capacity += amount;
            }
            reverse.setCapacity(reverse.getCapacity() + amount);
        } else {
            System.out.println("The operation is not possible");
        }
    }

    /**
     * Gets the reverse edge.
     *
     * @return Reverse residual edge.
     */

    @Override
    public ResidualEdge<V> getReverse() {
        return reverse;
    }


    /**
     * Sets the reverse edge associated with this edge.
     *
     * @param reverse Reverse residual edge associated with this edge.
     */

    @Override
    public void setReverse(ResidualEdge<V> reverse) {
        this.reverse = reverse;
    }

}
