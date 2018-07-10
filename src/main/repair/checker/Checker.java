package repair.checker;

import repair.fix.Fix;
import repair.heap.Collector;
import repair.heap.ExistVariable;
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
    public static int count = 0;

    public static void init(String dataNode, String pred, String state, Heap heap, String name) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);
        Initializer.initPrecondition(state);
        //Fix.origHeap = heap;

        for (Formula f : PreconditionMap.getFormulas()) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm heapTerm : hf.getHeapTerms()) {
                Variable[] vars = heapTerm.getVars();
                for (Variable var : vars) {
                    if (var.getName().equals(name)) {
                        var.setValue(heap.getRoot().getName());
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

        for (Fix fix : fixSet) {
            System.out.println(fix.toString());
        }
    }

    public static void search() {
        while (!track.isEmpty() && count < 100) {
            System.out.println("------Iteration " + count++ + "------");
            State state = track.peek();
            System.out.println("checking " + state.getState().toString());
            Bug error = state.check();

            System.out.println(error.status);

            if (error.status == Status.STOP) {
                System.out.println("abandoned state: " + state.getState().toString());
                track.pop();
            } else if (error.status == Status.PASS) {
                forward();
            }
            else
                fix(error);

            System.out.println("-----------------------\n");
        }
    }

    public static void forward() {
        State state = track.pop();
        if (state.isFinal()) {
            System.out.println("fixed state " + state.getState().toString());
            Fix newFix = new Fix(state.getHeap());
            fixSet.add(newFix);
        } else {
            state.unfold();
        }
    }

    public static void fix(Bug error) {
        if (error.isBackward()) {
            Set<State> states = backward(error.getVar().getValue());
            for (State state : states) {
                System.out.println("backward to " + state.getState().toString());
                if (state.backfix(error))
                    track.push(state);
            }
        } else {
            State state = track.pop();
            state.stayfix(error);
            track.push(state);
        }
    }

    public static Set<State> backward(String toFix) {
        Set<State> states = new HashSet<>();
        State state = track.pop();
        while (state.getParent() != null) {
            PointToTerm[] pts = state.getPointToTerms();
            for (PointToTerm pt : pts) {
                for (Variable var : pt.getVars()) {
                    if (var.getValue().equals(toFix) && var instanceof ExistVariable)
                        states.add(state);
                }
            }
            state = state.getParent();
        }
        return states;
    }
}
