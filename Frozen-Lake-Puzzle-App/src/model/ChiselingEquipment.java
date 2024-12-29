package model;

/**
 * Class representing chiseling equipment.
 */
public class ChiselingEquipment extends ResearcherEquipment {

    public ChiselingEquipment(String field) {
        super(field, "Chiseling Equipment");
    }

    @Override
    public void method() {
        System.out.println("Chiseling equipment in use.");
    }
}