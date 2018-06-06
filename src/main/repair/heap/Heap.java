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
    HeapNode root;  //root of current heap
    Map<String, HeapNode> name2node;    //map variable name to concrete heap node
    Map<Object, HeapNode> nodeMap;  //map original variable to concrete heap node
    Set<Variable> visitedVars;  // all visited variables
    Formula[] state;    //store the state of current heap

    public Heap() {
        this.root = null;
        this.nodeMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void addNode(HeapNode heapNode) {
        if (root == null)
            root = heapNode;
        this.nodeMap.put(heapNode.getValue(), heapNode);
    }

    public HeapNode getRoot() {
        return this.root;
    }

    public HeapNode getNode(String name) {
        return name2node.get(name);
    }

    public HeapNode findNode(Object var) {
        return nodeMap.get(var);
    }

    public Formula[] getState() {
        return state;
    }

    public Queue<Heap> unfold() {
        Queue<Heap> toVisit = new LinkedList<>();
        for (Formula f : state) {
            HeapFormula hf = f.getHeapFormula();
            for (HeapTerm ht : hf.getHeapTerms()) {
                if (ht instanceof InductiveTerm) {
                    ((InductiveTerm) ht).unfold();
                    Formula[] fs = ((InductiveTerm) ht).getUnfoldedFormulas();
                    for (int i = 0; i < fs.length; i++) {
                        Heap newHeap = new Heap();
                        fs[i].unfold((InductiveTerm) ht, i);
                        newHeap.state = fs;
                        toVisit.add(newHeap);
                    }
                }
            }
        }

        return toVisit;
    }

}
