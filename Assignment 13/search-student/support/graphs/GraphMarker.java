/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

public interface GraphMarker<V> {
	void clearMarks();

	void mark(V vertex);

	void unmark(V vertex);

	boolean isMarked(V vertex);

	int countMarked();
}
