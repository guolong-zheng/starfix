// Generated from Precondition.g4 by ANTLR 4.7

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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PreconditionParser}.
 */
public interface PreconditionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#pres}.
	 * @param ctx the parse tree
	 */
	void enterPres(PreconditionParser.PresContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#pres}.
	 * @param ctx the parse tree
	 */
	void exitPres(PreconditionParser.PresContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#tail}.
	 * @param ctx the parse tree
	 */
	void enterTail(PreconditionParser.TailContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#tail}.
	 * @param ctx the parse tree
	 */
	void exitTail(PreconditionParser.TailContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#pre}.
	 * @param ctx the parse tree
	 */
	void enterPre(PreconditionParser.PreContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#pre}.
	 * @param ctx the parse tree
	 */
	void exitPre(PreconditionParser.PreContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(PreconditionParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(PreconditionParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(PreconditionParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(PreconditionParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(PreconditionParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(PreconditionParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#heapTerms}.
	 * @param ctx the parse tree
	 */
	void enterHeapTerms(PreconditionParser.HeapTermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#heapTerms}.
	 * @param ctx the parse tree
	 */
	void exitHeapTerms(PreconditionParser.HeapTermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#heapTerm}.
	 * @param ctx the parse tree
	 */
	void enterHeapTerm(PreconditionParser.HeapTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#heapTerm}.
	 * @param ctx the parse tree
	 */
	void exitHeapTerm(PreconditionParser.HeapTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#pointToTerm}.
	 * @param ctx the parse tree
	 */
	void enterPointToTerm(PreconditionParser.PointToTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#pointToTerm}.
	 * @param ctx the parse tree
	 */
	void exitPointToTerm(PreconditionParser.PointToTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#inductiveTerm}.
	 * @param ctx the parse tree
	 */
	void enterInductiveTerm(PreconditionParser.InductiveTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#inductiveTerm}.
	 * @param ctx the parse tree
	 */
	void exitInductiveTerm(PreconditionParser.InductiveTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#pureTerms}.
	 * @param ctx the parse tree
	 */
	void enterPureTerms(PreconditionParser.PureTermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#pureTerms}.
	 * @param ctx the parse tree
	 */
	void exitPureTerms(PreconditionParser.PureTermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#pureTerm}.
	 * @param ctx the parse tree
	 */
	void enterPureTerm(PreconditionParser.PureTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#pureTerm}.
	 * @param ctx the parse tree
	 */
	void exitPureTerm(PreconditionParser.PureTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#comparisonTerm}.
	 * @param ctx the parse tree
	 */
	void enterComparisonTerm(PreconditionParser.ComparisonTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#comparisonTerm}.
	 * @param ctx the parse tree
	 */
	void exitComparisonTerm(PreconditionParser.ComparisonTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(PreconditionParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(PreconditionParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(PreconditionParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(PreconditionParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreconditionParser#ter}.
	 * @param ctx the parse tree
	 */
	void enterTer(PreconditionParser.TerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreconditionParser#ter}.
	 * @param ctx the parse tree
	 */
	void exitTer(PreconditionParser.TerContext ctx);
}