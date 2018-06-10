package repair.heap;

import starlib.formula.Variable;

import java.util.Queue;

public class ExistVariable extends Variable {

    Queue<Variable> potentialVars;

    public ExistVariable(String name, String type) {
        super(name, type);
    }

    public ExistVariable(String name) {
        super(name);
    }

    public ExistVariable(Variable var) {
        super(var);
    }

    public void next() {
        this.setName(potentialVars.remove().toString());
    }
}
