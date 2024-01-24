/*
 * Copyright 2021 Marc Liberatore.
 */

package mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import graphs.GraphMarker;
import graphs.UnweightedGraphInterface;
import graphs.UnweightedSparseGraph;

/**
 * A class to facilitate the building of random Mazes.
 * 
 * @author liberato
 *
 */
public class MazeGenerator {
	private final int width;
	private final int height;
	private final UnweightedGraphInterface<Cell> mazeGraph;
	private final Random random;

	/**
	 * Creates a new Maze of the given dimensions. 
	 * 
	 * Using the same width, height, and seed will always result in the same maze.
	 * 
	 * Change the seed to get a new maze of the same dimensions.
	 * 
	 * @param width
	 * @param height
	 * @param seed 
	 */
	public MazeGenerator(int width, int height, long seed) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		random = new Random(seed);
		mazeGraph = new UnweightedSparseGraph<Cell>(width * height);
	}

	/**
	 * 
	 * @return a new maze
	 */
	public Maze generateDfs() {

		// first, build the maze using a standard randomized DFS walk
		// (see https://en.wikipedia.org/wiki/Maze_generation_algorithm)
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				mazeGraph.addVertex(new Cell(x, y));
			}
		}

		final GraphMarker<Cell> marker = mazeGraph.getMarker();
		Cell current = randomCell();
		marker.mark(current);

		final Stack<Cell> stack = new Stack<Cell>();
		while (marker.countMarked() < width * height) {
			final List<Cell> neighbors = listNeighbors(current);
			final List<Cell> unvisitedNeighbors = new ArrayList<Cell>();
			for (Cell neighbor : neighbors) {
				if (!marker.isMarked(neighbor)) {
					unvisitedNeighbors.add(neighbor);
				}
			}
			if (!unvisitedNeighbors.isEmpty()) {
				final Cell neighbor = unvisitedNeighbors.get(random
						.nextInt(unvisitedNeighbors.size()));
				stack.push(current);
				mazeGraph.addEdge(current, neighbor);
				mazeGraph.addEdge(neighbor, current);
				current = neighbor;
				marker.mark(current);
			} else if (!stack.isEmpty()) {
				current = stack.pop();
			} else {
				// should never reach this branch unless the above
				// code is adjusted in some way
				assert false;
				final List<Cell> unvisitedCells = new ArrayList<Cell>();
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						final Cell c = new Cell(x, y);
						if (!marker.isMarked(c)) {
							unvisitedCells.add(c);
						}
					}
				}
				current = unvisitedCells.get(random.nextInt(unvisitedCells
						.size()));
				marker.mark(current);
			}
		}

		/*
		 * the maze is now fully connected, and has only one path from each node
		 * to each other node
		 * 
		 * next, we remove a small set of walls; the idea here is to add some
		 * additional connections to the underlying graph , so that there will
		 * be some mazes where there are multiple paths from the start to a
		 * goal; sometimes, one path will be shorter than others
		 */
		int numRemovals = (int) Math.ceil(Math.pow(width * height, 1.0 / 3.0));
		while (numRemovals > 0) {
			boolean removed = false;
			while (true) {
				final Cell cell = randomCell();
				final List<Cell> neighbors = listNeighbors(cell);
				for (Cell neighbor : neighbors) {
					if (!mazeGraph.hasEdge(cell, neighbor)) {
						mazeGraph.addEdge(cell, neighbor);
						mazeGraph.addEdge(neighbor, cell);
						removed = true;
						numRemovals--;
						break;
					}
				}
				if (removed) {
					break;
				}
			}
		}

		// finally, choose start/end cells
		final Cell start = randomCell();
		Cell goal = randomCell();
		final List<Cell> goals = new ArrayList<Cell>();
		while (goals.size() < (int) Math
				.ceil(Math.pow(width * height, 0.5) / 10.0)) {
			while (goal.equals(start) || goals.contains(goal)) {
				goal = randomCell();
			}
			goals.add(goal);
		}
		return new Maze(width, height, mazeGraph, start, goals);
	}

	private Cell randomCell() {
		return new Cell(random.nextInt(width), random.nextInt(height));
	}

	private List<Cell> listNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		if (cell.posY - 1 >= 0) {
			neighbors.add(new Cell(cell.posX, cell.posY - 1));
		}
		if (cell.posY + 1 < height) {
			neighbors.add(new Cell(cell.posX, cell.posY + 1));
		}
		if (cell.posX - 1 >= 0) {
			neighbors.add(new Cell(cell.posX - 1, cell.posY));
		}
		if (cell.posX + 1 < width) {
			neighbors.add(new Cell(cell.posX + 1, cell.posY));
		}

		return neighbors;
	}
}
