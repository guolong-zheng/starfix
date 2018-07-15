package repair.heap;

import repair.Utility;
import repair.checker.Bug;
import repair.checker.Checker;
import repair.checker.Status;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
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
    Formula state;    //store the state of a subheap, a collection of disjunction formulas
    InductiveTerm[] inductiveTerms; //all possible unfolds
    PointToTerm[] pointToTerms; //point to terms that get unfolded in this state
    Set<String> visited; // used to check for separation *
    int index;  //which to unfold; when 0 means have unfolded all inductive terms

    Map<String, String> var2value; //variable name to address value in heap

    Set<String> visitedVars; //all visited vars
    //Stack<String> unvisited;

    boolean checked;

    //used to initial first state
    public State(Heap heap, Formula formula) {
        this.heap = heap.copy();
        this.parent = null;
        this.state = formula;
        inductiveTerms = Utility.getInductiveTerms(formula);
        pointToTerms = Utility.getPointToTerms(formula);
        index = inductiveTerms.length;
        visited = new HashSet<>();
        visitedVars = new HashSet<>();
        checked = false;
    }

    //used to expand state
    public State(State st, Formula formula, PointToTerm[] pts) {
        this.state = formula;
        this.parent = st;
        this.heap = st.getHeap().copy();
        inductiveTerms = Utility.getInductiveTerms(formula);
        pointToTerms = pts;
        visited = new HashSet<>();
        visitedVars = new HashSet<>();
        visitedVars.addAll(st.getVisitedVars());
        index = inductiveTerms.length;
        checked = false;
    }

    public State getParent() {
        return this.parent;
    }

    public Set<String> getVisitedVars() {
        return visitedVars;
    }

    public boolean isChecked() {
        return checked;
    }

    public PointToTerm[] getPointToTerms() {
        return this.pointToTerms;
    }

    public Heap getHeap() {
        return heap;
    }

    public Formula getState() {
        return state;
    }

    public void unfold() {
        System.out.print("all inductive terms: | ");
        for (InductiveTerm it1 : inductiveTerms)
            System.out.print(it1.toString() + " | ");
        System.out.println();
        InductiveTerm it = inductiveTerms[0];
        HeapFormula heapFormula = state.getHeapFormula();
        PureFormula pureFormula = state.getPureFormula();
        Formula[] formulas = unfoldInductiveTerm(it);

        for (Formula f : formulas) {
            PointToTerm[] pts = Utility.getPointToTerms(f);
            int heapSize = f.heapFormula.getHeapTerms().length + state.heapFormula.getHeapTerms().length - 1;
            int pureSize = f.pureFormula.getPureTerms().length + state.pureFormula.getPureTerms().length;

            HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
            PureTerm[] newPureTerms = new PureTerm[pureSize];

            int curr = 0;
            for (int i = 0; i < heapFormula.getHeapTerms().length; i++) {
                if (!heapFormula.getHeapTerms()[i].equals(it)) {
                    newHeapTerms[curr] = heapFormula.getHeapTerms()[i].copy();
                    curr++;
                }
            }

            System.arraycopy(f.heapFormula.getHeapTerms(), 0,
                    newHeapTerms, heapFormula.getHeapTerms().length - 1, f.heapFormula.getHeapTerms().length);

            System.arraycopy(pureFormula.getPureTerms(), 0,
                    newPureTerms, 0, pureFormula.getPureTerms().length);
            System.arraycopy(f.pureFormula.getPureTerms(), 0,
                    newPureTerms, pureFormula.getPureTerms().length, f.pureFormula.getPureTerms().length);

            HeapFormula newHeapFormula = new HeapFormula(newHeapTerms);
            PureFormula newPureFormula = new PureFormula(newPureTerms);
            Formula newFormula = new Formula(newHeapFormula, newPureFormula);

            System.out.println("unfold got:" + newFormula.toString());
            State newState = new State(this, newFormula, pts);
            newState.updateVisitedVars();
            Checker.track.add(newState);
        }
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
        HeapFormula hp = state.getHeapFormula();
        PureFormula pf = state.getPureFormula();
        this.checked = true;
        if (!Utility.checkPureFormula(pf)) {
            System.out.println("unstasified pure formula [" + pf.toString() + "]");
            return new Bug(Status.STOP);
        }
        else {
            System.out.println("stasifing pure formula [" + pf.toString() + "]");
            return checkHeapFormula(hp);
        }
    }

    public Bug checkHeapFormula(HeapFormula heapFormula) {
        for (HeapTerm ht : heapFormula.getHeapTerms()) {
            if (ht instanceof PointToTerm) {
                Variable rootVar = ht.getRoot();
                if (rootVar.getValue().equals("null")) {
                    if (rootVar.hasChanged())
                        return new Bug(rootVar, Status.NULL_ROOT);
                    else
                        return new Bug(Status.STOP);
                }
                if (!visited.add(rootVar.getValue())) {
                    visited.clear();
                    return new Bug(rootVar, Status.DUPLICATE);
                }
                HeapNode root = heap.getNode(rootVar.getValue());
                PointToTerm heapNode = root.toPointToTerm();
                int index = ((PointToTerm) ht).compare(heapNode); //change this to boolean??
                if (index == -1)
                    continue;
                Variable toFix = ht.getVars()[index];
                visited.clear();
                if (toFix instanceof ExistVariable)
                    return new Bug(index, (PointToTerm) ht, toFix, Status.MISMATCH);
                else
                    return new Bug(index, (PointToTerm) ht, toFix, Status.STAY);
            }
        }
        visited.clear();
        return new Bug(Status.PASS);
    }

    public boolean backfix(Bug error) {
        String value = error.getVar().getValue();
        System.out.println("fixing variable " + error.getVar().getName() + " with value " + value);
        for (PointToTerm pt : pointToTerms) {
            Variable[] vars = pt.getVars();
            for (int i = 0; i < vars.length; i++) {
                if (vars[i].getValue().equals(value) && vars[i] instanceof ExistVariable) {
                    if (!((ExistVariable) vars[i]).next())
                        return false;
                    for (HeapTerm ht : state.getHeapFormula().getHeapTerms()) {
                        for (Variable var : ht.getVars()) {
                            if (var.getName().equals(vars[i].getName()))
                                var.setValue(vars[i].getValue());
                        }
                    }
                    HeapNode hn = heap.getNode(pt.getRoot().getValue());
                    hn.fieldsByName.set(i - 1, vars[i].getValue());
                    System.out.println("heap node " + hn.toString());
                    System.out.println("heap " + heap.toString());
                    System.out.println("fixed to " + state.toString());
                    return true;
                }
            }
        }
        return false;
    }

    public void stayfix(Bug error) {
        System.out.println("fixing " + error.toString());
        PointToTerm pointToTerm = error.getPointToTerm();
        Variable root = pointToTerm.getRoot();
        HeapNode hn = heap.getNode(root.getValue());
        Variable[] vars = pointToTerm.getVars();
        for (int i = 1; i < vars.length; i++) {
            if (!(vars[i] instanceof ExistVariable)) {
                if (!vars[i].getValue().equals(hn.fieldsByName.get(i - 1))) {
                    hn.fieldsByName.set(i - 1, vars[i].getValue());
                }
            } else {
                vars[i].setValue(hn.fieldsByName.get(i - 1));
            }
        }
    }

    public boolean isFinal() {
        HeapFormula hp = state.getHeapFormula();
        for (HeapTerm ht : hp.getHeapTerms()) {
            if (ht instanceof InductiveTerm)
                return false;
        }
        return true;
    }

    public void updateVisitedVars() {
        HeapFormula hf = state.getHeapFormula();
        for (HeapTerm ht : hf.getHeapTerms()) {
            if (ht instanceof PointToTerm) {
                visitedVars.add(ht.getVars()[0].getValue());
            }
        }
    }

}

