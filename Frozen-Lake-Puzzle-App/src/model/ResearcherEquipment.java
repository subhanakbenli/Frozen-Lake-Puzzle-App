package model;

/**
 * Abstract base class for all researcher equipment.
 */
public abstract class ResearcherEquipment extends MapItem {
    private String type;

    public ResearcherEquipment(String field, String type) {
        super(field);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public abstract void method();
}