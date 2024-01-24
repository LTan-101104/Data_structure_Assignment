/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

public class IntegerNode {
	private int data;
	private IntegerNode next;

	public IntegerNode(int data, IntegerNode next) {
		this.data = data;
		this.next = next;
	}

	public IntegerNode(int data) {
		this(data, null);
	}

	public int getData() {
		return data;
	}

	public IntegerNode getNext() {
		return next;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setNext(IntegerNode next) {
		this.next = next;
	}
}
