package repair.heap;

import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;

import java.util.LinkedList;
import java.util.List;

public class HeapNode {
    protected String type;
    protected String name;
    protected Object var;   //store object value to change field in original program
    protected List<String> fieldsByName;
    protected HeapNode[] fields;

    public HeapNode(String type, String name, Object var) {
        this.type = type;
        this.name = name;
        this.var = var;
    }

    public HeapNode(String type, String name, Object var, List<String> fieldsByName) {
        this.type = type;
        this.name = name;
        this.var = var;
        this.fieldsByName = new LinkedList<>();
        this.fieldsByName.addAll(fieldsByName);
    }

    public List<String> getFieldsValue() {
        return fieldsByName;
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
        Variable[] vars = new Variable[fieldsByName.size() + 1];
        vars[0] = new Variable(name, type);
        for (int i = 1; i < fieldsByName.size() + 1; i++) {
            String name = fieldsByName.get(i - 1);
            vars[i] = new Variable(name);
        }
        return vars;
    }

    public PointToTerm toPointToTerm() {
        return new PointToTerm(type, toVarArry());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type + " " + " " + name + "-> (");
        for (String s : fieldsByName) {
            sb.append(s + ",");
        }
        sb.append(")");
        return sb.toString();
    }

    public void next(int index, String newString) {
        fieldsByName.set(index, newString);
    }

    public HeapNode copy() {
        HeapNode newHn = new HeapNode(this.type, this.name, this.var);
        newHn.fieldsByName = new LinkedList<>();
        newHn.fieldsByName.addAll(this.fieldsByName);
        System.arraycopy(this.fields, 0, newHn.fields, 0, this.fields.length);
        return newHn;
    }
}
