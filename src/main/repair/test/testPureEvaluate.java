package repair.test;

import repair.Utility;
import starlib.formula.PureFormula;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.LiteralExpression;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;

public class testPureEvaluate {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        Expression exp1 = new LiteralExpression("N0");
        Expression exp2 = new LiteralExpression("N1");
        PureTerm pt = new ComparisonTerm(Comparator.EQ, exp1, exp2);

        boolean res = pt.evaluate();
        System.out.println(res);
    }

    public static void test2() {
        Expression exp1 = new LiteralExpression("N1");
        Expression exp2 = new LiteralExpression("N1");
        PureTerm pt = new ComparisonTerm(Comparator.EQ, exp1, exp2);

        boolean res = pt.evaluate();
        System.out.println(res);
    }

    public static void test3() {
        Expression exp1 = new LiteralExpression("N1");
        Expression exp2 = new LiteralExpression("N1");
        PureTerm pt1 = new ComparisonTerm(Comparator.EQ, exp1, exp2);

        Expression exp3 = new LiteralExpression("N2");
        Expression exp4 = new LiteralExpression("N3");
        PureTerm pt2 = new ComparisonTerm(Comparator.EQ, exp3, exp4);

        PureFormula pf = new PureFormula(pt1, pt2);
        boolean res = Utility.checkPureFormula(pf);
        System.out.println(res);
    }

    public static void test4() {
        Expression exp1 = new LiteralExpression("N1");
        Expression exp2 = new LiteralExpression("N1");
        PureTerm pt1 = new ComparisonTerm(Comparator.EQ, exp1, exp2);

        Expression exp3 = new LiteralExpression("N2");
        Expression exp4 = new LiteralExpression("N2");
        PureTerm pt2 = new ComparisonTerm(Comparator.EQ, exp3, exp4);

        PureFormula pf = new PureFormula(pt1, pt2);
        boolean res = Utility.checkPureFormula(pf);
        System.out.println(res);
    }
}
