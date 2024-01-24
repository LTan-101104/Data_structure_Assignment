/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class Node<E> {
    public E data;
    public Node<E> next;

    public Node() {        
    }
    
    public Node(E e) {
        data = e;
    }

    public Node(E e, Node<E> n) {
        data = e;
        next = n;
    }

    public E getData(){

        return this.data;
    }

    public void setData(E newDataE){
        this.data = newDataE;
    }
}
