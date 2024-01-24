/*
 * Copyright 2023 Marc Liberatore.
 */

package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import search.SearchProblem;
import search.Searcher;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */

	List<Integer> initialState;
	Map<Integer, List<Integer>> IndexToPossibleSwap;
	public EightPuzzle(List<Integer> startingValues) {
		List<Integer> goal = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
		Set<Integer> forCompare = new HashSet<>(goal);
		Set<Integer> temp = new HashSet<>(startingValues);
		if (startingValues.size() != 9 || !forCompare.equals(temp)) throw new IllegalArgumentException();
		this.initialState = startingValues;
		this.IndexToPossibleSwap = new HashMap<>();
		this.IndexToPossibleSwap.put(0, Arrays.asList(new Integer[] {1, 3}));
		this.IndexToPossibleSwap.put(1, Arrays.asList(new Integer[] {0, 4, 2}));
		this.IndexToPossibleSwap.put(2, Arrays.asList(new Integer[] {1, 5}));
		this.IndexToPossibleSwap.put(3, Arrays.asList(new Integer[] {0, 4, 6}));
		this.IndexToPossibleSwap.put(4, Arrays.asList(new Integer[] {1, 3, 5, 7}));
		this.IndexToPossibleSwap.put(5, Arrays.asList(new Integer[] {2, 4, 8}));

		this.IndexToPossibleSwap.put(6, Arrays.asList(new Integer[] {3, 7}));

		this.IndexToPossibleSwap.put(7, Arrays.asList(new Integer[] {4, 6, 8}));
		this.IndexToPossibleSwap.put(8, Arrays.asList(new Integer[]{5, 7}));


	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return this.initialState;
	}

	public void swap(List<Integer> list, int i, int j){
		Integer temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);

	}
	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> res = new ArrayList<>();

		int curIndex = currentState.indexOf(0);

		for (int possibleSwap : this.IndexToPossibleSwap.get(curIndex)){
			ArrayList<Integer> temp = new ArrayList<>(currentState);
			swap(temp, possibleSwap, curIndex);
			res.add(temp);
		}

		return res;
	}



	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		List<Integer> goal = Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 0});
		return (state.equals(goal));
	}

	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			System.out.println(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
