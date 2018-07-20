package repair.fix;

import repair.Utility;
import repair.heap.Heap;
import repair.heap.HeapNode;
import repair.heap.State;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.Utilities;
import starlib.formula.heap.HeapTerm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Fix {
    public static Heap origHeap;
    Heap toHeap;
    Set<HeapFix> fixes;
    int priority;

    public Fix(Heap toHeap) {
        this.toHeap = toHeap;
        this.fixes = new HashSet<>();
        this.priority = 0;
    }

    public Fix(State state) {
        toHeap = new Heap();
        Formula f = state.getState();
        HeapFormula hf = f.getHeapFormula();
        for (HeapTerm ht : hf.getHeapTerms()) {
            toHeap.addNode(Utility.toHeapNode(ht));
        }
    }

    public String toString() {
        return toHeap.toString();
    }

    //TODO: change design of HeapNode to store field name
    public void generate() {
        Map<String, HeapNode> name2node = toHeap.getName2NodeMap();
        for (HeapNode node : origHeap.getNodes()) {
            String name = node.getName();
            HeapNode toNode = name2node.get(name);
            List<String> origField = node.getFieldsValue();
            List<String> toField = toNode.getFieldsValue();
            for (int i = 0; i < origField.size(); i++) {
                String origName = origField.get(i);
                String toName = toField.get(i);
                if (!origName.equals(toName)) {
                    HeapNode hn = toHeap.getNode(toName);
                    HeapFix heapFix = new HeapFix(node, "", hn);
                    fixes.add(heapFix);
                }
            }
        }
    }

    public void applyFix() {
        for (HeapFix heapFix : fixes) {
            heapFix.perform();
        }
    }
}
