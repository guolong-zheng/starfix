package starlib.formula.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import repair.heap.Heap;
import repair.heap.State;
import starlib.StarVisitor;
import starlib.formula.Formula;
import starlib.formula.Variable;

public abstract class HeapTerm {
	
	// variables of current term, first variable is the root
	protected Variable[] vars;
	
	public abstract HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);

//	public abstract HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
//										Map<String, String> existVarSubMap, State state);

	public abstract HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
										Map<String, Variable> existVarSubMap, State state);

	public Variable getRoot() {
		return vars[0];
	}
	
	public Variable[] getVars() {
		return vars;
	}
	
	public Variable[] getVarsNoRoot() {
		return Arrays.copyOfRange(vars, 1, vars.length);
	}
	
	public HeapTerm copy() {
		return this;
	}
	
	public void updateType(HashMap<String,String> knownTypeVars) {
		return;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		else if (!(other instanceof HeapTerm))
			return false;
		else
			return this.toString().equals(other.toString());
	}
	
	public abstract String toS2SATString();
	
	public void accept(StarVisitor visitor) {}

	protected String getParams(int start) {
		int length = vars.length;
		assert length > 0;
				
		StringBuilder params = new StringBuilder();
		for (int i = start; i < length - 1; i++) {
			params.append(vars[i] + "[" + vars[i].getValue() + "]" + ",");
		}
		
		if (length > start) {
			params.append(vars[length - 1] + "[" + vars[length - 1].getValue() + "]");
		}
		return params.toString();
	}
}
