package starlib.formula.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import starlib.formula.Variable;

public abstract class Expression {
	
	protected static final Set<Variable> EMPTY_SET = new HashSet<Variable>();
	
	protected static final List<Variable> EMPTY_LIST = new ArrayList<Variable>();
	
	public abstract Set<Variable> getVars();
	
	public abstract List<Variable> getVarsList();
	
	public abstract Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
	
	public abstract Expression copy();
	
	public abstract void updateType(HashMap<String, String> knownTypeVars);

	public abstract String evaluate();

}
