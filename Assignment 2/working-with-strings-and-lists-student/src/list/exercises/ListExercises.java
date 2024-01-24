/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExercises {

	/**
	 * Counts the number of characters in total across all strings in the supplied list;
	 * in other words, the sum of the lengths of the all the strings.
	 * @param l a non-null list of strings
	 * @return the number of characters
	 */
	public static int countCharacters(List<String> l) {
		int sum = 0;

		for (String var : l){
			sum += var.length();
		}
		return sum;
	}
	
	/**
	 * Splits a string into words and returns a list of the words. 
	 * If the string is empty, split returns a list containing an empty string.
	 * 
	 * @param s a non-null string of zero or more words
	 * @return a list of words
	 */
	public static List<String> split(String s) {
		String[] temp = s.split("\\s+");

		List<String> res = Arrays.asList(temp);

		return res;

	}

	/**
	 * Returns a copy of the list of strings where each string has been 
	 * uppercased (as by String.toUpperCase).
	 * 
	 * The original list is unchanged.
	 * 
	 * @param l a non-null list of strings
	 * @return a list of uppercased strings
	 */
	public static List<String> uppercased(List<String> l) {
		//gotta create the copy of list first

		ArrayList<String> res = new ArrayList<>();

		for (String var : l){
			res.add(var.toUpperCase());
		}

		return res;
	}

	/**
	 * Returns true if and only if each string in the supplied list of strings
	 * starts with an uppercase letter. If the list is empty, returns false.
	 * 
	 * @param l a non-null list of strings
	 * @return true iff each string starts with an uppercase letter
	 */
	public static boolean allCapitalizedWords(List<String> l) {
		
		if (l.size() == 0) return false;

		boolean res = true;

		char first;

		for (String var : l){
			//For special character, Java does not conceive it as lower case or upper case

			if (var.equals("")) {
				res = false;
				continue;
			}
			
			first = var.charAt(0);
			if (!(first >= 'a' && first <= 'z' || first >= 'A' && first <='Z')){
				res = false;
			}

			if ( Character.isLowerCase(first)) {
				res = false;
			}			
		}

		return res;

	}

	/**
	 * Returns a list of strings selected from a supplied list, which contain the character c.
	 * 
	 * The returned list is in the same order as the original list, but it omits all strings 
	 * that do not contain c.
	 * 
	 * The original list is unmodified.
	 * 
	 * @param l a non-null list of strings
	 * @param c the character to filter on
	 * @return a list of strings containing the character c, selected from l
	 */
	public static List<String> filterContaining(List<String> l, char c) {
		List<String> res = new ArrayList<String>();
		for (String var : l){
			if (var.indexOf(c) != -1 ) res.add(var);
		}
		return res;
	}
	
	/**
	 * Inserts a string into a sorted list of strings, maintaining the sorted property of the list.
	 * 
	 * @param s the string to insert
	 * @param l a non-null, sorted list of strings
	 */
	public static void insertInOrder(String s, List<String> l) {
		// for (int i = 0; i < l.size(); i ++){
		// 	String cur = l.get(i);
		// 	if (i == 0 && s.compareTo(cur) < 0) {
		// 		l.add(0, s);
		// 		break;
		// 	}  

		// 	if ( i == l.size() - 1 && s.compareTo(cur) > 0){
		// 		l.add(i + 1, s);
		// 		break;
		// 	}

		// 	if (s.compareTo(cur) > 0 && s.compareTo(l.get(i + 1)) < 0){
		// 		l.add(i + 1, s);
		// 		break;
		// 	}
		// }

		int i = 0;

		do{
			if (l.size() == 0) {
				l.add(s);
				break;
			}

			String cur = l.get(i);
			if (i == 0 && s.compareTo(cur) < 0) {
				l.add(0, s);
				break;
			}  

			else if ( i == l.size() - 1 && s.compareTo(cur) > 0){
				l.add(i + 1, s);
				break;
			}

			else if (s.compareTo(cur) > 0 && s.compareTo(l.get(i + 1)) < 0){
				l.add(i + 1, s);
				break;
			}

			i++;
		}

		while(i < l.size());
	}
}
