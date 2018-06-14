package repair.test;

import repair.checker.Bug;
import repair.heap.Heap;
import repair.heap.HeapNode;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.Variable;
import starlib.formula.heap.InductiveTerm;
import starlib.precondition.Initializer;

import java.util.LinkedList;
import java.util.List;

public class testCheckAndFix {
    public static void main(String[] args) {
        Heap heap = new Heap();
        List<String> l1 = new LinkedList<>();
        l1.add("N2");
        l1.add("N2");
        heap.addNode(new HeapNode("Node", "N1", null, l1));

        List<String> l2 = new LinkedList<>();
        l2.add("N2");
        l2.add("N1");
        heap.addNode(new HeapNode("Node", "N2", null, l2));

        String pred = "pred list(header,prev,cur,next) == prev=cur & next=header ||" +
                "next::Node<cur,next1> * list(header,prev,next,next1)";
        Initializer.initPredicate(pred);

        Variable var1 = new Variable("N1");
        Variable var2 = new Variable("N2");
        InductiveTerm it = new InductiveTerm("list", var1, var2, var1, var2);
        System.out.println("state before unfold:" + it.toString());
        Formula f = new Formula();
        f.heapFormula = new HeapFormula(it);
        State s = new State(heap, f);
        State newState = s.unfold();
        System.out.println("state after unfold:");
        for (Formula foru : newState.getState()) {
            System.out.println(foru.toString());
        }

        Bug error = newState.check();
        System.out.println("error trace:" + error.toString());
        System.out.println("before fix:\n" + newState.getHeap().toString());
        newState.fix(error);
        System.out.println("after fix:\n" + newState.getHeap().toString());

        System.out.println("error is:" + newState.check());
    }
}
