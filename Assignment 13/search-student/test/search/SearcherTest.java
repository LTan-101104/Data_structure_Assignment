/*
 * Copyright 2021 Marc Liberatore.
 */

package search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import mazes.Cell;
import mazes.Maze;
import mazes.MazeGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class SearcherTest {
//	@Rule
//	public Timeout globalTimeout = new Timeout(500L, TimeUnit.MILLISECONDS);

	private Maze maze;

	@Before
	public void before() {
		maze = new MazeGenerator(3, 3, 2).generateDfs();
		/* maze should now be:
		#0#1#2#
		0  S  0
		# # # #
		1     1
		# ### #
		2  G  2
		#0#1#2#
		*/
	}
	
	@Test
	public void testIsValidSolution() {
		List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
		assertTrue(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionStartsNotAtInitialState() {
		List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionDoesNotReachGoal() {
		List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionSkipsState() {
		List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionNotAdjancentStates() {
		List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(1, 1));
		solution.add(new Cell(1, 2));
		assertFalse(s.isValidSolution(solution));
	}
	
	@Test
	public void testSolver() {
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		assertTrue(s.isValidSolution(s.findSolution()));
	}
}
