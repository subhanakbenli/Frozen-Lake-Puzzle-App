package Components.HazardEquipments;

import Components.Interfaces.Equipment;
import Components.Hazards.HazardEnum;
import Components.General.MapItem;

/**
 * Represents an abstract base class for equipment designed to handle specific hazards in the game.
 * Extends the MapItem class and implements the Equipment interface, providing functionality
 * for associating the equipment with a specific type of hazard.
 */
public abstract class HazardEquipmentItem extends MapItem implements Equipment {

    /** The type of hazard this equipment is designed to handle. */
    private HazardEnum hazardEnum;

    /**
     * Constructor to initialize the hazard equipment with a specific hazard type.
     *
     * @param hazardEnum The hazard type associated with this equipment.
     */
    public HazardEquipmentItem(HazardEnum hazardEnum) {
        this.hazardEnum = hazardEnum;
    }

    /**
     * Retrieves the hazard type this equipment is designed to address.
     *
     * @return The associated HazardEnum value.
     */
    public HazardEnum getHazardEnum() {
        return hazardEnum;
    }
    public abstract  String warning();
}

