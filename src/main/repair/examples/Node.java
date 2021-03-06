package repair.examples;

import repair.checker.Checker;

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

        String dataNode = "data Node { Node prev; Node next }";
        String pred1 = "pred cdll(header) == header=null || " +
                "header::Node<prev,next> * list(header,prev,header,next);";
        String pred2 = "pred list(header,prev,cur,next) == prev=cur & next=header ||" +
                "next::Node<cur,next1> * list(header,prev,next,next1)";
        String state = "pre data == cdll(N0)";

        Checker.repair(N0, dataNode, pred1 + pred2, state, "N0");
    }
}
