package Components.ResearchEquipmentItems;

import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The TemperatureDetectorItem class represents a piece of research equipment used for measuring
 * temperature. It can be used by a researcher to measure the temperature in a specific location
 * on the map, provided the location is not near an ice block.
 */
public class TemperatureDetectorItem extends ResearchEquipmentItem {

    /**
     * Default constructor for TemperatureDetectorItem.
     */
    public TemperatureDetectorItem() {
        super();
    }

    /**
     * Uses the temperature detector to measure the temperature at the researcher's current location
     * on the map. If the location is near an ice block or at the map's edge, the equipment cannot be used.
     *
     * @param map The map where the temperature detector will be used.
     * @param researcher The researcher using the temperature detector.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException {
        MapSquare currentResearcherSquare = map.findResearcherSquare(researcher);
        int[] currentPosition = currentResearcherSquare.getPosition();

        // Check if the location is at the map's edge or near an ice block
        if (map.isEdgeOrNextToIceBlock(currentPosition[1], currentPosition[0])) {

            throw new IncompatibleResearchEquipmentLocationException("*** The selected research equipment is incompatible with the current location.");
        }

        int temperature = generateRandomTemperature();
        currentResearcherSquare.setItem(this);
        researcher.removeEquipment(this);
        super.finishTask();
        super.setMessage("Measured temperature: " + temperature + " °C");
        System.out.println("The selected research equipment has been placed in the current location.");

    }

    /**
     * Generates a random temperature value between -30 and 0 degrees Celsius.
     *
     * @return A random temperature value.
     */
    private int generateRandomTemperature() {
        return (int) (Math.random() * 31) - 30; // Generates a number in the range [-30, 0]
    }

    /**
     * Returns the text description of the goal associated with the temperature detector item.
     *
     * @return The goal description.
     */
    @Override
    public String textOfGoal() {
        return "Temperature Measurement";
    }

    /**
     * Returns the name of the equipment.
     *
     * @return The equipment name.
     */
    @Override
    public String nameOfEquipment() {
        return "Temperature detector";
    }

    public String toString() {
        return "td";
    }

    @Override
    public boolean isAccomplish(){
        return super.isAccomplish();
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
