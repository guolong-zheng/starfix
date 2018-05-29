grammar Precondition;

@header {
package starlib.precondition;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.expression.BinaryExpression;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.LiteralExpression;
import starlib.formula.expression.NullExpression;
import starlib.formula.expression.Operator;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
}

// parser
pres returns [Precondition[] ps] :
	pre
	{
		$ps = new Precondition[1];
		$ps[0] = $pre.p;
	}
	| pre tail
	{
		int length = $tail.ps.length + 1;
		$ps = new Precondition[length];
		
		for (int i = 0; i < length; i++) {
			if (i == 0) $ps[0] = $pre.p;
			else $ps[i] = $tail.ps[i - 1];
		}
	}
;

tail returns [Precondition[] ps] :
	SM pre
	{
		$ps = new Precondition[1];
		$ps[0] = $pre.p;
	}
	| SM pre tail
	{
		int length = $tail.ps.length + 1;
		$ps = new Precondition[length];
		
		for (int i = 0; i < length; i++) {
			if (i == 0) $ps[0] = $pre.p;
			else $ps[i] = $tail.ps[i - 1];
		}
	}
;

pre returns [Precondition p] :
	PRE ID EQEQ formula
	{
		Formula f = $formula.f;
		
		$p = new Precondition($ID.text, f);
	}
;

params returns [Variable[] vars] :
	param
	{
		$vars = new Variable[1];
		$vars[0] = $param.var;
	}
	| param CM params
	{
		int length = $params.vars.length + 1;
		$vars = new Variable[length];
		
		for (int i = 0; i < length; i++) {
			if (i == 0) $vars[i] = $param.var;
			else $vars[i] = $params.vars[i - 1];
		}
	}
;

param returns [Variable var] : ID 
	{
		$var = new Variable($ID.text, "");
	}
;

formula returns [Formula f] :
	heapTerms
	{
		HeapFormula hFormula = $heapTerms.hFormula;
		PureFormula pFormula = new PureFormula();
		$f = new Formula(hFormula, pFormula);
	}
	| pureTerms
	{
		HeapFormula hFormula = new HeapFormula();
		PureFormula pFormula = $pureTerms.pFormula;
		$f = new Formula(hFormula, pFormula);
	}
	| heapTerms AND pureTerms
	{
		HeapFormula hFormula = $heapTerms.hFormula;
		PureFormula pFormula = $pureTerms.pFormula;
		$f = new Formula(hFormula, pFormula);
	}
;

heapTerms returns [HeapFormula hFormula] :
	heapTerm
	{
		$hFormula = new HeapFormula($heapTerm.term);
	}
	| heapTerm STAR heapTerms
	{
		HeapFormula oldHeapFormula = $heapTerms.hFormula;
		HeapTerm[] oldHeapTerms = oldHeapFormula.getHeapTerms();
		
		int length = oldHeapTerms.length + 1;
		HeapTerm[] newHeapTerms = new HeapTerm[length];
		
		for (int i = 0; i < length; i++) {
			if (i == 0) newHeapTerms[i] = $heapTerm.term;
			else newHeapTerms[i] = oldHeapTerms[i - 1];
		}
		
		$hFormula = new HeapFormula(newHeapTerms);
	}
;

heapTerm returns [HeapTerm term] :
	pointToTerm
	{
		$term = $pointToTerm.term;
	}
	| inductiveTerm
	{
		$term = $inductiveTerm.term;
	}
;

pointToTerm returns [HeapTerm term] :
	root=ID PT type=ID LT GT
	{
		Variable[] vars = new Variable[1];
		vars[0] = new Variable($root.text, "");
		$term = new PointToTerm($type.text, vars);
	}
	| root=ID PT type=ID LT params GT
	{
		int length = $params.vars.length + 1;
		
		Variable[] vars = new Variable[length];
		for (int i = 0; i < length; i++) {
			if (i == 0) vars[i] = new Variable($root.text, "");
			else vars[i] = $params.vars[i - 1];
		}
		
		$term = new PointToTerm($type.text, vars);
	}
