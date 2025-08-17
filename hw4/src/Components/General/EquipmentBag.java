package Components.General;

import Components.Interfaces.Equipment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A generic container for managing a collection of equipment.
 *
 * @param <T> The type of equipment, constrained to implement the Equipment interface.
 */
public class EquipmentBag<T extends Equipment> {
    private final Set<T> equipmentSet;

    /**
     * Constructs an empty EquipmentBag.
     */
    public EquipmentBag() {
        this.equipmentSet = new HashSet<>();
    }

    /**
     * Adds equipment to the bag.
     *
     * @param equipment The equipment to add.
     * @return True if the equipment was successfully added; false if it already exists or is null.
     */
    public boolean addEquipment(T equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment cannot be null.");
        }
        return equipmentSet.add(equipment);
    }

    /**
     * Removes equipment from the bag.
     *
     * @param equipment The equipment to remove.
     * @return True if the equipment was successfully removed; false otherwise.
     */
    public boolean removeEquipment(T equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment cannot be null.");
        }
        return equipmentSet.remove(equipment);
    }

    /**
     * Retrieves all equipment in the bag.
     *
     * @return An unmodifiable view of the equipment set.
     */
    public Set<T> getEquipmentSet() {
        return Collections.unmodifiableSet(equipmentSet);
    }

    /**
     * Returns the number of equipment items in the bag.
     *
     * @return The size of the equipment set.
     */
    public int size() {
        return equipmentSet.size();
    }

    /**
     * Checks if the bag is empty.
     *
     * @return True if the bag is empty; false otherwise.
     */
    public boolean isEmpty() {
        return equipmentSet.isEmpty();
    }

    /**
     * Returns a string representation of the equipment bag.
     *
     * @return A string listing all equipment in the bag.
     */
    @Override
    public String toString() {
        return "EquipmentBag{" + "equipmentSet=" + equipmentSet + '}';
    }


    public boolean isContain(String str){
        for (T equipment : equipmentSet){
            if (equipment.toString().equals(str)){
                return true;
            }
        }
        return false;
    }

    public Equipment getEquipment(String str){
        for (T equipment : equipmentSet){
            if (equipment.toString().equals(str)){
                return equipment;
            }
        }
        return null;
    }
}
