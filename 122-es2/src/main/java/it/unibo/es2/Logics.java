package it.unibo.es2;

import java.io.Serializable;

/**
 * the interface of the logic.
 */
public interface Logics extends Serializable {

    /**
     * @param p the coordinates
     * @return "*" if before there was " ", otherwise returns " " 
     */
    String hit(Pair<Integer, Integer> p);

    /**
     * @return true if you won, false otherwise
     */
    Boolean gameController();

}
