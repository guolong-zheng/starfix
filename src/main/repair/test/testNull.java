package repair.test;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.precondition.Initializer;

public class testNull {
    public static void main(String[] args) {
        Expression nullvar = NullExpression.getInstance();
        Variable a = new Variable("a");
        Variable c = new Variable("c");
        Variable b = new Variable("b");

        Initializer.initPredicate("pred tree(x) == x=null || x::Tree<nnull,right>*tree(nnull)*tree(right)");

        InductiveTerm it = new InductiveTerm("tree", a, b, c);
        Formula[] fs = it.unfold();

        for (Formula f : fs) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm ht : hf.getHeapTerms()) {
                System.out.println(ht.toString());
            }
        }
    }
}
