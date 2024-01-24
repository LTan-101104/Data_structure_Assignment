/*
 * Copyright 2021 Marc Liberatore.
 */

package graphs;

import java.util.Arrays;

public class BaseGraphMarker<V> implements GraphMarker<V> {
	BaseGraph<V> graph;
	protected boolean[] marks;

	public BaseGraphMarker(BaseGraph<V> graph) {
		this.graph = graph;
		marks = new boolean[graph.getNumVertices()];
	}

	public void clearMarks() {
		Arrays.fill(marks, false);
	}

	public void mark(V vertex) {
		marks[graph.getIndexOf(vertex)] = true;
	}

	public void unmark(V vertex) {
		marks[graph.getIndexOf(vertex)] = false;
	}

	public boolean isMarked(V vertex) {
		return marks[graph.getIndexOf(vertex)];
	}

	public int countMarked() {
		int count = 0;
		for (boolean b : marks) {
			if (b) {
				count++;
			}
		}
		return count;
	}
}
