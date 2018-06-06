package repair.concreteFormula;

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

    /*
    public boolean isSat(){
        HeapFormula hf = this.getHeapFormula();
        PureFormula pf = this.getPureFormula();

        Formula purePart = new Formula(new HeapFormula(), pf);
    }
    */

    /*
    public void unfold() {
        HeapFormula hf = this.getHeapFormula();
        PureFormula pf = this.getPureFormula();

        for(HeapTerm f : hf.getHeapTerms()){
            if(f instanceof InductiveTerm){
                Formula[] unfoldedFormulas = ((InductiveTerm) f).getUnfoldedFormulas();
                for(Formula uf : unfoldedFormulas){

                }
            }
        }
        Formula[] unfoldedFormulas = it.getUnfoldedFormulas();
        Formula f = unfoldedFormulas[index];

        int heapSize = f.heapFormula.getHeapTerms().length + heapFormula.getHeapTerms().length - 1;
        int pureSize = f.pureFormula.getPureTerms().length + pureFormula.getPureTerms().length;

        HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
        PureTerm[] newPureTerms = new PureTerm[pureSize];

        int curr = 0;
        for (int i = 0; i < heapFormula.getHeapTerms().length; i++) {
            if (!heapFormula.getHeapTerms()[i].equals(it)) {
                newHeapTerms[curr] = heapFormula.getHeapTerms()[i];
                curr++;
            }
        }

        System.arraycopy(f.heapFormula.getHeapTerms(), 0,
                newHeapTerms, heapFormula.getHeapTerms().length - 1, f.heapFormula.getHeapTerms().length);

        System.arraycopy(pureFormula.getPureTerms(), 0,
                newPureTerms, 0, pureFormula.getPureTerms().length);
        System.arraycopy(f.pureFormula.getPureTerms(), 0,
                newPureTerms, pureFormula.getPureTerms().length, f.pureFormula.getPureTerms().length);

        heapFormula = new HeapFormula(newHeapTerms);
        pureFormula = new PureFormula(newPureTerms);
        depth++;
    }
    */
}
