package testgene.testsuites;

import java.util.Set;

import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;

public class ConTestGenVisitor extends TestGenVisitor {

    public ConTestGenVisitor(TestGenVisitor that) {
        super(that);
    }

    @Override
    public void visit(PointToTerm term) {
        Variable var = term.getRoot();
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

        if (comp == Comparator.EQ) {
            if (isVar1 && isVar2) {
                // former EqTerm
                Variable var1 = (Variable) exp1;
                Variable var2 = (Variable) exp2;
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
                return;
            }
            if (isVar1 && exp2 instanceof NullExpression) {
                // former EqNullTerm
                Variable var1 = (Variable) exp1;
                if (!initVars.contains(var1)) {
                    initVars.add(var1);
                    test.append(makeDeclAndInit(var1, "null"));
                }
                return;
            }
        }

        // former ComparisonTerm
        Set<Variable> vars1 = exp1.getVars();
        Set<Variable> vars2 = exp2.getVars();
        boolean containsAll1 = initVars.containsAll(vars1);
        boolean containsAll2 = initVars.containsAll(vars2);

        if (comp == Comparator.EQ && isVar1 &&
                !containsAll1 && (vars2.isEmpty() || containsAll2)) {
            Variable var = (Variable) exp1;
            initVars.add(var);

            String type = var.getType();
            String value = exp2.toString();

            if (type.equals("boolean")) {
                value = value.equals("0") ? "false" : "true";
            }

            test.append(makeDeclAndInit(var, value));
        }

        if (comp == Comparator.EQ && isVar2 &&
                !containsAll2 && (vars1.isEmpty() || containsAll1)) {
            Variable var = (Variable) exp2;
            initVars.add(var);

            String type = var.getType();
            String value = exp1.toString();

            if (type.equals("boolean")) {
                value = value.equals("0") ? "false" : "true";
            }

            test.append(makeDeclAndInit(var, value));
        }
    }


}
