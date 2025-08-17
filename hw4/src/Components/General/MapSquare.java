package Components.General;

import Components.Hazards.HazardItem;

/**
 * Represents a single square on the lake puzzle map, capable of containing items and researchers.
 */
public class MapSquare {
    private MapItem item;
    private Researcher currentResearcher;
    private int row;
    private int column;

    /**
     * Creates a new empty map square at specified coordinates.
     * @param column X-coordinate of the square
     * @param row Y-coordinate of the square
     */
    public MapSquare(int column, int row) {
        item = null;
        currentResearcher = null;
        this.column = column;
        this.row = row;
    }

    /**
     * Checks if square contains any item.
     * @return true if square has an item
     */
    public boolean hasAnyItem() {
        return this.item != null;
    }

    /**
     * Places an item in the square if empty.
     * @param item Item to place
     * @throws IllegalArgumentException if square already contains an item
     */
    public void setItem(MapItem item) {
        if (this.item != null) {
            throw new IllegalArgumentException("Cannot add more than one item to a square.");
        }
        this.item = item;
    }

    /**
     * Gets current item in square.
     * @return Current item or null if empty
     */
    public MapItem getItem() {
        return this.item;
    }

    /**
     * Removes current item from square.
     */
    public void removeItem() {
        this.item = null;
    }

    /**
     * Checks if current item is of specified type.
     * @param itemClass Class type to check against
     * @return true if item matches specified type
     */
    public boolean hasItemOfType(Class<? extends MapItem> itemClass) {
        return itemClass.isInstance(this.item);
    }

    /**
     * Places researcher in square.
     * @param researcher Researcher to place
     */
    public void setResearcher(Researcher researcher) {
        this.currentResearcher = researcher;
    }

    /**
     * Gets current researcher in square.
     * @return Current researcher or null if empty
     */
    public Researcher getResearcher() {
        return this.currentResearcher;
    }

    /**
     * Removes current researcher from square.
     */
    public void removeResearcher() {
        this.currentResearcher = null;
    }

    /**
     * Checks if square contains a researcher.
     * @return true if square has a researcher
     */
    public boolean hasResearcher() {
        return this.currentResearcher != null;
    }

    /**
     * Gets coordinates of square.
     * @return int array with [column, row] coordinates
     */
    public int[] getPosition() {
        return new int[]{column, row};
    }

    /**
     * Gets hazard item if present.
     * @return HazardItem if square contains one, null otherwise
     */
    public HazardItem getHazard() {
        if (this.item instanceof HazardItem) {
            return (HazardItem) this.item;
        }
        return null;
    }

    /**
     * Creates string representation of square's contents.
     * @return Formatted string showing researcher and/or item
     */
    @Override
    public String toString() {
        if (this.hasResearcher()) {
            if (this.hasAnyItem()) {
                return this.currentResearcher.toString().toUpperCase() + "-" + this.item.toString().toUpperCase();
            } else {
                return " " + this.currentResearcher.toString().toUpperCase() + "  ";
            }
        } else if (this.hasAnyItem()) {
            return " " + this.item.toString().toUpperCase() + "  ";
        } else {
            return "     ";
        }
    }
}