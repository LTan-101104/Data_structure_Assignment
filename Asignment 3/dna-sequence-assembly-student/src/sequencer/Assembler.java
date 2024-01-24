/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {

	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list will not be
	 * modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */

	ArrayList<Fragment> holder;

	public Assembler(List<Fragment> fragments) {
		this.holder = new ArrayList<>(fragments);
	}

	/**
	 * Returns the current list of fragments this assembler contains.
	 * 
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return this.holder;
	}

	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was
	 * performed.
	 * 
	 * This method chooses the best assembly possible, that is, it merges the two
	 * fragments with the largest overlap, breaking ties between merged fragments by
	 * choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted
	 * into the list of fragments in this assembler, and the two original fragments
	 * are removed from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {
		// find largest overlap
		int max = 0;
		int countOverlap;
		Fragment[] tempFragOverlap = new Fragment[2];
		for (Fragment i : this.holder) {
			for (Fragment j : this.holder) {
				if (i == j)
					continue;
				countOverlap = i.calculateOverlap(j);
				if (countOverlap > max) {
					max = countOverlap;
					tempFragOverlap[0] = i;
					tempFragOverlap[1] = j;
				}
			}
		}

		if (max == 0 || this.holder.size() == 1 || this.holder.size() == 0)
			return false;

		// starting to merge

		Fragment mergedFrag = tempFragOverlap[0].mergedWith(tempFragOverlap[1]);
		// modify list to update
		this.holder.remove(tempFragOverlap[0]);
		this.holder.remove(tempFragOverlap[1]);

		this.holder.add(mergedFrag);

		return true;

	}

	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {
		while (this.assembleOnce()) {
			continue;
		}

		return;
	}
}
