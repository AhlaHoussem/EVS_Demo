package solutions.exercise2;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.ConstructionCostCalculatorImpl;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

import java.util.Comparator;

/**
 * compares the build costs between two PlayFieldElements.
 *
 * @author G10T01
 * @version 2.1.2
 * @since 16-11-2020
 */

public class PlayfieldElementComparator implements Comparator<PlayfieldElement>, ExerciseSubmission {

    private  ConstructionCostCalculator constructionCostCalculator;
    private  ProducerType producerType ;


    /**
     * The constructor initialises the scenario and the producertype.
     *
     * @param scenario     The scenario which provides the location of every playfielements
     * @param producerType The specific typ of producer which should be build on the playfielelement
     */

    public PlayfieldElementComparator(ProducerType producerType, Scenario scenario) {
        if (scenario == null) {
            throw new NullPointerException("scenario is not allowed to be null.");
        } else if (producerType == null) {
            throw new NullPointerException("producerTyp is not allowed to be null.");
        }

        this.constructionCostCalculator = new ConstructionCostCalculatorImpl(scenario);  // initialise  a new instance from the class ConstructionCostCalculatorImpl with the specific szenario
        this.producerType = producerType;

    }



    /**
     * compares two different playfieldelements based on the build costs.
     * The PlayfieldElement with the lowest cost gets the best rating.
     *
     * @param e1 the first playfieldelement
     * @param e2 The second playfieldelement
     * @return 1 if the first playfieldelement has lower building costs
     *        -1 if the first playfieldelement has higher building costs
     *         0 if the building costs are equal
     */

    @Override
    public int compare(PlayfieldElement e1, PlayfieldElement e2) {
        if (e1 == null) {
            throw new NullPointerException(" the first PlayfieldElement is not allowed to be null.");
        } else if (e2 == null) {
            throw new NullPointerException(" the second PlayfieldElement is not allowed to be null.");
        }

        double costOfe1 = constructionCostCalculator.calculateCost(e1,producerType);
        double costOfe2 = constructionCostCalculator.calculateCost(e2,producerType);


        if ((costOfe1 - costOfe2) < 0) {        // the second PlayfieldElement is more expensive
            return 1;                           // the first PlayfieldElement is cheaper
        }
        else if ((costOfe1 - costOfe2) > 0) {   // the first PlayfieldElement is more expensive
            return -1;                          // the second PlayfieldElement is cheaper
        } else {
            return 0;
        }


    }


    /**
     * Identifies the group.
     *
     * @return the group's number and its specific team
     */

    @Override
    public String getTeamIdentifier() {
        return "G10T01";
    }

}


