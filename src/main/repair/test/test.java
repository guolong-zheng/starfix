package repair.test;

import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Variable root = new Variable("root");
        Variable var1 = new Variable("next", "");
        Variable var2 = new Variable("k", "");

        HeapTerm it1 = new InductiveTerm("sll", root, var1, var2);

        Variable[] fromVars = {new Variable("root"), new Variable("next", "")};
        Variable[] toVars = {new Variable("next", ""), new Variable("next1", "")};
        Map<String, String> existVarSubMap = new HashMap<String, String>();

        HeapTerm it2 = it1.substitute(fromVars, toVars, existVarSubMap);

    }
}
