package Components.Hazards;

import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;
import Components.HazardEquipments.HazardEquipmentItem;
import Components.Interfaces.Equipment;

import java.util.Iterator;
import java.util.Set;

/**
 * The CliffEdgeItem class represents a cliff edge hazard in the game.
 * A researcher can interact with this hazard using specific equipment to prevent falling off the cliff.
 */
public class CliffEdgeItem extends HazardItem {

    /**
     * Constructor for the CliffEdgeItem.
     * Initializes the hazard with the corresponding HazardEnum value.
     */
    public CliffEdgeItem() {
        super(HazardEnum.Clifedge);
    }

    /**
     * Interacts with the researcher and checks for available equipment that can prevent falling off the cliff.
     * If the researcher has the required equipment, the hazard is neutralized.
     * If no equipment is available, the researcher "dies" (the interaction fails).
     *
     * @param researcher The researcher interacting with the hazard.
     * @param map The map where the interaction takes place.
     */
    public void interact(Researcher researcher, LakePuzzle map) {
        MapSquare currentSquare = map.findWithEquipment(this);
        Set<Equipment> equipments = researcher.getEquipmentSet();
        Iterator<Equipment> iter = equipments.iterator();
        boolean isPlayerKilled = true;

        // Loop through all available equipment to find the correct one for this hazard
        while (iter.hasNext()) {
            Equipment currentEquipment = iter.next();
            if (currentEquipment instanceof HazardEquipmentItem) {
                // Check if the equipment matches the hazard
                if (((HazardEquipmentItem) currentEquipment).getHazardEnum() == this.getHazardEnum()) {
                    ((HazardEquipmentItem) currentEquipment).warning();
                    currentSquare.removeItem();
                    currentSquare.setItem(((HazardEquipmentItem) currentEquipment));
                    researcher.removeEquipment(currentEquipment);
                    System.out.println(researcher.bagText());
                    isPlayerKilled = false;
                    break;
                }
            }
        }

        // If no equipment was found, the researcher "dies"
        if (isPlayerKilled) {

            researcher.kill();
        }
    }

    /**
     * Returns a string representation of the hazard.
     * This is used for identifying the hazard in the map or logging.
     *
     * @return A string representing the hazard type.
     */
    @Override
    public String toString() {
        return "ce";  // Represents CliffEdgeItem (ce)
    }
}
