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
    public static Queue<State> track = new LinkedList<>();
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
        track.add(initState);
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
            State state = track.remove();
            System.out.println("checking " + state.getState().toString());
            Bug error = state.check();
            System.out.println("error status: " + error.status);

            if (error.status == Status.STOP) {
                System.out.println("abandoned state: " + state.getState().toString());
            } else if (error.status == Status.PASS) {
                forward(state);
            }
            else
                fix(state, error);

            System.out.println("-----------------------\n");
        }
    }

    public static void forward(State state) {
        if (state.isFinal()) {
            System.out.println("fixed state " + state.getState().toString());
            Fix newFix = new Fix(state.getHeap());
            fixSet.add(newFix);
        } else {
            state.unfold();
        }
    }

    public static void fix(State origState, Bug error) {
        if (error.status == Status.MISMATCH || error.status == Status.NULL_ROOT) {
            State st = mismatch_backward(origState, error);
            if (st != null) {
                System.out.println("backward to " + st.getState().toString());
                st.backfix(error);
                track.add(st);
            }
        } else if (error.status == Status.DUPLICATE) {
            State state_1 = mismatch_backward(origState, error);
            if (state_1 != null) {
                System.out.println("backward to " + state_1.getState().toString());
                state_1.backfix(error);
                track.add(state_1);

                State state_2 = dup_backward(state_1, error);
                if (state_2 != null) {
                    System.out.println("backward to " + state_2.getState().toString());
                    state_2.backfix(error);
                    track.add(state_2);
                }
            }
        } else if (error.status == Status.STAY) {
            origState.stayfix(error);
            track.add(origState);
        }
    }

    public static State mismatch_backward(State origState, Bug error) {
        State state = origState;
        String toFix = error.getVar().getName();
        while (state != null) {
            PointToTerm[] pts = state.getPointToTerms();
            for (PointToTerm pt : pts) {
                Variable[] vars = pt.getVars();
                for (int i = 1; i < vars.length; i++) {
                    if (vars[i].getName().equals(toFix))
                        return state;
                }
            }
            state = state.getParent();
        }

        return null;
    }

    public static State dup_backward(State origState, Bug error) {
        State state = origState;
        String toFix = error.getVar().getValue();
        while (state != null) {
            PointToTerm[] pts = state.getPointToTerms();
            for (PointToTerm pt : pts) {
                Variable[] vars = pt.getVars();
                for (int i = 1; i < vars.length; i++) {
                    if (vars[i].getValue().equals(toFix) && vars[i] instanceof ExistVariable)
                        return state;
                }
            }
            state = state.getParent();
        }

        return null;
    }

    public static Set<State> backward(String toFix) {
        Set<State> states = new HashSet<>();
        State state = track.remove();
        while (state.getParent() != null) {
            PointToTerm[] pts = state.getPointToTerms();
            for (PointToTerm pt : pts) {
                for (Variable var : pt.getVars()) {
                    if (!var.equals(pt.getRoot()) && var.getValue().equals(toFix) && var instanceof ExistVariable)
                        states.add(state);
                }
            }
            state = state.getParent();
        }
        return states;
    }
}
