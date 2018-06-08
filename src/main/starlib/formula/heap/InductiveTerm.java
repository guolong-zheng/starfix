package starlib.formula.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import repair.heap.Heap;
import repair.heap.HeapNode;
import starlib.formula.Formula;
import starlib.formula.Utilities;
import starlib.formula.Variable;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

// x::sll(), ... term

public class InductiveTerm extends HeapTerm {
	
	// name of predicate: sll
	private String predName;
	
	// contains unfolded formulas for this term
	private Formula[] unfoldedFormulas;
	
	public InductiveTerm(String predName, Variable... vars) {
		this.predName = predName;
		this.vars = vars;
	}
	
	public String getPredName() {
		return predName;
	}
	
	public Formula[] getUnfoldedFormulas() {
		return unfoldedFormulas;
	}
	
	public void setUnfoldedFormulas(Formula[] ufs) {
		unfoldedFormulas = ufs;
	}
	
	// very imporant function
	// if we have x::sll()
	// and predicate root::sll() === root = null \/ root->Node(next) * next::sll()
	// unfold should returns x = null \/ x->Node(next) * next::sll()
	public Formula[] unfold() {
		if (unfoldedFormulas == null) {
			InductivePred pred = InductivePredMap.find(predName);
			
			Formula[] formulas = pred.getFormulas();
			Variable[] params = pred.getParams();
			
			int length = formulas.length;
			Formula[] newFormulas = new Formula[length];
			
			// besides parameters, formula may contain other existential variables
			// must guarantee all existential variables substitue to fresh variables
			// the same old variable should be substituted to the same new variables
			// should this map be here or in Formula???
			Map<String,String> existVarSubMap = new HashMap<String,String>();
			
			for (int i = 0; i < length; i++) {
				// substitute the parameters inside the predicate with current vars
				newFormulas[i] = formulas[i].substitute(params, vars, existVarSubMap);
			}
			
			unfoldedFormulas = newFormulas;
		}
		
		return unfoldedFormulas;
	}
	
	@Override
	public HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		int length = vars.length;
		
		Variable[] newVars = new Variable[length];
		
		for (int i = 0; i < length; i++) {
			Variable oldVar = vars[i];
			
			int index = Utilities.find(fromVars, oldVar);
			
			if (index != -1) {
				newVars[i] = new Variable(toVars[index]);
			} else if (existVarSubMap == null) {
				newVars[i] = oldVar;
			} else {
				if (existVarSubMap.containsKey(oldVar.getName())) {
					newVars[i] = new Variable(existVarSubMap.get(oldVar.getName()), oldVar.getType());
				} else {
					Variable freshVar = Utilities.freshVar(oldVar);
					existVarSubMap.put(oldVar.getName(), freshVar.getName());
					newVars[i] = new Variable(freshVar);
				}
			}
		}
		
		InductiveTerm newInductiveTerm = new InductiveTerm(predName, newVars);
		
		return newInductiveTerm;
	}

	@Override
	public HeapTerm copy() {
		Variable[] copyVars = new Variable[vars.length];
		for (int i = 0; i < vars.length; i++) {
			copyVars[i] = new Variable(vars[i].getName(), vars[i].getType());
		}
		
		String copyPredName = predName;
		
		Formula[] copyUnfoldedFormulas = null;
		
		if (unfoldedFormulas != null) {
			copyUnfoldedFormulas = new Formula[unfoldedFormulas.length];
		
			for (int i = 0; i < unfoldedFormulas.length; i++) {
				copyUnfoldedFormulas[i] = unfoldedFormulas[i].copy();
			}
		}
		
		InductiveTerm copyTerm = new InductiveTerm(copyPredName, copyVars);
		copyTerm.setUnfoldedFormulas(copyUnfoldedFormulas);
		
		return copyTerm;
	}
	
	@Override
	public String toString() {
		return predName + "(" + getParams(0) + ")";
	}
	
	public String toS2SATString() {
		return vars[0] + "::" + predName + "<" + getParams(1) + ">";
	}
	
}
