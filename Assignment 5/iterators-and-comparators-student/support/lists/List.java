/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public interface List<E> extends Iterable<E> {
    /**
     * Compares the specified object with this list for equality. Returns true 
     * if and only if the specified object is also a list, both lists have the same size, 
     * and all corresponding pairs of elements in the two lists are .equals() to one another. 
     * In other words, two lists are defined to be equal if they contain the same elements 
     * in the same order.
     * @param o - the object to be compared for equality with this list
     * @return true if the specified object is a list, equal to this list
     */
    public boolean equals(Object o);

    /**
     * Returns the number of elements in this list.
     */
    public int size();

    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E get(int index) throws IndexOutOfBoundsException;

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param e - element to be appended to this list
     */
    public void add(E e);

    /**
     * Inserts the specified element at the specified position in this list. Shifts the 
     * element currently at that position (if any) and any subsequent elements to the 
     * right (adds one to their indices).
     * @param index - index at which the specified element is to be inserted
     * @param e - element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    public void add(int index, E e) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified position in this list. Shifts any 
     * subsequent elements to the left (subtracts one from their indices).
     * @param index - the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public E remove(int index) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index - index of the element to replace
     * @param e - element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int index, E e) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the specified element (as determined by 
     * .equals()) in this list, or -1 if this list does not contain the element.  
     * @param e - element to search for
     * @return the index of the first occurrence of the specified element in this list, or 
     *         -1 if this list does not contain the element
     */
    public int indexOf(E e);
}