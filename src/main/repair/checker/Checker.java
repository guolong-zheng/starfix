package repair.checker;

import repair.heap.Heap;
import repair.heap.State;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;


public class Checker {

    public static void init(String dataNode, String pred, String state, Heap heap) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);
        Initializer.initPrecondition(state);

        State initState = new State(heap, PreconditionMap.getFormulas());
    }


    public static void main(String[] args) {
        String dataNode = "data Node { Node prev; Node next }";
        String pred1 = "pred cdll(header) == header=null || " +
                "header::Node<prev,next> * list(header,prev,header,next,size1);";
        String pred2 = "pred list(header,prev,cur,next,size) == prev=cur & next=header & size=0 ||" +
                "next::Node<cur,next1> * list(header,prev,next,next1,size1)";
        String state = "pre dll == cdll(N0) * list(N0)";
    }
}
