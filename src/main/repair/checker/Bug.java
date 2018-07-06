package repair.checker;

import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;

public class Bug {

    Status status;
    Variable var;
    PointToTerm pointToTerm;
    int index;

    public Bug(Status status) {
        this.status = status;
    }

    public Bug(Variable var, Status status) {
        this.var = var;
        this.status = status;
    }

    public Bug(int index, PointToTerm pointToTerm, Variable var, Status status) {
        this.index = index;
        this.pointToTerm = pointToTerm;
        this.var = var;
        this.status = status;
    }

    public boolean isBackward() {
        return status == Status.BACKWARD;
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
        return sb.toString();
    }
}
