package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import repair.heap.State;
import starlib.StarVisitor;
import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;

public class ComparisonTerm extends PureTerm {
	
	private Comparator comp;
	
	private Expression exp1;
	
	private Expression exp2;
	
	public ComparisonTerm(Comparator comp, Expression exp1, Expression exp2) {
		this.comp = comp;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public Comparator getComparator() {
		return comp;
	}
	
	public Expression getExp1() {
		return exp1;
	}
	
	public Expression getExp2() {
		return exp2;
	}
	
	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Expression newExp1 = exp1.substitute(fromVars, toVars, existVarSubMap);
		Expression newExp2 = exp2.substitute(fromVars, toVars, existVarSubMap);
		
		ComparisonTerm newTerm = new ComparisonTerm(comp, newExp1, newExp2);

		return newTerm;
	}

	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
							   Map<String, Variable> existVarSubMap, State state) {
		Expression newExp1 = exp1.substitute(fromVars, toVars, existVarSubMap, state);
		Expression newExp2 = exp2.substitute(fromVars, toVars, existVarSubMap, state);

		ComparisonTerm newTerm = new ComparisonTerm(comp, newExp1, newExp2);

		return newTerm;
	}
	
	@Override
	public PureTerm copy() {
		Expression copyExp1 = exp1.copy();
		Expression copyExp2 = exp2.copy();
		
		return new ComparisonTerm(comp, copyExp1, copyExp2);
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		if(exp1 instanceof Variable && exp2 instanceof Variable) {
			// TODO: Handle polymorphism. At the moment, we assume that both
			// LHS and RHS have the same type
			String name1 = exp1.toString();
			String name2 = exp2.toString();
			String type1 = knownTypeVars.get(name1);
			String type2 = knownTypeVars.get(name2);
			String type = type1 != null? type1: type2;
			if(type != null) {
				// TODO: becareful when handling polymorphism
				((Variable)exp1).setType(type);
				((Variable)exp2).setType(type);
				if(type1 == null) 
					knownTypeVars.put(name1, type);
				if(type2 == null) 
					knownTypeVars.put(name2, type);
			}
		}
		exp1.updateType(knownTypeVars);
		exp2.updateType(knownTypeVars);
	}
	
	
	@Override
	public String toString() {
		if(exp1 == null) {
			System.out.println("exp1 is null");
		}
		if(exp2 == null) {
			System.out.println("exp2 is null");
		}
		return exp1.toString() + comp.toString() + exp2.toString();
	}
	
	@Override
	public String toS2SATString() {
		if(exp1 == null) {
			System.out.println("exp1 is null");
		}
		if(exp2 == null) {
			System.out.println("exp2 is null");
		}
		return exp1.toString() + comp.toS2SATString() + exp2.toString();
	}

	@Override
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

	//TODO: only consider == and != of string values
	@Override
	public boolean evaluate() {
		String leftValue = exp1.evaluate();
		String rightValue = exp2.evaluate();
		switch (comp) {
			case EQ:
				return leftValue.equals(rightValue);
			case NE:
				return !leftValue.equals(rightValue);
			default:
				return false;
		}
	}
}
