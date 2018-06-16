package starlib.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import repair.heap.State;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;

public class Variable extends Expression{
	
	private String name;
	
	private String type;
	
	private Set<Variable> lazyGetVars;

	public Variable(String name) {
		this.name = name;
		this.type = ""; // unknown type
		this.lazyGetVars = new HashSet<Variable>();
		this.lazyGetVars.add(this);
	}
	
	public Variable(String name, String type) {
		this.name = name;
		this.type = type;
		this.lazyGetVars = new HashSet<Variable>();
		this.lazyGetVars.add(this);
	}
	
	public Variable(Variable var) {
		this.name = var.getName();
		this.type = var.getType();
		this.lazyGetVars = new HashSet<Variable>();
		this.lazyGetVars.add(this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean hasType() {
		return type != null && type.length() > 0;
	}
	
	public boolean isPrim() {
		if (type.equals("boolean") || type.equals("byte") || type.equals("char") ||
				type.equals("double") || type.equals("float") || type.equals("int") ||
				type.equals("long") || type.equals("short"))
			return true;
		else
			return false;
	}
	
	
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		else if (!(other instanceof Variable)) {
			return false;
		} else {
			Variable otherVar = (Variable) other;
			/*
			if (type.equals("") || otherVar.getType().equals("")) {
				return name.equals(otherVar.getName());
			} else {
				return name.equals(otherVar.getName()) &&
						type.equals(otherVar.getType());
			}
			//*/
			// Sang: I don't think checking type is necessary
			// It is not possible to have two variables with the same name
			return name.equals(otherVar.getName());
		}
	}
	
	@Override 
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public Set<Variable> getVars() {
		return lazyGetVars;
	}
	
	@Override
	public List<Variable> getVarsList() {
		List<Variable> vars = new ArrayList<Variable>(lazyGetVars);
		return vars;
	}

	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars, Map<String, String> existVarSubMap) {
		
		int index = Utilities.find(fromVars, this);
		Expression newVar = NullExpression.getInstance();

		if (index != -1) {
			newVar = new Variable(toVars[index]);
		} else if (existVarSubMap == null) {
			newVar = this;
		} else {
			if (existVarSubMap.containsKey(name)) {
				newVar = new Variable(existVarSubMap.get(name), type);
			} else {
				newVar = Utilities.freshVar(this);
				existVarSubMap.put(name, newVar.toString());
			}
		}

		return newVar;
	}

	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars, Map<String, Variable> existVarSubMap, State state) {

		int index = Utilities.find(fromVars, this);
		Expression newVar = NullExpression.getInstance();

		if (index != -1) {
			newVar = new Variable(toVars[index]);
		} else if (existVarSubMap == null) {
			newVar = this;
		} else {
			if (existVarSubMap.containsKey(name)) {
				newVar = existVarSubMap.get(name);
			} else {
				newVar = Utilities.freshVar(this);
				existVarSubMap.put(name, Utilities.freshVar(this));
			}
		}

		return newVar;
	}
	
	@Override
	public Expression copy() {
		return new Variable(name, type);
	}

	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		String type = knownTypeVars.get(name);
		if (type != null) {
			this.type = type;
		}
	}

	public String evaluate() {
		return name;
	}
}
