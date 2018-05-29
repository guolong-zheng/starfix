package starlib.data;

import starlib.formula.Variable;

public class DataNode {
	
	private String type;
	
	private Variable[] fields;
	
	public DataNode(String type, Variable[] fields) {
		this.type = type;
		this.fields = fields;
	}
	
	public String getType() {
		return type;
	}
	
	public Variable[] getFields() {
		return fields;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("data " + type + "{\n");
		
		for (int i = 0; i < fields.length; i++) {
			String fType = fields[i].getType();
			String fName = fields[i].getName();
			
			ret.append(fType + " " + fName + ";\n");
		}
		
		ret.append("}.");
		
		return ret.toString();
	}
	
	public String toS2SATString() {
		// it seems s2sat has object already
		if (type.equals("Object"))
			return "";
		else {
			StringBuilder ret = new StringBuilder();
			ret.append("data " + type + "{\n");
			
			for (int i = 0; i < fields.length; i++) {
				String fType = fields[i].getType();
				String fName = fields[i].getName();
				
				if (fType.equals("boolean"))
					fType = "int";
				
				ret.append(fType + " " + fName + ";\n");
			}
			
			ret.append("}.");
			
			return ret.toString();
		}
	}

}
