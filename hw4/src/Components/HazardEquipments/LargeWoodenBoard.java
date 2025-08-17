package Components.HazardEquipments;

import Components.Hazards.HazardEnum;
import Components.General.LakePuzzle;
import Components.General.Researcher;

/**
 * Represents a large wooden board used as equipment to handle the "Holl" hazard.
 */
public class LargeWoodenBoard extends HazardEquipmentItem {

    /**
     * Constructs a Large Wooden Board equipment item associated with the "Holl" hazard.
     */
    public LargeWoodenBoard() {
        super(HazardEnum.Holl);
    }

    /**
     * Provides a string representation of the equipment.
     *
     * @return "wb" as the identifier for this equipment.
     */
    @Override
    public String toString() {
        return "wb";
    }

    /**
     * Defines the specific usage behavior of the Large Wooden Board.
     *
     * @param map        The current game map.
     * @param researcher The researcher using the equipment.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) {
        // TODO: Implement the logic for using the wooden board to handle a "Holl" hazard.
    }

    /**
     * Retrieves the name of the equipment.
     *
     * @return "Large wooden board" as the name of this equipment.
     */
    @Override
    public String nameOfEquipment() {
        return "Large wooden board";
    }

    public String warning() {
        return "\n!!! Researcher comes across a hole in ice. However, Researcher is carrying a Large Wooden Board.\n" +
                "Researcher covers the ice with the board and starts standing on it.";
    }
}
