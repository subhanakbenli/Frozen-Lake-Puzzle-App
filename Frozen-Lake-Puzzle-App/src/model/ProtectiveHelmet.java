package model;

/**
 * Class representing a protective helmet.
 */
public class ProtectiveHelmet extends HazardEquipment {

    public ProtectiveHelmet(String field) {
        super(field, Hazard.IceSpike);
    }

    @Override
    public void method() {
        System.out.println("Protective helmet used for IceSpike hazard.");
    }
}