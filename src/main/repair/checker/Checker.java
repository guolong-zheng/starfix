package repair.checker;

import repair.heap.Collector;
import repair.heap.Heap;
import repair.heap.State;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

import java.util.Stack;


public class Checker {
    static Stack<State> track = new Stack<>();

    public static void init(String dataNode, String pred, String state, Heap heap) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);
        Initializer.initPrecondition(state);

        State initState = new State(heap, PreconditionMap.getFormulas());
        track.push(initState);
    }

    public static void repair(Object var, String dataNode, String pred, String state) {
        Heap heap = Collector.retrieveHeap(var);
        init(dataNode, pred, state, heap);
        search();
    }

    public static void search() {
        while (!track.isEmpty()) {
            State state = track.peek();
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
