package repair.heap;

import repair.concreteFormula.ConcreteVariable;

public class HeapNode {
    protected String type;
    protected String name;
    protected Object var;   //store object value to change field in original program

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return var;
    }

}
