/*
 * Copyright 2021 Marc Liberatore.
 */

package similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sets.SetUtilities;
public class SimilarityUtilities {
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {
		Set<String> res = new HashSet<>();
		String[] tempArr = text.split("\\n");
		for (String var : tempArr){
			String temp = var.trim();
			if (temp.equals("")) continue; 
			res.add(temp);
		}
		return res;
	}

	/**
	 * Returns a list of words in the text, in the order they appeared in the text, 
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous, non-empty sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) {
		List<String> res = new ArrayList<>();

		String[] tempArr = text.split("\\W+");

		for (String var : tempArr){
			if (var.equals("")) continue;
			res.add(var.toLowerCase());
		}
		return res;
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		Set<String> t1 = trimmedLines(text1), t2 = trimmedLines(text2);

		return SetUtilities.jaccardIndex(t1, t2);
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		Set<String> t1 = trimmedLines(text1), t2 = trimmedLines(text2), template = trimmedLines(templateText);
		t1.removeAll(template);
		t2.removeAll(template);		
		return SetUtilities.jaccardIndex(t1, t2);
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		Set<String> res = new HashSet<>();
		for (int i = 0; i <= words.size() - shingleLength; i ++){
			String temp = "";
			for (int j = i; j < shingleLength + i; j ++){
				temp += words.get(j);
			}
			res.add(temp);
		}

		return res;
	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText. Removes the templateText 
	 * from consideration after shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {

		// Set<String> t1 = trimmedLines(text1), t2 = trimmedLines(text2), template = trimmedLines(templateText);	
		
		List<String> w1 = asLowercaseWords(text1), w2 = asLowercaseWords(text2), template = asLowercaseWords(templateText);

		Set<String> s1 = shingle(w1, shingleLength), s2 = shingle(w2, shingleLength), setTemplate = shingle(template, shingleLength);
		
		s1.removeAll(setTemplate);
		s2.removeAll(setTemplate);

		return SetUtilities.jaccardIndex(s1, s2);
	}
}
