/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

import java.util.Iterator;
import java.util.List;

public interface GraphInterface<V> {
	int getNumVertices();

	boolean isEmpty();

	void addVertex(V vertex);

	boolean hasVertex(V vertex);

	List<V> getVertices();

	Iterator<V> vertexIterator();

	boolean hasEdge(V from, V to);

	List<V> getNeighbors(V vertex);

	GraphMarker<V> getMarker();
}
