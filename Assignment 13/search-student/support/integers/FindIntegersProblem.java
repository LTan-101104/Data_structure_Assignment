/*
 * Copyright 2021 Marc Liberatore.
 */

package integers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import search.SearchProblem;

public class FindIntegersProblem implements SearchProblem<Integer> {

	private final Integer positiveGoal;
	private final Integer negativeGoal;
	private final boolean positivesFirst;

	public FindIntegersProblem(int positiveGoal, int negativeGoal, boolean positivesFirst) {
		if ((positiveGoal < 0) || (negativeGoal > -1)) {
			throw new IllegalArgumentException();
		}
		this.positiveGoal = positiveGoal;
		this.negativeGoal = negativeGoal;
		this.positivesFirst = positivesFirst;
	}

	@Override
	public Integer getInitialState() {
		return 0;
	}

	@Override
	public List<Integer> getSuccessors(Integer currentState) {
		if (positivesFirst) {
			return new ArrayList<Integer>(Arrays.asList(new Integer[] {currentState + 1, currentState - 1}));
		} else {
			return new ArrayList<Integer>(Arrays.asList(new Integer[] {currentState - 1, currentState + 1}));
		}
	}

	@Override
	public boolean isGoal(Integer state) {
		return (state.equals(negativeGoal) || state.equals(positiveGoal));
	}
}
