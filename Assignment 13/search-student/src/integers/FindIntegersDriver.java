/*
 * Copyright 2021 Marc Liberatore.
 */

package integers;

import java.util.List;

import search.SearchProblem;
import search.Searcher;

public class FindIntegersDriver {

	public static void main(String[] args) {
		SearchProblem<Integer> problem = new FindIntegersProblem(7, -4, true);
		Searcher<Integer> searcher = new Searcher<Integer>(problem);
		List<Integer> solution = searcher.findSolution();
		for (Integer i: solution) {
			System.out.println(i);
		}
		System.out.println(solution.size() + " states in solution");
	}

}
