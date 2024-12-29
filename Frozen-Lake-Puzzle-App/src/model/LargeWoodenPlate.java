package model;

/**
 * Class representing a large wooden plate hazard equipment.
 */
public class LargeWoodenPlate extends HazardEquipment {

    public LargeWoodenPlate(String field) {
        super(field, Hazard.Holl);
    }

    @Override
    public void method() {
        System.out.println("Large wooden plate used for Holl hazard.");
    }
}
