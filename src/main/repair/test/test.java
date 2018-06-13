package repair.test;

import starlib.formula.Formula;
import starlib.precondition.Initializer;
import starlib.precondition.PreconditionMap;

public class test {
    public static void main(String[] args) {
        Initializer.initPrecondition("pre repair == cdll(N0,size && size=4)");
        for (Formula f : PreconditionMap.getFormulas()) {
            System.out.println(f.toString());
        }
    }
}
