/*
 * Copyright 2023 Marc Liberatore.
 */

package treaps;

import java.util.ArrayList;
import java.util.List;

public class Treap<E extends Comparable<E>> {
    Node<E> root;
    int size;

    public int size() {
        return size;
    }

    /**
     * Return true iff the tree contains the value e.
     * @param e
     * @return true iff the tree contains the value e
     */
    public boolean contains(E e) {
        return find(e) != null;
    }

    private Node<E> find(E e, Node<E> n) {
        if (n == null) {
            return null;
        } else if (e.equals(n.data)) {
            return n;
        } else if (e.compareTo(n.data) < 0) { // left
            return find(e, n.left);
        } else {  // right
            return find(e, n.right);
        }
    }
    
    private Node<E> find(E e) {
        return find(e, root);
    }


    /**
     * Perform an in-order traversal of the tree rooted at the given node, and return
     * a list of the elements in the order they were visited.
     * @param node
     * @return a list of elements from the tree from an in-order traversal starting at node
     */
    static <E> List<E> inOrder(Node<E> node) {
        ArrayList<E> res = new ArrayList<>();
        traversal(node, res);
        return res;
    }

    static <E> void traversal(Node<E> node, List<E> res){
        if (node == null) return;
        traversal(node.left, res);
        res.add(node.data);
        traversal(node.right, res);
    }
    
    /**
     * Returns true iff the tree rooted at n is a Binary Search Tree (based on its data values).
     * 
     * It must have no more than two children per node.
     * 
     * Each node's data value must be greater than all the values in its left subtree, and smaller
     * than all the values in its right subtree. (This implies duplicate values are not allowed.)
     * 
     * @param n true iff the tree rooted at n is a Binary Search Tree
     * @return 
     */
    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        if (n == null) return true;

        boolean checkLeft = true, checkRight = true;

        if (n.left != null && n.data.compareTo(n.left.data) < 0) checkLeft = false;

        if(n.right != null && n.data.compareTo(n.right.data) > 0) checkRight = false;

        return checkLeft && checkRight && isBST(n.left) && isBST(n.right);
    }

    /**
     * Returns true iff the tree rooted at n is heap (based on its priority values).
     * 
     * It must have no more than two children per node.
     * 
     * Each node's priority value must be greater than or equal to all the values 
     * in its children.
     * 
     * @param n true iff the tree rooted at n is a Binary Search Tree
     * @return 
     */
    static <E extends Comparable<E>> boolean isHeap(Node<E> n) {
        if (n == null) return true;

        boolean checkLeft = true, checkRight = true;

        if (n.left != null && n.priority < (n.left.priority)) checkLeft = false;

        if(n.right != null && n.priority < (n.right.priority)) checkRight = false;

        return checkLeft && checkRight && isHeap(n.left) && isHeap(n.right);

        
    }

    /**
     * Add the value e to the treap, maintaining the BST and heap properties.
     * 
     * @param e
     */
    public void add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size = 1;
            return;
        }
        add(e, root);
    }

    /**
     * Add the value e to the treap rooted at the given node, 
     * maintaining the BST and heap properties.
     * @param e
     * @param node
     */
    public void add(E e, Node<E> node) {
        // TODO! the BST add code below maintains the BST property,
        // but you need to add the code to restore the max-heap property
        //
        // I suggest writing a separate method and calling it from here
        // in the appropriate place(s)

        if (e.equals(node.data)) {
            node.data = e;
        }
        if (e.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                size++;
                checkHeapLeft(node);
                return;
            } else {
                add(e, node.left);
                checkHeapLeft(node);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                size++;
                checkHeapRight(node);
                return;
            } else {
                add(e, node.right);
                checkHeapRight(node);
            }
        }
    }

    // public void maintainMaxHeap(Node<E> cur){
    //     if (cur.left != null && cur.left.priority > cur.priority){
    //         rotateLL(cur);
    //     }

    //     if(cur.right != null && cur.right.priority < cur.priority){
    //         rotateRR(cur);
    //     }

    // }

    public void checkHeapRight(Node<E> cur){
        if (cur.right != null && cur.right.priority > cur.priority){
            rotateRR(cur);
        }
    }


    public void checkHeapLeft(Node<E> cur){
        if(cur.left != null && cur.left.priority > cur.priority) rotateLL(cur);
    }

    /**
     * Perform an LL rotation around n.
     * @param n
     */
    private void rotateLL(Node<E> n) {
        Node<E> A, B, T1, T2, T3, p; // p is B's parent ; note we never use T1 or T3!

        B = n;
        p = B.parent;
        A = B.left;
        T1 = A.left;
        T2 = A.right;
        T3 = B.right;

        // making A the root of the subtree
        if (B == root) { // special case: B was the root of the *whole* tree
            root = A;
            A.parent = null;
        } else { // otherwise, B was just a node in the tree, not its root
            if (p.left == B) {
                p.left = A;
            } else {
                p.right = A;
            }
            A.parent = p;
        }

        // now let's make B into A's right subchild
        A.right = B;
        B.parent = A;

        // finally, let's move T2 to B's new left subchild
        B.left = T2;
        if (T2 != null) {
            T2.parent = B;
        }
        
    }

    

    /**
     * Perform an RR rotation around n.
     * @param n
     */
    private void rotateRR(Node<E> n) {
        Node<E> pA, A, B, C, T2, T3;
        //pA is parent of A if exists
        //A is the node where imbalance happens
        //B is right child of A
        //C is left child of B, T3 is right child of B
        // T2 is left child of A

        pA = n.parent;
        A = n;
        T2 = A.left;
        B = A.right;
        T3 = B.left;
        C = B.right;

        if (root == A){
            root = B;
            B.parent = null;
        }

        else if (pA.left == A){
            pA.left = B;

        }

        else if (pA.right == A){
            pA.right = B;
        }

        B.parent = pA;

        //Begin rotation
        A.parent = B;
        B.left = A;
        A.right = T3;
        if (T3 != null) T3.parent = A;





    }

    public static void main(String[] args) {
        Treap<Integer> t = new Treap<>();

        for (int i = 0; i < 15; i++) {
            t.add(i);
            TreePrinter.print(t.root);
            System.out.println(isBST(t.root) + "/" + isHeap(t.root));
        }
    }
}
