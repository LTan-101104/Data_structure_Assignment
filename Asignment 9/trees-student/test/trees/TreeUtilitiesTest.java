/*
 * Copyright 2023 Marc Liberatore.
 */

package trees;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class TreeUtilitiesTest {

    //  @Rule
    //  public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds
    @Before
    public void setup() {
    }

    @Test
    public void testInOrderEmpty() throws Exception {
        assertEquals(new ArrayList<>(), TreeUtilities.inOrder(null));
    }

    @Test
    public void testInOrderOne() throws Exception {
        Node<Integer> n = new Node<>(1);
        assertEquals(Arrays.asList(1), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertEquals(Arrays.asList(2, 1), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertEquals(Arrays.asList(1, 2), TreeUtilities.inOrder(n));
    }

    @Test
    public void testInOrderBSTRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
            b.add(i);
        }
        assertEquals(l, TreeUtilities.inOrder(b.root));
    }

    @Test
    public void testInOrderBSTLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        for (int i = 9; i >= 0; i--) {
            b.add(i);
        }
        assertEquals(l, TreeUtilities.inOrder(b.root));
    }

    @Test
    public void testInOrderBSTBalanced() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            l.add(i);
        }
        b.add(4);
        b.add(2);
        b.add(1);
        b.add(3);
        b.add(6);
        b.add(5);
        b.add(7);

        assertEquals(l, TreeUtilities.inOrder(b.root));
    }

    @Test
    public void testInOrderAll() throws Exception {
        testInOrderEmpty();
        testInOrderOne();
        testInOrderTwoLeft();
        testInOrderTwoRight();
        testInOrderBSTRight();
        testInOrderBSTLeft();
        testInOrderBSTBalanced();
    }

    @Test
    public void testHeightEmpty() throws Exception {
        assertEquals(-1, TreeUtilities.height(null));
    }

    @Test
    public void testHeightOne() throws Exception {
        Node<Integer> n = new Node<>(1);
        assertEquals(0, TreeUtilities.height(n));
    }

    @Test
    public void testHeightTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertEquals(1, TreeUtilities.height(n));
    }

    @Test
    public void testHeightTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertEquals(1, TreeUtilities.height(n));
    }

    @Test
    public void testHeightBSTRight() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            b.add(i);
        }
        assertEquals(9, TreeUtilities.height(b.root));
    }

    @Test
    public void testHeightBSTLeft() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        for (int i = 9; i >= 0; i--) {
            b.add(i);
        }
        assertEquals(9, TreeUtilities.height(b.root));
    }

    @Test
    public void testHeightBSTBalanced() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(4);
        b.add(2);
        b.add(1);
        b.add(3);
        b.add(6);
        b.add(5);
        b.add(7);

        assertEquals(2, TreeUtilities.height(b.root));
    }

    @Test
    public void testHeightAll() throws Exception {
        testHeightEmpty();
        testHeightOne();
        testHeightTwoLeft();
        testHeightTwoRight();
        testHeightBSTRight();
        testHeightBSTLeft();
        testHeightBSTBalanced();
    }

    @Test
    public void testBalanceEmpty() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);
        assertTrue(TreeUtilities.equalSubtrees(b.root, t.root));
    }

    @Test
    public void testBalanceOne() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);
        assertTrue(TreeUtilities.equalSubtrees(b.root, t.root));
    }

    @Test
    public void testBalanceThree() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(3);
        b.add(2);
        b.add(1);
        BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);

        BinarySearchTree<Integer> bal = new BinarySearchTree<>();
        bal.add(2);
        bal.add(1);
        bal.add(3);

        assertTrue(TreeUtilities.equalSubtrees(bal.root, t.root));
    }

    @Test
    public void testBalanceSeven() throws Exception {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        for (int i = 1; i < 8; i++) {
            b.add(i);
        }
        BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);

        BinarySearchTree<Integer> bal = new BinarySearchTree<>();
        bal.add(4);
        bal.add(2);
        bal.add(1);
        bal.add(3);
        bal.add(6);
        bal.add(5);
        bal.add(7);

        assertTrue(TreeUtilities.equalSubtrees(bal.root, t.root));
    }

    @Test
    public void testBalance1337() throws Exception {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            l.add(i);
        }
        Collections.shuffle(l, new Random(1337)); // change this number to change the shuffle
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        for (Integer i : l) {
            b.add(i);
        }
        BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);
        assertEquals(31, t.size());
        assertEquals(4, TreeUtilities.height(t.root));
        Collections.sort(l);
        assertEquals(l, TreeUtilities.inOrder(t.root));
    }

    @Test
    public void testBalance100Randomized() throws Exception {
        for (int seed = 0; seed < 100; seed++) {
            // the seed controls the rest of the random choices in this method
            Random r = new Random(seed);

            int size = 1 + r.nextInt((int) Math.pow(2, 16));

            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                l.add(i);
            }
            Collections.shuffle(l, r);
            BinarySearchTree<Integer> b = new BinarySearchTree<>();
            for (Integer i : l) {
                b.add(i);
            }
            BinarySearchTree<Integer> t = TreeUtilities.intoBalanced(b);
            assertEquals(size, t.size());
            int height = TreeUtilities.height(t.root);
            assertEquals((int) Math.ceil(Math.log(size) / Math.log(2)) - 1, height);
            Collections.sort(l);
            assertEquals(l, TreeUtilities.inOrder(t.root));
        }
    }

    @Test
    public void testBalanceAll() throws Exception {
        testBalanceEmpty();
        testBalanceOne();
        testBalanceThree();
        testBalanceSeven();
        testBalance1337();
        testBalance100Randomized();
    }

    @Test
    public void testisBSTEmpty() throws Exception {
        assertTrue(TreeUtilities.isBST(null));
    }

    @Test
    public void testisBSTOne() throws Exception {
        assertTrue(TreeUtilities.isBST(new Node<Integer>(0)));
    }

    @Test
    public void testIsBSTTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        m.left = n;
        assertTrue(TreeUtilities.isBST(m));
    }

    @Test
    public void testIsNotBSTTwoLeft() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.left = m;
        assertFalse(TreeUtilities.isBST(n));
    }

    @Test
    public void testIsBSTTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        n.right = m;
        assertTrue(TreeUtilities.isBST(n));
    }

    @Test
    public void testIsNotBSTTwoRight() throws Exception {
        Node<Integer> n = new Node<>(1);
        Node<Integer> m = new Node<>(2);
        m.right = n;
        assertFalse(TreeUtilities.isBST(m));
    }

    // @Test
    // public void testIsBSTWarning() throws Exception {        
    //     fail("This test is intended to fail. Read the comment and then comment out this test.");
    //     // We are not providing you with exhaustive tests for your isBST() code.
    //     // Nor do you *have to* test it. But if you run into trouble with your AVL tree
    //     // implementation, it can be helpful to be able to automatically check the tree
    //     // after a rotation to see if it's still a binary tree.
    // }

    @Test
    public void testisAVLEmpty() throws Exception {
        assertTrue(TreeUtilities.isAVLTree(null));
    }

    @Test
    public void testisAVLTreeOne() throws Exception {
        assertTrue(TreeUtilities.isAVLTree(new Node<Integer>(0)));
    }

    @Test
    public void testIsAVLTreeTwoLeft() throws Exception {
        Node<Integer> m = new Node<>(2);
        Node<Integer> n = new Node<>(1, m);
        m.left = n;
        assertTrue(TreeUtilities.isAVLTree(m));
    }

    @Test
    public void testIsNotAVLTreeThreeLeft() throws Exception {
        Node<Integer> o = new Node<>(3);
        Node<Integer> m = new Node<>(2, o);
        Node<Integer> n = new Node<>(1, m);
        o.left = m;
        m.left = n;
        assertFalse(TreeUtilities.isAVLTree(o));
    }

    // @Test
    // public void testIsAVLTreeWarning() throws Exception {        
    //     fail("This test is intended to fail. Read the comment and then comment out this test.");
    //     // We are not providing you with exhaustive tests for your isAVLTree code.
    //     // Nor do you *have to* test it. But if you run into trouble with your AVL tree
    //     // implementation, it can be helpful to be able to automatically check the tree
    //     // after a rotation to see if it's still an AVL tree.
    // }
}