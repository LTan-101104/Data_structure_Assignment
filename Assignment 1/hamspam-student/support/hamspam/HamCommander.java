/*
 * Copyright 2021 Marc Liberatore.
 */

package hamspam;

import java.util.Scanner;

public class HamCommander {
	// !You can do whatever the hell you want in the support folder
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		try {

			System.out.println("Enter a ham number and a spam number:");
			final int hamNumber = conIn.nextInt();
			final int spamNumber = conIn.nextInt();
			System.out.println("Ham number: " + hamNumber);
			System.out.println("Spam number: " + spamNumber);
			HamSpam hs = new HamSpam(hamNumber, spamNumber);

			System.out.println("Enter an integer greater than zero:");
			final int n = conIn.nextInt();
			System.out.println("The hamspam value of " + n + " is: " +
					hs.getValue(n));

		} finally {
			conIn.close();
		}
	}
}