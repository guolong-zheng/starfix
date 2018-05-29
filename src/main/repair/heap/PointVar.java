package repair.heap;

import java.util.HashMap;
import java.util.Map;

public class PointVar extends HeapNode {
    Map<String, HeapNode> name2field;   //map field name to its heap node

    public PointVar(String type){
        this.type = type;
        this.var = null;
        this.name2field = null;
    }

    public PointVar(String type, Object var){
        this.type = type;
        this.var = var;
        this.name2field = new HashMap<>();
    }

    public PointVar(String type, Object var, Map<String, HeapNode> name2field){
        this.type = type;
        this.var = var;
        this.name2field = name2field;
    }
}
