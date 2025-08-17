package Components.General;

import Components.Hazards.CliffEdgeItem;
import Components.Hazards.HoleInIceItem;
import Components.Hazards.IceBlockItem;
import Components.Hazards.IceSpikeItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The LakePuzzle class represents a puzzle game where a map is generated with various elements such as walls, cliffs, ice blocks, and hazards.
 * The map is a grid of MapSquare objects, and the puzzle involves navigating through the map while avoiding hazards and utilizing ice blocks.
 */
public class LakePuzzle {

    private static final int ROWS = 10;
    private static final int COLUMNS = 13;
    private static final int NUM_ICE_BLOCKS = 8;
    private static final int NUM_HOLES = 3;
    private static final int NUM_SPIKES = 3;
    private int CLIFEDGE_DIRECTION;
    private ArrayList<ArrayList<MapSquare>> map;
    private Random random;

    /**
     * Constructs a new LakePuzzle object and initializes the map.
     */
    public LakePuzzle() {
        map = new ArrayList<>(ROWS);
        random = new Random();
        generateMap();
    }

    /**
     * Generates the puzzle map by initializing, setting the entrance, walls, cliffs, ice blocks, and hazards.
     */
    private void generateMap() {
        initializeMap();
        setEntrance();
        setWallsAndCliffside();
        placeIceBlocks();
        placeHazards();
    }

    /**
     * Initializes the map with empty MapSquare objects.
     */
    private void initializeMap() {
        for (int i = 0; i < ROWS; i++) {
            ArrayList<MapSquare> row = new ArrayList<>(COLUMNS);
            for (int j = 0; j < COLUMNS; j++) {
                row.add(new MapSquare(j, i));
            }
            map.add(row);
        }
    }

