package repair.heap;

import starlib.formula.Variable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ExistVariable extends Variable {

    Queue<String> potentialVars;  //TODO: store in one public place as an array and use index to control access,
    // to save memory and access time

    public ExistVariable(String name, String type, Set<String> visited) {
        super(name, type);
        potentialVars = new LinkedList<>(visited);
    }

    public ExistVariable(String name, String type) {
        super(name, type);
    }

    public ExistVariable(String name) {
        super(name);
    }

    public ExistVariable(Variable var, Set<String> visited) {
        super(var);
        potentialVars = new LinkedList<>(visited);
    }

    public ExistVariable(String name, Set<String> potentialVars) {
        super(name);
        this.potentialVars = new LinkedList<>();
        this.potentialVars.addAll(potentialVars);
    }

    public boolean next() {
        for (String s : potentialVars) {
            System.out.println("possibility : " + s);
        }

        if (potentialVars.isEmpty()) {
            if (this.getName().equals("null"))
                // has explored all possibilities
                return false;
            else
                this.setName("null");
        } else {
            String newName = potentialVars.remove();
            System.out.println("new name" + newName + " this name: " + this.getName());
            this.setName(newName);
            System.out.println(this.getName());
        }

        return true;
    }

    public ExistVariable copy() {
        ExistVariable newVar = (ExistVariable) super.copy();
        newVar.potentialVars = new LinkedList<>();
        newVar.potentialVars.addAll(this.potentialVars);
        return newVar;
    }
}
