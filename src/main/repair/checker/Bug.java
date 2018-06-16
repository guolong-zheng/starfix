package repair.checker;

import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;

public class Bug {
    Variable var;
    PointToTerm pointToTerm;
    int index;
    boolean backward;
    public boolean stop;

    public Bug() {
        this.stop = true;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(var.toString() + " ");
        sb.append(pointToTerm.toString() + " ");
        sb.append(index + " ");
        sb.append(backward);
        return sb.toString();
    }
}
