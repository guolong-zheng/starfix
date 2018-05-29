package repair.heap;

public class PrimVar extends HeapNode {
    int value;

    public PrimVar(String type, Object var, int value){
        this.type = type;
        this.var = var;
        this.value = value;
    }
}
