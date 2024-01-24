/*
 * Copyright 2021 Marc Liberatore.
 */

package mazes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import graphs.UnweightedGraphInterface;

/**
 * A maze, with one start position and one or more goal positions. The maze is
 * fully connected -- that is, every space is reachable from every other space.
 * 
 * @author liberato
 *
 */
public class Maze implements SearchProblem<Cell> {
	private final int width;
	private final int height;
	private final UnweightedGraphInterface<Cell> mazeGraph;
	private final Cell start;
	private final List<Cell> goals;

	Maze(int width, int height, UnweightedGraphInterface<Cell> mazeGraph,
			Cell start, List<Cell> goals) {
		this.width = width;
		this.height = height;
		this.mazeGraph = mazeGraph;
		this.start = start;
		this.goals = Collections.unmodifiableList(goals);
	}

	public String toString() {
		final List<String> lines = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		// top border
		lines.add(topOrBottomBorder());

		// rows
		for (int i = 0; i < height - 1; i++) {
			// a row of alternating cells / walls
			sb = new StringBuilder();
			// always starts with a border wall
			sb.append(i % 10);
			for (int j = 0; j < width - 1; j++) {
				final Cell cell = new Cell(j, i);
				sb.append(cellValue(cell));
				sb.append(wallValue(new Cell(j, i), new Cell(j + 1, i)));
			}
			// always ends with cell then the border wall
			{
				final Cell cell = new Cell(width - 1, i);
				sb.append(cellValue(cell));
				sb.append(i % 10);
				lines.add(sb.toString());
			}

			// then a row of walls
			sb = new StringBuilder();
			// border wall
			sb.append("#");
			for (int j = 0; j < width - 1; j++) {
				sb.append(wallValue(new Cell(j, i), new Cell(j, i + 1)));
				// diagonal offset from cells are always filled
				sb.append("#");
			}
			// remember the last column
			sb.append(wallValue(new Cell(width - 1, i), new Cell(width - 1,
					i + 1)));
			// and border wall
			sb.append("#");
			lines.add(sb.toString());
		}

		// finally the last row of cells/walls
		sb = new StringBuilder();
		sb.append((height - 1) % 10);
		for (int j = 0; j < width - 1; j++) {
			final int i = height - 1;
			final Cell cell = new Cell(j, i);
			sb.append(cellValue(cell));
			sb.append(wallValue(cell, new Cell(j + 1, i)));
		}
		// last cell
		{
			final Cell cell = new Cell(width - 1, height - 1);
			sb.append(cellValue(cell));
			sb.append((height - 1) % 10);
			lines.add(sb.toString());
		}

		// and the bottom border
		lines.add(topOrBottomBorder());

		sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line);
			sb.append("\n");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	private String cellValue(Cell cell) {
		if (cell.equals(start)) {
			return "S";
		} else if (goals.contains(cell)) {
			return "G";
		} else {
			return " ";
		}
	}

	private String wallValue(Cell c1, Cell c2) {
		if (mazeGraph.hasEdge(c1, c2)) {
			return " ";
		} else {
			return "#";
		}
	}

	private String topOrBottomBorder() {
		final int printWidth = 2 * width + 1;
		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < printWidth; i++) {
			if (i % 2 == 0) {
				sb.append("#");
			} else {
				sb.append((i / 2) % 10);
			}
		}
		return sb.toString();
	}

	@Override
	public Cell getInitialState() {
		return start;
	}

	@Override
	public List<Cell> getSuccessors(Cell currentState) {
		return mazeGraph.getNeighbors(currentState);
	}

	@Override
	public boolean isGoal(Cell state) {
		return goals.contains(state);
	}
}