package testgene.testsuites;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import testgene.FieldInfo;
import testgene.PathFinderUtils;

public class TestGenVisitor extends InitVarsVisitor {

    protected StringBuffer test;

    public TestGenVisitor(HashMap<String, String> knownTypeVars, HashSet<Variable> initVars,
                          String objName, String clsName, FieldInfo[] insFields,
                          FieldInfo[] staFields, StringBuffer test) {
        super(knownTypeVars, initVars, objName, clsName, insFields, staFields);
        this.test = test;
    }

    public TestGenVisitor(TestGenVisitor that) {
        super(that);
        this.test = that.test;
    }

    @Override
    public void visit(Formula formula) {
        ConTestGenVisitor con = new ConTestGenVisitor(this);
        NoConTestGenVisitor ncon = new NoConTestGenVisitor(this);
        SetFieldsTestGenVisitor setFields = new SetFieldsTestGenVisitor(this);

        HeapFormula heapFormula = formula.getHeapFormula();
        PureFormula pureFormula = formula.getPureFormula();
        heapFormula.accept(con);
        pureFormula.accept(con);
        pureFormula.accept(ncon);

        //genDefaultVars();

        heapFormula.accept(setFields);
    }

    @Override
    public void visit(HeapFormula formula) {
        for (HeapTerm heapTerm : formula.getHeapTerms()) {
            heapTerm.accept(this);
        }
    }

    private void genDefaultVars() {
        if (knownTypeVars.size() == initVars.size())
            return;

        for (Entry<String, String> entry : knownTypeVars.entrySet()) {
            String name = entry.getKey(); // name is key, type is value
            if (name.startsWith("Anon_")) continue;
            String type = entry.getValue();
            Variable var = new Variable(name, type);
            if (!initVars.contains(var)) {
                if (var.isPrim()) {
                    String val = type.equals("boolean") ? "false" : "0";
                    test.append(makeDeclAndInit(var, val));
                } else {
                    //test.append(makeDeclAndInit(var, "null"));
                }
            }
        }
    }

    public String standardizeName(Variable var) {
        String name = var.getName();

        if (isInstanceVariable(var))
            name = name.replace("this_", objName + ".");
        else if (isClassVariable(var))
            name = name.replace(clsName + "_", clsName + ".");

        return name;
    }

    /*
     * Generate the declaration for this variable
     */
    public String makeDeclaration(Variable var) {
        String name = var.getName();
        if (isInstanceVariable(var))
            name = name.replace("this_", objName + ".");
        else if (isClassVariable(var))
            name = name.replace(clsName + "_", clsName + ".");
        else
            name = PathFinderUtils.toJavaType(var.getType()) + " " + name;
        return name;
    }

    public String makeDeclAndInit(Variable var, String value) {
        return "\t\t" + makeDeclaration(var) + " = " + value + ";\n";
    }

    public String makeDeclAndInitWithConstructor(Variable var) {
        return makeDeclAndInit(var, "new " + PathFinderUtils.toJavaType(var.getType()) + "()");
    }

    public boolean shoudlMutate() {
        Random rand = new Random(System.currentTimeMillis());
        int n = rand.nextInt(initVars.size());
        if (TestGenerator.count >= TestGenerator.size && n > TestGenerator.size)
            return false;
        else
            return true;
    }
}
