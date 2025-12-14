package it.unibo.es2;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * the logic part of the game.
 */
public class LogicsImpl implements Logics {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int size;
    private final Map<Pair<Integer, Integer>, String> map;
    private int contatore;
    private final String asterisk;

    /**
     * @param size the number of rows and columns
     */
    public LogicsImpl(final int size) {
        this.size = size;
        this.asterisk = "*";
        this.map = new LinkedHashMap<>();
        this.contatore = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.map.put(new Pair<>(i, j), " ");
            }
        }

    }

    /**
     * @param position the coordinates of the button
     * @return "*" if before there was " ", otherwise returns " " 
     */
    @Override
    public String hit(final Pair<Integer, Integer> position) {
        if (map.containsKey(position) && asterisk.equals(map.get(position))) {
            map.put(position, " ");
            return " "; 
        }
        map.put(position, asterisk);
        return asterisk;
    }

    /**
     * @return true if you won, false otherwise
     */
    @Override
    public Boolean gameController() {
        for (this.contatore = 0; contatore < this.size; contatore++) {
            final boolean areAllAsterisks = map.keySet().stream()
                .filter(x -> x.x() == contatore) // filter same row
                .allMatch(value -> asterisk.equals(map.get(value))); // all "*"?
            if (areAllAsterisks) {
                return true;
            }
        }

        for (this.contatore = 0; contatore < this.size; contatore++) {
            final boolean areAllAsterisks = map.keySet().stream()
                .filter(y -> y.y() == contatore) // filter same column
                .allMatch(value -> asterisk.equals(map.get(value))); // all "*"?
            if (areAllAsterisks) {
                return true;
            }
        }

        return false;
    }

}
