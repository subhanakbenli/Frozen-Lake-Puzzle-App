package Components.HazardEquipments;

import Components.Hazards.HazardEnum;
import Components.General.LakePuzzle;
import Components.General.Researcher;

/**
 * The ClimbingEquipment class represents equipment used to interact with cliff-edge hazards.
 * This equipment allows the researcher to safely navigate such hazards.
 */
public class ClimbingEquipment extends HazardEquipmentItem {

    /**
     * Constructor for ClimbingEquipment.
     * Initializes the equipment with the corresponding HazardEnum value.
     */
    public ClimbingEquipment() {
        super(HazardEnum.Clifedge);
    }

    /**
     * Returns a string representation of the climbing equipment.
     * This is used for identifying the equipment in the map or logging.
     *
     * @return A string representing the climbing equipment type.
     */
    @Override
    public String toString() {
        return "cl";  // Represents ClimbingEquipment (cl)
    }

    /**
     * Defines how the climbing equipment is used.
     * This method currently has no specific implementation and should be expanded as needed.
     *
     * @param map The map where the equipment is being used.
     * @param researcher The researcher using the equipment.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) {
        // Implementation needed based on game logic
    }

    /**
     * Returns the name of the equipment.
     *
     * @return The name of the equipment, "Climbing equipment".
     */
    @Override
    public String nameOfEquipment() {
        return "Climbing equipment";
    }

    public String warning() {
        return "\n!!! Researcher comes across a cliff edge. However, Researcher is equipped with Climbing Equipment.\n" +
                "Researcher safely navigates the cliff edge.";
    }
}
