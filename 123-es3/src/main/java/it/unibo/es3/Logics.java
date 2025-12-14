package it.unibo.es3;

import java.io.Serializable;

/**
 * Interface for the game logic.
 */
public interface Logics extends Serializable {

    /**
     * Updates the map according to the game rules.
     */
    void updateMap();

    /**
     * @param asteriskNumber number of asterisks to set up
     */
    void randomAsteriskSetUp(int asteriskNumber);

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return the state of the cell at (x, y)
     */
    String getCellState(int x, int y);

    /**
     * @return true if the game is over, false otherwise
     */
    Boolean quit();

}
