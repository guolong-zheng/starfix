package repair.fix;

import repair.heap.Heap;
import repair.heap.HeapNode;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Fix {
    Heap origHeap;
    Heap toHeap;
    Set<HeapFix> fixes;
    int priority;

    public Fix(Heap origHeap) {
        this.origHeap = origHeap.copy();
        this.fixes = new HashSet<>();
    }

    public void setToHeap(Heap toHeap) {
        this.toHeap = toHeap.copy();
    }

    public Fix(Heap origHeap, Heap toHeap) {
        this.origHeap = origHeap;
        this.toHeap = toHeap;
        this.fixes = new HashSet<>();
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
