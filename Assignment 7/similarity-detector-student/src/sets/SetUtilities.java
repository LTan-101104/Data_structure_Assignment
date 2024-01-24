/*
 * Copyright 2021 Marc Liberatore.
 */

package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities {
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */
	public static <E> Set<E> union(Set<E> s, Set<E> t) {
		Set<E> res = new HashSet<>();
		res.addAll(s);
		res.addAll(t);
		return res;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t) {
		Set<E> res = new HashSet<>(s);
		res.retainAll(s);
		res.retainAll(t);
		return res;
	}

	/**
	 * Returns a new set representing the set difference s and t,
	 * that is, s \ t (or s - t).
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t) {
		Set<E> res = new HashSet<>(s);
		//removeAll(<collection>): remove from the current set all objects that are in the parameter collection 
		res.removeAll(t);
		return res;
	}
	
	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as 1 if both sets are empty.
     *
	 * Otherwise, it is defined as the size of the intersection of the sets, 
	 * divided by the size of the union of the sets.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	public static <E> double jaccardIndex(Set<E> s, Set<E> t) {
		if(s.isEmpty() && t.isEmpty()) return 1.0;

		Set<E> union = new HashSet<>(s);
		union.addAll(t);

		Set<E> Intersect = new HashSet<>(s);
		Intersect.retainAll(t);
		
		System.out.println(Intersect.size());
		System.out.println(union.size());
		double res = (double) Intersect.size() / (double) union.size();
		return res ;
	}
}
