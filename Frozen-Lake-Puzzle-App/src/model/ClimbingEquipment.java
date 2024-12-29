package model;

/**
 * Class representing climbing equipment.
 */
public class ClimbingEquipment extends HazardEquipment {

    public ClimbingEquipment(String field) {
        super(field, Hazard.Cliffedge);
    }

    @Override
    public void method() {
        System.out.println("Climbing equipment used for Cliffedge hazard.");
    }
}