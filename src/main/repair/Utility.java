package repair;

import repair.checker.Bug;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;

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

    public static Set<String> getVariable(Formula[] fs) {
        Set<String> vars = new HashSet<>();
        PointToTerm[] pts = getPointToTerms(fs);
        for (PointToTerm pt : pts) {
            Variable var = pt.getRoot();
            vars.add(var.getName());
        }
        return vars;
    }

    public static PointToTerm[] getPointToTerms(Formula[] fs) {
        Set<PointToTerm> terms = new HashSet<>();
        for (Formula f : fs) {
            HeapTerm[] hts = f.getHeapFormula().getHeapTerms();
            for (HeapTerm ht : hts) {
                if (ht instanceof PointToTerm)
                    terms.add((PointToTerm) ht);
            }
        }
        return terms.toArray(new PointToTerm[terms.size()]);
    }

    public static boolean checkPureFormula(PureFormula pureFormula) {
        boolean res = true;
        for (PureTerm pt : pureFormula.getPureTerms()) {
            res = res && pt.evaluate();
            if (res == false)
                return res;
        }
        return res;
    }

}
