package solutions.exercise4;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import java.util.Deque;
import java.util.NoSuchElementException;

//hjnjkl,
/**
 * Represents a Ford-Fulkerson algorithm for computing maximum flows in a flow network.
 *
 * @author G10T01
 * @version 4.1.0
 * @since 17-12-2020
 */


public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {






    /**
     * Computes the maximum flow in a flow network from start to target.
     *
     * Given a flow network and a pair of nodes start & target,
     * the algorithm calculates a maximum flow from start to target in given network.
     *
     * @param graph  Flow graph representing the flow network. Flows along edges in this graph will be set to max flows
     * @param start  Source node
     * @param target Sink node
     * @throws java.lang.IllegalArgumentException  If any parameter is null
     * @throws java.util.NoSuchElementException  If start or target are not nodes in the graph
     */

    @Override
    public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
        if ( graph == null || start == null || target == null ) {
            throw new IllegalArgumentException("The parameters should not be null!");
        }
        if (!graph.containsNode(start) || !graph.containsNode(target)) {
            throw new NoSuchElementException("The start/target node does not exist in the graph");
        }



    }



    /**
     * Finds the shortest path with remaining capacity using Breadth-First-Search (BFS) on a residual graph from start to end.
     * A path is considered shorter than another path, if the number of edges in that path is lower.
     * If a path with remaining capacity along the edges exists, a double ended queue containing the edges of the path is returned.
     * The edges are ordered depending on their occurrence in the path starting with an edge to end as first entry, and therefore ending with an edges from start as last entry.
     * If no path with remaining capacity along the edges exists null is returned.
     *
     *
     * @param start Start node of the path
     * @param end   End node of the path
     * @param graph Residual graph to search in
     * @return Path (represented by a double ended queue) from the graph with ordering depending on occurrence in the path,
     * or null if path with remaining capacity does not exist.
     * @throws java.lang.IllegalArgumentException  If at least one of the parameters is null
     */

    @Override
    public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
        return null;
    }




    /**
     * Finds the minimum capacity along the given path and adds the minimum capacity to flow of each edges of the path.
     *
     * @param path  - Augmented path (represented by a double ended queue) where minimum capacity of path is added to flow
     * @throws java.lang.IllegalArgumentException  if parameter is null
     */

    @Override
    public void augmentPath(Deque<ResidualEdge<V>> path) {
        if (path == null) {
            throw new IllegalArgumentException("The path should not be null! ");
        }

        int mini = path.element().getCapacity(); //  choosing the capacity of a random edge

        for ( ResidualEdge<V> edge: path) {
            if ( edge.getCapacity() < mini) {
                mini = edge.getCapacity();
            }
        }
        for ( ResidualEdge<V> edge: path) {
            edge.addFlow(mini);
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
