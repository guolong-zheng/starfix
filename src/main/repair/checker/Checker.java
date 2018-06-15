package repair.checker;

import repair.heap.Collector;
import repair.heap.Heap;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

import java.util.Stack;


public class Checker {
    static Stack<State> track = new Stack<>();
    public static int count = 0;

    public static void init(String dataNode, String pred, String state, Heap heap, String name) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);
        Initializer.initPrecondition(state);

        //TODO: get variable name using debugger??
        for (Formula f : PreconditionMap.getFormulas()) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm heapTerm : hf.getHeapTerms()) {
                Variable[] vars = heapTerm.getVars();
                for (Variable var : vars) {
                    if (var.getName().equals(name)) {
                        System.out.println("from " + name + " to " + heap.getRoot().getName());
                        var.setName(heap.getRoot().getName());
                    }
                }
            }
        }

        State initState = new State(heap, PreconditionMap.getFormulas());
        track.push(initState);
    }

    public static void repair(Object var, String dataNode, String pred, String state, String name) {
        Heap heap = Collector.retrieveHeap(var, name);
        System.out.println("original heap: " + heap.toString());
        init(dataNode, pred, state, heap, name);
        search();
    }

    public static void search() {

        while (!track.isEmpty() && count < 10) {
            State state = track.peek();
            System.out.println(count++ + " : " + state.toString());
            if (state.isChecked()) {
                if (state.hasNext()) {
                    State newState = state.unfold();
                    track.push(newState);
                } else {
                    track.pop();
                }
            } else {
                Bug error = state.check();
                if (error != null) {
                    System.out.println("error: " + error.toString());
                    if (error.isBackward()) {
                        track.pop();
                        state = track.peek();
                    }
                    state.fix(error);
                }
            }
        }
    }
}
