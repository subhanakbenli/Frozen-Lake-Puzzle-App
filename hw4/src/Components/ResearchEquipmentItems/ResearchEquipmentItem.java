package Components.ResearchEquipmentItems;

import Components.Interfaces.Equipment;
import Components.General.LakePuzzle;
import Components.General.MapItem;
import Components.General.Researcher;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The ResearchEquipmentItem class represents an abstract base class for all research equipment items.
 * It provides common functionality for equipment, such as managing task completion status, messages,
 * and the abstract method for using the equipment.
 */
public abstract class ResearchEquipmentItem extends MapItem implements Equipment {

    private boolean isFinished = false;
    private String message;

    /**
     * Abstract method that defines how the equipment is used on a specific map by a researcher.
     * This method must be implemented by subclasses.
     *
     * @param map The map where the equipment will be used.
     * @param researcher The researcher using the equipment.
     */
    public abstract void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException;

    /**
     * Checks whether the task associated with this equipment has been accomplished.
     *
     * @return true if the task is finished, false otherwise.
     */
    public boolean isAccomplish() {
        return isFinished;
    }

    /**
     * Marks the task associated with this equipment as finished.
     */
    public void finishTask() {
        this.isFinished = true;
    }

    /**
     * Retrieves the message associated with the equipment.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a new message for the equipment.
     *
     * @param newMessage The new message to be set.
     */
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    /**
     * Abstract method that returns a textual description of the goal associated with the equipment.
     * This method must be implemented by subclasses.
     *
     * @return The goal description.
     */
    public abstract String textOfGoal();
}
