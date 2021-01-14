package solutions.exercise4;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;
import solutions.exercise3.ResidualGraphImpl;

import java.util.*;


/**
 * Represents a Ford-Fulkerson algorithm for computing maximum flows in a flow network.
 *
 * @author G10T01
 * @version 4.1.7
 * @since 17-12-2020
 */


public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {


    /**
     * Computes the maximum flow in a flow network from start to target.
     * Given a flow network and a pair of nodes start & target,
     * the algorithm calculates a maximum flow from start to target in given network.
     *
     * @param graph  Flow graph representing the flow network. Flows along edges in this graph will be set to max flows
     * @param start  Source node
     * @param target Sink node
     * @throws java.lang.IllegalArgumentException If any parameter is null
     * @throws java.util.NoSuchElementException   If start or target are not nodes in the graph
     */

    @Override
    public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
        if (graph == null || start == null || target == null) {
            throw new IllegalArgumentException("The parameters should not be null!");
        }
        if (!graph.containsNode(start) || !graph.containsNode(target)) {
            throw new NoSuchElementException("The start/target node does not exist in the graph");
        }

        if (start.equals(target)) {
            throw new IllegalArgumentException("A path starting and ending in the same node is not possible");
        }

        //inspired by the slides of the frontal event page 7

        for (FlowEdge<V> flowEdge : graph.getEdges()) {
            flowEdge.setFlow(0);
        }

        ResidualGraph<V> residualGraph = new ResidualGraphImpl<>(graph);
        Deque<ResidualEdge<V>> path = findPath(start, target, residualGraph);
        int MaxFlow = 0;
        while (path != null) {
            MaxFlow += findMiniCapacity(path);
            augmentPath(path);
            path = findPath(start, target, residualGraph);

          /*
              MaxFlow of flowGraphA = 17
              MaxFlow of flowGraphB = 24
              MaxFlow of flowGraphC = 10

           */

        }

        // actualising the flowGraph by setting the remaining capacity
        // assuming that the graph is symmetric

        for (ResidualEdge<V> res_edge : residualGraph.getEdges()) {

            FlowEdge<V> backFlow_edge = graph.getEdge(res_edge.getEnd(), res_edge.getStart());

            //setting the flow

            int init_capacity = (res_edge.getCapacity() + res_edge.getReverse().getCapacity())/2;
            if (res_edge.getCapacity() - init_capacity >= 0) {
                backFlow_edge.setFlow(res_edge.getCapacity() - init_capacity);
            } else {
                backFlow_edge.setFlow(0);
            }

        }

    }


    /**
     * Gets the smallest remaining capacity in a path
     *
     * @param path The path of residual edges
     * @return the smallest remaining capacity in a path
     */
    private int findMiniCapacity(Deque<ResidualEdge<V>> path) {
        // in this case path will never be null

        if (path == null) {
            throw new IllegalArgumentException("The path should not be null! ");
        }

        int mini = path.element().getCapacity(); //  choosing the capacity of a random edge

        for (ResidualEdge<V> res_edge : path) {
            if (res_edge.getCapacity() < mini) {
                mini = res_edge.getCapacity();
            }
        }
        return mini;
    }


    /**
     * Finds the shortest path with remaining capacity using Breadth-First-Search (BFS) on a residual graph from start to end.
     * A path is considered shorter than another path, if the number of edges in that path is lower.
     * The edges are ordered depending on their occurrence in the path starting with an edge to end as first entry,
     * and therefore ending with an edges from start as last entry.
     * <p>
     * If a path with remaining capacity along the edges exists, a double ended queue containing the edges of the path is returned.
     * If no path with remaining capacity along the edges exists null is returned.
     *
     * @param start Start node of the path
     * @param end   End node of the path
     * @param graph Residual graph to search in
     * @return Path (represented by a double ended queue) from the graph with ordering depending on occurrence in the path,
     * or null if path with remaining capacity does not exist.
     * @throws java.lang.IllegalArgumentException If at least one of the parameters is null
     */

    @Override
    public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
        if (start == null || end == null || graph == null) {
            throw new IllegalArgumentException("The parameters should not be null!");
        }

        if (start.equals(end)) {
            throw new IllegalArgumentException("A path starting and ending in the same node is not possible");
        }
        if (!graph.getNodes().contains(start) || !graph.getNodes().contains(end)) {
            throw new NoSuchElementException("The start/target node does not exist in the graph");
        }


        // Breadth-First-Search (BFS)

        Deque<V> queue = new ArrayDeque<>();      // Set the queue
        Set<V> seen = new HashSet<>();            // Set of the explored nodes
        Map<V, V> nodesTree = new HashMap<>();    // Set the availability tree
        boolean foundTheTail = false;


        queue.add(start);
        seen.add(start);

        while (!queue.isEmpty()) {
            V currentNode = queue.poll(); // node in zero position

            for (ResidualEdge<V> res_edge : graph.edgesFrom(currentNode)) {  // the edge leaving the current node( the possible other nodes)
                if (!seen.contains(res_edge.getEnd()) && res_edge.getCapacity() > 0) {

                    seen.add(res_edge.getEnd());                               // adding the node to the seen list
                    queue.add(res_edge.getEnd());                              // adding the node to the queue
                    nodesTree.put(res_edge.getEnd(), res_edge.getStart());     // adding the node to the availability tree
                    //  System.out.println(res_edge.getEnd());

                }
                if (res_edge.getEnd().equals(end)) {
                    foundTheTail = true;
                }
            }
        }


        Deque<ResidualEdge<V>> path = new ArrayDeque<>();

        if (foundTheTail && nodesTree.get(end) != null) {        // the target node exist in the tree

            V node = nodesTree.get(end);    // getting the last node before reaching the target
            path.addFirst(graph.getEdge(node, end));

            while (!start.equals(nodesTree.get(node))) {
                for (ResidualEdge<V> res_edge : graph.getEdges()) {
                    ResidualEdge<V> wantedEdge = graph.getEdge(nodesTree.get(node), node);
                    if (res_edge.equals(wantedEdge)) {
                        path.addLast(wantedEdge);
                    }
                }
                node = nodesTree.get(node);

    /*               flowGraph1
                  1) s->a, a->d, d->t;  first appeared, first added (forwards : from the start node till the end node)
                  2) t->d, d->a, a->s;  (backwards: from the end node till the end  )

                  3) a->s, d->a, t->d;  other possibilities
                  4) d->t, a->d, s->a;
                */

            }
            path.addLast(graph.getEdge(nodesTree.get(node), node));
            //reverseQueue(path);
            return path;
        } else {
            return null;
        }
    }

    /**
     * Reverses the order of a deque.
     *
     * @param path The path if the Residual edges
     */
    private void reverseQueue(Deque<ResidualEdge<V>> path) {

        //assuming the path is not null
        if (path.isEmpty()) return;
        Stack<ResidualEdge<V>> stack = new Stack<>();
        while (!path.isEmpty()) {
            stack.push(path.getFirst());
            path.pop();
        }
        while (!stack.isEmpty()) {
            path.addLast(stack.lastElement());
            stack.pop();
        }
    }


    /**
     * Finds the minimum capacity along the given path and adds the minimum capacity to flow of each edges of the path.
     *
     * @param path - Augmented path (represented by a double ended queue) where minimum capacity of path is added to flow
     * @throws java.lang.IllegalArgumentException if parameter is null
     */

    @Override
    public void augmentPath(Deque<ResidualEdge<V>> path) {
        if (path == null) {
            throw new IllegalArgumentException("The path should not be null! ");
        }

        int mini = path.element().getCapacity(); //  choosing the capacity of a random edge

        for (ResidualEdge<V> res_edge : path) {
            if (res_edge.getCapacity() < mini) {
                mini = res_edge.getCapacity();
            }
        }
        for (ResidualEdge<V> res_edge : path) {
            res_edge.addFlow(mini);
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
