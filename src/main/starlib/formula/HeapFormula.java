package starlib.formula;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.heap.HeapTerm;

// a heap formula includes multiple heap terms

public class HeapFormula {
	
	// contains array of heap term, empty mean the heap is empty
	private HeapTerm[] heapTerms;
	
	public HeapFormula(HeapTerm... heapTerms) {
		this.heapTerms = heapTerms;
	}
	
	public HeapTerm[] getHeapTerms() {
		return heapTerms;
	}
	
	public HeapFormula substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		int length = heapTerms.length;
		HeapTerm[] newHeapTerms = new HeapTerm[length];
		
		for (int i = 0; i < length; i++) {
			newHeapTerms[i] = heapTerms[i].substitute(fromVars, toVars, existVarSubMap);
		}
		
		HeapFormula newHeapFormula = new HeapFormula(newHeapTerms);
		
		return newHeapFormula;
	}
	
	public HeapFormula copy() {
		int length = heapTerms.length;
		HeapTerm[] newHeapTerms = new HeapTerm[length];
		
		for (int i = 0; i < length; i++) {
			newHeapTerms[i] = heapTerms[i].copy();
		}
		
		HeapFormula newHeapFormula = new HeapFormula(newHeapTerms);
		return newHeapFormula;
	}
	
	public void addTerm(HeapTerm term) {
		int length = heapTerms.length + 1;
		HeapTerm[] newHeapTerms = new HeapTerm[length];
		
		for (int i = 0; i < length - 1; i++) {
			newHeapTerms[i] = heapTerms[i];
		}
		
		newHeapTerms[length - 1] = term;
		heapTerms = newHeapTerms;
	}
	
	public void updateType(HashMap<String,String> knownTypeVars) {
		int oldLength = knownTypeVars.size();
		
		while (true) {
			int length = heapTerms.length;
			
			for (int i = 0; i < length; i++) {
				heapTerms[i].updateType(knownTypeVars);
			}
			
			int newLength = knownTypeVars.size();
			
			if (newLength == oldLength) break;
			else oldLength = newLength;
		}
	}
	
	@Override
	public String toString() {
		if (heapTerms.length == 0)
			return "emp";
		else {
			int length = heapTerms.length;
			StringBuilder ret = new StringBuilder();
			
			for (int i = 0; i < length - 1; i++) {
				ret.append(heapTerms[i] + " * ");
			}
			ret.append(heapTerms[length - 1]);
			
			return ret.toString();
		}
	}
	
	public String toS2SATString() {
		if (heapTerms.length == 0)
			return "emp";
		else {
			int length = heapTerms.length;
			StringBuilder ret = new StringBuilder();
			
			for (int i = 0; i < length - 1; i++) {
				ret.append(heapTerms[i].toS2SATString() + " * ");
			}
			ret.append(heapTerms[length - 1].toS2SATString());
			
			return ret.toString();
		}
	}
	
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

}
