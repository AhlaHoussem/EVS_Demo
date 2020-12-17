package solutions.exercise1;

import java.util.ArrayList;
//import java.util.Deque;
//import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.ScenarioUtil;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

/**
 * Class ScenarioUtilImpl implements the scenario of the game
 *
 * @author G10T01
 * @version 1.1
 * @since 09-11-2020
 */


public class ScenarioUtilImpl implements ExerciseSubmission, ScenarioUtil {

    @Override
    public List<PlayfieldElement> getPlayfieldElementsByType(Scenario scenario, ElementType type) {
	if (scenario == null) {
	    throw new IllegalArgumentException("scenario is not allowed to be null.");
	}else if (type == null) {
	    throw new IllegalArgumentException("elementTyp is not allowed to be null.");
	}

	List<PlayfieldElement> ret = new ArrayList<>();
	for (int x = 0; x < scenario.getPlayfield().getHorizontalSize(); x++) {
	    for (int y = 0; y < scenario.getPlayfield().getVerticalSize(); y++) {
			PlayfieldElement element = scenario.getPlayfield().getPlayfieldElement(x, y);
			if (element.getElementType() == type) {
		    	ret.add(element);
			}
	    }
	}
	return ret;
    }

	/**
	 * Searches for the given Powerlinetypes in the graph and puts them in a list
	 *
	 * @param graph A Graph which contains all nodes and edges of the scenario
	 * @param type The type of the powerLine
	 * @return ArrayList which contains all powerline objects of the given type
	 */
	@Override
	public List<PowerLine> getPowerLinesByType(Graph<EnergyNode, PowerLine> graph, PowerLineType type) {
		if (graph == null) {
			throw new IllegalArgumentException("scenario is not allowed to be null.");
		}else if (type == null) {
			throw new IllegalArgumentException("PowerLineTyp is not allowed to be null.");
		}

		List<PowerLine> alltypes = graph.getEdges(); //gets all edges of the graph
		List<PowerLine> linetype = new ArrayList<>(); // initialise an empty list
		for (PowerLine line : alltypes) {
			if (line.getType() == type) { //tests the given type
				linetype.add(line);
			}
		}
		return linetype;
	}


	/**
	 * Searches for all controllable producers of the given graph
	 *
	 * @param graph A graph, which contains all nodes and edges of a scenario
	 * @return ArrayList of all producer which are controllable
	 */
	@Override
	public List<ControllableProducer> getControllableProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null ");
		}

		Set<EnergyNode> nodes = graph.getNodes();
		List<ControllableProducer> controlledenergie = new ArrayList<>();
		for (EnergyNode node : nodes) {
			if (node instanceof ControllableProducer) {
				controlledenergie.add((ControllableProducer) node);
			}
		}
		return controlledenergie;
	}

	/**
	 * Searches for all controllable consumers of the given graph
	 *
	 * @param graph A graph object which contains all nodes and edges of a scenario
	 * @return ArrayList of all controllable consumers
	 */
	@Override
	public List<ControllableConsumer> getControllableConsumers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null ");
		}

		Set<EnergyNode> nodes = graph.getNodes();
		List<ControllableConsumer> controlledconsumer = new ArrayList<>();
		for (EnergyNode node : nodes) {
			if (node instanceof ControllableConsumer) {
				controlledconsumer.add((ControllableConsumer) node);
			}
		}
		return controlledconsumer;
	}

	/**
	 * Searches for all producers of the given graph
	 *
	 * @param graph A graph object which contains all nodes and edges of a scenario
	 * @return ArrayList which contains all producers in this graph
	 */
	@Override
	public List<Producer> getProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null ");
		}

		Set<EnergyNode> nodes = graph.getNodes();
		List<Producer> producerlist = new ArrayList<>();
		for (EnergyNode node : nodes) {
			if (node instanceof Producer) {
				producerlist.add((Producer) node);
			}
		}
		return producerlist;
	}

	/**
	 * Searches for all consumers of the given type
	 *
	 * @param graph A graph object which contains all nodes and edges of a scenario
	 * @return ArrayList which contains all consumers in this graph
	 */
	@Override
	public List<Consumer> getConsumers(Graph<EnergyNode, PowerLine> graph) {

		if (graph == null) {
			throw new IllegalArgumentException("graph is not allowed to be null ");
		}

		Set<EnergyNode> nodes = graph.getNodes();
		List<Consumer> consumerlist = new ArrayList<>();
		for (EnergyNode node : nodes) {
			if (node instanceof Consumer) {
				consumerlist.add((Consumer) node);
			}
		}
		return consumerlist;
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
