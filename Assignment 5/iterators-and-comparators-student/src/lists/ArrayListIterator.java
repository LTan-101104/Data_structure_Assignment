/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayListIterator<E> implements Iterator<E> {
    ArrayList<E> list;
    int index;

    public ArrayListIterator(ArrayList<E> list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public E next() {
        if (!this.hasNext())
            throw new NoSuchElementException();

        E res = this.list.get(index);
        index++;
        return res;
    }

}
