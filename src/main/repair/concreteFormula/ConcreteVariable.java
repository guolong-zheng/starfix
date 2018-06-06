package repair.concreteFormula;

import starlib.formula.Variable;

public class ConcreteVariable extends Variable {
    Object value;

    public ConcreteVariable(String name, String type, Object value) {
        super(name, type);
        this.value = value;
    }
}
