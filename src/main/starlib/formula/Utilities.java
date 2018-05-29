package starlib.formula;

import java.util.Set;

import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;

public class Utilities {
	
	private static int count = 1;
	
	public static int find(Variable[] vars, Variable var) {
		int length = vars.length;
		
		for (int i = 0; i < length; i++) {
			if (vars[i].equals(var)) {
				return i;
			}
		}
		
		return -1;
	}
//	
//	public static boolean contains(List<Variable> vars, Variable var) {
//		int length = vars.size();
//		
//		for (int i = 0; i < length; i++) {
//			if (vars.get(i).getName().equals(var.getName()) &&
//					vars.get(i).getType().equals(var.getType()))
//				return true;
//		}
//		
//		return false;
//	}
	
	// may have problem, but may be it is enough with Java name convention
	public static Variable freshVar(Variable oldVar) {
		String oldName = oldVar.getName();
		if (oldName.equals("_")) return oldVar;
		
		if (oldName.matches(".*_\\d+")) {
			oldName = oldName.substring(0, oldName.lastIndexOf('_'));
		}
		
		String freshName = freshName(oldName);
		
		return new Variable(freshName, oldVar.getType());
	}
	
	private static String freshName(String oldName) {
		return oldName + "_" + count++;
	}
	
	public static void reset() {
		count = 1;
	}
	
	public static boolean isNull(Formula pc, Variable var) {
		return isNull(pc,var.getName());
	}
	
	public static boolean isNull(Formula pc, String varName) {
		Set<String> alias = pc.getAlias("null");

		if(alias == null) {
			return false;
		} 
		
		return alias.contains(varName);
	}
	
	/*
	public static boolean isNull(Formula pc, String varName) {
		PureFormula pf = pc.getPureFormula();
		List<List<Variable>> alias = pc.getAlias();

		for (PureTerm term : pf.getPureTerms()) {
			if (term instanceof EqNullTerm) {
				EqNullTerm eqNullTerm = (EqNullTerm) term;
				Variable root = eqNullTerm.getVar();
				String rootName = root.getName();

				if (rootName.equals(varName)) {
					return true;
				} else {
					for (List<Variable> vars : alias) {
						if (vars.contains(root)) {
							for (Variable var : vars) {
								if (var.getName().equals(varName)) {
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}
	//*/
	
	//*
	public static HeapTerm findHeapTerm(Formula pc, String varName) {
		HeapFormula hf = pc.getHeapFormula();
		Set<String> alias = pc.getAlias(varName);

		for (HeapTerm term : hf.getHeapTerms()) {
			Variable root = null;
			
			if (term instanceof PointToTerm) {
				PointToTerm ptTerm = (PointToTerm) term;
				root = ptTerm.getRoot();
			} else if (term instanceof InductiveTerm) {
				InductiveTerm itTerm = (InductiveTerm) term;
				root = itTerm.getRoot();
			}
			
			String rootName = root.getName();

			if (rootName.equals(varName)) {
				return term;
			} else {
				if(alias != null && alias.contains(rootName)) {
					return term;
				}
			}
		}

		return null;
	}
	
	public static HeapTerm findHeapTermNoRoot(Formula pc, String varName) {
		HeapFormula hf = pc.getHeapFormula();
		Set<String> alias = pc.getAlias(varName);

		for (HeapTerm term : hf.getHeapTerms()) {			
			if (term instanceof PointToTerm) {
				PointToTerm ptTerm = (PointToTerm) term;
				Variable root = ptTerm.getRoot();
				
				String rootName = root.getName();

				if (rootName.equals(varName)) {
					return term;
				} else {
					if(alias != null && alias.contains(rootName)) {
						return term;
					}
				}
			} else if (term instanceof InductiveTerm) {
				InductiveTerm itTerm = (InductiveTerm) term;
				
				for (Variable root : itTerm.getVars()) {
					String rootName = root.getName();

					if (rootName.equals(varName)) {
						return term;
					} else {
						if(alias != null && alias.contains(rootName)) {
							return term;
						}
					}
				}
			}
		}

		return null;
	}
	//*/
	
	
	public static String toBinaryType(String type) {
		type = type.replaceAll("__", "\\$");
		type = type.replace("_", ".");
		
		return type;
	}	
	
	/*
	public static HeapTerm findHeapTerm(Formula pc, String varName) {
		HeapFormula hf = pc.getHeapFormula();
		List<List<Variable>> alias = pc.getAlias();

		for (HeapTerm term : hf.getHeapTerms()) {
			Variable root = null;
			
			if (term instanceof PointToTerm) {
				PointToTerm ptTerm = (PointToTerm) term;
				root = ptTerm.getRoot();
			} else if (term instanceof InductiveTerm) {
				InductiveTerm itTerm = (InductiveTerm) term;
				root = itTerm.getRoot();
			}
			
			String rootName = root.getName();

			if (rootName.equals(varName)) {
				return term;
			} else {
				for (List<Variable> vars : alias) {
					if (vars.contains(root)) {
						for (Variable var : vars) {
							if (var.getName().equals(varName)) {
								return term;
							}
						}
					}
				}
			}
		}

		return null;
	}
	//*/
}
