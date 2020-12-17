package solutions.exercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.producer.Producer;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractPairFinder;
import org.sopra.api.exercises.exercise1.PairFinder;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PowerLine;

/**
 * 	This class provides a method to make pairs of consumers and their nearest producer.
 *
 *	@author  Softwarepraktikum
 *	@version 1.5.2
 */
public class PairFinderImpl extends AbstractPairFinder implements ExerciseSubmission, PairFinder {

/**
 * Returns pairs of all existing consumers and the nearest producer that is not already assigned to another consumer.
 *
 * @param	graph  a graph object which contains all nodes and edges of a scenario
 * @return	map of all consumers and their nearest not already assigned producer
 */
	@Override
	public Map<Consumer, Producer> findConsumerProducerPairs(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null.");
		}

		List<Consumer> consumers = this.util.getConsumers(graph);		// List of all consumers
		List<Producer> assigned = new ArrayList<>();					// List of all already assigned producers
		List<Consumer> orderedConsumers = new ArrayList<>();			// List of consumers in correct order
		Map<Consumer, Producer> consumerProducerPair = new HashMap<>();	// Map of consumers and producers

		for (int y=0;y<=9;y++) {
			for(int x=0;x<=9;x++) {
				if (consumers.contains(getNodeAtPosition(graph, x, y))) {
					orderedConsumers.add((Consumer) getNodeAtPosition(graph, x, y));
				}
			}
		}

		for (Consumer consumer : orderedConsumers) {
			consumerProducerPair.put(consumer, findNextProducer(graph, assigned, consumer));
			assigned.add(consumerProducerPair.get(consumer));
		}
		return consumerProducerPair;
	}


	/**
	 * Returns the nearest Producer to the EnergyNode start. <br>
	 * The method searches for the Producer in circles around the EnergyNode start. It starts at the PlayFieldElement diagonally
	 * left below the start and then searches in a clockwise circle around start in bigger circles each round. Stops when finding a producer or after 9 circles.<p>
	 *
	 * @param  graph  a graph object which contains all nodes and edges of a scenario
	 * @param  assigned	a list of all Producers that are already assigned to consumers
	 * @param  start  the EnergyNode around which the closest Producer should be found
	 * @return        the nearest Producer to start or null if there are no unassigned Producers in the graph.
	 */
	private Producer findNextProducer(Graph<EnergyNode, PowerLine> graph, List<Producer> assigned, EnergyNode start) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null.");
		}else if (assigned == null) {
			throw new IllegalArgumentException("assigned is not allowed to be null.");
		}else if (start == null) {
			throw new IllegalArgumentException("start is not allowed to be null.");
		}

		int currentX = start.getXPos() - 1;
		int currentY = start.getYPos() + 1;

		for (int round = 1; round < 10; round++) {

			if (isProducerAtPosition(graph, assigned, currentX, currentY)) {
				return (Producer) getNodeAtPosition(graph, currentX, currentY);
			}
			// up
			for (int i = 1; i <= 2 * round; i++) {
				currentY = currentY - 1;
				if (isProducerAtPosition(graph, assigned, currentX, currentY)) {
					return (Producer) getNodeAtPosition(graph, currentX, currentY);
				}

			}
			// right
			for (int i = 1; i <= 2 * round; i++) {
				currentX = currentX + 1;
				if (isProducerAtPosition(graph, assigned, currentX, currentY)) {
					return (Producer) getNodeAtPosition(graph, currentX, currentY);
				}
			}
			// down
			for (int i = 1; i <= 2 * round; i++) {
				currentY = currentY + 1;
				if (isProducerAtPosition(graph, assigned, currentX, currentY)) {
					return (Producer) getNodeAtPosition(graph, currentX, currentY);
				}
			}
			// left
			for (int i = 1; i <= 2 * round; i++) {
				currentX = currentX - 1;
				if (isProducerAtPosition(graph, assigned, currentX, currentY)) {
					return (Producer) getNodeAtPosition(graph, currentX, currentY);
				}
			}
			// new round
			currentX = currentX - 1;
			currentY = currentY + 1;
		}
		return null;
	}


	/**
	 * Checks whether there is an unassigned Producer at a specified position.
	 *
	 * @param  graph  a graph object which contains all nodes and edges of a scenario
	 * @param  assigned	a list of all Producers that are already assigned to consumers
	 * @param  x	coordinate of the position that should be checked
	 * @param  y	coordinate of the position that should be checked
	 * @return        true if there is an unassigned Producer at the position or false if not
	 */
	private Boolean isProducerAtPosition(Graph<EnergyNode, PowerLine> graph, List<Producer> assigned, int x, int y) {

		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null.");
		}else if (assigned == null) {
			throw new IllegalArgumentException("assigned is not allowed to be null.");
		}

		if (x < 0 || y < 0 || x > 9 || y > 9) {
			return false;
		}

		List<Producer> producers = this.util.getProducers(graph);

		for (EnergyNode node : graph.getNodes()) {
			if ( (node.getXPos() == x) && (node.getYPos() == y) && (producers.contains(node)) && !(assigned.contains(node))) {
				return true;
			}
		}
		return false;

	}


	/**
	 * Checks whether there is an unassigned Producer at a specified position.
	 *
	 * @param  graph  a graph object which contains all nodes and edges of a scenario
	// * @param  assigned	a list of all Producers that are already assigned to consumers (false)
	 * @param  x	coordinate of the position that should be checked
	 * @param  y	coordinate of the position that should be checked
	// * @return  true if there is an unassigned Producer at the position or false if not (false)
	 * @return        node at the given position
	 */
	private EnergyNode getNodeAtPosition(Graph<EnergyNode, PowerLine> graph, int x, int y) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null.");
		}

		for (EnergyNode node : graph.getNodes()) {
			if (node.getXPos() == x && node.getYPos() == y) {
				return node;
			}
		}
		return null;

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