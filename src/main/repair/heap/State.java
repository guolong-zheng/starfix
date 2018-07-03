package repair.heap;

import repair.Utility;
import repair.checker.Bug;
import repair.checker.SearchState;
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
    PointToTerm[] pointToTerms; //all point to terms, used to keep track of all visited nodes
    Set<String>[] visitedVars;
    int index;  //which to unfold; when 0 means have unfolded all inductive terms
    Stack<String> visited; //store visited variable names
    Stack<String> unvisited; //store all unvisited variable names
    boolean checked;

    //used to initial first state
    public State(Heap heap, Formula formula) {
        this.heap = heap.copy();
        this.parent = null;
        this.state = formula;
        inductiveTerms = Utility.getInductiveTerms(formula);
        pointToTerms = Utility.getPointToTerms(formula);
        index = inductiveTerms.length;
        visited = new Stack<>();
        checked = false;
    }

    //used to expand state
    public State(State st, Formula formula) {
        this.state = formula;
        this.parent = st;
        this.heap = st.getHeap().copy();
        inductiveTerms = Utility.getInductiveTerms(formula);
        pointToTerms = Utility.getPointToTerms(formula);
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

    public Formula getState() {
        return state;
    }

    public void unfold() {
        InductiveTerm it = inductiveTerms[index];
        HeapFormula heapFormula = state.getHeapFormula();
        PureFormula pureFormula = state.getPureFormula();
        Formula[] formulas = unfoldInductiveTerm(it);

        for (Formula f : formulas) {
            int heapSize = f.heapFormula.getHeapTerms().length + state.heapFormula.getHeapTerms().length - 1;
            int pureSize = f.pureFormula.getPureTerms().length + state.pureFormula.getPureTerms().length;

            HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
            PureTerm[] newPureTerms = new PureTerm[pureSize];

            int curr = 0;
            for (int i = 0; i < heapFormula.getHeapTerms().length; i++) {
                if (!heapFormula.getHeapTerms()[i].equals(it)) {
                    newHeapTerms[curr] = heapFormula.getHeapTerms()[i];
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

            State newState = new State(this, newFormula);
            SearchState.track.push(newState);
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
        if (hp.toString().contains("emp")) {
            if (Utility.checkPureFormula(pf)) {
                this.checked = true;
                return new Bug();
                }
        } else {
            this.checked = true;
            return checkHeapFormula(hp);
            }
        this.checked = true;
        return null;
    }

    public Bug checkHeapFormula(HeapFormula heapFormula) {
        for (HeapTerm ht : heapFormula.getHeapTerms()) {
            if (ht == null) {
                System.out.println("root is null");
                return new Bug();
            }
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

    //check the * separation, returns the repeated variable name
    public String checkStarSep() {
        Set<String> vars = new HashSet<>();
        for (PointToTerm pt : this.pointToTerms) {
            Variable var = pt.getRoot();
            if (!vars.add(var.toString())) {
                return var.toString();
            }
        }
        return null;
    }

    public boolean isFinal() {
        return inductiveTerms.length > 0;
    }

    public void updateVisited() {
        HeapFormula hf = state.getHeapFormula();
        for (HeapTerm ht : hf.getHeapTerms()) {
            if (ht instanceof PointToTerm) {
                visited.add(ht.getVars()[0].getName());
            }
        }
        }
}

