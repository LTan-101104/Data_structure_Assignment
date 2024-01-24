/*
 * Copyright 2021 Marc Liberatore.
 */

package search;

import java.util.List;

/**
 * An interface to a generic search problem. A search problem is defined by an
 * initial state, one or more goal states, and method that returns the
 * successors of an arbitrary state.
 * 
 * @author liberato
 *
 * @param <T>
 *            the type of each state in the search problem
 */
public interface SearchProblem<T> {
	/**
	 * @return the initial (starting) state of the search problem
	 */
	T getInitialState();

	/**
	 * Returns the list of successors (that is, reachable neighbors) from this
	 * state.
	 * 
	 * The list will be empty if there are no successors (though this behavior
	 * will only appear in degenerate search problems).
	 * 
	 * @param currentState
	 * @return the list of successors of currentState
	 */
	List<T> getSuccessors(T currentState);

	/**
	 * @param state
	 * @return true iff state is a goal state for this problem
	 */
	boolean isGoal(T state);
}
