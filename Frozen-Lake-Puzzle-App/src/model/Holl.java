package model;

/**
 * Class representing the Holl hazard.
 */
public class Holl extends HazardBase {

    public Holl() {
        super(Hazard.Holl);
    }

    @Override
    public void method() {
        System.out.println("Method for Holl hazard.");
    }
}