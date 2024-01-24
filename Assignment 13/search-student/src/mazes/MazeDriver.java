/*
 * Copyright 2021 Marc Liberatore.
 */

package mazes;

import java.util.List;

import search.Searcher;

public class MazeDriver {
	public static void main(String[] args) {
		MazeGenerator mg = new MazeGenerator(2, 2, 0);
		Maze maze = mg.generateDfs();
		System.out.println(maze.toString());
		Searcher<Cell> searcher = new Searcher<Cell>(maze);
		List<Cell> solution = searcher.findSolution();
		for (Cell cell : solution) {
			System.out.println(cell);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
