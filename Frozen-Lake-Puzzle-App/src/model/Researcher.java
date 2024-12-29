package model;
/**
 * Class representing a researcher.
 */
public class Researcher {
    private int id;
    private boolean isTired;
    private ResearcherEquipment equipment;

    public Researcher(int id, boolean isTired, ResearcherEquipment equipment) {
        this.id = id;
        this.isTired = isTired;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public ResearcherEquipment getEquipment() {
        return equipment;
    }

    public void move() {
        System.out.println("Researcher is moving.");
    }
}
