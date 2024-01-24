/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

import java.util.Iterator;

public class IntegerNodeIterator implements Iterator<Integer> {
	private IntegerNode next;

	public IntegerNodeIterator(IntegerNode first) {
		next = first;
	}

	public boolean hasNext() {
		return next != null;
	}

	public Integer next() {
		int result = next.getData();
		next = next.getNext();
		return result;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
