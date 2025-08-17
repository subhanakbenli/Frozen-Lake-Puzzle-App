/**
 * Represents a Researcher in the game.
 * A researcher can carry equipment, move on the map, and interact with hazards and items.
 */
package Components.General;

import Components.ResearchEquipmentItems.ResearchEquipmentItem;
import exceptions.IncorrectBagContentsException;
import exceptions.UnavailableDirectionException;
import Components.Interfaces.Equipment;
import Components.Hazards.HazardEnum;
import Components.Hazards.HazardItem;
import Components.Hazards.IceBlockItem;
import exceptions.UnavailableEquipmentException;

import java.util.ArrayList;
import java.util.Set;

/**
 * The Researcher class defines the behavior and properties of a researcher in the game.
 * Researchers can carry equipment, move on the map, and interact with items and hazards.
 */
public class Researcher extends MapItem {


    private boolean isAlive = true;

    public boolean researcherAlive(){
        return isAlive;
    }
    /**
     * Unique identifier for the researcher.
     */
    private final int id;

    /**
     * Bag to store equipment carried by the researcher.
     */
    private final EquipmentBag<Equipment> equipmentBag;

    /**
     * Maximum number of equipment items a researcher can carry.
     */
    private static final int MAX_EQUIPMENT = 3;

    public void kill(){
        isAlive = false;
    }

