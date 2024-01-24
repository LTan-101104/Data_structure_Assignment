/*
 * Copyright 2023 Marc Liberatore.
 */

package treaps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class TreapTest {

   //  @Rule
   //  public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

   @Before
   public void setup() {
   }

   // @Test
   // public void testIsHeapWarning() throws Exception {        
   //    // fail("This test is intended to fail. Read the comment and then comment out this test.");
   //    // // We are not providing you with exhaustive tests for your inOrder, isBST, or isHeap code.
   //    // // Nor do you *have to* implement them. But your local version of testTreap1000Randomized
   //    // // won't pass until you do. (The Gradescope test will pass if your treap code is correct
   //    // // -- it doesn't use your version of inOrder, isBST, or isHeap.)
   // }

   @Test
   public void testTreap1000Randomized() throws Exception {
      final int size = 100;
      for (int seed = 0; seed < 1000; seed++) {
         // the seed controls the rest of the random choices in this method
         Random r = new Random(seed);

         List<Integer> l = new ArrayList<>(size);
         for (int i = 0; i < size; i++) {
            l.add(i);
         }
         Collections.shuffle(l, r);

         Treap<Integer> t = new Treap<>();
         List<Integer> v = new ArrayList<>();
         int count = 0;
         for (int i : l) {
            count++;
            t.add(i);
            v.add(i);
            assertTrue(Treap.isBST(t.root));
            assertTrue(Treap.isHeap(t.root));
            assertEquals(count, t.size());
            for (int j : v) {
               assertTrue(t.contains(j));
            }
         }
      }
   }
}