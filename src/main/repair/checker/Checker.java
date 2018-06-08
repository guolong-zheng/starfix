package repair.checker;

import repair.concreteFormula.ConcreteFormula;
import repair.heap.Heap;
import repair.heap.HeapNode;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.expression.*;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.precondition.Initializer;

import java.util.*;

public class Checker {
    public static Map<String, List<Variable>> visitedVars = new HashMap<>();    //type to v
    public static Stack<Heap> trackes = new Stack<>();
    public static Queue<Formula> toVisit = new LinkedList<>();

    public static void check(String dataNode, String pred, Heap state) {
        Initializer.initDataNode(dataNode);
        Initializer.initPredicate(pred);

        HeapNode root = state.getRoot();
        Variable var = new Variable(root.getName(), root.getType());
        HeapFormula hp = new HeapFormula(new InductiveTerm("cdll", var));
        ConcreteFormula ps = new ConcreteFormula(hp, new PureFormula());

        ps.unfold(new InductiveTerm("cdll", var), 0);

    }

    public static void add(Formula f) {
        toVisit.add(f);
    }

    public static Formula next() {
        return toVisit.remove();
    }

    public static boolean check(Heap heap, State state) {
        boolean res = true;
        for (Formula f : state.getState()) {
            HeapFormula hp = f.getHeapFormula();
            PureFormula pf = f.getPureFormula();
            if (hp.toString().contains("emp")) {
                for (PureTerm pt : pf.getPureTerms()) {
                    ComparisonTerm ct = (ComparisonTerm) pt;
                    EvaluateVisitor cv = new EvaluateVisitor();
                    res = res && cv.visit(ct);
                }
            } else {
                for (HeapTerm it : hp.getHeapTerms()) {
                    if (it instanceof PointToTerm) {
                        HeapNode root = heap.getNode(it.getVars()[0].getName());
                        for (Variable var : it.getVars()) {
                            HeapNode hn = heap.getNode(var.getName());
                            if (hn.getName() != var.toString()) {
                                res = false;
                                return res;
                            }
                        }
                    }
                }
            }
        }
        //TODO: checking the heap in a recursive way??
        return res;
    }
}
