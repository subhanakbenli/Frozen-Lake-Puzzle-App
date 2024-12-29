package model;

/**
 * Class representing the IceSpike hazard.
 */
public class IceSpike extends HazardBase {

    public IceSpike() {
        super(Hazard.IceSpike);
    }

    @Override
    public void method() {
        System.out.println("Method for IceSpike hazard.");
    }
}