package repair.examples;

import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;
import starlib.solver.Solver;
import testgene.ClassInfo;
import testgene.Config;
import testgene.FieldInfo;
import testgene.MethodInfo;
import testgene.testsuites.TestGenerator;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenerateTest {

    public static void main(String[] args) {
        int size = 3;
        String pred = "pred tree(x) == x=null || x::Tree<left, right> * tree(left) * tree(right)";
        String prec = "pre prec == tree(x)";
        String dn = "data Tree { Tree left; Tree right }";

        Initializer.initPredicate(pred);
        Initializer.initPrecondition(prec);
        Initializer.initDataNode(dn);
        getClassInfo();
        TestGenerator.setPredicates(pred, dn, prec);

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

        System.out.println(f.toString());
        System.out.println(Solver.checkSat(f));
        TestGenerator.addModel(Solver.getModel());
        System.out.println(Solver.getModel());
        TestGenerator.generateTests();

        // mutate(testFile, 5);
    }

    public static void generateMutatedTest(String pred, String dataNode, String formula) {

    }

    public static boolean isBaseCase(Formula f) {
        for (HeapTerm ht : f.getHeapFormula().getHeapTerms()) {
            if (ht instanceof InductiveTerm) {
                return false;
            }
        }
        return true;
    }

    public static void getClassInfo() {
        DataNode dn = DataNodeMap.getAll()[0];
        Variable[] fields = dn.getFields();
        FieldInfo[] fieldInfos = new FieldInfo[fields.length];
        for (int i = 0; i < fieldInfos.length; i++) {
            fieldInfos[i] = new FieldInfo(fields[i].getName(), fields[i].getType());
        }

        ClassInfo clsInfo = new ClassInfo(dn.getType(), fieldInfos);
        MethodInfo methodInfo = new MethodInfo(dn.getType());

        TestGenerator.setClassAndMethodInfo(clsInfo, methodInfo, new Config());
        return;
    }

}
