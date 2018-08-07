package testgene.testsuites;

import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
import starlib.formula.pure.ComparisonTerm;

/*
 * This class initializes variables with their default constructions, i.e. no arguments.
 */
public class NoConTestGenVisitor extends TestGenVisitor {

    public NoConTestGenVisitor(TestGenVisitor that) {
        super(that);
    }

    private void genConcreteVars(Variable var) {
        if (!initVars.contains(var)) {
            initVars.add(var);
            test.append(makeDeclAndInitWithConstructor(var));
        }
    }

    @Override
    public void visit(ComparisonTerm term) {
        Expression exp1 = term.getExp1();
        Expression exp2 = term.getExp2();
        Comparator comp = term.getComparator();
        boolean isVar1 = exp1 instanceof Variable;
        boolean isVar2 = exp2 instanceof Variable;
        boolean isNull2 = exp2 instanceof NullExpression;

        if (comp == Comparator.NE) {
            if (isVar1 && isVar2) {
                // NEqTerm: what happens if x = 5?
                genConcreteVars((Variable) exp1);
                genConcreteVars((Variable) exp2);
            }
            if (isVar1 && isNull2) {
                // NEqNullTerm
                genConcreteVars((Variable) exp1);
            }
            return;
        }

        //EqTerm
        if (comp == Comparator.EQ && isVar1 && isVar2) {
            Variable var1 = (Variable) exp1;
            Variable var2 = (Variable) exp2;

            // TODO: re-factor
            // Start copying from ConTestGenVisitor
            boolean constains1 = initVars.contains(var1);
            boolean constains2 = initVars.contains(var2);

            if (!constains1 && constains2) {
                initVars.add(var1);
                String name2 = standardizeName(var2);
                test.append(makeDeclAndInit(var1, name2));
            }

            if (constains1 && !constains2) {
                initVars.add(var2);
                String name1 = standardizeName(var1);
                test.append(makeDeclAndInit(var2, name1));
            }
            // End copying from ConTestGenVisitor


            if (!constains1 && !constains2) {
                initVars.add(var1);
                initVars.add(var2);
                test.append(makeDeclAndInitWithConstructor(var1));
                test.append(makeDeclAndInit(var2, standardizeName(var1)));
            }
        }
    }
}
