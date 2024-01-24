/*
 * Copyright 2023 Marc Liberatore.
 */
package trees;

public class BinarySearchTree<E extends Comparable<E>> {
    Node<E> root;
    int size;

    public int size() {
        return size;
    }

    public void add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size = 1;
            return;
        }
        add(e, root);
    }

    private void add(E e, Node<E> node) {
        if (e.equals(node.data)) {
            node.data = e;
        }
        if (e.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                size++;
                return;
            } else {
                add(e, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                size++;
                return;
            } else {
                add(e, node.right);
            }
        }
    }

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

    private void splice(Node<E> n) {
        if (n.left != null && n.right != null) {
            throw new IllegalArgumentException();
        }

        Node<E> subNode, newParent;
        if (n.left != null) { // if the left subnode is not not null, that's the one we're going
                              // to "slide up" or splice into place
            subNode = n.left;
        } else { // otherwise it's the right subnode we're going to slide up
            subNode = n.right;
        }

        // if both were null, then subNode is now also null

        if (n == root) { // special case, n is the root
            root = subNode;
            newParent = null;
        } else {
            newParent = n.parent;
            // now do the slide up
            if (newParent.left == n) { // if the node we're deleting is the left
                                       // child of its parent
                newParent.left = subNode;
            } else { // otherwise it's the right child
                newParent.right = subNode;
            }
        }

        // finally, we fix the parent pointer of subNode
        if (subNode != null) {
            subNode.parent = newParent;
        }
    }

    public void remove(E e) {
        Node<E> n = find(e);

        if (n == null) {
            return;
        }

        if (n.left == null || n.right == null) { // easy case, 0 or 1 children
            splice(n);
        } else { // two children case
            Node<E> successor = n.right; // go to a bigger than n node
            while (successor.left != null) { // is there a smaller node (but still bigger than n)?
                successor = successor.left; // go there repeatedly!
            }

            // this node, successor, guaranteed to have 0 or 1 children

            // overwrite the value of the delete node with the successor's value
            n.data = successor.data; // note: preserves the BST property

            // then remove the successor from the tree
            splice(successor);
        }
        size--;
    }

}
