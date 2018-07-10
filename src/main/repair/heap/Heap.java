package repair.heap;

import java.util.*;

public class Heap {
    HeapNode root;  //root of current heap
    Set<HeapNode> heapNodes;
    public static Map<String, HeapNode> name2node = new HashMap<>();    //map variable name to concrete heap node
    public static Map<Object, HeapNode> var2node = new HashMap<>();  //map original variable to concrete heap node
    public static Set<String> allVars = new HashSet<>();

    public Heap() {
        this.root = null;
        this.heapNodes = new HashSet<>();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Set<HeapNode> getNodes() {
        return heapNodes;
    }

    public Map<String, HeapNode> getName2NodeMap() {
        return name2node;
    }

    public void addNode(HeapNode heapNode) {
        this.heapNodes.add(heapNode);
        if (this.root == null)
            this.root = heapNode;
        this.var2node.put(heapNode.getValue(), heapNode);
        this.name2node.put(heapNode.getName(), heapNode);
        this.allVars.add(heapNode.getName());
    }

    public Set<String> getVars() {
        return this.allVars;
    }
    public HeapNode getRoot() {
        return this.root;
    }

    public HeapNode getNode(String name) {
        for (HeapNode hn : heapNodes)
            if (hn.getName().equals(name))
                return hn;
        return null;
//        return name2node.get(name);
    }

    public HeapNode findNode(Object var) {
        return var2node.get(var);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HeapNode hn : heapNodes) {
            sb.append(hn.toString() + "\n");
        }
        return sb.toString();
    }


    public Heap copy() {
        Heap newHeap = new Heap();
        newHeap.root = this.root.copy();
        for (HeapNode hn : this.heapNodes) {
            newHeap.heapNodes.add(hn.copy());
        }
        return newHeap;
    }
}
