package repair.heap;

public class HeapNode {
    protected String type;
    protected String name;
    protected Object var;   //store object value to change field in original program
    protected HeapNode[] fields;

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
}
