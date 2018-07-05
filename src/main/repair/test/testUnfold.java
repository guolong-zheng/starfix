package repair.test;

import repair.checker.Checker;
import repair.heap.ExistVariable;
import repair.heap.Heap;
import repair.heap.HeapNode;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

import java.util.LinkedList;
import java.util.List;

public class testUnfold {
    public static void main(String[] args) {
        Heap heap = new Heap();
        List<String> l1 = new LinkedList<String>();
        l1.add("N2");
        l1.add("N3");
        heap.addNode(new HeapNode("Node", "N0", null, l1));
        Initializer.initPredicate("pred cdll(header) == header=null || " +
                "header::Node<prev,next> * list(header,prev,header,next)");

        Variable var = new Variable("N0");
        InductiveTerm it = new InductiveTerm("cdll", var);
        Formula f = new Formula();
        f.heapFormula = new HeapFormula(it);
        State s = new State(heap, f);
        s.unfold();
        for (State newState : Checker.track) {

            Formula t = newState.getState();
            System.out.println(t.toString());

            /*
            for (Formula t : newState.getState()) {
                HeapFormula hf = t.getHeapFormula();
                for (HeapTerm ht : hf.getHeapTerms()) {
                    for (Variable v : ht.getVars()) {
                        if (v.getName().equals("N2") || v.getName().equals("N3")) {
                            assert (v instanceof ExistVariable);
                        }
                    }
                }
            }
            */
        }
    }
}
