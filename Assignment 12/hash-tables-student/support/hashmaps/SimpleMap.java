/*
 * Copyright 2023 Marc Liberatore.
 */

package hashmaps;

import java.util.Set;

/**
 * An interface specifying a sample key-value mapping.
 */
public interface SimpleMap<K, V> {
    /**
     * 
     * @return the number of distinct key-valued pairs stored in the map.
     */
    public int size();

    /**
     * Insert the given key-value pair into the map; if the key already 
     * exists in the map, the current key-value mapping is overwritten
     * with the values passed to this method.
     * 
     * k must be a non-null value.
     * 
     * @param k the (non-null) key to insert
     * @param v the value to associate with the key
     */
    public void put(K k, V v);

    /**
     * Return the value in the map associated with the given key, or null if the key
     * is not in the map.
     * 
     * k must be a non-null value.
     * 
     * @param k the (non-null) key
     * @return the value in the map associated with the k
     */
    public V get(K k);

    /**
     * Return the value in the map associated with the given key, or the
     * defaultValue if the key
     * is not in the map.
     * 
     * @param k            the (non-null) key
     * @param defaultValue the default value to return if the key is not in the map
     * @return the value in the map associated with the given key, or the
     *         defaultValue if the key is not in the map
     */
    public V getOrDefault(K k, V defaultValue);

    /**
     * Remove the key from the map (if present) returning the previously associated
     * value (or null, if the key was not present).
     * 
     * @param k the key to remove
     * @return the value associated with k, or null if no such key was in the map
     */
    public V remove(K k);

    /**
     * Return the set of keys stored in the map. This set does not share structure
     * with the internals of the SimpleMap.
     * 
     * @return the set of keys stored in the map
     */
    public Set<K> keys();

}
