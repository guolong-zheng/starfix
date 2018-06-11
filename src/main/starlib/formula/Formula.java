package starlib.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import repair.heap.Heap;
import starlib.StarVisitor;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.EqNullTerm;
import starlib.formula.pure.EqTerm;
import starlib.formula.pure.NEqNullTerm;
import starlib.formula.pure.NEqTerm;
import starlib.formula.pure.PureTerm;

// a formula includes heap part and pure part

public class Formula {

	public HeapFormula heapFormula;

	public PureFormula pureFormula;
	
	private Map<String, List<Variable>> typeMap;
	
	private Map<String, Integer> addressMap;

	public int depth;
	
	public Formula(HeapFormula heapFormula, PureFormula pureFormula,
			Map<String, List<Variable>> typeMap, Map<String, Integer> addressMap, int depth) {
		this.heapFormula = heapFormula;
		this.pureFormula = pureFormula;
		this.typeMap = typeMap;
		this.addressMap = addressMap;
		this.depth = depth;
	}
	
	public Formula(HeapFormula heapFormula, PureFormula pureFormula,
			Map<String, List<Variable>> typeMap, Map<String, Integer> addressMap) {
		this.heapFormula = heapFormula;
		this.pureFormula = pureFormula;
		this.typeMap = typeMap;
		this.addressMap = addressMap;
		this.depth = 0;
	}
	
	public Formula(HeapFormula heapFormula, PureFormula pureFormula) {
		this.heapFormula = heapFormula;
		this.pureFormula = pureFormula;
		this.typeMap = new HashMap<String, List<Variable>>();
		this.addressMap = new HashMap<String, Integer>();
		this.depth = 0;
	}
	
	public Formula() {
		this.heapFormula = new HeapFormula();
		this.pureFormula = new PureFormula();
		this.typeMap = new HashMap<String, List<Variable>>();
		this.addressMap = new HashMap<String, Integer>();
		this.depth = 0;
	}
	
	public HeapFormula getHeapFormula() {
		return heapFormula;
	}
	
	public PureFormula getPureFormula() {
		return pureFormula;
	}
	
	public Map<String,Set<String>> getAliasMap(){
		return pureFormula.getAliasMap();
	}
	
	public void setDepth(int d) {
		this.depth = d;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public Set<String> getAlias(String name) {		
		return getAliasMap().get(name);
	}
	
//	public Formula rename(Variable var, Variable[] fields) {
//		HeapTerm ht = Utilities.findHeapTerm(this, var.getName());
//		
//		if (ht instanceof PointToTerm) {
//			Variable[] fromVars = ((PointToTerm) ht).getVarsNoRoot();
//			Variable[] toVars = new Variable[fromVars.length];
//			
//			for (int i = 0; i < fields.length; i++) {
//				toVars[i] = new Variable(var.getName() + "_" + fields[i].getName(), "");
//			}
//			
//			Formula newFormula = this.substitute(fromVars, toVars, null);
//			heapFormula = newFormula.getHeapFormula();
//			pureFormula = newFormula.getPureFormula();
//		}
//		
//		return this;
//	}
	
	// substitute parameters with new vars
	public Formula substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		HeapFormula newHeapFormula = heapFormula.substitute(fromVars, toVars, existVarSubMap);
		PureFormula newPureFormula = pureFormula.substitute(fromVars, toVars, existVarSubMap);
		
		Formula newFormula = new Formula(newHeapFormula, newPureFormula);

		return newFormula;
	}

	public Formula substitute(Variable[] fromVars, Variable[] toVars,
							  Map<String, String> existVarSubMap, Heap heap) {
		HeapFormula newHeapFormula = heapFormula.substitute(fromVars, toVars, existVarSubMap, heap);
		PureFormula newPureFormula = pureFormula.substitute(fromVars, toVars, existVarSubMap);

		Formula newFormula = new Formula(newHeapFormula, newPureFormula);

		return newFormula;
	}
	
