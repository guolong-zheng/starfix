package repair.heap;

import repair.checker.SearchState;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.PureTerm;

import java.util.Set;
import java.util.Stack;

public class NewState {
    Heap heap;
    Formula state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms; //all possible unfolds
    Set<String>[] visitedVars;
    int index;  //which to unfold; when 0 means have unfolded all inductive terms
    Stack<String> visited; //store visited variable names
    boolean checked;

    public NewState(Formula f) {
        this.state = f;
    }

    public void unfold() {
        HeapFormula heapFormula = state.getHeapFormula();
        PureFormula pureFormula = state.getPureFormula();
        InductiveTerm it = inductiveTerms[index];
        Formula[] fs = it.unfold();
        for (int i = 0; i < fs.length; i++) {
            Formula f = fs[i];

            int heapSize = f.heapFormula.getHeapTerms().length + heapFormula.getHeapTerms().length - 1;
            int pureSize = f.pureFormula.getPureTerms().length + pureFormula.getPureTerms().length;

            HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
            PureTerm[] newPureTerms = new PureTerm[pureSize];

            int curr = 0;
            for (int j = 0; j < heapFormula.getHeapTerms().length; j++) {
                if (!heapFormula.getHeapTerms()[j].equals(it)) {
                    newHeapTerms[curr] = heapFormula.getHeapTerms()[j];
                    curr++;
                }
            }

            System.arraycopy(f.heapFormula.getHeapTerms(), 0,
                    newHeapTerms, heapFormula.getHeapTerms().length - 1, f.heapFormula.getHeapTerms().length);

            System.arraycopy(pureFormula.getPureTerms(), 0,
                    newPureTerms, 0, pureFormula.getPureTerms().length);
            System.arraycopy(f.pureFormula.getPureTerms(), 0,
                    newPureTerms, pureFormula.getPureTerms().length, f.pureFormula.getPureTerms().length);

            Formula newFormula = new Formula(new HeapFormula(newHeapTerms), new PureFormula(newPureTerms));

            SearchState.newStates.push(new NewState(newFormula));
        }
    }
}
