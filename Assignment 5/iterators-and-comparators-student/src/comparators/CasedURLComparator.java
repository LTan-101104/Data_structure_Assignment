/*
 * Copyright 2023 Marc Liberatore.
 */

package comparators;

import java.util.Comparator;

/**
 * A comparator to determine the order of two web pages. The two web pages are
 * compared lexicographically. However, if the CasedURLComparator is created
 * with ignoreCase set to true, then the comparison should be case-insensitive.
 */
public class CasedURLComparator implements Comparator<WebPageRecord> {
    final boolean ignoreCase;

    public CasedURLComparator(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        if (this.ignoreCase) {
            return x.URL.toLowerCase().compareTo(y.URL.toLowerCase());
        }

        return x.URL.compareTo(y.URL);
    }

    // public static void main(String args[]) {
    // String s1 = "A";
    // String s2 = "a";
    // System.out.println(s1.compareTo(s2));
    // }

}
