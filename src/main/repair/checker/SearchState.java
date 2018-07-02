package repair.checker;

import repair.heap.Heap;
import repair.heap.State;

import java.util.Map;
import java.util.Stack;

public class SearchState {
    Stack<State> states;
    Heap heap;

    Map<String, State> var2state; //map variable to the state(index) where it get unfolded

    public void rollback(String name) {
        State aimState = var2state.get(name);
        while (!states.isEmpty()) {
            if (states.peek() != aimState) {
                states.pop();
                heap.revert();
            } else
                break;
        }

        states.pop();
    }
}
