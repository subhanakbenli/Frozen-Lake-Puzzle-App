package Components.Hazards;

import Components.General.LakePuzzle;
import Components.General.Researcher;

/**
 * The IceBlockItem class represents a hazard where the researcher encounters an ice block.
 * The researcher must deal with this hazard, potentially using equipment.
 */
public class IceBlockItem extends HazardItem {

    /**
     * Constructor for the IceBlockItem.
     * Initializes the hazard with the corresponding HazardEnum value.
     */
    public IceBlockItem() {
        super(HazardEnum.IceBlock);
    }

    /**
     * Interacts with the researcher when encountering the ice block hazard.
     * In this example, the researcher's status changes or a message is displayed.
     * Equipment could be used to resolve the hazard.
     *
     * @param researcher The researcher interacting with the ice block hazard.
     * @param map The map where the interaction takes place.
     */
    @Override
    public void interact(Researcher researcher , LakePuzzle map) {
        // Example interaction: Display a message indicating the researcher encountered the hazard
        System.out.println("Researcher " + researcher.getId() + " encountered an Ice Block!");

        // Equipment check could be added here
        // researcher.useEquipment(); // Appropriate method to use equipment could be called here
    }

    /**
     * Returns a string representation of the hazard.
     * This is used for identifying the hazard in the map or logging.
     *
     * @return A string representing the ice block hazard type.
     */
    @Override
    public String toString(){
        return "ib";  // Represents IceBlockItem (ib)
    }
}
