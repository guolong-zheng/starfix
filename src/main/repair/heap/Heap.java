package repair.heap;

import java.util.*;

public class Heap {
    HeapNode root;  //root of current heap
    Set<HeapNode> heapNodes;
    Map<String, HeapNode> name2node;    //map variable name to concrete heap node
    Map<Object, HeapNode> var2node;  //map original variable to concrete heap node

    public Heap() {
        this.root = null;
        this.var2node = new HashMap<>();
        this.name2node = new HashMap<>();
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
        heapNodes.add(heapNode);
        if (root == null)
            root = heapNode;
        this.var2node.put(heapNode.getValue(), heapNode);
        this.name2node.put(heapNode.getName(), heapNode);
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HeapNode hn : heapNodes) {
            sb.append(hn.toString() + "\n");
        }
        return sb.toString();
    }
}
