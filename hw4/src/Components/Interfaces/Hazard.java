package Components.Interfaces;

import Components.General.LakePuzzle;
import Components.General.Researcher;

/**
 * The Hazard interface defines the common functionality for all hazards in the game.
 * Any class implementing this interface must provide an implementation for interacting
 * with the researcher and affecting the game map.
 */
public interface Hazard {

    /**
     * Defines the method for interacting with a researcher and affecting the map.
     * This interaction could result in a variety of outcomes depending on the type of hazard.
     *
     * @param researcher The researcher interacting with the hazard.
     * @param map The map where the hazard is located and the interaction takes place.
     */
    void interact(Researcher researcher, LakePuzzle map);
}
