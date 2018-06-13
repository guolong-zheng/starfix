package repair.heap;

import repair.checker.Bug;
import repair.checker.EvaluateVisitor;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

import java.util.*;

/*
    State represents the state of a sub-heap. State is explored in a DFS way.
    For example, state s = x=null \/ x->(l,r)*tree(l)*tree(r)
    thus: s.inductiveTerms = [tree(l), tree(r)]
    First unfolding tree(l) gives a new state s' = l=null \/ l->(l',r')*tree(l')*tree(r'),
    then we can check the SAT of s', if the base case l=null is SAT, then roll back to s and
    continue check for tree(r) of s; if the base case is UNSAT, then check if l->(l',r')
    satisfies the concrete heap, if not, report a bug and try to repair; if yes, continue to unfold.
    s.index = 0 means all inductive terms have been checked and s is SAT roll back to its parents.
    s.parent = null means finishing checking.
 */
public class State {
    Heap heap;
    State parent;
    Formula[] state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms; //all possible unfolds
    int index;  //which to unfold; when 0 means have unfolded all inductive terms
    Stack<Variable> visited; //TODO:store variable or heapNode??
    boolean checked;

    public State(Heap heap, Formula... fs) {
        this.heap = heap;
        this.parent = null;
        this.state = fs;
        inductiveTerms = Utility.getInductiveTerms(fs);
        index = inductiveTerms.length;
        visited = new Stack<>();
        checked = false;
    }

    public State(State st, Formula[] fs) {
        this.parent = st;
        this.state = fs;
        Set<InductiveTerm> terms = new HashSet<>();
        for (Formula f : fs) {
            HeapTerm[] hts = f.getHeapFormula().getHeapTerms();
            for (HeapTerm ht : hts) {
                if (ht instanceof InductiveTerm)
                    terms.add((InductiveTerm) ht);
            }
        }
        inductiveTerms = terms.toArray(new InductiveTerm[terms.size()]);
        index = inductiveTerms.length;
        checked = false;
    }

    public boolean isChecked() {
        return checked;
    }
    public Heap getHeap() {
        return heap;
    }

    public State getParent() {
        return parent;
    }

    public Formula[] getState() {
        return state;
    }

    public State unfold() {
        if (index > 0) {
            Formula[] fs = unfoldInductiveTerm(inductiveTerms[index - 1]);
            index--;
            return new State(this, fs);
        }
        return null;
    }

    public boolean hasNext() {
        return index > 0;
    }

    public Formula[] unfoldInductiveTerm(InductiveTerm it) {
        InductivePred pred = InductivePredMap.find(it.getPredName());
        Formula[] formulas = pred.getFormulas();
        Variable[] params = pred.getParams();

        int length = formulas.length;
        Formula[] newFormulas = new Formula[length];
        Map<String, String> existVarSubMap = new HashMap<String, String>();

        for (int i = 0; i < length; i++) {
            newFormulas[i] = formulas[i].substitute(params, it.getVars(), existVarSubMap, heap);
        }
        it.setUnfoldedFormulas(newFormulas);

        return newFormulas;
    }

    public Bug check() {
        Bug error = new Bug();
        boolean res = true;
        for (Formula f : state) {
            HeapFormula hp = f.getHeapFormula();
            PureFormula pf = f.getPureFormula();
            if (hp.toString().contains("emp")) {
                for (PureTerm pt : pf.getPureTerms()) {
                    ComparisonTerm ct = (ComparisonTerm) pt;
                    EvaluateVisitor cv = new EvaluateVisitor();
                    res = res && cv.visit(ct);
                }
                if (res) {
                    return null;
                }
            } else {
                for (HeapTerm ht : hp.getHeapTerms()) {
                    if (ht instanceof PointToTerm) {
                        HeapNode root = heap.getNode(ht.getVars()[0].getName());
                        PointToTerm heapNode = root.toPointToTerm();
                        int index = ((PointToTerm) ht).equals(heapNode);
                        Variable toFix = ht.getVars()[index];
                        if (visited.contains(ht.getRoot())) {
                            return new Bug(index, (PointToTerm) ht, toFix, true);
                        } else {
                            //TODO: put this part to fix(Bug error) function
                            if (toFix instanceof ExistVariable) {
                                ((ExistVariable) toFix).next();
                            } else {
                                toFix.setName(heapNode.getVars()[index].getName());
                            }
                        }
                    }
                }
            }
        }
        this.checked = true;
        return null;
    }

    //TODO: also fix corresponding heapNode and generate HeapFix
    public void fix(Bug error) {
        PointToTerm pointToTerm = error.getPointToTerm();
        Variable root = pointToTerm.getRoot();
        HeapNode hn = heap.getNode(root.getName());
        for (Variable var : pointToTerm.getVars()) {
            if (var instanceof ExistVariable) {
                ((ExistVariable) var).next();
                break;
            }
        }
    }
}
