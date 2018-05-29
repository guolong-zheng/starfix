package starlib.formula.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import starlib.formula.Variable;

public class NullExpression extends Expression{

	private static final NullExpression INSTANCE = new NullExpression();
	
	private NullExpression(){}
	
	public static NullExpression getInstance() {
		return INSTANCE;
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
	public Expression substitute(Variable[] fromVars, Variable[] toVars, Map<String, String> existVarSubMap) {
		return INSTANCE;
	}
	
	@Override
	public Expression copy() {
		return INSTANCE;
	}

	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		// Do nothing
	}
	
	@Override
	public String toString() {
		return "null";
	}
	
	@Override 
	public int hashCode() {
		return "null".hashCode();
	}

}
