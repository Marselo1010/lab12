package it.unibo.es1;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int size;
    private final List<Integer> list;
    private final List<Boolean> boolList;
    private String s = "";

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.size = size;
        this.list = new LinkedList<>();
        this.boolList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            boolList.add(true);
            list.add(0);
        }

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
        return Collections.unmodifiableList(this.list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {

        for (int i = 0; i < size; i++) {
            if (list.get(i) == size) {
                boolList.set(i, false);
            } else {
                boolList.set(i, true);
            }
        }
        return Collections.unmodifiableList(this.boolList);
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
        s = "";

        list.stream()
            .limit(size - 1)
            .forEach(i -> s = s.concat(String.valueOf(i)).concat("|"));
            s = s.concat(String.valueOf(list.get(size - 1)));

        return "<<" + s + ">>";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return boolList.stream()
            .noneMatch(Boolean::booleanValue);
    }
}
