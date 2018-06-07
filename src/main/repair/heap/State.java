package repair.heap;

import starlib.formula.Formula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
    Heap heap;
    State parent;
    Formula[] state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms;
    int index;

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

    public State unfold() {
        if (index > 0) {
            Formula[] fs = unfoldInductiveTerm(inductiveTerms[index]);
            index--;
            return new State(this, fs);
        }
        return null;
    }

    public Formula[] unfoldInductiveTerm(InductiveTerm it) {
        InductivePred pred = InductivePredMap.find(it.getPredName());
        Formula[] formulas = pred.getFormulas();
        Variable[] params = pred.getParams();

        int length = formulas.length;
        Formula[] newFormulas = new Formula[length];
        //TODO: collecting this existVarSubMap to use for repairing
        Map<String, String> existVarSubMap = new HashMap<String, String>();

        for (int i = 0; i < length; i++) {
            newFormulas[i] = formulas[i].substitute(params, it.getVars(), existVarSubMap);
        }
        it.setUnfoldedFormulas(newFormulas);

        return newFormulas;
    }
}
