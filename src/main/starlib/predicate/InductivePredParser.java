// Generated from InductivePred.g4 by ANTLR 4.7

package starlib.predicate;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InductivePredParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRED=1, NULL=2, EQEQ=3, EQ=4, NE=5, GE=6, GT=7, LE=8, LT=9, PLUS=10, MINUS=11, 
		MUL=12, DIV=13, LB=14, RB=15, CM=16, SM=17, OR=18, AND=19, PT=20, STAR=21, 
		ID=22, INT=23, DOUBLE=24, WS=25;
	public static final int
		RULE_preds = 0, RULE_tail = 1, RULE_pred = 2, RULE_params = 3, RULE_param = 4, 
		RULE_formulas = 5, RULE_formula = 6, RULE_heapTerms = 7, RULE_heapTerm = 8, 
		RULE_pointToTerm = 9, RULE_inductiveTerm = 10, RULE_pureTerms = 11, RULE_pureTerm = 12, 
		RULE_comparisonTerm = 13, RULE_comp = 14, RULE_exp = 15, RULE_ter = 16;
	public static final String[] ruleNames = {
		"preds", "tail", "pred", "params", "param", "formulas", "formula", "heapTerms", 
		"heapTerm", "pointToTerm", "inductiveTerm", "pureTerms", "pureTerm", "comparisonTerm", 
		"comp", "exp", "ter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pred'", "'null'", "'=='", "'='", "'!='", "'>='", "'>'", "'<='", 
		"'<'", "'+'", "'-'", "'**'", "'/'", "'('", "')'", "','", "';'", "'||'", 
		"'&'", "'::'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PRED", "NULL", "EQEQ", "EQ", "NE", "GE", "GT", "LE", "LT", "PLUS", 
		"MINUS", "MUL", "DIV", "LB", "RB", "CM", "SM", "OR", "AND", "PT", "STAR", 
		"ID", "INT", "DOUBLE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "InductivePred.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InductivePredParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PredsContext extends ParserRuleContext {
		public InductivePred[] ips;
		public PredContext pred;
		public TailContext tail;
		public PredContext pred() {
			return getRuleContext(PredContext.class,0);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public PredsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPreds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPreds(this);
		}
	}

	public final PredsContext preds() throws RecognitionException {
		PredsContext _localctx = new PredsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_preds);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				((PredsContext)_localctx).pred = pred();

						((PredsContext)_localctx).ips =  new InductivePred[1];
						_localctx.ips[0] = ((PredsContext)_localctx).pred.ip;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				((PredsContext)_localctx).pred = pred();
				setState(38);
				((PredsContext)_localctx).tail = tail();

						int length = ((PredsContext)_localctx).tail.ips.length + 1;
						((PredsContext)_localctx).ips =  new InductivePred[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ips[0] = ((PredsContext)_localctx).pred.ip;
							else _localctx.ips[i] = ((PredsContext)_localctx).tail.ips[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TailContext extends ParserRuleContext {
		public InductivePred[] ips;
		public PredContext pred;
		public TailContext tail;
		public TerminalNode SM() { return getToken(InductivePredParser.SM, 0); }
		public PredContext pred() {
			return getRuleContext(PredContext.class,0);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public TailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitTail(this);
		}
	}

	public final TailContext tail() throws RecognitionException {
		TailContext _localctx = new TailContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tail);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(SM);
				setState(44);
				((TailContext)_localctx).pred = pred();

						((TailContext)_localctx).ips =  new InductivePred[1];
						_localctx.ips[0] = ((TailContext)_localctx).pred.ip;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(SM);
				setState(48);
				((TailContext)_localctx).pred = pred();
				setState(49);
				((TailContext)_localctx).tail = tail();

						int length = ((TailContext)_localctx).tail.ips.length + 1;
						((TailContext)_localctx).ips =  new InductivePred[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ips[0] = ((TailContext)_localctx).pred.ip;
							else _localctx.ips[i] = ((TailContext)_localctx).tail.ips[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredContext extends ParserRuleContext {
		public InductivePred ip;
		public Token ID;
		public ParamsContext params;
		public FormulasContext formulas;
		public TerminalNode PRED() { return getToken(InductivePredParser.PRED, 0); }
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public TerminalNode EQEQ() { return getToken(InductivePredParser.EQEQ, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public PredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPred(this);
		}
	}

	public final PredContext pred() throws RecognitionException {
		PredContext _localctx = new PredContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pred);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(PRED);
			setState(55);
			((PredContext)_localctx).ID = match(ID);
			setState(56);
			match(LB);
			setState(57);
			((PredContext)_localctx).params = params();
			setState(58);
			match(RB);
			setState(59);
			match(EQEQ);
			setState(60);
			((PredContext)_localctx).formulas = formulas();

					Variable[] ps = ((PredContext)_localctx).params.vars;
					Formula[] fs = ((PredContext)_localctx).formulas.fs;
					
					((PredContext)_localctx).ip =  new InductivePred((((PredContext)_localctx).ID!=null?((PredContext)_localctx).ID.getText():null), ps, fs);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Variable[] vars;
		public ParamContext param;
		public ParamsContext params;
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public TerminalNode CM() { return getToken(InductivePredParser.CM, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		try {
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				((ParamsContext)_localctx).param = param();

						((ParamsContext)_localctx).vars =  new Variable[1];
						_localctx.vars[0] = ((ParamsContext)_localctx).param.var;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				((ParamsContext)_localctx).param = param();
				setState(67);
				match(CM);
				setState(68);
				((ParamsContext)_localctx).params = params();

						int length = ((ParamsContext)_localctx).params.vars.length + 1;
						((ParamsContext)_localctx).vars =  new Variable[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.vars[i] = ((ParamsContext)_localctx).param.var;
							else _localctx.vars[i] = ((ParamsContext)_localctx).params.vars[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Variable var;
		public Token ID;
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			((ParamContext)_localctx).ID = match(ID);

					((ParamContext)_localctx).var =  new Variable((((ParamContext)_localctx).ID!=null?((ParamContext)_localctx).ID.getText():null));
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulasContext extends ParserRuleContext {
		public Formula[] fs;
		public FormulaContext formula;
		public FormulasContext formulas;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode OR() { return getToken(InductivePredParser.OR, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public FormulasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterFormulas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitFormulas(this);
		}
	}

	public final FormulasContext formulas() throws RecognitionException {
		FormulasContext _localctx = new FormulasContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formulas);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				((FormulasContext)_localctx).formula = formula();

						((FormulasContext)_localctx).fs =  new Formula[1];
						_localctx.fs[0] = ((FormulasContext)_localctx).formula.f;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				((FormulasContext)_localctx).formula = formula();
				setState(80);
				match(OR);
				setState(81);
				((FormulasContext)_localctx).formulas = formulas();

						int length = ((FormulasContext)_localctx).formulas.fs.length + 1;
						((FormulasContext)_localctx).fs =  new Formula[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.fs[i] = ((FormulasContext)_localctx).formula.f;
							else _localctx.fs[i] = ((FormulasContext)_localctx).formulas.fs[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public Formula f;
		public HeapTermsContext heapTerms;
		public PureTermsContext pureTerms;
		public HeapTermsContext heapTerms() {
			return getRuleContext(HeapTermsContext.class,0);
		}
		public PureTermsContext pureTerms() {
			return getRuleContext(PureTermsContext.class,0);
		}
		public TerminalNode AND() { return getToken(InductivePredParser.AND, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formula);
		try {
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((FormulaContext)_localctx).heapTerms = heapTerms();

						HeapFormula hFormula = ((FormulaContext)_localctx).heapTerms.hFormula;
						PureFormula pFormula = new PureFormula();
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				((FormulaContext)_localctx).pureTerms = pureTerms();

						HeapFormula hFormula = new HeapFormula();
						PureFormula pFormula = ((FormulaContext)_localctx).pureTerms.pFormula;
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				((FormulaContext)_localctx).heapTerms = heapTerms();
				setState(93);
				match(AND);
				setState(94);
				((FormulaContext)_localctx).pureTerms = pureTerms();

						HeapFormula hFormula = ((FormulaContext)_localctx).heapTerms.hFormula;
						PureFormula pFormula = ((FormulaContext)_localctx).pureTerms.pFormula;
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeapTermsContext extends ParserRuleContext {
		public HeapFormula hFormula;
		public HeapTermContext heapTerm;
		public HeapTermsContext heapTerms;
		public HeapTermContext heapTerm() {
			return getRuleContext(HeapTermContext.class,0);
		}
		public TerminalNode STAR() { return getToken(InductivePredParser.STAR, 0); }
		public HeapTermsContext heapTerms() {
			return getRuleContext(HeapTermsContext.class,0);
		}
		public HeapTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heapTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterHeapTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitHeapTerms(this);
		}
	}

	public final HeapTermsContext heapTerms() throws RecognitionException {
		HeapTermsContext _localctx = new HeapTermsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_heapTerms);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();

						((HeapTermsContext)_localctx).hFormula =  new HeapFormula(((HeapTermsContext)_localctx).heapTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();
				setState(103);
				match(STAR);
				setState(104);
				((HeapTermsContext)_localctx).heapTerms = heapTerms();

						HeapFormula oldHeapFormula = ((HeapTermsContext)_localctx).heapTerms.hFormula;
						HeapTerm[] oldHeapTerms = oldHeapFormula.getHeapTerms();
						
						int length = oldHeapTerms.length + 1;
						HeapTerm[] newHeapTerms = new HeapTerm[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) newHeapTerms[i] = ((HeapTermsContext)_localctx).heapTerm.term;
							else newHeapTerms[i] = oldHeapTerms[i - 1];
						}
						
						((HeapTermsContext)_localctx).hFormula =  new HeapFormula(newHeapTerms);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeapTermContext extends ParserRuleContext {
		public HeapTerm term;
		public PointToTermContext pointToTerm;
		public InductiveTermContext inductiveTerm;
		public PointToTermContext pointToTerm() {
			return getRuleContext(PointToTermContext.class,0);
		}
		public InductiveTermContext inductiveTerm() {
			return getRuleContext(InductiveTermContext.class,0);
		}
		public HeapTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heapTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterHeapTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitHeapTerm(this);
		}
	}

	public final HeapTermContext heapTerm() throws RecognitionException {
		HeapTermContext _localctx = new HeapTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_heapTerm);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				((HeapTermContext)_localctx).pointToTerm = pointToTerm();

						((HeapTermContext)_localctx).term =  ((HeapTermContext)_localctx).pointToTerm.term;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				((HeapTermContext)_localctx).inductiveTerm = inductiveTerm();

						((HeapTermContext)_localctx).term =  ((HeapTermContext)_localctx).inductiveTerm.term;
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointToTermContext extends ParserRuleContext {
		public HeapTerm term;
		public Token root;
		public Token type;
		public ParamsContext params;
		public TerminalNode PT() { return getToken(InductivePredParser.PT, 0); }
		public TerminalNode LT() { return getToken(InductivePredParser.LT, 0); }
		public TerminalNode GT() { return getToken(InductivePredParser.GT, 0); }
		public List<TerminalNode> ID() { return getTokens(InductivePredParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(InductivePredParser.ID, i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public PointToTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointToTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPointToTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPointToTerm(this);
		}
	}

	public final PointToTermContext pointToTerm() throws RecognitionException {
		PointToTermContext _localctx = new PointToTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointToTerm);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				((PointToTermContext)_localctx).root = match(ID);
				setState(118);
				match(PT);
				setState(119);
				((PointToTermContext)_localctx).type = match(ID);
				setState(120);
				match(LT);
				setState(121);
				match(GT);

						Variable[] vars = new Variable[1];
						vars[0] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null));
						((PointToTermContext)_localctx).term =  new PointToTerm((((PointToTermContext)_localctx).type!=null?((PointToTermContext)_localctx).type.getText():null), vars);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				((PointToTermContext)_localctx).root = match(ID);
				setState(124);
				match(PT);
				setState(125);
				((PointToTermContext)_localctx).type = match(ID);
				setState(126);
				match(LT);
				setState(127);
				((PointToTermContext)_localctx).params = params();
				setState(128);
				match(GT);

						int length = ((PointToTermContext)_localctx).params.vars.length + 1;
						
						Variable[] vars = new Variable[length];
						for (int i = 0; i < length; i++) {
							if (i == 0) vars[i] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null));
							else vars[i] = ((PointToTermContext)_localctx).params.vars[i - 1];
						}
						
						((PointToTermContext)_localctx).term =  new PointToTerm((((PointToTermContext)_localctx).type!=null?((PointToTermContext)_localctx).type.getText():null), vars);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InductiveTermContext extends ParserRuleContext {
		public HeapTerm term;
		public Token ID;
		public ParamsContext params;
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public InductiveTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inductiveTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterInductiveTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitInductiveTerm(this);
		}
	}

	public final InductiveTermContext inductiveTerm() throws RecognitionException {
		InductiveTermContext _localctx = new InductiveTermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_inductiveTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((InductiveTermContext)_localctx).ID = match(ID);
			setState(134);
			match(LB);
			setState(135);
			((InductiveTermContext)_localctx).params = params();
			setState(136);
			match(RB);

					((InductiveTermContext)_localctx).term =  new InductiveTerm((((InductiveTermContext)_localctx).ID!=null?((InductiveTermContext)_localctx).ID.getText():null), ((InductiveTermContext)_localctx).params.vars);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PureTermsContext extends ParserRuleContext {
		public PureFormula pFormula;
		public PureTermContext pureTerm;
		public PureTermsContext pureTerms;
		public PureTermContext pureTerm() {
			return getRuleContext(PureTermContext.class,0);
		}
		public TerminalNode AND() { return getToken(InductivePredParser.AND, 0); }
		public PureTermsContext pureTerms() {
			return getRuleContext(PureTermsContext.class,0);
		}
		public PureTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPureTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPureTerms(this);
		}
	}

	public final PureTermsContext pureTerms() throws RecognitionException {
		PureTermsContext _localctx = new PureTermsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pureTerms);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				((PureTermsContext)_localctx).pureTerm = pureTerm();

						((PureTermsContext)_localctx).pFormula =  new PureFormula(((PureTermsContext)_localctx).pureTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				((PureTermsContext)_localctx).pureTerm = pureTerm();
				setState(143);
				match(AND);
				setState(144);
				((PureTermsContext)_localctx).pureTerms = pureTerms();

						PureFormula oldPureFormula = ((PureTermsContext)_localctx).pureTerms.pFormula;
						PureTerm[] oldPureTerms = oldPureFormula.getPureTerms();
						
						int length = oldPureTerms.length + 1;
						PureTerm[] newPureTerms = new PureTerm[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) newPureTerms[i] = ((PureTermsContext)_localctx).pureTerm.term;
							else newPureTerms[i] = oldPureTerms[i - 1];
						}
						
						((PureTermsContext)_localctx).pFormula =  new PureFormula(newPureTerms);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PureTermContext extends ParserRuleContext {
		public PureTerm term;
		public ComparisonTermContext comparisonTerm;
		public ComparisonTermContext comparisonTerm() {
			return getRuleContext(ComparisonTermContext.class,0);
		}
		public PureTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPureTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPureTerm(this);
		}
	}

	public final PureTermContext pureTerm() throws RecognitionException {
		PureTermContext _localctx = new PureTermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pureTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			((PureTermContext)_localctx).comparisonTerm = comparisonTerm();

					((PureTermContext)_localctx).term =  ((PureTermContext)_localctx).comparisonTerm.term;
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonTermContext extends ParserRuleContext {
		public PureTerm term;
		public ExpContext exp1;
		public CompContext comp;
		public ExpContext exp2;
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ComparisonTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterComparisonTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitComparisonTerm(this);
		}
	}

	public final ComparisonTermContext comparisonTerm() throws RecognitionException {
		ComparisonTermContext _localctx = new ComparisonTermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comparisonTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((ComparisonTermContext)_localctx).exp1 = exp(0);
			setState(153);
			((ComparisonTermContext)_localctx).comp = comp();
			setState(154);
			((ComparisonTermContext)_localctx).exp2 = exp(0);

					((ComparisonTermContext)_localctx).term =  new ComparisonTerm(((ComparisonTermContext)_localctx).comp.c, ((ComparisonTermContext)_localctx).exp1.e, ((ComparisonTermContext)_localctx).exp2.e);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompContext extends ParserRuleContext {
		public Comparator c;
		public TerminalNode EQ() { return getToken(InductivePredParser.EQ, 0); }
		public TerminalNode NE() { return getToken(InductivePredParser.NE, 0); }
		public TerminalNode GE() { return getToken(InductivePredParser.GE, 0); }
		public TerminalNode GT() { return getToken(InductivePredParser.GT, 0); }
		public TerminalNode LE() { return getToken(InductivePredParser.LE, 0); }
		public TerminalNode LT() { return getToken(InductivePredParser.LT, 0); }
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitComp(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comp);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(EQ);

						((CompContext)_localctx).c =  Comparator.EQ;
					
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(NE);

						((CompContext)_localctx).c =  Comparator.NE;
					
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 3);
				{
				setState(161);
				match(GE);

						((CompContext)_localctx).c =  Comparator.GE;
					
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(GT);

						((CompContext)_localctx).c =  Comparator.GT;
					
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				match(LE);

						((CompContext)_localctx).c =  Comparator.LE;
					
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 6);
				{
				setState(167);
				match(LT);

						((CompContext)_localctx).c =  Comparator.LT;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Expression e;
		public ExpContext exp1;
		public TerContext ter;
		public TerContext ter() {
			return getRuleContext(TerContext.class,0);
		}
		public TerminalNode NULL() { return getToken(InductivePredParser.NULL, 0); }
		public TerminalNode PLUS() { return getToken(InductivePredParser.PLUS, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(InductivePredParser.MINUS, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case ID:
			case INT:
			case DOUBLE:
				{
				setState(172);
				((ExpContext)_localctx).ter = ter();

						((ExpContext)_localctx).e =  ((ExpContext)_localctx).ter.e;
					
				}
				break;
			case NULL:
				{
				setState(175);
				match(NULL);
					
						((ExpContext)_localctx).e =  NullExpression.getInstance();
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(189);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(179);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(180);
						match(PLUS);
						setState(181);
						((ExpContext)_localctx).ter = ter();

						          		Expression exp1 = ((ExpContext)_localctx).exp1.e;
						          		Expression exp2 = ((ExpContext)_localctx).ter.e;
						          		
						          		((ExpContext)_localctx).e =  new BinaryExpression(Operator.PLUS, exp1, exp2);
						          	
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(184);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(185);
						match(MINUS);
						setState(186);
						((ExpContext)_localctx).ter = ter();

						          		Expression exp1 = ((ExpContext)_localctx).exp1.e;
						          		Expression exp2 = ((ExpContext)_localctx).ter.e;
						          		
						          		((ExpContext)_localctx).e =  new BinaryExpression(Operator.MINUS, exp1, exp2);
						          	
						}
						break;
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TerContext extends ParserRuleContext {
		public Expression e;
		public Token var1;
		public Token var2;
		public Token ID;
		public Token INT;
		public Token DOUBLE;
		public ExpContext exp;
		public TerminalNode MUL() { return getToken(InductivePredParser.MUL, 0); }
		public List<TerminalNode> ID() { return getTokens(InductivePredParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(InductivePredParser.ID, i);
		}
		public TerminalNode DIV() { return getToken(InductivePredParser.DIV, 0); }
		public TerminalNode INT() { return getToken(InductivePredParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(InductivePredParser.DOUBLE, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public TerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterTer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitTer(this);
		}
	}

	public final TerContext ter() throws RecognitionException {
		TerContext _localctx = new TerContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ter);
		try {
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				((TerContext)_localctx).var1 = match(ID);
				setState(195);
				match(MUL);
				setState(196);
				((TerContext)_localctx).var2 = match(ID);

						Expression exp1 = new Variable((((TerContext)_localctx).var1!=null?((TerContext)_localctx).var1.getText():null));
						Expression exp2 = new Variable((((TerContext)_localctx).var2!=null?((TerContext)_localctx).var2.getText():null));
						
						((TerContext)_localctx).e =  new BinaryExpression(Operator.MUL, exp1, exp2);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				((TerContext)_localctx).var1 = match(ID);
				setState(199);
				match(DIV);
				setState(200);
				((TerContext)_localctx).var2 = match(ID);

						Expression exp1 = new Variable((((TerContext)_localctx).var1!=null?((TerContext)_localctx).var1.getText():null));
						Expression exp2 = new Variable((((TerContext)_localctx).var2!=null?((TerContext)_localctx).var2.getText():null));
					
						((TerContext)_localctx).e =  new BinaryExpression(Operator.DIV, exp1, exp2);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				((TerContext)_localctx).ID = match(ID);

						((TerContext)_localctx).e =  new Variable((((TerContext)_localctx).ID!=null?((TerContext)_localctx).ID.getText():null));
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				((TerContext)_localctx).INT = match(INT);

						((TerContext)_localctx).e =  new LiteralExpression((((TerContext)_localctx).INT!=null?((TerContext)_localctx).INT.getText():null));
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(206);
				((TerContext)_localctx).DOUBLE = match(DOUBLE);

						((TerContext)_localctx).e =  new LiteralExpression((((TerContext)_localctx).DOUBLE!=null?((TerContext)_localctx).DOUBLE.getText():null));
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(208);
				match(LB);
				setState(209);
				((TerContext)_localctx).exp = exp(0);
				setState(210);
				match(RB);

						((TerContext)_localctx).e =  ((TerContext)_localctx).exp.e;
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00da\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3\67\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5J\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5"+
		"\7W\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bd\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\tn\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nv\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u0086\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u0096\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ac\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u00b4\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\7\21\u00c0\n\21\f\21\16\21\u00c3\13\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u00d8\n\22\3\22\2\3 \23\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"\2\2\2\u00df\2+\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\bI\3\2\2\2"+
		"\nK\3\2\2\2\fV\3\2\2\2\16c\3\2\2\2\20m\3\2\2\2\22u\3\2\2\2\24\u0085\3"+
		"\2\2\2\26\u0087\3\2\2\2\30\u0095\3\2\2\2\32\u0097\3\2\2\2\34\u009a\3\2"+
		"\2\2\36\u00ab\3\2\2\2 \u00b3\3\2\2\2\"\u00d7\3\2\2\2$%\5\6\4\2%&\b\2\1"+
		"\2&,\3\2\2\2\'(\5\6\4\2()\5\4\3\2)*\b\2\1\2*,\3\2\2\2+$\3\2\2\2+\'\3\2"+
		"\2\2,\3\3\2\2\2-.\7\23\2\2./\5\6\4\2/\60\b\3\1\2\60\67\3\2\2\2\61\62\7"+
		"\23\2\2\62\63\5\6\4\2\63\64\5\4\3\2\64\65\b\3\1\2\65\67\3\2\2\2\66-\3"+
		"\2\2\2\66\61\3\2\2\2\67\5\3\2\2\289\7\3\2\29:\7\30\2\2:;\7\20\2\2;<\5"+
		"\b\5\2<=\7\21\2\2=>\7\5\2\2>?\5\f\7\2?@\b\4\1\2@\7\3\2\2\2AB\5\n\6\2B"+
		"C\b\5\1\2CJ\3\2\2\2DE\5\n\6\2EF\7\22\2\2FG\5\b\5\2GH\b\5\1\2HJ\3\2\2\2"+
		"IA\3\2\2\2ID\3\2\2\2J\t\3\2\2\2KL\7\30\2\2LM\b\6\1\2M\13\3\2\2\2NO\5\16"+
		"\b\2OP\b\7\1\2PW\3\2\2\2QR\5\16\b\2RS\7\24\2\2ST\5\f\7\2TU\b\7\1\2UW\3"+
		"\2\2\2VN\3\2\2\2VQ\3\2\2\2W\r\3\2\2\2XY\5\20\t\2YZ\b\b\1\2Zd\3\2\2\2["+
		"\\\5\30\r\2\\]\b\b\1\2]d\3\2\2\2^_\5\20\t\2_`\7\25\2\2`a\5\30\r\2ab\b"+
		"\b\1\2bd\3\2\2\2cX\3\2\2\2c[\3\2\2\2c^\3\2\2\2d\17\3\2\2\2ef\5\22\n\2"+
		"fg\b\t\1\2gn\3\2\2\2hi\5\22\n\2ij\7\27\2\2jk\5\20\t\2kl\b\t\1\2ln\3\2"+
		"\2\2me\3\2\2\2mh\3\2\2\2n\21\3\2\2\2op\5\24\13\2pq\b\n\1\2qv\3\2\2\2r"+
		"s\5\26\f\2st\b\n\1\2tv\3\2\2\2uo\3\2\2\2ur\3\2\2\2v\23\3\2\2\2wx\7\30"+
		"\2\2xy\7\26\2\2yz\7\30\2\2z{\7\13\2\2{|\7\t\2\2|\u0086\b\13\1\2}~\7\30"+
		"\2\2~\177\7\26\2\2\177\u0080\7\30\2\2\u0080\u0081\7\13\2\2\u0081\u0082"+
		"\5\b\5\2\u0082\u0083\7\t\2\2\u0083\u0084\b\13\1\2\u0084\u0086\3\2\2\2"+
		"\u0085w\3\2\2\2\u0085}\3\2\2\2\u0086\25\3\2\2\2\u0087\u0088\7\30\2\2\u0088"+
		"\u0089\7\20\2\2\u0089\u008a\5\b\5\2\u008a\u008b\7\21\2\2\u008b\u008c\b"+
		"\f\1\2\u008c\27\3\2\2\2\u008d\u008e\5\32\16\2\u008e\u008f\b\r\1\2\u008f"+
		"\u0096\3\2\2\2\u0090\u0091\5\32\16\2\u0091\u0092\7\25\2\2\u0092\u0093"+
		"\5\30\r\2\u0093\u0094\b\r\1\2\u0094\u0096\3\2\2\2\u0095\u008d\3\2\2\2"+
		"\u0095\u0090\3\2\2\2\u0096\31\3\2\2\2\u0097\u0098\5\34\17\2\u0098\u0099"+
		"\b\16\1\2\u0099\33\3\2\2\2\u009a\u009b\5 \21\2\u009b\u009c\5\36\20\2\u009c"+
		"\u009d\5 \21\2\u009d\u009e\b\17\1\2\u009e\35\3\2\2\2\u009f\u00a0\7\6\2"+
		"\2\u00a0\u00ac\b\20\1\2\u00a1\u00a2\7\7\2\2\u00a2\u00ac\b\20\1\2\u00a3"+
		"\u00a4\7\b\2\2\u00a4\u00ac\b\20\1\2\u00a5\u00a6\7\t\2\2\u00a6\u00ac\b"+
		"\20\1\2\u00a7\u00a8\7\n\2\2\u00a8\u00ac\b\20\1\2\u00a9\u00aa\7\13\2\2"+
		"\u00aa\u00ac\b\20\1\2\u00ab\u009f\3\2\2\2\u00ab\u00a1\3\2\2\2\u00ab\u00a3"+
		"\3\2\2\2\u00ab\u00a5\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\37\3\2\2\2\u00ad\u00ae\b\21\1\2\u00ae\u00af\5\"\22\2\u00af\u00b0\b\21"+
		"\1\2\u00b0\u00b4\3\2\2\2\u00b1\u00b2\7\4\2\2\u00b2\u00b4\b\21\1\2\u00b3"+
		"\u00ad\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00c1\3\2\2\2\u00b5\u00b6\f\6"+
		"\2\2\u00b6\u00b7\7\f\2\2\u00b7\u00b8\5\"\22\2\u00b8\u00b9\b\21\1\2\u00b9"+
		"\u00c0\3\2\2\2\u00ba\u00bb\f\5\2\2\u00bb\u00bc\7\r\2\2\u00bc\u00bd\5\""+
		"\22\2\u00bd\u00be\b\21\1\2\u00be\u00c0\3\2\2\2\u00bf\u00b5\3\2\2\2\u00bf"+
		"\u00ba\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2!\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5\7\30\2\2\u00c5\u00c6"+
		"\7\16\2\2\u00c6\u00c7\7\30\2\2\u00c7\u00d8\b\22\1\2\u00c8\u00c9\7\30\2"+
		"\2\u00c9\u00ca\7\17\2\2\u00ca\u00cb\7\30\2\2\u00cb\u00d8\b\22\1\2\u00cc"+
		"\u00cd\7\30\2\2\u00cd\u00d8\b\22\1\2\u00ce\u00cf\7\31\2\2\u00cf\u00d8"+
		"\b\22\1\2\u00d0\u00d1\7\32\2\2\u00d1\u00d8\b\22\1\2\u00d2\u00d3\7\20\2"+
		"\2\u00d3\u00d4\5 \21\2\u00d4\u00d5\7\21\2\2\u00d5\u00d6\b\22\1\2\u00d6"+
		"\u00d8\3\2\2\2\u00d7\u00c4\3\2\2\2\u00d7\u00c8\3\2\2\2\u00d7\u00cc\3\2"+
		"\2\2\u00d7\u00ce\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d7\u00d2\3\2\2\2\u00d8"+
		"#\3\2\2\2\20+\66IVcmu\u0085\u0095\u00ab\u00b3\u00bf\u00c1\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}