package repair.heap;

import starlib.formula.Variable;
import starlib.formula.heap.InductiveTerm;

public class ConcretePointToTerm extends InductiveTerm {

    public ConcretePointToTerm(String predName, Variable... vars) {
        super(predName, vars);
    }
}
