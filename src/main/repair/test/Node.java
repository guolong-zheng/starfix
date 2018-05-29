package repair.test;

import repair.heap.Collector;

public class Node {
    public Node prev;
    public Node next;

    public void setPrev(Node prev){
        this.prev = prev;
    }

    public Node getPrev(){
        return this.prev;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return this.next;
    }

    public static void main(String[] args){
        Node N0 = new Node();
        Node N1 = new Node();
        Node N2 = new Node();
        Node N3 = new Node();

        N0.prev = N3;
        N0.next = N1;

        N1.prev = N0;
        N1.next = N2;

        N2.prev = N1;
        N2.next = N1;

        N3.prev = N1;
        N3.next = N0;

        Collector.retrieveHeap(N0);
    }
}
