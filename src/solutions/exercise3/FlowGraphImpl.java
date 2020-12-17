package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

import java.util.*;


/**
 * Represents a flow graph.
 *
 * @author G10T01
 * @version 3.2.0
 * @since 01-12-2020
 */


public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

    private Map<V, Map<V, FlowEdge<V>>> map;

    /**
     * The constructor initialises the map as a Hashmap.
     */

    public FlowGraphImpl() {
        map = new HashMap<>(); // initialise the map as a Hashmap
    }


    /**
     * Adds new node to flow graph (the hashmap) if node does not already exist.
     * If node is added returns true, else returns false.
     *
     * @param node Node to add
     * @return true if the node was successfully added, false otherwise
     */

    @Override
    public boolean addNode(V node) {
        if (!map.containsKey(node) && !(node == null)) {
            map.put(node, new HashMap<>());
            return true;
        } else {
            return false;
        }
    }


    /**
     * Gets a set of all nodes from graph.
     *
     * @return a Set of all nodes
     */

    @Override
    public Set<V> getNodes() {
        return map.keySet();  // deliver the set of all key (nodes)
    }


    /**
     * Returns true if the given node is contained in the graph. Otherwise returns false.
     *
     * @param node Node to be tested if contained in graph
     * @return true if node is contained in the graph otherwise false
     */

    @Override
    public boolean containsNode(V node) {
        return map.containsKey(node);
    }


    /**
     * Adds a new flow edge to a start and a destination node with initial flow of 0.
     * If either start or destination node does not exist in graph,
     * throws an NoSuchElementException. Returns the existing edge if edge already exists.
     *
     * @param start    Start node
     * @param dest     Destination node
     * @param capacity Capacity of the edge
     * @return Newly created flow edge between start and destination node
     * @throws java.util.NoSuchElementException - if either start or destination node does not exist in graph
     */

    @Override
    public FlowEdge<V> addEdge(V start, V dest, int capacity) {
        if ((!map.containsKey(start)) || (!map.containsKey(dest))) {
            throw new NoSuchElementException("Both or either the start node or the end node does not exist in the graph!");
        }
        if ((start == null) || (dest == null)) {
            throw new NullPointerException(" The start and the end node should not be null!");
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("The value of the capacity should not be negative ");
        }
        // the edge available
        if (map.get(start).containsKey(dest)) {
            return map.get(start).get(dest);
        }

        FlowEdge<V> flowEdge = new FlowEdgeImpl (start, dest, capacity); // initialise a new intance of typ FlowEdgeImpl
        map.get(start).put(dest, flowEdge); // add a new flow edge
        return flowEdge;
    }


    /**
     * Returns a flow edge going from start to end.
     * Returns null if flow edge is not present or at least one parameter is null.
     *
     * @param startnode Start of this edge
     * @param endnode   End of this edge
     * @return A flow edge
     */

    @Override
    public FlowEdge<V> getEdge(V startnode, V endnode) {

        FlowEdge<V> flowEdge = map.get(startnode).get(endnode);
        if ((startnode == null) || (endnode == null) || (flowEdge == null)) {
            return null;
        }
        return flowEdge;
    }


    /**
     * Returns only the leaving edges from given node. If node is not given in the graph, throws a NoSuchElementException.
     *
     * @param node Node whose edges should be retrieved
     * @return A Collection of all flow edges leaving the node
     * @throws java.util.NoSuchElementException if node is not contained in graph
     */

  /*< s, <u, flowEdge1>>
    < u, <v, flowEdge2>>
    < u, <t, flowEdge3>>
    < s, <v, flowEdge4>>
    < v, <t, flowEdge5>>

    node: V
    * flowEdge2,4,5 false
    * flowEdge5 right
    => Only the leaving edges
*/
    @Override
    public Collection<FlowEdge<V>> edgesFrom(V node) {
        if (!map.containsKey(node)) {
            throw new NoSuchElementException("The node does not exist in the graph!");
        }
        return map.get(node).values();
    }


    /**
     * Gets list of all edges from graph.
     *
     * @return a List off all flow edges
     */

    @Override
    public List<FlowEdge<V>> getEdges() {
        List<FlowEdge<V>> listOfFlowEdges = new ArrayList<>();
        if (map.size() > 0) {   // test if the map empty
            for (Map<V, FlowEdge<V>> edge : map.values()) {
                listOfFlowEdges.addAll(edge.values());
            }
        }
        return listOfFlowEdges;
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
