/*
 * Copyright 2023 Marc Liberatore.
 */

package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 * @author liberato
 *
 * @param <T> the type for each vertex in the search graph
 */
public class Searcher<T> {
	private final SearchProblem<T> searchProblem;
	
	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public List<T> findSolution() {		
		// TODO

		List<T> res = new ArrayList<>();

		Queue<T> openList = new LinkedList<>();


		//! the map is for retrieving the predecessor  
		Map<T, T> curToPredecessor = new HashMap<>(); 

		//start the search

		T start = this.searchProblem.getInitialState();

		curToPredecessor.put(start, null);

		openList.add(start);

		while (!openList.isEmpty()){
			T cur = openList.remove();

			//when cur is our goal state
			if (this.searchProblem.isGoal(cur)){
				//start retrieving and add and the predecessors to the return list

				res.add(cur);
				T prev = curToPredecessor.get(cur);

				while (prev != null){
					res.add(0, prev);
					prev = curToPredecessor.get(prev);
				}

				return res;
			}


			for (T neighbor : this.searchProblem.getSuccessors(cur)){

				//if neighbor is already seen before
				if (curToPredecessor.containsKey(neighbor) || openList.contains(neighbor)) continue;
				
				openList.add(neighbor);

				curToPredecessor.put(neighbor, cur);
			}
		}


		//there is no solution
		return res;
	}

	/**
	 * Checks that a solution is valid.
	 * 
	 * THIS METHOD DOES NOT PERFORM SEARCH! It only checks if a provided solution
	 * is valid!
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValidSolution(List<T> solution) {
		// TODO

		if (solution == null) throw new NullPointerException();

		if (solution.size() == 0) return false;

		if(!solution.get(0).equals(this.searchProblem.getInitialState())) return false;

		if (!this.searchProblem.isGoal(solution.get(solution.size() - 1))) return false;

		for (int i = 0; i < solution.size() - 1; i ++){
			T curState = solution.get(i);
			T nextState = solution.get(i + 1);
			//if the nextState is not one of the sucessor of the curState then it is false
			if (!this.searchProblem.getSuccessors(curState).contains(nextState)) return false;
		}
		
		

		
		return true;
	}
}
