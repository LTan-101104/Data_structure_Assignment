/*
 * Copyright 2023 Marc Liberatore.
 */
package trees;


/**
 * A (partial) implementation of AVL tree. You'll need to complete the rotations to make
 * insertion work. This project doesn't require you to implement remove(), but you're welcome
 * to do so if you want -- it requires a slight modification to `insertionCheck` as written.
 * 
 * If you're feeling like challenging yourself, reimplement the add-and-rotation-fix algorithm
 * the "traditional" way -- recursively.
 */
public class AVLTree<E extends Comparable<E>> {
    Node<E> root;
    int size;

    /**
     * Return the size of (number of elements stored in) the tree.
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Add e to the tree.
     * 
     * e is overwritten if it's already in the tree, but no duplication occurs.
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
     * The recursive helper method for add(E e).
     * @param e
     * @param node
     */
    private void add(E e, Node<E> node) {
        if (e.equals(node.data)) {
            node.data = e;
        }  else if (e.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                size++;
                insertionCheck(node.left);
                return;
            } else {
                add(e, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                size++;
                insertionCheck(node.right);
                return;
            } else {
                add(e, node.right);
            }
        }
    }

    /**
     * Check that the AVL property has not been broken after node's insertion.
     * If it has, then perform the rotation needed to restore it.
     * 
     * @param node
     */
    private void insertionCheck(Node<E> node) {
        Node<E> n = node;
        String path = "";
        while (true) {
            if (!isAVL(n)) {
                // rotate (and be done, if this is insertion)
                String rot = path.substring(0, 2);
                if (rot.equals("LL")) {
                    rotateLL(n);
                } else if (rot.equals("RR")) {
                    rotateRR(n);
                } else if (rot.equals("LR")) {
                    rotateLR(n);
                } else if (rot.equals("RL")) {
                    rotateRL(n);
                } else {
                    // should never get here!
                    throw new IllegalStateException();
                }
                break; // if insertion, you're done after one fix
            } else if (n != root) {
                if (n == n.parent.left) {
                    path = "L" + path;
                } else {
                    path = "R" + path;
                }
                n = n.parent;
            } else { // n was the root
                // and so we're done
                break;
            }
        }
    }

    private int height(Node<E> n) {
        if (n == null) {
            return -1;
        } else if (n.left == null && n.right == null) {
            return 0;
        } else {
            return 1 + Math.max(height(n.left), height(n.right));
        }
    }

    private boolean isAVL(Node<E> n) {
        return Math.abs(height(n.left) - height(n.right)) <= 1;
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

    /**
     * Perform an LR rotation around n.
     * @param n
     */
    private void rotateLR(Node<E> n) {
        //! in here I use the fast roration, normally I should rotate left first then perfomr a right rotation
        Node<E> A, C, B, B1, A2, C1, C2, pA;
        pA = n.parent;
        A = n;
        B = A.left;
        A2 = A.right;
        C = B.right;
        B1 = B.left;
        C1 = C.left;
        C2 = C.right;

        if (A == root){
            root = C;
        }

        else if (pA.left == A){
            pA.left = C;
        }

        else if (pA.right == A){
            pA.right = C;
        }

        C.parent = pA;

        //Begin fast rotation
        C.left = B;
        C.right = A;
        B.parent = C;
        A.parent = C;

        B.right = C1;
        A.left = C2;

        if (C1 != null) C1.parent = B;
        if (C2 != null) C2.parent = A;


    }

    /**
     * Perform an RL rotation around n.
     * @param n
     */
    private void rotateRL(Node<E> n) {
        Node<E> pA ,A, A1, B, C, C1, C2, B2;

        A = n;
        A1 = A.left;
        B = A.right;
        B2 = B.right;
        C= B.left;
        C1 = C.left;
        C2 = C.right;
        pA = n.parent;

        // //rotate C and B to turn into RR case
        // A.right = C;
        // C.parent = A;

        // C.left = B;
        // B.parent = C;

        // B.left = C2;

        // if ( C2 != null) C2.parent = B;


        // rotateLL(n);


        if (root == A){
            root = C;

        }

        else if (pA.left == A){
            pA.left = C;
        }

        else if (pA.right == A){
            pA.right = C;
        }

        C.parent = pA;

        //rotation

        C.left = A;
        C.right = B;
        A.parent = B.parent = C;

        A.right = C1;
        if (C1 != null) C1.parent = A;

        B.left = C2;
        if (C2 != null) C2.parent = B;



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

    public E findData(E e) {
        return find(e, root).data;
    }
}
