package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import repair.heap.State;
import starlib.StarVisitor;
import starlib.formula.Utilities;
import starlib.formula.Variable;

// x = y term

@Deprecated
public class EqTerm extends PureTerm {
	
	private Variable var1;
	
	private Variable var2;
	
	public EqTerm(Variable var1, Variable var2) {
		this.var1 = var1;
		this.var2 = var2;
	}
	
	public Variable getVar1() {
		return var1;
	}
	
	public Variable getVar2() {
		return var2;
	}
	
	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Variable oldVar1 = var1;
		Variable oldVar2 = var2;
		
		int index1 = Utilities.find(fromVars, oldVar1);
		int index2 = Utilities.find(fromVars, oldVar2);
		
		Variable newVar1 = null;
		Variable newVar2 = null;
		
		if (index1 != -1) {
			newVar1 = new Variable(toVars[index1]);
		} else if (existVarSubMap == null) {
			newVar1 = oldVar1;
		} else {
			if (existVarSubMap.containsKey(oldVar1.getName())) {
				newVar1 = new Variable(existVarSubMap.get(oldVar1.getName()), oldVar1.getType());
			} else {
				Variable freshVar = Utilities.freshVar(oldVar1);
				existVarSubMap.put(oldVar1.getName(), freshVar.getName());
				newVar1 = new Variable(freshVar);
			}
		}
		
		if (index2 != -1) {
			newVar2 = new Variable(toVars[index2]);
		} else if (existVarSubMap == null) {
			newVar2 = oldVar2;
		} else {
			if (existVarSubMap.containsKey(oldVar2.getName())) {
				newVar2 = new Variable(existVarSubMap.get(oldVar2.getName()), oldVar2.getType());
			} else {
				Variable freshVar = Utilities.freshVar(oldVar2);
				existVarSubMap.put(oldVar2.getName(), freshVar.getName());
				newVar2 = new Variable(freshVar);
			}
		}

		EqTerm newEqTerm = new EqTerm(newVar1, newVar2);
		return newEqTerm;
	}

	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
							   Map<String, Variable> existVarSubMap, State state) {
		Variable oldVar1 = var1;
		Variable oldVar2 = var2;

		int index1 = Utilities.find(fromVars, oldVar1);
		int index2 = Utilities.find(fromVars, oldVar2);

		Variable newVar1 = null;
		Variable newVar2 = null;

		if (index1 != -1) {
			newVar1 = new Variable(toVars[index1]);
		} else if (existVarSubMap == null) {
			newVar1 = oldVar1;
		} else {
			if (existVarSubMap.containsKey(oldVar1.getName())) {
				newVar1 = existVarSubMap.get(oldVar1.getName());
			} else {
				Variable freshVar = Utilities.freshVar(oldVar1);
				newVar1 = new Variable(freshVar);
				existVarSubMap.put(oldVar1.getName(), newVar1);
			}
		}

		if (index2 != -1) {
			newVar2 = new Variable(toVars[index2]);
		} else if (existVarSubMap == null) {
			newVar2 = oldVar2;
		} else {
			if (existVarSubMap.containsKey(oldVar2.getName())) {
				newVar2 = existVarSubMap.get(oldVar2.getName());
			} else {
				Variable freshVar = Utilities.freshVar(oldVar2);
				newVar2 = new Variable(freshVar);
				existVarSubMap.put(oldVar2.getName(), newVar2);
			}
		}

		EqTerm newEqTerm = new EqTerm(newVar1, newVar2);
		return newEqTerm;
	}
	
//	@Override
//	public PureTerm copy() {
//		return new EqTerm(var1, var2);
//	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		// TODO: Handle polymorphism. At the moment, we assume that both
		// LHS and RHS have the same type
		String type1 = knownTypeVars.get(var1.getName());
		String type2 = knownTypeVars.get(var2.getName());
		String type = type1 != null? type1: type2;
		if(type != null) {
			// TODO: becareful when handling polymorphism
			var1.setType(type);
			var2.setType(type);
			if(type1 == null) 
				knownTypeVars.put(var1.getName(), type);
			if(type2 == null) 
				knownTypeVars.put(var2.getName(), type);
		}
	}
	
	@Override
	public String toString() {
		return var1.toString() + " = " + var2.toString();
	}

	@Override
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean evaluate() {
		return var1.toString().equals(var2.toString());
	}
}
