/*
 * Copyright 2023 Marc Liberatore.
 */

package hashtables;

/**
 * An interface specifying a hash table, that is, an array-based 
 * constant-time data structure to track the membership of 
 * distinct elements.
 */
public interface HashTable<E> extends Iterable<E> {

    /**
     * Return the capacity of the hash table. The capacity is the length of the
     * underlying array in this hash table.
     * 
     * @return the capacity of the hash table
     */
    int capacity();

    /**
     * Return the size of the hash table. The size is the number of elements 
     * stored in the hash table.
     * @return the size of the hash table.
     */
    int size();

    /**
     * Return the load factor of the hash table. The load factor is the ratio of the
     * size to the capacity.
     * 
     * @return the load factor of the hash table
     */
    double loadFactor();

    /**
     * Add the element e to the hash table. 
     * 
     * e must be non-null.
     * 
     * Overwrite the current value in the hash table, if any, that is `equals()` to e.
     * @param e the element
     * @return true if the element is newly inserted, false if an existing element is overwritten
     */
    boolean add(E e);

    /**
     * Return the element e from the hash table.
     * 
     * e must be non-null.
     * 
     * Retrieve the current value in the hash table that is `equals()` to e, if
     * present, or null otherwise.
     * 
     * @param e the element
     * @return the element e from the hash table, or null if not present.
     */
    E get(E e);

    /**
     * Remove the element e from the table, if present, returning true iff the
     * element was present.
     * 
     * e must be non-null.
     * 
     * Removes the element from the table that is `equals()` to e, if
     * present.
     * 
     * @param e the element
     * @return true iff the element was present and removed from the table
     */
    boolean remove(E e);

    /**
     * Return true iff the element e is stored in the hash table. 
     * 
     * e must be non-null.
     * 
     * An element is in the table if it is `equals()` to e.
     * 
     * @param e the element
     * @return true iff the element e is stored in the hash table
     */
    boolean contains(E e);
}