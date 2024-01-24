/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {

	// ! In case of tie in choosing the largest overlap, choose the order that after
	// merging it will result in the shortest string

	/**
	 * Creates a new Fragment based upon a String representing a sequence of
	 * nucleotides, containing only the uppercase characters G, C, A and T.
	 * 
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of
	 *                                  nucleotides
	 */

	String nucleotides;

	private static boolean checkCharacters(String s) {
		boolean res = true;
		char temp;
		for (int i = 0; i < s.length(); i++) {
			temp = s.charAt(i);
			if (temp != 'G' && temp != 'C' && temp != 'T' && temp != 'A')
				res = false;
		}
		return res;
	}

	public Fragment(String nucleotides) throws IllegalArgumentException {

		if (!checkCharacters(nucleotides))
			throw new IllegalArgumentException();

		this.nucleotides = nucleotides;
	}

	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return this.nucleotides.length();
	}

	/**
	 * Returns a String representation of this fragment, exactly as was passed to
	 * the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return this.nucleotides;
	}

	/**
	 * Return true if and only if this fragment contains the same sequence of
	 * nucleotides as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Fragment)) {
			return false;
		}

		Fragment f = (Fragment) o;

		// Don't unconditionally return false; check that
		// the relevant instances variables in this and f
		// are semantically equal

		return (this.nucleotides.equals(f.toString()));

	}

	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment
	 * and the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2,
	 * not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {

		int count = 0;
		int i = this.length() - 1, holder = f.length() - 1;

		int j = holder;
		while (j >= 0) {
			if (i < 0 || f.toString().charAt(j) != this.nucleotides.charAt(i)) {
				i = this.length() - 1;
				holder--;
				j = holder;
				count = 0;
			}

			else {
				// when detect a similar letter
				count++;
				j--;
				i--;
			}

		}
		return count;
	}

	/**
	 * Returns a new fragment based upon merging this fragment with another fragment
	 * f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right;
	 * the fragments will be overlapped as much as possible during the merge.
	 * 
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment
	 */
	public Fragment mergedWith(Fragment f) {
		String temp;

		int countOverlap = this.calculateOverlap(f);

		temp = this.nucleotides + f.toString().substring(countOverlap);

		return new Fragment(temp);
	}
}
