package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;

public abstract class PureTerm {
	
	public abstract PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
	
	public PureTerm copy() {
		return this;
	}
	
	public void updateType(HashMap<String, String> knownTypeVars) {
		return;
	}

	public abstract void accept(StarVisitor visitor);
	
	public String toS2SATString() {
		return this.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		else if (!(other instanceof PureTerm))
			return false;
		else
			return this.toString().equals(other.toString());
	}
	
}
