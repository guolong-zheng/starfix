package testgene.testsuites;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import starlib.StarVisitor;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.pure.PureTerm;
import testgene.FieldInfo;

public class InitVarsVisitor extends StarVisitor {

    protected HashMap<String, String> knownTypeVars;
    protected HashSet<Variable> initVars;
    protected String objName;
    protected String clsName;
    protected Set<String> instanceVars;
    protected Set<String> classVars;
    protected Set<String> finalVars;

    public InitVarsVisitor(HashMap<String, String> knownTypeVars, HashSet<Variable> initVars,
                           String objName, String clsName,
                           FieldInfo[] insFields, FieldInfo[] staFields) {
        this.knownTypeVars = knownTypeVars;
        this.initVars = initVars;
        this.objName = objName;
        this.clsName = clsName;

        this.finalVars = new HashSet<String>();

        this.instanceVars = new HashSet<String>();
        for (FieldInfo fi : insFields) {
            String name = "this_" + fi.getName();
            instanceVars.add(name);
            if (fi.isFinal())
                finalVars.add(name);
        }

        this.classVars = new HashSet<String>();
        if (staFields != null)
            for (FieldInfo fi : staFields) {
                String name = clsName + "_" + fi.getName();
                classVars.add(name);
                if (fi.isFinal())
                    finalVars.add(name);
            }
    }

    public InitVarsVisitor(InitVarsVisitor that) {
        this.knownTypeVars = that.knownTypeVars;
        this.initVars = that.initVars;
        this.objName = that.objName;
        this.clsName = that.clsName;
        this.instanceVars = that.instanceVars;
        this.classVars = that.classVars;
        this.finalVars = that.finalVars;
    }

    @Override
    public void visit(HeapFormula formula) {
        int oldLength = initVars.size();
        while (true) {

            for (HeapTerm heapTerm : formula.getHeapTerms()) {
                heapTerm.accept(this);
            }

            int newLength = initVars.size();

            if (newLength == oldLength) break;
            else oldLength = newLength;
        }
    }

    @Override
    public void visit(PureFormula formula) {
        int oldLength = initVars.size();
        while (true) {

            for (PureTerm pureTerm : formula.getPureTerms()) {
                pureTerm.accept(this);
            }

            int newLength = initVars.size();

            if (newLength == oldLength) break;
            else oldLength = newLength;
        }
    }

    public boolean isInstanceVariable(Variable var) {
        return instanceVars.contains(var.getName());
    }

    public boolean isClassVariable(Variable var) {
        return classVars.contains(var.getName());
    }

}
