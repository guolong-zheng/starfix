package starlib.precondition;

import starlib.formula.Formula;

import java.util.*;

public class PreconditionMap {
	
	private static Map<String,Precondition> preMap = new HashMap<String,Precondition>();
	
	public static void put(Precondition pre) {
		String methodName = pre.getMethodName();
		assert !preMap.containsKey(methodName);
		preMap.put(methodName, pre);
	}
	
	public static void put(Precondition[] ips) {
		for (int i = 0; i < ips.length; i++) {
			put(ips[i]);
		}
	}

    public static Formula[] getFormulas() {
        Set<Formula> allFormula = new HashSet<>();
        for (Precondition prec : preMap.values()) {
            allFormula.add(prec.getFormula());
        }
        return allFormula.toArray(new Formula[allFormula.size()]);
    }

	public static Precondition find(String methodName) {
		Precondition pre = preMap.get(methodName);
		return pre;
	}

	public static void reset() {
		preMap = new HashMap<String,Precondition>();
	}
}
