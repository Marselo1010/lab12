package it.unibo.es3;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implementation of the game logic.
 */

public class LogicsImpl implements Logics {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<Pair<Integer, Integer>, String> map;
    private final int width;
    private final List<Pair<Integer, Integer>> coordList;
    private final String asterisk;
    private final Random random = new Random();

    /**
     * constructor.
     * 
     * @param width the width of the map 
     */
    public LogicsImpl(final int width) {
        this.asterisk = "*";
        this.width = width;
        this.map = new LinkedHashMap<>();
        this.coordList = new LinkedList<>();
        //inizialize the map
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                map.put(new Pair<>(i, j), " ");
            }
        }
    }

    /**
     * Updates the map according to the game rules.
     */
    @Override
    public void updateMap() {
        final List<Pair<Integer, Integer>> newlyActivated = new LinkedList<>();
        // For every currently active coordinate (coordList) spread to neighbors
        for (final var coord : coordList) {
            final int x = coord.x();
            final int y = coord.y();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {

                    final int nx = x + dx;
                    final int ny = y + dy;
                    final Pair<Integer, Integer> neighbor = new Pair<>(nx, ny);
                    if (nx >= 0 && nx < width && ny >= 0 
                        && ny < width && !asterisk.equals(map.get(neighbor)) && !newlyActivated.contains(neighbor)) {
                        newlyActivated.add(neighbor);
                    }
                }
            }
        }
        // Apply newly activated cells
        for (final Pair<Integer, Integer> p : newlyActivated) {
            map.put(p, asterisk);
            coordList.add(p);
        }
    }

    /**
     * @param asteriskNumber number of asterisks to set up
     */
    @Override
    public void randomAsteriskSetUp(final int asteriskNumber) {

        for (int count = 0; count < asteriskNumber; count++) {
            int x = random.nextInt(0, width);
            int y = random.nextInt(0, width);
            while (asterisk.equals(map.get(new Pair<>(x, y)))) {
                x = random.nextInt(0, width);
                y = random.nextInt(0, width);
            }
            map.put(new Pair<>(x, y), asterisk);
            coordList.add(new Pair<>(x, y));
        }

    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return the state of the cell at (x, y)
     */
    @Override
    public String getCellState(final int x, final int y) {
        return map.get(new Pair<>(x, y));
    }

    /**
     * @return true if the game is over, false otherwise
     */
    @Override
    public Boolean quit() {
        return map.keySet().stream().allMatch(value -> asterisk.equals(map.get(value)));
    }

}
