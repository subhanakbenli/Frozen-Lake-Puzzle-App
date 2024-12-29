package model;

/**
 * Class representing the Cliffedge hazard.
 */
public class Cliffedge extends HazardBase {

    public Cliffedge() {
        super(Hazard.Cliffedge);
    }

    @Override
    public void method() {
        System.out.println("Method for Cliffedge hazard.");
    }
}