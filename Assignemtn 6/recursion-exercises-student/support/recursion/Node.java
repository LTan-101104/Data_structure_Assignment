package recursion;

public class Node<E> {
    E data;
    Node<E> next;

    Node(E d) {
        data = d;
    }

    Node(E d, Node<E> n) {
        data = d;
        next = n;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + next + "]";
    }
}
