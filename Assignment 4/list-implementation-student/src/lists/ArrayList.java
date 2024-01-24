/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class ArrayList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    E[] array;
    int size;

    public ArrayList() {
        this.size = 0;
        // array = (E[]) new Object[10];
        this.array = (E[]) new Object[10];

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + array[i].hashCode();
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
        List other = (List<E>) obj;
        if (size != other.size())
            return false;
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
        return this.array[index];
    }

    public E[] doubledArray(E[] arr) {
        E[] res = (E[]) new Object[this.size * 2];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    @Override
    public void add(E e) {
        if (this.size >= this.array.length) {
            this.array = doubledArray(this.array);
        }
        this.array[this.size] = e;
        this.size++;

    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();

        // create new array if out of range
        if (this.size >= this.array.length) {
            this.array = doubledArray(this.array);
        }

        for (int i = this.size - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }

        this.array[index] = e;
        this.size++;

    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        E res = this.array[index];

        for (int i = index; i < this.size - 1; i++) {

            this.array[i] = this.array[i + 1];
        }

        this.size--;

        return res;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        E res = this.array[index];
        this.array[index] = e;
        return res;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(e))
                return i;
        }

        return -1;
    }
}