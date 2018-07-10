package repair.heap;

import starlib.formula.Variable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ExistVariable extends Variable {

    Queue<String> potentialVars;  //TODO: store in one public place as an array and use index to control access,
    // to save memory and access time

    public ExistVariable(String name, String type, Set<String> allVars, Set<String> visited) {
        super(name, type);
        potentialVars = new LinkedList<>();
    }

    public ExistVariable(String name, String type) {
        super(name, type);
    }

    public ExistVariable(String name) {
        super(name);
    }

    public ExistVariable(Variable var, Set<String> allVars, Set<String> visited) {
        super(var);
        potentialVars = new LinkedList<>();
        potentialVars.add("null");
        for (String s : allVars) {
            if (!visited.contains(s)) {
                potentialVars.add(s);
            }
        }
    }

    public ExistVariable(String name, Set<String> potentialVars) {
        super(name);
        this.potentialVars = new LinkedList<>();
        this.potentialVars.addAll(potentialVars);
    }

    public boolean next() {
        System.out.print("selecting from : ");
        for (String s : potentialVars) {
            System.out.print(s + " ");
        }
        System.out.println();
        if (potentialVars.isEmpty()) {
            if (this.getValue().equals("null"))
                // has explored all possibilities
                return false;
            else {
                System.out.println("set [" + this.getName() + "] form " + this.getValue() + " to [null]");
                this.setValue("null");
            }
        } else {
            String newValue = potentialVars.remove();
            System.out.println("set [" + this.getName() + "] form " + this.getValue() + " to [" + newValue + "]");
            this.setValue(newValue);
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
