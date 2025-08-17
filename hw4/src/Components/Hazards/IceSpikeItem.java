package Components.Hazards;

import Components.Interfaces.Equipment;
import Components.HazardEquipments.HazardEquipmentItem;
import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;

import java.util.Iterator;
import java.util.Set;

/**
 * The IceSpikeItem class represents a hazard where the researcher encounters sharp ice spikes.
 * The researcher can interact with this hazard using specific equipment.
 */
public class IceSpikeItem extends HazardItem {

    /**
     * Constructor for the IceSpikeItem.
     * Initializes the hazard with the corresponding HazardEnum value.
     */
    public IceSpikeItem() {
        super(HazardEnum.IceSpike);
    }

    /**
     * Interacts with the researcher when encountering the ice spike hazard.
     * The researcher uses specific equipment to neutralize the hazard, or else the researcher dies.
     *
     * @param researcher The researcher interacting with the ice spike hazard.
     * @param map The map where the interaction takes place.
     */
    @Override
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
     * @return A string representing the ice spike hazard type.
     */
    @Override
    public String toString() {
        return "is";  // Represents IceSpikeItem (is)
    }
}
