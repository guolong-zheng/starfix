package repair.checker;

import starlib.StarVisitor;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.pure.ComparisonTerm;

public class EvaluateVisitor {
    public boolean visit(ComparisonTerm term) {
        Expression exp1 = term.getExp1();
        Expression exp2 = term.getExp2();
        Comparator cp = term.getComparator();
        return cp.evaluate(exp1, exp2);
    }
}
