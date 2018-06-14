package repair.heap;

import starlib.formula.Variable;

import java.util.LinkedList;
import java.util.Queue;

public class ExistVariable extends Variable {

    Queue<Variable> potentialVars;  //TODO: store in one public place as an array and use index to control access,
    // to save memory and access time

    public ExistVariable(String name, String type) {
        super(name, type);
    }

    public ExistVariable(String name) {
        super(name);
    }

    public ExistVariable(Variable var) {
        super(var);
    }

    public ExistVariable(String name, Queue<Variable> potentialVars) {
        super(name);
        this.potentialVars = new LinkedList<>();
        this.potentialVars.addAll(potentialVars);
    }

    public boolean next() {
        if (potentialVars.isEmpty()) {
            if (this.getName().equals("null"))
                // has explored all possibilities
                return false;
            else
                this.setName("null");
        } else
            this.setName(potentialVars.remove().toString());

        return true;
    }
}
