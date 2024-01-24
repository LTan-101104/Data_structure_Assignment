/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

public class UnweightedSparseGraph<V> extends BaseGraph<V> implements UnweightedGraphInterface<V> {
	private IntegerNode[] edges;

	public UnweightedSparseGraph(int maxVertices) {
		super(maxVertices);
		edges = new IntegerNode[maxVertices];
	}

	protected boolean hasEdge(int fromIndex, int toIndex) {
		for (IntegerNode node = edges[fromIndex]; node != null; node = node.getNext()) {
			if (node.getData() == toIndex) {
				return true;
			}
		}
		return false;
	}

	public void addEdge(V from, V to) {
		int fromIndex = getIndexOf(from);
		int toIndex = getIndexOf(to);
		edges[fromIndex] = new IntegerNode(toIndex, edges[fromIndex]);
	}
}
