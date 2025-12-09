package it.unibo.es1;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private static final String ERROR_MESSAGE = "Unimplemented method";
    private final int size;
    private List<Integer> list= new LinkedList<>();
    private List<Boolean> boolList= new LinkedList<>();
    private String s= "";


    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {

        for(int i = 0; i < size; i++) {
            list.add(i, 0);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {

        for(int i = 0; i < size; i++) {
            if (list.get(i) == size) {
                boolList.add(i,false);
            } else {
                boolList.add(i,true);
            }
        }
        return this.boolList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        list.set(elem, list.get(elem) + 1);
        return this.list.get(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        
        list.stream()
            .forEach(i -> s.concat(String.valueOf(i)).concat(" | "));

        return "<< " + s + ">>";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }
}
