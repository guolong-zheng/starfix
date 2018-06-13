package repair.checker;

import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;

public class Bug {
    Variable var;
    PointToTerm pointToTerm;
    int index;
    boolean backward;

    public Bug() {

    }

    public Bug(int index, PointToTerm pointToTerm, Variable var, boolean backward) {
        this.index = index;
        this.pointToTerm = pointToTerm;
        this.var = var;
        this.backward = backward;
    }

    public boolean isBackward() {
        return backward;
    }

    public PointToTerm getPointToTerm() {
        return pointToTerm;
    }

    public int getIndex() {
        return index;
    }

    public Variable getVar() {
        return var;
    }
}
