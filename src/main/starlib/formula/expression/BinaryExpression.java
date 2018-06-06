package starlib.formula.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import starlib.formula.Variable;

public class BinaryExpression extends Expression {

	private Operator op;
	
	private Expression exp1;
	
	private Expression exp2;
	
	public BinaryExpression(Operator op, Expression exp1, Expression exp2) {
		this.op = op;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	@Override
	public Set<Variable> getVars() {
		Set<Variable> vars = new HashSet<Variable>();
		
		Set<Variable> vars1 = exp1.getVars();
		Set<Variable> vars2 = exp2.getVars();
		
		vars.addAll(vars1);
		vars.addAll(vars2);
		
		return vars;
	}
	
	@Override
	public List<Variable> getVarsList() {
		List<Variable> vars = new ArrayList<Variable>();
		
		List<Variable> vars1 = exp1.getVarsList();
		List<Variable> vars2 = exp2.getVarsList();
		
		vars.addAll(vars1);
		vars.addAll(vars2);
		
		return vars;
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Expression newExp1 = exp1.substitute(fromVars, toVars, existVarSubMap);
		Expression newExp2 = exp2.substitute(fromVars, toVars, existVarSubMap);
		BinaryExpression newBinaryExp = new BinaryExpression(op, newExp1, newExp2);
		
		return newBinaryExp;
	}
	
	@Override
	public Expression copy() {
		Expression copyExp1 = exp1.copy();
		Expression copyExp2 = exp2.copy();
		BinaryExpression copyBinaryExp = new BinaryExpression(op, copyExp1, copyExp2);
		
		return copyBinaryExp;
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		exp1.updateType(knownTypeVars);
		exp2.updateType(knownTypeVars);
	}
	
	@Override
	public String toString() {
		return "(" + exp1.toString() + op.toString() + exp2.toString() + ")";
	}

	public String evaluate() {
		return exp1.toString() + op.toString() + exp2.toString();
	}
}
