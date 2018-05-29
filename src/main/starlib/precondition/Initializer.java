package starlib.precondition;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import starlib.data.DataNode;
import starlib.data.DataNodeLexer;
import starlib.data.DataNodeMap;
import starlib.data.DataNodeParser;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredLexer;
import starlib.predicate.InductivePredMap;
import starlib.predicate.InductivePredParser;

public class Initializer {

	public static void initDataNode(String data) {
		CharStream in = CharStreams.fromString(data);
		DataNodeLexer lexer = new DataNodeLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DataNodeParser parser = new DataNodeParser(tokens);

		DataNode[] dns = parser.datas().dns;
		DataNodeMap.reset();
		DataNodeMap.put(dns);
	}
	
	public static void initPredicate(String pred) {
		CharStream in = CharStreams.fromString(pred);
		initPredicate(in);
	}
	
	public static void initPredicateFromFile(String pred) throws IOException{
		CharStream in = CharStreams.fromFileName(pred);
		initPredicate(in);
	}
	
	private static void initPredicate(CharStream in) {
		InductivePredLexer lexer = new InductivePredLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		InductivePredParser parser = new InductivePredParser(tokens);

		InductivePred[] ips = parser.preds().ips;
		InductivePredMap.reset();
		InductivePredMap.put(ips);
	}
	
	public static void initPrecondition(String pre) {		
		CharStream in = CharStreams.fromString(pre);
		PreconditionLexer lexer = new PreconditionLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PreconditionParser parser = new PreconditionParser(tokens);
        
        Precondition[] ps = parser.pres().ps;
        PreconditionMap.reset();
        PreconditionMap.put(ps);
	}
}
