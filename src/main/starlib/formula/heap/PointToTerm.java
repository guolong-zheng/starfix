package starlib.formula.heap;

import java.util.HashMap;
import java.util.Map;

import repair.heap.ExistVariable;
import repair.heap.Heap;
import repair.heap.HeapNode;
import starlib.StarVisitor;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Utilities;
import starlib.formula.Variable;

// x -> Node(...) term

public class PointToTerm extends HeapTerm {
	
	// type of pointed node, e.g. Node
	private String type;
	
	// variables of current PointToTerm,
	// first var is the root node
	// other var is the fields of pointed node
	// private Variable[] vars;
	
	public PointToTerm(String type, Variable... vars) {
		this.type = type;
		this.vars = vars;
	}
	
	public String getType() {
		return type;
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
		
		PointToTerm newPointToTerm = new PointToTerm(type, newVars);

		return newPointToTerm;
	}

	@Override
	public HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
							   Map<String, String> existVarSubMap, Heap heap) {
		int length = vars.length;

		Variable[] newVars = new Variable[length];
		Variable[] concreteVars = heap.getNode(toVars[0].getName()).toVarArry();

		for (int i = 0; i < length; i++) {
			Variable oldVar = vars[i];

			int index = Utilities.find(fromVars, oldVar);

			if (index != -1) {
				newVars[i] = new Variable(toVars[index]);
			} else if (existVarSubMap == null) {
				newVars[i] = oldVar;
			} else {
				if (existVarSubMap.containsKey(oldVar.getName())) {
					newVars[i] = new ExistVariable(existVarSubMap.get(oldVar.getName()), oldVar.getType());
				} else {
					Variable freshVar = concreteVars[i];
					existVarSubMap.put(oldVar.getName(), freshVar.getName());
					newVars[i] = new ExistVariable(freshVar);
				}
			}
		}

		PointToTerm newPointToTerm = new PointToTerm(type, newVars);

		return newPointToTerm;
	}
	
	@Override
	public HeapTerm copy() {
		Variable[] copyVars = new Variable[vars.length];
		for (int i = 0; i < vars.length; i++) {
			copyVars[i] = new Variable(vars[i].getName(), vars[i].getType());
		}
		
		String copyType = type;
		
		return new PointToTerm(copyType, copyVars);
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		DataNode dn = DataNodeMap.find(type);
		Variable[] fields = dn.getFields();
		
		for (int i = 0; i < vars.length; i++) {
			if (i == 0) {
				vars[i].setType(type);
			} else {
				vars[i].setType(fields[i - 1].getType());
			}
			String newType =  i == 0 ? Utilities.toBinaryType(type) : Utilities.toBinaryType(fields[i - 1].getType());
			vars[i].setType(newType);
			knownTypeVars.put(vars[i].getName(), newType);
		}
	}
	
	@Override
	public String toString() {
		return vars[0] + "->" + type + "(" + getParams(1) + ")";
	}
	
	public String toS2SATString() {
		return vars[0] + "::" + type + "<" + getParams(1) + ">";
	}
	
	@Override
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

	public int compare(PointToTerm pt) {
		Variable[] compVars = pt.getVars();
		for (int i = 1; i < vars.length; i++) {
			if (!vars[i].equals(compVars[i])) {
				return i;
			}
		}
		return -1;
	}
	
}
