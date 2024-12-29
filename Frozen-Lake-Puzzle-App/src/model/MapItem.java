package model;

/**
 * Abstract base class for all map items.
 */
public abstract class MapItem {
    private String field;

    public MapItem(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public abstract void method();
}
