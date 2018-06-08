package repair.concreteFormula;

import repair.heap.Heap;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.pure.PureTerm;
import starlib.solver.Solver;

public class ConcreteFormula extends Formula {

    public ConcreteFormula(HeapFormula heapFormula, PureFormula pureFormula) {
        super(heapFormula, pureFormula);
    }

}