;

inductiveTerm returns [HeapTerm term] : ID LB params RB 
	{
		$term = new InductiveTerm($ID.text, $params.vars);
	}
;

pureTerms returns [PureFormula pFormula]:
	pureTerm 
	{
		$pFormula = new PureFormula($pureTerm.term);
	}
	| pureTerm AND pureTerms
	{
		PureFormula oldPureFormula = $pureTerms.pFormula;
		PureTerm[] oldPureTerms = oldPureFormula.getPureTerms();
		
		int length = oldPureTerms.length + 1;
		PureTerm[] newPureTerms = new PureTerm[length];
		
		for (int i = 0; i < length; i++) {
			if (i == 0) newPureTerms[i] = $pureTerm.term;
			else newPureTerms[i] = oldPureTerms[i - 1];
		}
		
		$pFormula = new PureFormula(newPureTerms);
	}
;

pureTerm returns [PureTerm term]: 
	comparisonTerm
	{
		$term = $comparisonTerm.term;
	}
;

comparisonTerm returns [PureTerm term] : exp1=exp comp exp2=exp
	{
		$term = new ComparisonTerm($comp.c, $exp1.e, $exp2.e);
	}
;

comp returns [Comparator c] :
	EQ
	{
		$c = Comparator.EQ;
	}
	| NE
	{
		$c = Comparator.NE;
	}
	| GE
	{
		$c = Comparator.GE;
	}
	| GT
	{
		$c = Comparator.GT;
	}
	| LE
	{
		$c = Comparator.LE;
	}
	| LT
	{
		$c = Comparator.LT;
	}
;

exp returns [Expression e] :
	exp1=exp PLUS ter
	{
		Expression exp1 = $exp1.e;
		Expression exp2 = $ter.e;
		
		$e = new BinaryExpression(Operator.PLUS, exp1, exp2);
	}
	| exp1=exp MINUS ter
	{
		Expression exp1 = $exp1.e;
		Expression exp2 = $ter.e;
		
		$e = new BinaryExpression(Operator.MINUS, exp1, exp2);
	}
	| ter
	{
		$e = $ter.e;
	}
	| NULL
	{	
		$e = NullExpression.getInstance();
	}
;
	
ter returns [Expression e] :
	var1=ID MUL var2=ID
	{
		Expression exp1 = new Variable($var1.text);
		Expression exp2 = new Variable($var2.text);
		
		$e = new BinaryExpression(Operator.MUL, exp1, exp2);
	}
	| var1=ID DIV var2=ID
	{
		Expression exp1 = new Variable($var1.text);
		Expression exp2 = new Variable($var2.text);
	
		$e = new BinaryExpression(Operator.DIV, exp1, exp2);
	}
	| ID
	{
		$e = new Variable($ID.text);
	}
	| INT
	{
		$e = new LiteralExpression($INT.text);
	}
	| DOUBLE
	{
		$e = new LiteralExpression($DOUBLE.text);
	}
	| LB exp RB
	{
		$e = $exp.e;
	}
;

// lexer
PRE    	: 'pre' ;
NULL    : 'null' ;
EQEQ    : '==' ;
EQ      : '=' ;
NE      : '!=' ;
GE		: '>=' ;
GT		: '>' ;
LE		: '<=' ;
LT		: '<' ;
PLUS	: '+' ;
MINUS	: '-' ;
MUL		: '**' ;
DIV		: '/' ;
LB      : '(' ;
RB      : ')' ;
CM      : ',' ;
SM      : ';' ;
OR      : '||' ;
AND     : '&' ;
PT      : '::' ;
STAR    : '*' ;
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;
INT		: '0'|[1-9][0-9]* ;
DOUBLE	: INT'.'INT ;
WS      : [ \t\r\n]+ -> skip ;

// tests
// pred sll(x) == x = null || x->Node(next) * sll(next)
