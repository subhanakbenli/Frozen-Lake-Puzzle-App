package Components.ResearchEquipmentItems;

import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;
import Components.Hazards.CliffEdgeItem;
import Components.Hazards.HazardItem;
import Components.Interfaces.Hazard;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The CameraItem class represents a research equipment item, the camera, that can be used
 * to capture data on a given map. It interacts with a researcher and may encounter hazards
 * in its path.
 */
public class CameraItem extends ResearchEquipmentItem  {

    private boolean isCameraStartedSuccessfully;

    /**
     * Default constructor for CameraItem.
     */
    public CameraItem() {
        super();
    }

    /**
     * Uses the camera on the map to capture data, starting from the researcher's current position.
     * If a hazard, such as a cliff edge, is encountered, it places the equipment and removes it
     * from the researcher's inventory.
     *
     * @param map The map where the camera will be used.
     * @param researcher The researcher using the camera.
     */
    public void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException{
        int[] mapSize = map.getMapSize();
        MapSquare researcherSquare = map.findResearcherSquare(researcher);
        int[] position = researcherSquare.getPosition();
        int direction = map.getCliffEdgeDirection();
        int row = position[1];
        int col = position[0];
        HazardItem hazard = null;

        // Loop through the map depending on the cliff edge direction
        switch (direction) {
            case 0: // Right
                for (col = position[0]; col < mapSize[0]; col++) {
                    MapSquare square = map.getMapSquare(row, col);
                    hazard = square.getHazard();
                    if (hazard != null) {
                        break;

                    }
                }
                break;
            case 1: // Bottom
                for (row = position[1]; row < mapSize[1]; row--) {
                    MapSquare square = map.getMapSquare(row, col);
                    hazard = square.getHazard();
                    if (hazard != null) {
                        break;

                    }
                }
                break;
            case 2: // Left
                for (col = position[0]; col >= 0; col--) {
                    MapSquare square = map.getMapSquare(row, col);
                    hazard = square.getHazard();
                    if (hazard != null) {
                        break;

                    }
                }
                break;
            // Add more cases for other directions if necessary
        }

        // Simulate the camera working successfully or not
        String text = "";
        int isWorked = (int) (Math.random() * 11);
        if (isWorked < 8) {
            isCameraStartedSuccessfully = true;
            text += "The camera start recording.";
        } else {
            isCameraStartedSuccessfully = false;
            text += " The camera failed to start recording.";
        }

        // If hazard is a cliff edge, place the camera and finish the task
        if (hazard instanceof CliffEdgeItem) {
            researcherSquare.setItem(this);
            researcher.removeEquipment(this);
            super.finishTask();
            super.setMessage("Camera Placement: "+text );
            System.out.println("The selected research equipment has been placed in the current location.");
        } else {
            throw new IncompatibleResearchEquipmentLocationException("*** The selected research equipment is incompatible with the current location.");
        }
    }

    /**
     * Returns the text description of the goal associated with the camera item.
     *
     * @return The goal description.
     */
    @Override
    public String textOfGoal() {
        return "Camera Placement";
    }

    /**
     * Returns the name of the equipment.
     *
     * @return The equipment name.
     */
    @Override
    public String nameOfEquipment() {
        return "Camera";
    }

    @Override
    public String toString(){
        return "cm";
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
