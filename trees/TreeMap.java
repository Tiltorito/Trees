package trees;

import java.util.Comparator;

/**
 * Created by harry on 23/03/2018.
 */

public abstract class TreeMap<Key extends Comparable<Key>, Value> {
    private Comparator<Key> comparator; // A comparator in case the client wants to compare the elements with custom comparator.

    public TreeMap() {}

    public TreeMap(Comparator<Key> comparator) {
        this.comparator = comparator;
    }

    /**
     * Returns the value associated with the specified key, null if not found.
     * @param key the key
     * @return the value associated with the specified key, null if not found
     */
    public abstract Value get(Key key);

    /**
     * Puts or update the element associated with the specified key.
     * @param key the key
     * @param value the value to be added/updated in the specified key
     */
    public abstract void put(Key key, Value value);

    /**
     * Removes an element from the tree using its key.
     * @param key the key
     */
    public abstract void remove(Key key);

    /**
     * Returns the minimum key, null if the Tree is empty.
     * @return the minimum key, null if the Tree is empty
     */
    public abstract Key min();

    /**
     * Returns the maximum key, null if the Tree is empty.
     * @return the maximum key, null if the Tree is empty
     */
    public abstract Key max();

    /**
     * Returns the Key of the node which has the largest key in the Tree which is less than or equal to Key, null if it didn't find.
     * @param key the key
     * @return the Value of the node which has the largest key in the Tree which is less than or equal to Key, null if it didn't find.
     */
    public abstract Key floor(Key key);


    public abstract Key ceil(Key key);

    /**
     * Returns the size of the tree.
     * @return the size of the tree
     */
    public abstract int size();

    /**
     * Returns true if the Tree is empty, false otherwise.
     * @return true if the Tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Compares keys using the comparator if it has provided otherwise it's compare them using the compareTo method.
     * @param k1 the first key
     * @param k2 the second key
     * @return the value returned from the comparator if it has been provided, if it is not, the result of the compareTo method
     */
    int compare(Key k1, Key k2) {
        if(comparator != null) {
            return comparator.compare(k1, k2);
        }

        return k1.compareTo(k2);
    }

}
