/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class LinkedList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Node<E> n = head;
        while (n != null) {
            result = prime * result + head.data.hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List other = (List) obj;
        if (size != other.size())
            return false;
        Node<E> tempNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (!this.get(i).equals(other.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return this.head.getData();

        if (index == this.size - 1)
            return this.tail.getData();

        Node<E> tempNode = this.head;
        int i = 0;
        while (i < index) {
            tempNode = tempNode.next;
            i++;
        }

        return tempNode.getData();

    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();

        Node<E> newNode = new Node<>(e);
        if (index == this.size)
            this.add(e);

        else {
            int i = -1;
            Node<E> dummy = new Node<>();
            dummy.next = this.head;
            Node<E> tempNode = dummy;
            while (i < index - 1) {
                tempNode = tempNode.next;
                i++;
            }
            // if we want to add a new element at index i, we have to access the element
            // before i
            newNode.next = tempNode.next;
            tempNode.next = newNode;
            this.head = dummy.next; // in case there is an addition at the front so we have to update the head again

        }
        this.size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        Node<E> removed;

        Node<E> dummy = new Node<>();
        dummy.next = this.head;
        Node<E> tempNode = dummy;
        int i = -1;
        // consider edge case for tail as well
        while (i < index - 1) {
            tempNode = tempNode.next;
            i++;
        }

        if (index == this.size - 1)
            this.tail = tempNode;

        removed = tempNode.next;
        tempNode.next = tempNode.next.next;
        this.head = dummy.next; 

        this.size--;
        return removed.getData();
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        E res;
        Node<E> tempNode = this.head;
        int i = 0;
        while (i < index) {
            tempNode = tempNode.next;
            i++;
        }

        res = tempNode.getData();
        tempNode.setData(e);

        return res;
    }

    @Override
    public int indexOf(E e) {
        int i = 0;
        Node<E> tempNode = this.head;
        while (tempNode != null) {
            if (tempNode.getData().equals(e))
                return i;
            tempNode = tempNode.next;
            i++;
        }
        return -1;
    }
}
