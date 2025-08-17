package Components.Interfaces;

import Components.General.LakePuzzle;
import Components.General.Researcher;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The Equipment interface defines the common functionality for all research equipment items.
 * Any class implementing this interface must provide implementations for using the equipment
 * and retrieving its name.
 */
public interface Equipment {

    /**
     * Defines the method for using the equipment in a given map by a researcher.
     *
     * @param map The map where the equipment will be used.
     * @param researcher The researcher who will use the equipment.
     */
    void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException;

    /**
     * Retrieves the name of the equipment.
     *
     * @return The name of the equipment.
     */
    String nameOfEquipment();
}
