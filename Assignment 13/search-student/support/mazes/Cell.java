/*
 * Copyright 2021 Marc Liberatore.
 */

package mazes;

/**
 * A class representing an (x,y) position (or cell) in a grid-based maze.
 * 
 * @author liberato
 *
 */
public class Cell implements Comparable<Cell> {
	public final int posX;
	public final int posY;

	public Cell(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public int compareTo(Cell other) {
		int cmp = Integer.compare(posX, other.posX);
		if (cmp != 0) {
			return cmp;
		}
		return Integer.compare(posY, other.posY);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Cell other = (Cell) obj;
		if (posX != other.posX) return false;
		if (posY != other.posY) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [x=" + posX + ", y=" + posY + "]";
	}
}
