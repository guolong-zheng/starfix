package starlib.formula.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import starlib.formula.Utilities;
import starlib.formula.Variable;

@Deprecated
public class VariableExpression extends Expression {
	
	private Variable var;
	
	public VariableExpression(String name) {
		this.var = new Variable(name);
	}
	
	public VariableExpression(Variable var) {
		this.var = var;
	}
	
	public Variable getVar() {
		return var;
	}
	
	public Set<Variable> getVars() {
		Set<Variable> vars = new HashSet<Variable>();
		vars.add(var);
		
		return vars;
	}
	
	public List<Variable> getVarsList() {
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(var);
		
		return vars;
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
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
		
		VariableExpression newExpr = new VariableExpression(newVar);
		return newExpr;
	}
	
	@Override
	public Expression copy() {
		return new VariableExpression((Variable) var.copy());
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		//TODO: Refactor, since this is cloned from EqNullTerm
		String type = knownTypeVars.get(var.getName());
		if(type != null) {
			var.setType(type);
		}
	}

	@Override
	public String toString() {
		return var.toString();
	}

	@Override
	public String evaluate() {
		return null;
	}
}
