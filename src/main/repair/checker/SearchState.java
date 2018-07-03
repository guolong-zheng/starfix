package repair.checker;

import repair.fix.Fix;
import repair.heap.State;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SearchState {
    public static Stack<State> track = new Stack<>();
    public static Set<Fix> fixSet = new HashSet<>();

    Map<String, State> var2state; //map variable to the state(index) where it get unfolded

    public void search() {
        while (!track.isEmpty()) {
            State state = track.peek();
            Bug error = state.check();
            if (error == null) {
                forward();
            } else {
                fix(error);
            }
        }
    }

    public void forward() {
        State state = track.pop();
        if (state.isFinal()) {
            Fix newFix = new Fix(state.getHeap());
            fixSet.add(newFix);
        } else {
            state.unfold();
        }
    }

    public void fix(Bug error) {
        if (error.isBackward()) {
            backward(error.getVar().toString());
        }
        State state = track.pop();
        state.fix(error);
    }

    public void backward(String name) {
        State aimState = var2state.get(name);
        while (!track.isEmpty()) {
            if (track.peek() != aimState) {
                track.pop();
            } else
                break;
        }
        track.pop();
    }
}
