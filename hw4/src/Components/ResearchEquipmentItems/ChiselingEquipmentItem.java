package Components.ResearchEquipmentItems;

import Components.General.LakePuzzle;
import Components.General.MapSquare;
import Components.General.Researcher;
import Components.Hazards.HazardItem;
import Components.Hazards.IceBlockItem;
import exceptions.IncompatibleResearchEquipmentLocationException;

/**
 * The ChiselingEquipmentItem class represents a piece of research equipment used
 * for chiseling ice samples from ice blocks. It interacts with a researcher and
 * works only when the researcher is located on a square with an ice block hazard.
 */
public class ChiselingEquipmentItem extends ResearchEquipmentItem {

    private int weightOfSample;

    /**
     * Uses the chiseling equipment to collect an ice sample if the current location
     * contains an IceBlockItem hazard. Once successful, it places the equipment in
     * the current square and removes it from the researcher's inventory.
     *
     * @param map The map where the chiseling equipment will be used.
     * @param researcher The researcher using the chiseling equipment.
     */
    @Override
    public void use(LakePuzzle map, Researcher researcher) throws IncompatibleResearchEquipmentLocationException {
        MapSquare currentResearcherSquare = map.findResearcherSquare(researcher);
        int[] position = currentResearcherSquare.getPosition();
        boolean isNextToIceBlock = map.isNextToIceBlock(position[1],position[0]);

        // Check if the hazard is an IceBlockItem
        if (isNextToIceBlock) {
            generateRandomWeight();
            currentResearcherSquare.setItem(this);
            researcher.removeEquipment(this);
            super.finishTask();
            super.setMessage("Glacial Sampling: "+this.weightOfSample + " gr");
            System.out.println("The selected research equipment has been placed in the current location.");
        } else {
            throw new IncompatibleResearchEquipmentLocationException("*** The selected research equipment is incompatible with the current location.");
        }
    }

    /**
     * Generates a random weight for the ice sample between 0 and 20.
     */
    private void generateRandomWeight() {
        this.weightOfSample = (int) (Math.random() * 21);
    }

    /**
     * Returns the text description of the goal associated with the chiseling equipment item.
     *
     * @return The goal description.
     */
    @Override
    public String textOfGoal() {
        return "Glacial Sampling";
    }

    /**
     * Returns the name of the equipment.
     *
     * @return The equipment name.
     */
    @Override
    public String nameOfEquipment() {
        return "Chiseling equipment";
    }


    @Override
    public String toString(){
        return "ch";
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
