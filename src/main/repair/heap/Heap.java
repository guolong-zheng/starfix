package repair.heap;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.solver.Solver;

import java.util.*;

public class Heap {
    Heap parent;    //store the parent of current heap for the purpose of roll back
    HeapNode root;  //root of current heap
    Set<HeapNode> heapNodes;
    Map<String, HeapNode> name2node;    //map variable name to concrete heap node
    Map<Object, HeapNode> var2node;  //map original variable to concrete heap node
    Set<Variable> visitedVars;  // all visited variables
    Set<Formula> state;    //store the state of a heap, a collection of disjunction formulas

    public Heap() {
        this.root = null;
        this.var2node = new HashMap<>();
        this.name2node = new HashMap<>();
        this.visitedVars = new HashSet<>();
        this.heapNodes = new HashSet<>();
        this.state = new HashSet<>();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void addNode(HeapNode heapNode) {
        if (root == null)
            root = heapNode;
        this.var2node.put(heapNode.getValue(), heapNode);
    }

    public HeapNode getRoot() {
        return this.root;
    }

    public HeapNode getNode(String name) {
        return name2node.get(name);
    }

    public HeapNode findNode(Object var) {
        return var2node.get(var);
    }

    public Set<Formula> getState() {
        return state;
    }

    public Heap unfold() {
        Heap heap = new Heap();
        heap.parent = this;
        for (Formula f : state) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm ht : hf.getHeapTerms()) {
                if (ht instanceof InductiveTerm) {
                    ((InductiveTerm) ht).unfold();
                    Formula[] fs = ((InductiveTerm) ht).getUnfoldedFormulas();
                    heap.state.addAll(Arrays.asList(fs));
                }
            }
        }
        return heap;
    }

}
