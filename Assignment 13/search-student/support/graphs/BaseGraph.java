/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public abstract class BaseGraph<V> implements GraphInterface<V> {
	protected ArrayList<V> vertices;

	public BaseGraph(int maxV) {
		vertices = new ArrayList<V>(maxV);
	}

	public BaseGraph() {
		vertices = new ArrayList<V>();
	}

	public int getNumVertices() {
		return vertices.size();
	}

	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	public void addVertex(V vertex) {
		vertices.add(vertex);
	}

	protected int indexOf(V vertex) {
		return vertices.indexOf(vertex);
	}

	protected int getIndexOf(V vertex) {
		int result = vertices.indexOf(vertex);
		if (result < 0) {
			throw new GraphException("Vertex not in graph: " + vertex);
		}
		return result;
	}

	public boolean hasVertex(V vertex) {
		return vertices.contains(vertex);
	}

	public List<V> getVertices() {
		return new ArrayList<V>(vertices);
	}

	public Iterator<V> vertexIterator() {
		return vertices.iterator();
	}

	public List<V> getNeighbors(V from) {
		int fromIndex = getIndexOf(from);
		List<V> neighbors = new ArrayList<V>();
		for (int i = 0; i < vertices.size(); i += 1) {
			if (hasEdge(fromIndex, i)) {
				neighbors.add(vertices.get(i));
			}
		}
		return neighbors;
	}

	public boolean hasEdge(V from, V to) {
		return hasEdge(getIndexOf(from), getIndexOf(to));
	}

	protected abstract boolean hasEdge(int fromIndex, int toIndex);

	public GraphMarker<V> getMarker() {
		return new BaseGraphMarker<V>(this);
	}
}
