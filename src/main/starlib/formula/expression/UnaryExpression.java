package starlib.formula.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import repair.heap.State;
import starlib.formula.Variable;

public class UnaryExpression extends Expression {
	
	private Operator op;
	
	private Expression exp;
	
	public UnaryExpression(Operator op, Expression exp) {
		this.op = op;
		this.exp = exp;
	}
	
	@Override
	public Set<Variable> getVars() {
		return exp.getVars();
	}
	
	@Override
	public List<Variable> getVarsList() {
		return exp.getVarsList();
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Expression newExp = exp.substitute(fromVars, toVars, existVarSubMap);
		UnaryExpression newUnaryExp = new UnaryExpression(op, newExp);

		return newUnaryExp;
	}

	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
								 Map<String, Variable> existVarSubMap, State state) {
		Expression newExp = exp.substitute(fromVars, toVars, existVarSubMap, state);
		UnaryExpression newUnaryExp = new UnaryExpression(op, newExp);

		return newUnaryExp;
	}
	
	@Override
	public Expression copy() {
		Expression copyExp = exp.copy();
		UnaryExpression copyUnaryExp = new UnaryExpression(op, copyExp);
		
		return copyUnaryExp;
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		exp.updateType(knownTypeVars);
	}
	
	@Override
	public String toString() {
		return "(" + op.toString() + exp.toString() + ")";
	}

	@Override
	public String evaluate() {
		return op.toString() + exp.evaluate();
	}
}
