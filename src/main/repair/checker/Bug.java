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

    public Bug(int index, PointToTerm pointToTerm, Variable var) {
        this.index = index;
        this.pointToTerm = pointToTerm;
        this.var = var;
    }

    public boolean isBackward() {
        return backward;
    }
}
