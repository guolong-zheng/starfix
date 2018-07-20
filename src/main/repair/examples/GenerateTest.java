package repair.examples;

import starlib.formula.Formula;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

public class GenerateTest {
    public static void main(String[] args) {
        int size = 3;
        String pred = "pred tree(x) == x=null || x::Tree<left, right> * tree(left) * tree(right)";
        String prec = "pre prec == tree(x) * tree(y)";
        String dn = "data Tree { Tree left; Tree right }";

        Initializer.initPredicate(pred);
        Initializer.initPrecondition(prec);
        Initializer.initDataNode(dn);

        Formula f = PreconditionMap.getFormulas()[0];

        while (f.depth < size - 1) {
            for (HeapTerm ht : f.getHeapFormula().getHeapTerms()) {
                if (ht instanceof InductiveTerm) {
                    InductiveTerm it = (InductiveTerm) ht;
                    Formula[] unfoldedFormulas = it.unfold();
                    for (int i = 0; i < unfoldedFormulas.length; i++) {
                        if (!isBaseCase(unfoldedFormulas[i])) {
                            f.unfold(it, i);
                        }
                    }
                }
            }
        }

        for (HeapTerm ht : f.getHeapFormula().getHeapTerms()) {
            if (ht instanceof InductiveTerm) {
                InductiveTerm it = (InductiveTerm) ht;
                Formula[] unfoldedFormulas = it.unfold();
                for (int i = 0; i < unfoldedFormulas.length; i++) {
                    if (isBaseCase(unfoldedFormulas[i])) {
                        f.unfold(it, i);
                    }
                }
            }
        }

        System.out.println(f.toS2SATString());
    }

    public static boolean isBaseCase(Formula f) {
        for (HeapTerm ht : f.getHeapFormula().getHeapTerms()) {
            if (ht instanceof InductiveTerm) {
                return false;
            }
        }
        return true;
    }
}
