/**
 * Map entry class used for implementing the ExternalChainingHshMap.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class MapEntry<K, V> {

    private K key;
    private V value;
    private MapEntry<K, V> next;

    /**
     * Constructs a new MapEntry with only the given key and value.
     *
     * @param key   the key in the new entry
     * @param value the value in the new entry
     */
    MapEntry(K key, V value) {
        this(key, value, null);
    }

    /**
     * Constructs a new MapEntry with the given key, value, and
     * next reference.
     *
     * @param key   the key in the new entry
     * @param value the value in the new entry
     * @param next  the next entry in the external chain
     */
    MapEntry(K key, V value,
             MapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    K getKey() {
        return key;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue() {
        return value;
    }

    /**
     * Gets the next entry.
     *
     * @return the next entry
     */
    public MapEntry<K, V> getNext() {
        return next;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    void setValue(V value) {
        this.value = value;
    }

    /**
     * Sets the next entry.
     *
     * @param next the new next entry
     */
    void setNext(MapEntry<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String key = this.key == null ? "null" : this.key.toString();
        String value = this.value == null ? "null" : this.value.toString();
        return String.format("(%s, %s)", key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        // DO NOT USE THIS METHOD IN YOUR CODE! This is for testing ONLY!
        if (!(o instanceof MapEntry)) {
            return false;
        } else {
            MapEntry<K, V> that =
                (MapEntry<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }
}