	public Formula copy() {
		HeapFormula newHeapFormula = heapFormula.copy();
		PureFormula newPureFormula = pureFormula.copy();
		
		Map<String, List<Variable>> newTypeMap = new HashMap<String, List<Variable>>();
		for (Entry<String, List<Variable>> entry : typeMap.entrySet()) {
			String type = entry.getKey();
			List<Variable> vars = entry.getValue();
			
			newTypeMap.put(type, new ArrayList<Variable>(vars));
		}
		
		
		Map<String, Integer> newAddressMap = new HashMap<String, Integer>(addressMap);
		
		Formula newFormula = new Formula(newHeapFormula, newPureFormula, newTypeMap, newAddressMap, depth);
		return newFormula;
	}
	
//	public Formula copyWithAliasMap() {
//		HeapFormula newHeapFormula = heapFormula.copy();
//		PureFormula newPureFormula = pureFormula.copyWithAliasMap();
//		
//		Map<String, List<Variable>> newTypeMap = new HashMap<String, List<Variable>>();
//		for (Entry<String, List<Variable>> entry : typeMap.entrySet()) {
//			String type = entry.getKey();
//			List<Variable> vars = entry.getValue();
//			
//			newTypeMap.put(type, new ArrayList<Variable>(vars));
//		}
//		
//		Map<String, Integer> newAddressMap = new HashMap<String, Integer>(addressMap);
//		
//		Formula newFormula = new Formula(newHeapFormula, newPureFormula, newTypeMap, newAddressMap, depth);
//		return newFormula;
//	}
	
	public void unfold(InductiveTerm it, int index) {
		Formula[] unfoldedFormulas = it.getUnfoldedFormulas();
		Formula f = unfoldedFormulas[index];
		
		int heapSize = f.heapFormula.getHeapTerms().length + heapFormula.getHeapTerms().length - 1;
		int pureSize = f.pureFormula.getPureTerms().length + pureFormula.getPureTerms().length;
		
		HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
		PureTerm[] newPureTerms = new PureTerm[pureSize];
		
		int curr = 0;
		for (int i = 0; i < heapFormula.getHeapTerms().length; i++) {
			if (!heapFormula.getHeapTerms()[i].equals(it)) {
				newHeapTerms[curr] = heapFormula.getHeapTerms()[i];
				curr++;
			}
		}
		
		System.arraycopy(f.heapFormula.getHeapTerms(), 0,
				newHeapTerms, heapFormula.getHeapTerms().length - 1, f.heapFormula.getHeapTerms().length);
		
		System.arraycopy(pureFormula.getPureTerms(), 0,
				newPureTerms, 0, pureFormula.getPureTerms().length);
		System.arraycopy(f.pureFormula.getPureTerms(), 0,
				newPureTerms, pureFormula.getPureTerms().length, f.pureFormula.getPureTerms().length);
		
		heapFormula = new HeapFormula(newHeapTerms);
		pureFormula = new PureFormula(newPureTerms);
		depth++;
	}
	
//	public void unfoldWithAssign(InductiveTerm it, int index) {
//		Formula[] unfoldedFormulas = it.getUnfoldedFormulas();
//		Formula f = unfoldedFormulas[index];
//		
//		int heapSize = f.heapFormula.getHeapTerms().length + heapFormula.getHeapTerms().length - 1;
//		int pureSize = f.pureFormula.getPureTerms().length + pureFormula.getPureTerms().length;
//		
//		HeapTerm[] newHeapTerms = new HeapTerm[heapSize];
//		PureTerm[] newPureTerms = new PureTerm[pureSize];
//		
//		int curr = 0;
//		for (int i = 0; i < heapFormula.getHeapTerms().length; i++) {
//			if (!heapFormula.getHeapTerms()[i].equals(it)) {
//				newHeapTerms[curr] = heapFormula.getHeapTerms()[i];
//				curr++;
//			}
//		}
//		
//		System.arraycopy(f.heapFormula.getHeapTerms(), 0,
//				newHeapTerms, heapFormula.getHeapTerms().length - 1, f.heapFormula.getHeapTerms().length);
//		
//		System.arraycopy(f.pureFormula.getPureTerms(), 0,
//				newPureTerms, 0, f.pureFormula.getPureTerms().length);
//		System.arraycopy(pureFormula.getPureTerms(), 0,
//				newPureTerms, f.pureFormula.getPureTerms().length, pureFormula.getPureTerms().length);
//		
//		Map<String,Set<String>> oldAliasMap = pureFormula.getAliasMap();
//		
//		heapFormula = new HeapFormula(newHeapTerms);
//		pureFormula = new PureFormula(newPureTerms);
//		
////		pureFormula.getAliasMap().putAll(oldAliasMap);
//		
//		depth++;
//	}
	
	public void addPointToTerm(Variable[] vars, String type) {
		HeapTerm ht = new PointToTerm(type, vars);
		heapFormula.addTerm(ht);
	}
	
