package repair.heap;

import repair.Utility;
import repair.checker.Bug;
import repair.checker.Checker;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
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
    static Heap heap;
    State parent;
    Formula[] state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms; //all possible unfolds
    Set<String>[] visitedVars;
    int index;  //which to unfold; when 0 means have unfolded all inductive terms
    Stack<String> visited; //store visited variable names
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
        inductiveTerms = Utility.getInductiveTerms(fs);
        visited = new Stack<>();
        visited.addAll(st.getVisited());
        index = inductiveTerms.length;
        checked = false;
    }

    public Stack<String> getVisited() {
        return visited;
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
        } else {  //after unfolded all inductive terms, check if there is any overlaps between visited variables

        }
        return null;
    }

    public State unfold(String name) {
        if (index > 0) {
            Formula[] fs = unfoldInductiveTerm(inductiveTerms[index - 1]);
            index--;
            return new State(this, fs);
        } else {

        }
        return null;
    }

    public Formula[] unfoldInductiveTerm(InductiveTerm it, String name) {
        System.out.println("unfolding: " + it.toString());
        InductivePred pred = InductivePredMap.find(it.getPredName());
        Formula[] formulas = pred.getFormulas();
        Variable[] params = pred.getParams();

        int length = formulas.length;
        Formula[] newFormulas = new Formula[length];
        Map<String, Variable> existVarSubMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            newFormulas[i] = formulas[i].substitute(params, it.getVars(), existVarSubMap, this);
        }
        it.setUnfoldedFormulas(newFormulas);

        return newFormulas;
    }

    public boolean hasNext() {
        return index > 0;
    }

    public Formula[] unfoldInductiveTerm(InductiveTerm it) {
        System.out.println("unfolding: " + it.toString());
        InductivePred pred = InductivePredMap.find(it.getPredName());
        Formula[] formulas = pred.getFormulas();
        Variable[] params = pred.getParams();

        int length = formulas.length;
        Formula[] newFormulas = new Formula[length];
        Map<String, Variable> existVarSubMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            newFormulas[i] = formulas[i].substitute(params, it.getVars(), existVarSubMap, this);
        }
        it.setUnfoldedFormulas(newFormulas);

        return newFormulas;
    }

    public Bug check() {
        for (Formula f : state) {
            HeapFormula hp = f.getHeapFormula();
            PureFormula pf = f.getPureFormula();
            if (hp.toString().contains("emp")) {
                if (Utility.checkPureFormula(pf)) {
                    this.checked = true;
                    return new Bug();
                }
            } else {
                this.checked = true;
                return checkHeapFormula(hp);
            }
        }
        this.checked = true;
        return null;
    }

    public Bug checkHeapFormula(HeapFormula heapFormula) {
        for (HeapTerm ht : heapFormula.getHeapTerms()) {
            if (ht instanceof PointToTerm) {
                Variable rootVar = ht.getRoot();
                HeapNode root = heap.getNode(rootVar.getName());
                PointToTerm heapNode = root.toPointToTerm();
                int index = ((PointToTerm) ht).compare(heapNode); //change this to boolean??
                if (index == -1)
                    continue;
                Variable toFix = ht.getVars()[index];
                for (String name : visited)
                    System.out.println("visited " + name);
                return new Bug(index, (PointToTerm) ht, toFix, visited.contains(ht.getRoot().getName()));
                /*
                if (visited.contains(ht.getRoot().getName())) {
                    //root variable has been visited before
                    return new Bug(index, (PointToTerm) ht, toFix, true);
                } else {
                    //root variable hasn't been visited before
                   // return new Bug(index, (PointToTerm) ht, toFix, false);
                    if (toFix instanceof ExistVariable) {
                        ((ExistVariable) toFix).next();
                    } else {
                        toFix.setName(heapNode.getVars()[index].getName());
                    }
                }
                */
            }
        }
        return null;
    }

    public void rollback() {
        this.index++;
    }

    public void fix() {
        PointToTerm[] pts = Utility.getPointToTerms(state);
        for (PointToTerm pt : pts) {
            Variable[] vars = pt.getVars();
            HeapNode hn = heap.getNode(vars[0].getName());
            for (int i = 1; i < vars.length; i++) {
                if (vars[i] instanceof ExistVariable) {
                    System.out.println("exist var " + vars[i].toString());
                    ((ExistVariable) vars[i]).next();
                    hn.fieldsByName.set(i - 1, vars[i].getName());
                    break;
                }
            }
        }
    }

    public void fix(Bug error) {
        //System.out.println("the error is:" + error.toString());
        PointToTerm pointToTerm = error.getPointToTerm();
        Variable root = pointToTerm.getRoot();
        HeapNode hn = heap.getNode(root.getName());
        Variable[] vars = pointToTerm.getVars();

        for (int i = 1; i < vars.length; i++) {
            if (error.isBackward()) {
                if (vars[i] instanceof ExistVariable) {
                    //TODO: what to do when explored all possibilities
                    System.out.println("exist var " + vars[i].toString());
                    ((ExistVariable) vars[i]).next();
                    hn.fieldsByName.set(i - 1, vars[i].getName());
                    break;
                }
            } else {
                if (!(vars[i] instanceof ExistVariable)) {
                    if (!vars[i].getName().equals(hn.fieldsByName.get(i - 1))) {
                        hn.fieldsByName.set(i - 1, vars[i].getName());
                    }
                } else {
                    vars[i].setName(hn.fieldsByName.get(i - 1));
                }
            }
        }
        return;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Formula f : state) {
            sb.append("[" + f.toString() + "]; ");
        }
        return sb.toString();
    }

    public void updateVisited() {
        for (Formula f : state) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm ht : hf.getHeapTerms()) {
                if (ht instanceof PointToTerm) {
                    visited.add(ht.getVars()[0].getName());
                }
            }
        }
    }
}
