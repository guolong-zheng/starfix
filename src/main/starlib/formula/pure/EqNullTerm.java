package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Utilities;
import starlib.formula.Variable;

// x = null term

@Deprecated
public class EqNullTerm extends PureTerm {
	
	private Variable var;
	
	public EqNullTerm(Variable var) {
		this.var = var;
	}
	
	public Variable getVar() {
		return var;
	}
	
	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Variable oldVar = var;
		
		int index = Utilities.find(fromVars, oldVar);
		
		Variable newVar = null;
		
		if (index != -1) {
			newVar = new Variable(toVars[index]);
		} else if (existVarSubMap == null) {
			newVar = oldVar;
		} else {
			if (existVarSubMap.containsKey(oldVar.getName())) {
				newVar = new Variable(existVarSubMap.get(oldVar.getName()), oldVar.getType());
			} else {
				Variable freshVar = Utilities.freshVar(oldVar);
				existVarSubMap.put(oldVar.getName(), freshVar.getName());
				newVar = new Variable(freshVar);
			}
		}
		
		EqNullTerm newEqNullTerm = new EqNullTerm(newVar);
		return newEqNullTerm;
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		String type = knownTypeVars.get(var.getName());
		if(type != null) {
			var.setType(type);
		}
	}
	
	@Override
	public String toString() {
		return var.toString() + " = null";
	}

	@Override
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

}
