/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class AssemblerTest {
	// @Rule
	// public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private List<Fragment> one;
	private List<Fragment> two;
	private List<Fragment> three;
	private List<Fragment> dupFullOverlap;

	// This method is run before each test, performing initialization
	// on and of objects for the test.
	@Before
	public void setup() {
		one = new ArrayList<Fragment>();
		one.add(new Fragment("GCAT"));

		two = new ArrayList<Fragment>();
		two.add(new Fragment("CATG"));
		two.add(new Fragment("GCAT"));

		three = new ArrayList<Fragment>();
		three.add(new Fragment("ACTGTG"));
		three.add(new Fragment("ACACAC"));
		three.add(new Fragment("TGTGGG"));

		dupFullOverlap = new ArrayList<Fragment>();
		dupFullOverlap.add(new Fragment("AAGAA"));
		dupFullOverlap.add(new Fragment("AAGAA"));

	}

	@Test
	public void testValidConstructor() {
		Assembler a = new Assembler(one);
	}

	@Test
	public void testSimpleAssemble() {
		Assembler a = new Assembler(two);
		assertTrue(a.assembleOnce());
	}

	@Test
	public void testSimpleAssembleStop() {
		Assembler a = new Assembler(two);
		assertTrue(a.assembleOnce());
		assertFalse(a.assembleOnce());
	}

	@Test
	public void testCorrectSimpleAssemble() {
		Assembler a = new Assembler(two);
		assertTrue(a.assembleOnce());
		assertFalse(a.assembleOnce());
		assertEquals(Arrays.asList(new Fragment("GCATG")), a.getFragments());
	}

	@Test
	public void testNoAssemble() {
		one.add(new Fragment("CAGT"));
		Assembler a = new Assembler(one);
		assertFalse(a.assembleOnce());
		assertEquals(2, a.getFragments().size());
	}

	@Test
	public void testDuplicateFullOverlapAssemble() {
		Assembler a = new Assembler(dupFullOverlap);
		assertTrue(a.assembleOnce());
		assertFalse(a.assembleOnce());
		assertEquals(Arrays.asList(new Fragment("AAGAA")), a.getFragments());
	}

	@Test
	public void testCorrectThreeAssembleStepwise() {
		Assembler a = new Assembler(three);
		assertEquals(3, a.getFragments().size());
		assertTrue(a.assembleOnce());
		assertEquals(2, a.getFragments().size());
		assertTrue(a.assembleOnce());
		assertEquals(1, a.getFragments().size());
		assertFalse(a.assembleOnce());
		assertEquals(1, a.getFragments().size());
		assertEquals(Arrays.asList(new Fragment("ACACACTGTGGG")), a.getFragments());
	}

	@Test
	public void testCorrectThreeAssembleAll() {
		Assembler a = new Assembler(three);
		a.assembleAll();
		assertEquals(Arrays.asList(new Fragment("ACACACTGTGGG")), a.getFragments());
	}

	@Test
	public void testConstructorArgumentUnharmed() {
		List<Fragment> copy = new ArrayList<Fragment>(three);
		Assembler a = new Assembler(three);
		a.assembleOnce();
		assertEquals(copy, three);
	}

	@Test
	public void testTiebreaker() {
		List<Fragment> l = new ArrayList<Fragment>(Arrays.asList(new Fragment("GGGAAAC"), new Fragment("AAACGGG"),
				new Fragment("CCCGTTTA"), new Fragment("TTTAGCCC")));
		Assembler a = new Assembler(l);
		a.assembleOnce();
		assertTrue(a.getFragments().contains(new Fragment("GGGAAACGGG")));
	}

	@Test
	public void testThreeCorrectAssembleOrder() {
		Assembler a = new Assembler(
				Arrays.asList(new Fragment("GCATAG"), new Fragment("GGCCAT"), new Fragment("CATAGG")));
		a.assembleAll();
		assertEquals(Arrays.asList(new Fragment("GCATAGGCCAT")), a.getFragments());
	}

	@Test
	public void testSequenceHaemoglobinSubunitBeta() {
		Assembler a = new Assembler(Arrays.asList(new Fragment(
				"CAAGGTGAACGTGGATGAAGTTGGTGGTGAGGCCCTGGGCAGGCTGCTGGTGGTCTACCCTTGGACCCAGAGGTTCTTTGAGTCCTTTGGGGATCTGTCCACTCCTGATGCTGTTATGGGCAACCCTAAGGTGAAGGCTCA"),
				new Fragment(
						"TCACTTTGGCAAAGAATTCACCCCACCAGTGCAGGCTGCCTATCAGAAAGTGGTGGCTGGTGTGGCTAATGCCCTGGCCCACAAGTATCACTAAGCTCGCTTTCTTGCTGTCCAATTTCTATTAAAGGTTCCTT"),
				new Fragment(
						"TTTCTTGCTGTCCAATTTCTATTAAAGGTTCCTTTGTTCCCTAAGTCCAACTACTAAACTGGGGGATATTATGAAGGGCCTTGAGCATCTGGATTCTGCCTAATAAAAAACATTTATTTTCATTGC"),
				new Fragment(
						"ACTCCTGATGCTGTTATGGGCAACCCTAAGGTGAAGGCTCATGGCAAGAAAGTGCTCGGTGCCTTTAGTGATGGCCTGGCTCACCTGGACAACCTCAAGGGCACCTTTGCCACACTGAGTGAGCTGCACTGTGACAAGCTGCACGTGGATCCTGAGA"),
				new Fragment(
						"GCACCTTTGCCACACTGAGTGAGCTGCACTGTGACAAGCTGCACGTGGATCCTGAGAACTTCAGGCTCCTGGGCAACGTGCTGGTCTGTGTGCTGGCCCATCACTTTGGCAAAGAATTCACCCCACCAGTGCAGGCTGCCTATCA"),
				new Fragment(
						"ACATTTGCTTCTGACACAACTGTGTTCACTAGCAACCTCAAACAGACACCATGGTGCATCTGACTCCTGAGGAGAAGTCTGCCGTTACTGCCCTGTGGGGCAAGGTGAACGTGGATGAAGTTGGTGGTGAGGCCCTGGGCAGGCTGCT")));
		a.assembleAll();
		assertEquals(1, a.getFragments().size());
		assertEquals(new Fragment(
				"ACATTTGCTTCTGACACAACTGTGTTCACTAGCAACCTCAAACAGACACCATGGTGCATCTGACTCCTGAGGAGAAGTCTGCCGTTACTGCCCTGTGGGGCAAGGTGAACGTGGATGAAGTTGGTGGTGAGGCCCTGGGCAGGCTGCTGGTGGTCTACCCTTGGACCCAGAGGTTCTTTGAGTCCTTTGGGGATCTGTCCACTCCTGATGCTGTTATGGGCAACCCTAAGGTGAAGGCTCATGGCAAGAAAGTGCTCGGTGCCTTTAGTGATGGCCTGGCTCACCTGGACAACCTCAAGGGCACCTTTGCCACACTGAGTGAGCTGCACTGTGACAAGCTGCACGTGGATCCTGAGAACTTCAGGCTCCTGGGCAACGTGCTGGTCTGTGTGCTGGCCCATCACTTTGGCAAAGAATTCACCCCACCAGTGCAGGCTGCCTATCAGAAAGTGGTGGCTGGTGTGGCTAATGCCCTGGCCCACAAGTATCACTAAGCTCGCTTTCTTGCTGTCCAATTTCTATTAAAGGTTCCTTTGTTCCCTAAGTCCAACTACTAAACTGGGGGATATTATGAAGGGCCTTGAGCATCTGGATTCTGCCTAATAAAAAACATTTATTTTCATTGC"),
				a.getFragments().get(0));
	}

}
