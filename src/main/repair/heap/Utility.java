package repair.heap;

import starlib.formula.Formula;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;

import java.util.HashSet;
import java.util.Set;

public class Utility {
    public static InductiveTerm[] getInductiveTerms(Formula[] fs) {
        Set<InductiveTerm> terms = new HashSet<>();
        for (Formula f : fs) {
            HeapTerm[] hts = f.getHeapFormula().getHeapTerms();
            for (HeapTerm ht : hts) {
                if (ht instanceof InductiveTerm)
                    terms.add((InductiveTerm) ht);
            }
        }
        return terms.toArray(new InductiveTerm[terms.size()]);
    }
}