	public void addPointToTerm(Variable var, String type) {
		DataNode dn = DataNodeMap.find(type);
		Variable[] fields = dn.getFields();
		
		Variable[] vars = new Variable[fields.length + 1];
		
		for (int i = 0; i < vars.length; i++) {
			if (i == 0) vars[i] = new Variable(var.getName(), "");
			else vars[i] = Utilities.freshVar(fields[i - 1]);
		}
		
		HeapTerm ht = new PointToTerm(type, vars);
		heapFormula.addTerm(ht);
	}
	
	public void addPointToTermConcrete(Variable var, String type) {
		DataNode dn = DataNodeMap.find(type);
		if (dn == null) return;
		
		Variable[] fields = dn.getFields();
		
		Variable[] vars = new Variable[fields.length + 1];
		
		for (int i = 0; i < vars.length; i++) {
			if (i == 0) vars[i] = new Variable(var.getName(), "");
			else vars[i] = Utilities.freshVar(fields[i - 1]);
		}
		
		HeapTerm ht = new PointToTerm(type, vars);
		heapFormula.addTerm(ht);
	}
	
	public void addPointToTermMockUp(Variable var, String type) {
		DataNode dn = new DataNode(type, new Variable[0]);
		DataNodeMap.put(dn);
		
		Variable[] vars = new Variable[1];
		
		vars[0] = new Variable(var.getName(), "");
		
		HeapTerm ht = new PointToTerm(type, vars);
		heapFormula.addTerm(ht);
	}
	
	public void addComparisonTerm(Comparator comp, Expression exp1, Expression exp2) {
		PureTerm term = new ComparisonTerm(comp, exp1, exp2);
		pureFormula.addTerm(term);
	}
	
	public void putType(String type, Variable var) {
		List<Variable> vars = findType(type);
		if (vars.size() == 0) {
			vars.add(var);
			typeMap.put(type, vars);
		} else {
			vars.add(var);
		}
	}
	
	public void putAddress(String name, int address) {
		addressMap.put(name, address);
	}
	
	public List<Variable> findType(String type) {
		List<Variable> vars = typeMap.get(type);
		
		if (vars == null) vars = new ArrayList<Variable>();
		
		return vars;
	}
	
	public int findAddress(String name) {
		Integer address = addressMap.get(name);
		
		if (address == null) return -1;
		
		return address;
	}
	
	public int findAddress(Set<String> vars) {
		if (vars == null)
			return -1;
		
		for (String var : vars) {
			int address = findAddress(var);
			if (address != -1) return address;
		}
		
		return -1;
	}
	
	public void updateType(HashMap<String,String> knownTypeVars) {
		heapFormula.updateType(knownTypeVars);
		pureFormula.updateType(knownTypeVars);
	}
	
	public void removeTerm() {
		pureFormula.removeTerm();
	}
	
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}
	
	
	@Override
	public String toString() {
		String heapString = heapFormula.toString();
		String pureString = pureFormula.toString();
		
		if (heapString.equals("emp"))
			return pureString;
		else if (pureString.equals("true"))
			return heapString;
		else
			return heapString + " & " + pureString;
	}
	
	public String toS2SATString() {
		String heapString = heapFormula.toS2SATString();
		String pureString = pureFormula.toS2SATString(); 
		
		if (heapString.equals("emp"))
			return pureString;
		else if (pureString.equals("true"))
			return heapString;
		else
			return heapString + " & " + pureString;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		else if (!(other instanceof Formula))
			return false;
		else
			return this.toString().equals(other.toString());
	}
	
	@Override 
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	// Deprecated APIs, should use ComparisonTerms instead of those redundant Terms
	
	@Deprecated
	public void addEqNullTerm(Variable var) {
		PureTerm term = new EqNullTerm(var);
		pureFormula.addTerm(term);
	}
	
	@Deprecated
	public void addNEqNullTerm(Variable var) {
		PureTerm term = new NEqNullTerm(var);
		pureFormula.addTerm(term);
	}
	
	@Deprecated
	public void addEqTerm(Variable var1, Variable var2) {
		PureTerm term = new EqTerm(var1, var2);
		pureFormula.addTerm(term);
	}
	
	@Deprecated
	public void addNEqTerm(Variable var1, Variable var2) {
		PureTerm term = new NEqTerm(var1, var2);
		pureFormula.addTerm(term);
	}
}
