package repair.checker;

import repair.concreteFormula.ConcreteFormula;
import repair.heap.Heap;
import repair.heap.HeapNode;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
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

    public static boolean check(Heap heap) {
        for (Formula f : heap.getState()) {
            HeapFormula hp = f.getHeapFormula();
            PureFormula pf = f.getPureFormula();
            if (hp.toString().contains("emp")) {

            } else {
                for (HeapTerm it : hp.getHeapTerms()) {
                    if (it instanceof PointToTerm) {
                        for (Variable var : it.getVars()) {
                            HeapNode hn = heap.getNode(var.getName());
                        }
                    }
                }
            }
        }
        return false;
    }
}
