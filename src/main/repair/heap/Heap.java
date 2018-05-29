package repair.heap;

import java.util.HashMap;
import java.util.Map;

public class Heap {
    HeapNode root;
    Map<Object, HeapNode> nodeMap;  //map variable name to heap node

    public Heap(){
        this.root = null;
        this.nodeMap = new HashMap<>();
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void addNode(HeapNode heapNode){
        if(root ==null)
            root = heapNode;
        this.nodeMap.put(heapNode.var, heapNode);
    }

    public HeapNode findNode(Object var){
        return nodeMap.get(var);
    }

}
