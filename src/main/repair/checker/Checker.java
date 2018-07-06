package repair.checker;

import repair.fix.Fix;
import repair.heap.Collector;
import repair.heap.Heap;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.PointToTerm;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

import java.util.*;


public class Checker {
    public static Stack<State> track = new Stack<>();
    public static Set<Fix> fixSet = new HashSet<>();
    public static Map<String, State> var2state = new HashMap<>();
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

        State initState = new State(heap, PreconditionMap.getFormulas()[0]);
        track.push(initState);
    }

    public static void repair(Object var, String dataNode, String pred, String state, String name) {
        Heap heap = Collector.retrieveHeap(var);
        System.out.println("original heap: " + heap.toString());
        init(dataNode, pred, state, heap, name);
        search();
        System.out.println("fixed heap:" + heap.toString());
    }

    public static void search() {
        while (!track.isEmpty()) {
            State state = track.peek();
            Bug error = state.check();

            if (error.status == Status.STOP) {
                System.out.println("abandoned state: " + state.getState().toString());
                track.pop();
            } else if (error.status == Status.PASS)
                forward();
            else
                fix(error);
        }
    }

    public static void forward() {
        State state = track.pop();
        if (state.isFinal()) {
            Fix newFix = new Fix(state.getHeap());
            fixSet.add(newFix);
        } else {
            state.unfold();
        }
    }

    public static void fix(Bug error) {
        State state;
        if (error.isBackward()) {
            state = backward(error.getVar().toString());
            state.backfix(error);
        } else {
            state = track.pop();
            state.stayfix(error);
        }
        track.push(state);
    }

    public static State backward(String name) {
        State state = track.pop();
        while (state.getParent() != null) {
            PointToTerm[] pts = state.getPointToTerms();
            for (PointToTerm pt : pts) {
                if (pt.getRoot().getName().equals(name)) {
                    return state.getParent();
                }
            }
            state = state.getParent();
        }
        return state;
    }
}
