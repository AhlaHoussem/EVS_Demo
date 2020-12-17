package solutions.exercise1;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;


import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractScenarioUtilTest;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;


/**
 * Tests all methods of the class ScenarioUtilImpl
 *
 * @author G10T01
 * @version 1.1
 * @since 10-11-2020
 */
public class ScenarioUtilTest extends AbstractScenarioUtilTest implements ExerciseSubmission {

	@Override
	@Test
	public void testGetPowerLinesByType() {
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
		assertThat("Number of high voltage lines", highVoltageLines.size(), is(11));

		List<PowerLine> lowVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3));

		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.MEDIUM_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(4));
	}

	@Override
	@Test
	public void testGetPowerLinesByType_Parameters() {
		try {
			sut.getPowerLinesByType(null, null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));

		}

		try {
			sut.getPowerLinesByType(null, PowerLineType.HIGH_VOLTAGE);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}

		try {
			sut.getPowerLinesByType(graph1, null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}
	}


	/**
	 * Tests the functionality of the method getControllableProducers
	 */
	@Override
	@Test
	public void testGetControllableProducers() {
		List<ControllableProducer> controllableProducerList= sut.getControllableProducers(graph1);
		assertThat("Number of all Controllable Producers", controllableProducerList.size(), is(4));
	}


	/**
	 * Tests the throwing of IllegalArgumentException of the method getControllableProducers
	 */
	@Override
	@Test
	public void testGetControllableProducers_Parameters() {
		try {
			sut.getControllableProducers(null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}
	}


	/**
	 * Tests the functionality of the method getControllableConsumers
	 */
	@Override
	@Test
	public void testGetControllableConsumers() {
		List<ControllableConsumer> controllableConsumerList= sut.getControllableConsumers(graph1);
		assertThat("Number of all controllable consumers", controllableConsumerList.size(), is(3));
	}


	/**
	 * Tests the throwing of IllegalArgumentException of the method getControllableConsumers
	 */
	@Override
	@Test
	public void testGetControllableConsumers_Parameters() {
		try {
			sut.getControllableConsumers(null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}
	}


	/**
	 * Tests the functionality of the method getProducers
	 */
	@Override
	@Test
	public void testGetProducers() {
		List<Producer> producersList= sut.getProducers(graph1);
		assertThat("Number of producers", producersList.size(), is(10));
	}


	/**
	 * Tests the throwing of IllegalArgumentException of the method getProducers
	 */
	@Override
	@Test
	public void testGetProducers_Parameters() {
		try {
			sut.getProducers(null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}
	}


	/**
	 * Tests the functionality of the method getConsumers
	 */
	@Override
	@Test
	public void testGetConsumers() {
		List<Consumer> consumerList= sut.getConsumers(graph1);
		assertThat("Number of consumers", consumerList.size(), is(8));
	}


	/**
	 * Tests the throwing of IllegalArgumentException of the method getConsumers
	 */
	@Override
	@Test
	public void testGetConsumers_Parameters() {
		try {
			sut.getConsumers(null);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			//Expected IllegalArgumentException
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
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


