package repair.heap;

import starlib.formula.Formula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

import java.util.*;

/*
    State represents the state of a sub-heap. State is explored in a DFS way.
    For example, state s = x=null \/ x->(l,r)*tree(l)*tree(r)
    thus: s.inductiveTerms = [tree(l), tree(r)]
    First unfolding tree(l) gives a new state s' = l=null \/ l->(l',r')*tree(l')*tree(r'),
    then we can check the SAT of s', if the base case l=null is SAT, then roll back to s and
    continue check for tree(r) of s; if the base case is UNSAT, then check if l->(l',r')
    satisfies the concrete heap, if not, report a bug and try to repair; if yes, continue to unfold.
    s.index = 0 means all inductive terms have been checked and s is SAT roll back to its parents.
    s.parent = null means finishing checking.
 */
public class State {
    Heap heap;
    State parent;
    Formula[] state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms; //all possible unfolds
    int index;  //which to unfold; when 0 means have unfolded all inductive terms
    Stack<HeapNode> visited;

    public State(State st, Formula[] fs) {
        this.parent = st;
        Set<InductiveTerm> terms = new HashSet<>();
        for (Formula f : fs) {
            HeapTerm[] hts = f.getHeapFormula().getHeapTerms();
            for (HeapTerm ht : hts) {
                if (ht instanceof InductiveTerm)
                    terms.add((InductiveTerm) ht);
            }
        }
        inductiveTerms = (InductiveTerm[]) terms.toArray();
        index = inductiveTerms.length;
    }

    public Formula[] getState() {
        return state;
    }

    public State unfold() {
        if (index > 0) {
            Formula[] fs = unfoldInductiveTerm(inductiveTerms[index]);
            index--;
            return new State(this, fs);
        }
        return null;
    }

    public boolean hasNext() {
        return index > 0;
    }

    public Formula[] unfoldInductiveTerm(InductiveTerm it) {
        InductivePred pred = InductivePredMap.find(it.getPredName());
        Formula[] formulas = pred.getFormulas();
        Variable[] params = pred.getParams();

        int length = formulas.length;
        Formula[] newFormulas = new Formula[length];
        //TODO: collecting this existVarSubMap to use for repairing
        Map<String, String> existVarSubMap = new HashMap<String, String>();
        Set<Variable> boundedVars = new HashSet<>();

        for (int i = 0; i < length; i++) {
            newFormulas[i] = formulas[i].substitute(params, it.getVars(), existVarSubMap);
        }
        it.setUnfoldedFormulas(newFormulas);

        return newFormulas;
    }
}