    /**
     * Retrieves a specific row from the map.
     *
     * @param rowIndex The index of the row to retrieve.
     * @return The specified row as an ArrayList of MapSquare objects, or null if the index is invalid.
     */
    public ArrayList<MapSquare> getRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < ROWS) {
            return map.get(rowIndex);
        }
        return null;
    }

    /**
     * Sets the map with a new configuration.
     *
     * @param map The new map configuration as a 2D ArrayList of MapSquare objects.
     */
    public void setMap(ArrayList<ArrayList<MapSquare>> map) {
        this.map = map;
    }

    /**
     * Retrieves a specific MapSquare from the map.
     *
     * @param row The row index of the square.
     * @param col The column index of the square.
     * @return The MapSquare object at the specified position, or null if the position is invalid.
     */
    public MapSquare getMapSquare(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
            return map.get(row).get(col);
        }
        return null;
    }

    /**
     * Finds the MapSquare containing the specified equipment item.
     *
     * @param item The MapItem to search for.
     * @return The MapSquare containing the item, or null if not found.
     */
    public MapSquare findWithEquipment(MapItem item) {
        for (ArrayList<MapSquare> rows : map) {
            for (MapSquare square : rows) {
                if (square.getItem() == item) {
                    return square;
                }
            }
        }
        return null;
    }

    /**
     * Finds the MapSquare containing the specified researcher.
     *
     * @param researcher The Researcher to search for.
     * @return The MapSquare containing the researcher, or null if not found.
     */
    public MapSquare findResearcherSquare(Researcher researcher) {
        for (ArrayList<MapSquare> rows : map) {
            for (MapSquare square : rows) {
                Researcher currentResearcher = square.getResearcher();
                if (currentResearcher == researcher) {
                    return square;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the entire map.
     *
     * @return The map as a 2D ArrayList of MapSquare objects.
     */
    public ArrayList<ArrayList<MapSquare>> getMap() {
        return map;
    }

    /**
     * Adds a researcher to the map at the entrance position.
     *
     * @param researcher The Researcher to add to the map.
     */
    public void addResearcherToMap(Researcher researcher) {
        int row = 1;
        int col = 6;
        getMapSquare(row, col).setResearcher(researcher);
    }

    /**
     * Removes the researcher from the entrance position on the map.
     *
     * @param researcher The Researcher to remove.
     */
    public void removeResearcherWhereInEntrance(Researcher researcher) {
        getMapSquare(1, 6).removeResearcher();
    }

    /**
     * Sets the entrance point on the map and places walls around it.
     */
    private void setEntrance() {
        getMapSquare(0, COLUMNS / 2).setItem(new EntranceItem());
        for (int i = 0; i < COLUMNS; i++) {
            if (i != COLUMNS / 2) {
                getMapSquare(0, i).setItem(new WallItem());
            }
        }
    }

    /**
     * Retrieves the direction of the cliff edge.
     *
     * @return The direction of the cliff edge as an integer (0: Right, 1: Bottom, 2: Left).
     */
    public int getCliffEdgeDirection() {
        return CLIFEDGE_DIRECTION;
    }

    /**
     * Retrieves the size of the map.
     *
     * @return An array where the first element is the number of columns and the second is the number of rows.
     */
    public int[] getMapSize() {
        return new int[]{COLUMNS, ROWS};
    }

    /**
     * Sets walls and the cliffside based on a randomly selected direction.
     */
    private void setWallsAndCliffside() {
        int cliffSide = random.nextInt(3); // 0: Right, 1: Bottom, 2: Left
        this.CLIFEDGE_DIRECTION = cliffSide;
        switch (cliffSide) {
            case 0: // Right
                setRightCliffside();
                break;
            case 1: // Bottom
                setBottomCliffside();
                break;
            case 2: // Left
                setLeftCliffside();
                break;
        }
    }

    private void setRightCliffside() {
        // Set right side as cliff edge
        for (int i = 1; i < ROWS-1 ; i++) {
            getMapSquare(i, COLUMNS - 1).setItem(new CliffEdgeItem());
        }
        // Set bottom wall except last column
        for (int i = 0; i < COLUMNS - 1; i++) {
            getMapSquare(ROWS - 1, i).setItem(new WallItem());
        }
        // Set left wall
        for (int i = 1; i < ROWS - 1; i++) {
            getMapSquare(i, 0).setItem(new WallItem());
        }
    }

    private void setBottomCliffside() {
        // Set bottom as cliff edge
        for (int i = 1; i < COLUMNS -1 ; i++) {
            getMapSquare(ROWS - 1, i).setItem(new CliffEdgeItem());
        }
        // Set right wall
        for (int i = 1; i < ROWS - 1; i++) {
            getMapSquare(i, COLUMNS - 1).setItem(new WallItem());
        }
        // Set left wall
        for (int i = 1; i < ROWS - 1; i++) {
            getMapSquare(i, 0).setItem(new WallItem());
        }
    }

    private void setLeftCliffside() {
        // Set left side as cliff edge
        for (int i = 1; i < ROWS-1 ; i++) {
            getMapSquare(i, 0).setItem(new CliffEdgeItem());
        }
        // Set bottom wall except first column
        for (int i = 1; i < COLUMNS; i++) {
            getMapSquare(ROWS - 1, i).setItem(new WallItem());
        }
        // Set right wall
        for (int i = 1; i < ROWS - 1; i++) {
            getMapSquare(i, COLUMNS - 1).setItem(new WallItem());
        }
    }

    private void placeIceBlocks() {
        // Her satır için olası sütunları tutacak liste
        ArrayList<Integer> possibleColumns = initializeColumnList();
        boolean hasMiddleColumn = false;
        int cliffBlockCount = 0;

        // 1. satırdan başlayıp sondan bir satıra kadar git
        for (int row = 1; row < ROWS - 1; row++) {
            // Özel durumlar için sütun seçimi
            int selectedColumn;

            // 1. satır için özel durum - index 6'ya yerleştirme yapılamaz
            if (row == 1) {
                selectedColumn = getValidColumnForFirstRow(possibleColumns);
            }else if(row == 6){
                if(!hasMiddleColumn){
                    selectedColumn = COLUMNS / 2;
                }else{
                    selectedColumn = getRandomValidColumn(possibleColumns);
                }
            }
            // 7. satır için özel durum - eğer cliff kenarında hiç IceBlock yoksa
            else if (row == 7 && isCliffSideEmpty() && (isRightCliffside() || isLeftCliffside())) {
                selectedColumn = getCliffAdjacentColumn();
                cliffBlockCount++;
            }
            // 8. satır için özel durum
            else if (row == 8) {
                if (isBottomCliffside()) {
                    // Alt cliff durumunda iki IceBlock yerleştir
                    placeDoubleIceBlocksForBottomCliff(row);
                    continue;
                } else if ((isRightCliffside() || isLeftCliffside()) && cliffBlockCount == 1) {
                    selectedColumn = getCliffAdjacentColumn();
                    cliffBlockCount++;
                } else {
                    selectedColumn = getRandomValidColumn(possibleColumns);
                }
            }
            // Normal durumlar
            else {
                selectedColumn = getRandomValidColumn(possibleColumns);
            }

            // Orta sütun kontrolü
            if (selectedColumn == COLUMNS / 2) {
                hasMiddleColumn = true;
            }
            getMapSquare(row, selectedColumn).setItem(new IceBlockItem());
        }
    }

    private void placeDoubleIceBlocksForBottomCliff(int row) {
        // İlk IceBlock'u yerleştir
        getMapSquare(row, COLUMNS / 3).setItem(new IceBlockItem());
        // İkinci IceBlock'u yerleştir
        getMapSquare(row, 2 * COLUMNS / 3).setItem(new IceBlockItem());
    }

    private int getValidColumnForFirstRow(ArrayList<Integer> possibleColumns) {
        ArrayList<Integer> validColumns = new ArrayList<>(possibleColumns);
        validColumns.remove(Integer.valueOf(6)); // index 6'yı kaldır
        return validColumns.get(random.nextInt(validColumns.size()));
    }

    private int getCliffAdjacentColumn() {
        if (isRightCliffside()) {
            return COLUMNS - 2;
        } else if (isLeftCliffside()) {
            return 1;
        }
        return -1;
    }

    private boolean isCliffSideEmpty() {
        if (isRightCliffside()) {
            for (int i = 1; i < ROWS - 1; i++) {
                if (getMapSquare(i, COLUMNS - 2).hasItemOfType(IceBlockItem.class)) {
                    return false;
                }
            }
        } else if (isLeftCliffside()) {
            for (int i = 1; i < ROWS - 1; i++) {
                if (getMapSquare(i, 1).hasItemOfType(IceBlockItem.class)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getRandomValidColumn(ArrayList<Integer> possibleColumns) {
        ArrayList<Integer> validColumns = new ArrayList<>();
        for (Integer col : possibleColumns) {
            if (!isNextToCliffside(ROWS - 2, col) && !isNextToWall(ROWS - 2, col)) {
                validColumns.add(col);
            }
        }

        // Eğer validColumns boşsa, possibleColumns'dan rastgele bir sütun seç
        if (validColumns.isEmpty()) {
            return possibleColumns.get(random.nextInt(possibleColumns.size()));
        }

        return validColumns.get(random.nextInt(validColumns.size()));
    }

    private ArrayList<Integer> initializeColumnList() {
        ArrayList<Integer> possibleColumns = new ArrayList<>();
        for (int j = 1; j < COLUMNS - 1; j++) {
            possibleColumns.add(j);
        }
        return possibleColumns;
    }
    
    private boolean isRightCliffside() {
        return getMapSquare(1, COLUMNS - 1).hasItemOfType(CliffEdgeItem.class);
    }

    private boolean isLeftCliffside() {
        return getMapSquare(1, 0).hasItemOfType(CliffEdgeItem.class);
    }


    private boolean isBottomCliffside() {
        return getMapSquare(ROWS - 1, 1).hasItemOfType(CliffEdgeItem.class);
    }

    private void placeHazards() {
        ArrayList<Integer[]> availableIndices = getAvailableHazardIndices();
        Collections.shuffle(availableIndices, random);

        placeHoles(availableIndices);
        placeSpikes(availableIndices);
    }

    private ArrayList<Integer[]> getAvailableHazardIndices() {
        ArrayList<Integer[]> indices = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (isSafeForHazard(i, j)) {
                    indices.add(new Integer[]{i, j});
                }
            }
        }
        return indices;
    }

    private void placeHoles(ArrayList<Integer[]> availableIndices) {
        for (int i = 0; i < NUM_HOLES; i++) {
            Integer[] pos = availableIndices.remove(0);
            getMapSquare(pos[0], pos[1]).setItem(new HoleInIceItem());
        }
    }

    /**
     * Places spikes on the map at positions specified in the availableIndices list.
     * The spikes are only placed if the position is next to a wall.
     * If a position is not next to a wall, it retries with the next available position.
     *
     * @param availableIndices A list of available positions (as Integer arrays) where spikes can be placed.
     */
    private void placeSpikes(ArrayList<Integer[]> availableIndices) {
        for (int i = 0; i < NUM_SPIKES; i++) {
            Integer[] pos = availableIndices.remove(0);
            if (isNextToWall(pos[0], pos[1])) {
                getMapSquare(pos[0], pos[1]).setItem(new IceSpikeItem());
            } else {
                i--; // Retry if not next to a wall
            }
        }
    }

    private boolean nearEntrance(int row, int col) {
        return row < 3 && Math.abs(col - COLUMNS / 2) < 3;
    }

    /**
     * Checks if the specified map square is a cliffside.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if the map square has an item of type CliffEdgeItem, false otherwise.
     */
    private boolean onCliffside(int row, int col) {
        return getMapSquare(row, col).hasItemOfType(CliffEdgeItem.class);
    }

    /**
     * Checks if the specified map square is adjacent to a wall.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if any neighboring square has an item of type WallItem, false otherwise.
     */
    private boolean isNextToWall(int row, int col) {
        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (isValidPosition(r, c) && getMapSquare(r, c).hasItemOfType(WallItem.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the specified map square is safe for placing a hazard.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if the square is safe for a hazard, false otherwise.
     */
    private boolean isSafeForHazard(int row, int col) {
        return !getMapSquare(row, col).hasAnyItem() &&
                !nearEntrance(row, col) &&
                !onCliffside(row, col) &&
                !isNextToCliffside(row, col);
    }

    /**
     * Checks if the specified map square is adjacent to a cliffside.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if any directly adjacent square has an item of type CliffEdgeItem, false otherwise.
     */
    private boolean isNextToCliffside(int row, int col) {
        int[][] offsets = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (isValidPosition(r, c) && getMapSquare(r, c).hasItemOfType(CliffEdgeItem.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates whether the specified position is within the map boundaries.
     *
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @return true if the position is valid, false otherwise.
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    /**
     * Checks if the specified map square is next to the edge of the map.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if the square is on the edge of the map, false otherwise.
     */
    public boolean isNextToEdge(int row, int col) {
        return row == 0 || row == ROWS - 1 || col == 0 || col == COLUMNS - 1;
    }

    /**
     * Checks if the specified map square is adjacent to an ice block.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if any neighboring square has an item of type IceBlockItem, false otherwise.
     */
    public boolean isNextToIceBlock(int row, int col) {
        int[][] offsets = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Adjacent cells
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonal cells
        };

        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (isValidPosition(r, c) && getMapSquare(r, c).hasItemOfType(IceBlockItem.class)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the specified map square is on the edge or adjacent to an ice block.
     *
     * @param row The row index of the map square.
     * @param col The column index of the map square.
     * @return true if the square is on the edge or next to an ice block, false otherwise.
     */
    public boolean isEdgeOrNextToIceBlock(int row, int col) {
        return isNextToEdge(row, col) || isNextToIceBlock(row, col);
    }

    /**
     * Prints the map to the console with a formatted representation.
     */
    public void printMap() {
        for (int i = 1; i < ROWS; i++) {
            if (i == 1) {
                System.out.println("     -" + "------".repeat((COLUMNS - 2) / 2) + "      " + "------".repeat((COLUMNS - 2) / 2));
            } else {
                System.out.println("     -" + "------".repeat((COLUMNS - 2)));
            }

            if (i == ROWS - 1) {
                for (int j = 0; j < COLUMNS; j++) {
                    MapSquare square = getMapSquare(i, j);
                    System.out.print(square.toString());
                    System.out.print(" ");
                }
            } else {
                for (int j = 0; j < COLUMNS; j++) {
                    MapSquare square = getMapSquare(i, j);
                    System.out.print(square.toString());
                    if (j != COLUMNS - 1) {
                        System.out.print("|");
                    }
                }
            }
            System.out.println();
        }
    }
}
