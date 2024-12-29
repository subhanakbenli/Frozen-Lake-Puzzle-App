package model;
/**
 * Abstract base class for all hazard equipment.
 */
public abstract class HazardEquipment extends MapItem {
    private Hazard hazardEnum;

    public HazardEquipment(String field, Hazard hazardEnum) {
        super(field);
        this.hazardEnum = hazardEnum;
    }

    public Hazard getHazardEnum() {
        return hazardEnum;
    }

    @Override
    public abstract void method();
}
