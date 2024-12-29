package model;
/**
 * Abstract base class for all hazard types.
 */
public abstract class HazardBase {
    private Hazard type;

    public HazardBase(Hazard type) {
        this.type = type;
    }

    public Hazard getType() {
        return type;
    }

    public abstract void method();
}
