package Components.HazardEquipments;

import Components.Hazards.HazardEnum;
import Components.General.LakePuzzle;
import Components.General.Researcher;

/**
 * Represents a protective helmet used as equipment to handle the "IceSpike" hazard.
 */
public class ProtectiveHelmet extends HazardEquipmentItem {

    /**
     * Constructs a Protective Helmet equipment item associated with the "IceSpike" hazard.
     */
    public ProtectiveHelmet() {
        super(HazardEnum.IceSpike);
    }

    /**
     * Provides a string representation of the equipment.
     *
     * @return "ph" as the identifier for this equipment.
     */
    @Override
    public String toString() {
        return "ph";
    }

    /**
     * Defines the specific usage behavior of the Protective Helmet.
     *
     * @param map        The current game map.
     * @param researcher The researcher using the equipment.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) {
        // TODO: Implement the logic for using the protective helmet against "IceSpike" hazards.
        // Example: Prevent damage to the researcher or enable passage through the hazard.
    }

    /**
     * Retrieves the name of the equipment.
     *
     * @return "Protective helmet" as the name of this equipment.
     */
    @Override
    public String nameOfEquipment() {
        return "Protective helmet";
    }

    public String warning() {
        return "\n!!! Researcher comes across a sharp ice spike. However, Researcher is wearing a Protective Helmet.\n" +
                "Researcher carefully navigates around the spike without any harm.";
    }
}
