package starlib.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import starlib.StarVisitor;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;

// a pure formula includes multiple pure terms

public class PureFormula {
	
	// contains array of pure terms, empty means true
	private PureTerm[] pureTerms;
	
	private Map<String,Set<String>> aliasMap;
	
	
	public PureFormula(PureTerm... pureTerms) {
		this.pureTerms = pureTerms;
				
		this.aliasMap = new HashMap<String,Set<String>>();
		
		for (PureTerm term : pureTerms) {

			if(term instanceof ComparisonTerm) {
				updateAlias((ComparisonTerm) term);
			}
		}
	}
	
	public PureTerm[] getPureTerms() {
		return pureTerms;
	}
	
	public void setPureTerms(PureTerm[] pureTerms) {
		this.pureTerms = pureTerms;
	}
	
	public Map<String,Set<String>> getAliasMap(){
		return aliasMap;
	}
	
	public void setAliasMap(Map<String,Set<String>> aliasMap) {
		this.aliasMap = aliasMap;
	}
	
	/*
	 * This method replaced the old updateAlias for deprecated EqTerm
	 */
	public void updateAlias(ComparisonTerm term) {
		Expression exp1 = term.getExp1();
		Expression exp2 = term.getExp2();

		if(term.getComparator() != Comparator.EQ)
			return;
		if(!(exp1 instanceof Variable))
			return;
		if(!(exp2 instanceof Variable || exp2 instanceof NullExpression)) 
			return;

		String lhs = exp1.toString();
		String rhs = exp2.toString();
		Set<String> alias1 = aliasMap.get(lhs);
		Set<String> alias2 = aliasMap.get(rhs);
		if(alias1 != null) {
			// group everything to alias1
			if(alias2 != null) {
				// union the two sets of aliases
				alias1.addAll(alias2);
			} else {
				// just add the new one
				alias1.add(rhs);
			}
			aliasMap.put(rhs, alias1);
		} else {
			// group everything to alias2
			if(alias2 != null) {
				alias2.add(lhs);
			} else {
				// both lhs and rhs are new
				alias2 = new HashSet<String>();
				alias2.add(lhs);
				alias2.add(rhs);
				aliasMap.put(rhs, alias2);
			}
			aliasMap.put(lhs, alias2);
		}
	}
	
	public void printAliasMap() {
		System.out.println(">>>>> Start alias map");
		for(Set<String> alias : aliasMap.values()) {
			System.out.println("\n----------------------------------------");
			for(String var : alias)
				System.out.print(var + " ");
		}
		System.out.println("\n>>>>> End alias map");
	}
	
	public PureFormula substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		int length = pureTerms.length;
		PureTerm[] newPureTerms = new PureTerm[length];
		
		for (int i = 0; i < length; i++) {
			newPureTerms[i] = pureTerms[i].substitute(fromVars, toVars, existVarSubMap);
		}
		
		PureFormula newPureFormula = new PureFormula(newPureTerms);
		
		return newPureFormula;
	}
	
	public PureFormula copy() {
		int length = pureTerms.length;
		PureTerm[] newPureTerms = new PureTerm[length];
		
		for (int i = 0; i < length; i++) {
			newPureTerms[i] = pureTerms[i].copy();
		}
		
		PureFormula newPureFormula = new PureFormula(newPureTerms);
		return newPureFormula;
	}
	
//	public PureFormula copyWithAliasMap() {
//		int length = pureTerms.length;
//		PureTerm[] newPureTerms = new PureTerm[length];
//		
//		for (int i = 0; i < length; i++) {
//			newPureTerms[i] = pureTerms[i].copy();
//		}
//		
//		PureFormula newPureFormula = new PureFormula();
//		newPureFormula.setPureTerms(newPureTerms);
//		
//		Map<String, Set<String>> newAliasMap = new HashMap<String, Set<String>>();
//		for (Entry<String, Set<String>> entry : aliasMap.entrySet()) {
//			String var = entry.getKey();
//			Set<String> aliasVars = entry.getValue();
//			
//			newAliasMap.put(var, new HashSet<String>(aliasVars));
//		}
//		newPureFormula.setAliasMap(newAliasMap);
//		
//		return newPureFormula;
//	}
	
	public void addTerm(PureTerm term) {
		int length = pureTerms.length + 1;
		PureTerm[] newPureTerms = new PureTerm[length];
		
		for (int i = 0; i < length - 1; i++) {
			newPureTerms[i] = pureTerms[i];
		}
		
		newPureTerms[length - 1] = term;
		pureTerms = newPureTerms;
		
		if(term instanceof ComparisonTerm) {
			updateAlias((ComparisonTerm) term);
		}
	}
	
	public void updateType(HashMap<String,String> knownTypeVars) {
		int oldLength = knownTypeVars.size();
		
		while (true) {
			int length = pureTerms.length;
			
			for (int i = 0; i < length; i++) {
				pureTerms[i].updateType(knownTypeVars);
			}
			
			int newLength = knownTypeVars.size();
			
			if (newLength == oldLength) break;
			else oldLength = newLength;
		}
	}
	
	// remove all primitive terms because we have sat assignment later
	// we also remove terms without type
	public void removeTerm() {
		List<PureTerm> tmp = new ArrayList<PureTerm>();
		
		for (PureTerm pt : pureTerms) {
			if(pt instanceof ComparisonTerm) {
				Expression exp1 = ((ComparisonTerm) pt).getExp1();
				if(exp1 instanceof Variable) {
					Variable var = (Variable)exp1;
					if(var.hasType() && !var.isPrim()) {
						tmp.add(pt);
					}
				}
			}
		}
		
		int length = tmp.size();
		PureTerm[] newPureTerms = new PureTerm[length];
		tmp.toArray(newPureTerms);
		
		pureTerms = newPureTerms;
	}
	
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		if (pureTerms.length == 0)
			return "true";
		else {
			int length = pureTerms.length;
			StringBuilder ret = new StringBuilder();
			
			for (int i = 0; i < length - 1; i++) {
				ret.append(pureTerms[i] + " & ");
			}
			
			ret.append(pureTerms[length - 1]);
			
			return ret.toString();
		}
	}
	
	public String toS2SATString() {
		if (pureTerms.length == 0)
			return "true";
		else {
			int length = pureTerms.length;
			StringBuilder ret = new StringBuilder();
			
			for (int i = 0; i < length - 1; i++) {
				ret.append(pureTerms[i].toS2SATString() + " & ");
			}
			ret.append(pureTerms[length - 1].toS2SATString());
			
			return ret.toString();
		}
	}
	
}
