package Components.Hazards;

import Components.General.LakePuzzle;
import Components.General.MapItem;
import Components.General.Researcher;
import Components.Interfaces.Hazard;

/**
 * The HazardItem class represents a general hazard in the game.
 * It serves as the base class for specific types of hazards like CliffEdgeItem.
 * Each hazard is associated with a specific HazardEnum to identify the hazard type.
 */
public abstract class HazardItem extends MapItem implements Hazard {
    private HazardEnum hazardEnum;

    /**
     * Constructor for the HazardItem.
     * Initializes the hazard with a specific HazardEnum value.
     *
     * @param hazardEnum The type of hazard this item represents.
     */
    public HazardItem(HazardEnum hazardEnum) {
        this.hazardEnum = hazardEnum;
    }

    /**
     * Retrieves the HazardEnum associated with this hazard item.
     *
     * @return The HazardEnum value that represents the type of hazard.
     */
    public HazardEnum getHazardEnum() {
        return this.hazardEnum;
    }

    /**
     * Sets the HazardEnum value for this hazard item.
     * This method allows for changing the hazard type after the item is created.
     *
     * @param hazardEnum The new HazardEnum value to set.
     */
    public void setHazardEnum(HazardEnum hazardEnum) {
        this.hazardEnum = hazardEnum;
    }

    /**
     * Defines how the researcher interacts with the hazard.
     * This method must be implemented by subclasses to specify the interaction logic.
     *
     * @param researcher The researcher interacting with the hazard.
     * @param map The map where the interaction takes place.
     */
    @Override
    public abstract void interact(Researcher researcher, LakePuzzle map);

}
