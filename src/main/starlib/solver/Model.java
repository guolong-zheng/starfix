package starlib.solver;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import starlib.formula.Formula;
import starlib.precondition.Precondition;
import starlib.precondition.PreconditionLexer;
import starlib.precondition.PreconditionParser;

public class Model {
	
	private Formula f;
	private String pure;
	
	public Model(String model) {
		model = model.replaceAll("FLOAT 0.", "0");
		String[] tmp = model.split(";");
		
		model = tmp[0];
		pure = tmp[1];
		
		if (pure.contains("Sat")) {
			pure = pure.substring(tmp[1].indexOf('[') + 1, tmp[1].length() - 1);
			pure = pure.replaceAll("\\),", ");");
		} else {
			pure = "";
		}
					
		model = standardizeModel(model);
		model = "pre temp == " + model;
		
		CharStream in = CharStreams.fromString(model);
		PreconditionLexer lexer = new PreconditionLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PreconditionParser parser = new PreconditionParser(tokens);
        
        Precondition[] ps = parser.pres().ps;
        f = ps[0].getFormula();
	}

	public Formula getFormula() {
		return f;
	}
	
	public String getPure() {
		return pure;
	}

	public String standardizeModel(String model) {
		String ret = model;
		
		ret = ret.substring(8, model.length());
		ret = ret.replaceAll("[\\[\\]]", "");
		
		if (ret.endsWith("@M")) {
			ret = ret.replaceAll("@M,", " *");
			ret = ret.replaceAll("@M", "");
		} else {
			ret = ret.replaceAll("@M,", " *");
			ret = ret.replaceAll("@M", " &");
		}
		
		return ret.substring(1);
	}
}
