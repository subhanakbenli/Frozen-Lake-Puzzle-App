package model;

/**
 * Class representing the IceBlock hazard.
 */
public class IceBlock extends HazardBase {

    public IceBlock() {
        super(Hazard.IceBlock);
    }

    @Override
    public void method() {
        System.out.println("Method for IceBlock hazard.");
    }
}
