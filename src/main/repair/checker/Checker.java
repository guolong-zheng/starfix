package repair.checker;

import repair.heap.ExistVariable;
import repair.heap.Heap;
import repair.heap.HeapNode;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.precondition.Initializer;

import java.util.*;

public class Checker {

    public static void repair(String dataNode, String pred, State state) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);

    }

    public static boolean check(State state) {
        boolean res = true;
        Heap heap = state.getHeap();

        for (Formula f : state.getState()) {
            HeapFormula hp = f.getHeapFormula();
            PureFormula pf = f.getPureFormula();
            if (hp.toString().contains("emp")) {
                for (PureTerm pt : pf.getPureTerms()) {
                    ComparisonTerm ct = (ComparisonTerm) pt;
                    EvaluateVisitor cv = new EvaluateVisitor();
                    res = res && cv.visit(ct);
                }
                if (res) {
                    return true;
                }
            } else {
                for (HeapTerm it : hp.getHeapTerms()) {
                    if (it instanceof PointToTerm) {
                        HeapNode root = heap.getNode(it.getVars()[0].getName());
                        PointToTerm heapNode = root.toPointToTerm();
                        int index = ((PointToTerm) it).equals(heapNode);
                        Variable toFix = it.getVars()[index];
                        if (toFix instanceof ExistVariable) {
                            ((ExistVariable) toFix).next();
                            check(state);
                        } else {
                            toFix.setName(heapNode.getVars()[index].getName());
                            check(state);
                        }
                    }
                }
            }
        }
        return res;
    }
}
