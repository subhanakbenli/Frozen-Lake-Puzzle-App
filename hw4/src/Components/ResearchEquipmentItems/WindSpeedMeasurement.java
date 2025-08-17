package Components.ResearchEquipmentItems;

import Components.Hazards.HazardItem;
import Components.Hazards.IceBlockItem;
import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The WindSpeedMeasurement class represents a piece of research equipment used to measure wind speed.
 * It can be used by a researcher to measure the wind speed in a specific location on the map,
 * provided the location is not hazardous (i.e., not near an IceBlockItem).
 */
public class WindSpeedMeasurement extends ResearchEquipmentItem {

    /**
     * Default constructor for WindSpeedMeasurement.
     */
    public WindSpeedMeasurement() {
        super();
    }

    /**
     * Generates a random wind speed between 0 and 30.
     *
     * @return A random wind speed value.
     */
    private int generateRandomWind() {
        return (int) (Math.random() * 31); // Generates a number in the range [0, 30]
    }

    /**
     * Uses the wind speed measurement equipment to measure the wind speed at the researcher's current location.
     * The equipment can only be used if there is no hazard (or the hazard is an IceBlockItem).
     *
     * @param map The map where the wind speed measurement will take place.
     * @param researcher The researcher using the wind speed measurement equipment.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException {
        MapSquare currentResearcherPosition = map.findResearcherSquare(researcher);
        HazardItem hazard = currentResearcherPosition.getHazard();

        // Check if the location is safe for using the equipment (no hazard or ice block hazard)
        if (hazard == null || hazard instanceof IceBlockItem) {
            int windSpeed = generateRandomWind();
            currentResearcherPosition.setItem(this);
            researcher.removeEquipment(this);
            super.finishTask();
            super.setMessage("Wind Speed Measurement: " + windSpeed + " m/s");
            System.out.println("The selected research equipment has been placed in the current location.");
        } else {
            throw new IncompatibleResearchEquipmentLocationException("*** The selected research equipment is incompatible with the current location.");
        }
    }

    /**
     * Returns the text description of the goal associated with the wind speed measurement item.
     *
     * @return The goal description.
     */
    @Override
    public String textOfGoal() {
        return "Wind Speed Measurement";
    }

    /**
     * Returns the name of the equipment.
     *
     * @return The equipment name.
     */
    @Override
    public String nameOfEquipment() {
        return "Wind speed detector";
    }

    public String toString() {
        return "ws";
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
