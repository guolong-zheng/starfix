package repair.heap;

import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;

import java.util.Set;

public class HeapNode {
    protected String type;
    protected String name;
    protected Object var;   //store object value to change field in original program
    protected Set<String> fieldsByName;
    protected HeapNode[] fields;

    public HeapNode(String type, String name, Object var, Set<String> fieldsByName) {
        this.type = type;
        this.name = name;
        this.var = var;
        this.fieldsByName = fieldsByName;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return var;
    }

    public HeapNode[] getFields() {
        return fields;
    }

    public HeapNode getFieldsbyName(String name) {
        for (HeapNode hp : fields) {
            if (hp.getName() == name)
                return hp;
        }
        return null;
    }

    public boolean isPrim() {
        if (type.equals("boolean") || type.equals("byte") || type.equals("char") ||
                type.equals("double") || type.equals("float") || type.equals("int") ||
                type.equals("long") || type.equals("short"))
            return true;
        else
            return false;
    }

    public Variable[] toVarArry() {
        Variable[] vars = new Variable[fields.length + 1];
        vars[0] = new Variable(name, type);
        for (int i = 1; i < fields.length + 1; i++) {
            vars[i] = new Variable(fields[i - 1].getName(), fields[i - 1].getType());
        }
        return vars;
    }

    public PointToTerm toPointToTerm() {
        return new PointToTerm(type, toVarArry());
    }
}
