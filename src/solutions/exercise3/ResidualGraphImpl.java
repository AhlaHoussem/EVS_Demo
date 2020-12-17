package solutions.exercise3;


import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;

import java.util.*;

/**
 * Represents a residual graph.
 *
 * @author G10T01
 * @version 3.5.1
 * @since 03-12-2020
 */


public class ResidualGraphImpl<V> implements ResidualGraph<V>, ExerciseSubmission {

    private Map<V, List<ResidualEdge<V>>> map = new HashMap<>();


    /**
     * This constructor initialises the map as a Hashmap.
     */

    public ResidualGraphImpl(FlowGraph<V> flowGraph) {

        List<FlowEdge<V>> visited = new ArrayList<>();

        for ( V node : flowGraph.getNodes() ) {
            map.put(node, new ArrayList<>());
        }

        for (V start : flowGraph.getNodes() ) {
            for ( FlowEdge<V> edge : flowGraph.edgesFrom(start)) {
                if (!visited.contains(edge)) {
                    ResidualEdge<V> forward;
                    ResidualEdge<V> backward;
                    int c_1 = edge.getCapacity();
                    int f_1 = edge.getFlow();

                    FlowEdge<V> return_edge = flowGraph.getEdge(edge.getEnd(), start);

                    if (return_edge != null) {
                        int c_2 = return_edge.getCapacity();
                        int f_2 = return_edge.getFlow();

                        if (f_1 > 0 && f_2 == 0) {
                            forward = new ResidualEdgeImpl<>(edge.getStart(), edge.getEnd(), c_1 - f_1);
                            backward = new ResidualEdgeImpl<>(edge.getEnd(),edge.getStart(),  c_2 + f_1);
                        } else if (f_2 > 0 && f_1 == 0) {
                            forward = new ResidualEdgeImpl<>(edge.getStart(), edge.getEnd(), c_1 + f_2);
                            backward = new ResidualEdgeImpl<>(edge.getEnd(),edge.getStart(), c_2 - f_2);
                        } else if (f_1 == 0 && f_2 == 0) {
                            forward = new ResidualEdgeImpl<>(edge.getStart(), edge.getEnd(), c_1);
                            backward = new ResidualEdgeImpl<>(edge.getEnd(),edge.getStart(), c_2);
                        } else {
                            throw new IllegalArgumentException("Flow in both directions not possible. Look up edges from/to: "
                                    + edge.getStart() + " <-> " + edge.getEnd());
                        }
                        visited.add(return_edge);
                    } else {
                        forward = new ResidualEdgeImpl<>(edge.getStart(), edge.getEnd(), c_1 - f_1);
                        backward = new ResidualEdgeImpl<>(edge.getEnd(),edge.getStart(), f_1);
                    }

                    forward.setReverse(backward);
                    backward.setReverse(forward);

                    map.get(edge.getStart()).add(forward);
                    map.get(edge.getEnd()).add(backward);
                }

            }
        }

    }



    /**
     * Returns the edges leaving a particular node.
     *
     * @param node - Node to look up.
     * @return List of residual edges leaving that node.
     * @throws java.util.NoSuchElementException - if node does not exist.
     */

    public List<ResidualEdge<V>> edgesFrom(V node) {
        if (!map.containsKey(node)) {
            throw new NoSuchElementException("The node does not exist in the graph!");
        }
        return map.get(node);
    }


    /**
     * Returns all edges existing in the graph.
     *
     * @return List of all residual edges
     */

    public List<ResidualEdge<V>> getEdges() {
        List<ResidualEdge<V>> listOfResidualEdges = new ArrayList<>();
        if (map.size() > 0) {   // test if the map empty
            for (List<ResidualEdge<V>> ResEdge : map.values()) {
                listOfResidualEdges.addAll (ResEdge);
            }
        }
        return listOfResidualEdges;
    }



    /**
     * Returns the @link ResidualEdge edge} between node start and node end.
     * Return null if no @link ResidualEdge edge} exists.
     *
     * @param startNode - Start node
     * @param endNode   - End node
     * @return Residual edge between start and end
     * @throws java.lang.IllegalArgumentException - if either parameter start or end are null
     * @throws java.util.NoSuchElementException - if either start or end are not contained in the graph
     *
     */

    public ResidualEdge<V> getEdge(V startNode, V endNode) {
        if ((startNode == null) || (endNode == null)) {
            throw new IllegalArgumentException("The start node and the end node should not be null");
        }
        if ( (!map.containsKey(startNode)) || (!map.containsKey(endNode)) ) {
            throw new NoSuchElementException("The start node or the end node are not contained in the graph");
        }

        for (ResidualEdge<V> ResEdge : map.get(startNode)) {    // the RessidualEdge of the start node
            if (endNode.equals(ResEdge.getEnd())) {             // if the given ende node matches with edge's ende node
                return ResEdge;
            }
        }
        return null;
    }


    /**
     * Returns all nodes existing in the graph.
     *
     * @return Set off all nodes.
     */

    public Set<V> getNodes() {
        return map.keySet();

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