    /**
     * Constructs a Researcher with a unique ID.
     * @param id the unique identifier for the researcher. Must be greater than 0.
     * @throws IllegalArgumentException if the ID is less than or equal to 0.
     */
    public Researcher(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }
        this.id = id;
        this.equipmentBag = new EquipmentBag<>();
    }

    /**
     * Retrieves the ID of the researcher.
     * @return the researcher's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Adds equipment to the researcher's bag.
     * @param equipment the equipment to be added.
     * @throws IncorrectBagContentsException if the bag is full or contains equipment of a different type.
     */
    public void addEquipment(Equipment equipment) throws IncorrectBagContentsException {
        if (equipmentBag.size() >= MAX_EQUIPMENT) {
            throw new IncorrectBagContentsException("*** Cannot carry more than 3 pieces of equipment.");
        }

        if (!equipmentBag.isEmpty()) {
            Equipment firstItem = equipmentBag.getEquipmentSet().iterator().next();
            if (!equipment.getClass().getSuperclass().equals(firstItem.getClass().getSuperclass())) {
                throw new IncorrectBagContentsException("*** Researchers cannot carry different types of equipment in their bags.");
            }
        }
        equipmentBag.addEquipment(equipment);
    }

    /**
     * Removes equipment from the researcher's bag.
     * @param equipment the equipment to be removed.
     * @return true if the equipment was successfully removed, false otherwise.
     */
    public boolean removeEquipment(Equipment equipment) {
        return equipmentBag.removeEquipment(equipment);
    }

    /**
     * Retrieves the set of equipment carried by the researcher.
     * @return a set of equipment items in the researcher's bag.
     */
    public Set<Equipment> getEquipmentSet() {
        return equipmentBag.getEquipmentSet();
    }

    /**
     * Retrieves a specific piece of equipment by index.
     * @param index the index of the equipment to retrieve.
     * @return the equipment at the specified index.
     */
    public Equipment getEquipment(int index) {
        return (Equipment) equipmentBag.getEquipmentSet().toArray()[index];
    }

    /**
     * Checks if the researcher has any equipment.
     * @return true if the researcher is carrying equipment, false otherwise.
     */
    public boolean hasEquipment() {
        return !equipmentBag.isEmpty();
    }


    public boolean isHaveReseachItem(){
        return (!equipmentBag.isEmpty() && getEquipment(0) instanceof ResearchEquipmentItem);
    }

    public boolean isHaveThisItemWithCode(String code ){
        return equipmentBag.isContain(code);
    }

    public Equipment getItemWithCode(String code,LakePuzzle lakePuzzle) throws UnavailableEquipmentException  {

        if(equipmentBag.isContain(code)){
            Equipment equipment = equipmentBag.getEquipment(code);
            return equipment;
        }else {
            throw new UnavailableEquipmentException("Researcher is not have item with code: " + code);
        }
    }

    /**
     * Moves the researcher in the specified direction on the map.
     * @param DirectionInput the direction to move ("U" for up, "D" for down, "L" for left, "R" for right).
     * @param map the game map.
     * @return the updated game map after the movement.
     * @throws UnavailableDirectionException if the move is invalid or blocked by an obstacle.
     */
    public ArrayList<ArrayList<MapSquare>> move(String DirectionInput, LakePuzzle map) throws UnavailableDirectionException {
        // Find current position
        ArrayList<ArrayList<MapSquare>> gameMap = map.getMap();
        int currentRow = -1;
        int currentCol = -1;
        for (int i = 0; i < gameMap.size(); i++) {
            for (int j = 0; j < gameMap.get(i).size(); j++) {
                if (gameMap.get(i).get(j).getResearcher() != null && gameMap.get(i).get(j).getResearcher().equals(this)) {
                    currentRow = i;
                    currentCol = j;
                    break;
                }
            }
            if (currentRow != -1) break;
        }

        // Define direction vectors
        int rowChange = 0;
        int colChange = 0;
        switch (DirectionInput) {
            case "U": rowChange = -1; break;
            case "D": rowChange = 1; break;
            case "L": colChange = -1; break;
            case "R": colChange = 1; break;
        }

        // Check first move
        int nextRow = currentRow + rowChange;
        int nextCol = currentCol + colChange;

        if (nextRow < 0 || nextRow >= gameMap.size() ||
                nextCol < 0 || nextCol >= gameMap.get(0).size() ||
                gameMap.get(nextRow).get(nextCol).getResearcher() != null ||
                (gameMap.get(nextRow).get(nextCol).getItem() != null &&
                        (gameMap.get(nextRow).get(nextCol).getItem().getClass() == IceBlockItem.class ||
                                gameMap.get(nextRow).get(nextCol).getItem().getClass() == WallItem.class ||
                                gameMap.get(nextRow).get(nextCol).getItem().getClass() == EntranceItem.class))) {
            throw new UnavailableDirectionException("Cannot move in that direction");
        }

        // Continue moving until hitting obstacle or edge
        int finalRow = currentRow;
        int finalCol = currentCol;

        while (true) {
            int testRow = finalRow + rowChange;
            int testCol = finalCol + colChange;

            if (testRow < 0 || testRow >= gameMap.size() ||
                    testCol < 0 || testCol >= gameMap.get(0).size()) {
                break;
            } else if (gameMap.get(testRow).get(testCol).hasResearcher()) {
                break;
            } else if (gameMap.get(testRow).get(testCol).getItem() != null) {
                MapItem item = gameMap.get(testRow).get(testCol).getItem();
                if (item instanceof HazardItem) {
                    if (((HazardItem) item).getHazardEnum() == HazardEnum.IceBlock) {
                        break;
                    } else {
                        ((HazardItem) item).interact(this, map);
                        finalRow = testRow;
                        finalCol = testCol;
                        break;
                    }
                } else if (item instanceof Equipment) {
                    finalRow = testRow;
                    finalCol = testCol;
                    break;
                } else {
                    break;
                }
            }

            finalRow = testRow;
            finalCol = testCol;
        }

        // Update map
        gameMap.get(finalRow).get(finalCol).setResearcher(this);
        gameMap.get(currentRow).get(currentCol).removeResearcher();

        return gameMap;
    }

    /**
     * Returns a string representation of the researcher.
     * @return a string in the format "r" followed by the researcher's ID.
     */
    @Override
    public String toString() {
        return "r" + id;
    }
    public String isBagEmpty() throws IncorrectBagContentsException {
        if (equipmentBag.isEmpty()) {
            throw new IncorrectBagContentsException("*** Researchers cannot head to the lake with an empty bag.");
        }
        return "The equipment bag is not empty.";
    }

    public String bagText(){

        String str ="\n- The updated contents of the bag of Researcher "+this.getId() +": ";

        for (Equipment e : this.getEquipmentSet()){
            str += e.toString() + " ";
        }
        return str;
    }

}
