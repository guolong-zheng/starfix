package starlib.formula.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import repair.heap.State;
import starlib.formula.Variable;

public class LiteralExpression extends Expression {
	
	private String value;
	
	public LiteralExpression(boolean value) {
		if (value)
			this.value = "1";
		else
			this.value = "0";
	}
		
	public LiteralExpression(int value) {
		this.value = Integer.toString(value);
	}
	
	public LiteralExpression(long value) {
		this.value = Long.toString(value);
	}
	
	public LiteralExpression(double value) {
		this.value = Double.toString(value);
	}
	
	public LiteralExpression(String value) {
		if (value.equals("true"))
			this.value = "1";
		else if (value.equals("false"))
			this.value = "0";
		else
			this.value = value;
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		return this;
	}

	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
								 Map<String, Variable> existVarSubMap, State state) {
		return this;
	}
	
	@Override
	public Expression copy() {
		return this;
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public Set<Variable> getVars() {
		// return empty list
		return EMPTY_SET;
	}
	
	@Override
	public List<Variable> getVarsList() {
		// return empty list
		return EMPTY_LIST;
	}

	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		// Do nothing
	}

	@Override
	public String evaluate() {
		return value;
	}
}
