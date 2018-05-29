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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreconditionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRE=1, NULL=2, EQEQ=3, EQ=4, NE=5, GE=6, GT=7, LE=8, LT=9, PLUS=10, MINUS=11, 
		MUL=12, DIV=13, LB=14, RB=15, CM=16, SM=17, OR=18, AND=19, PT=20, STAR=21, 
		ID=22, INT=23, DOUBLE=24, WS=25;
	public static final int
		RULE_pres = 0, RULE_tail = 1, RULE_pre = 2, RULE_params = 3, RULE_param = 4, 
		RULE_formula = 5, RULE_heapTerms = 6, RULE_heapTerm = 7, RULE_pointToTerm = 8, 
		RULE_inductiveTerm = 9, RULE_pureTerms = 10, RULE_pureTerm = 11, RULE_comparisonTerm = 12, 
		RULE_comp = 13, RULE_exp = 14, RULE_ter = 15;
	public static final String[] ruleNames = {
		"pres", "tail", "pre", "params", "param", "formula", "heapTerms", "heapTerm", 
		"pointToTerm", "inductiveTerm", "pureTerms", "pureTerm", "comparisonTerm", 
		"comp", "exp", "ter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pre'", "'null'", "'=='", "'='", "'!='", "'>='", "'>'", "'<='", 
		"'<'", "'+'", "'-'", "'**'", "'/'", "'('", "')'", "','", "';'", "'||'", 
		"'&'", "'::'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PRE", "NULL", "EQEQ", "EQ", "NE", "GE", "GT", "LE", "LT", "PLUS", 
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
	public String getGrammarFileName() { return "Precondition.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PreconditionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PresContext extends ParserRuleContext {
		public Precondition[] ps;
		public PreContext pre;
		public TailContext tail;
		public PreContext pre() {
			return getRuleContext(PreContext.class,0);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public PresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pres; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterPres(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitPres(this);
		}
	}

	public final PresContext pres() throws RecognitionException {
		PresContext _localctx = new PresContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_pres);
		try {
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				((PresContext)_localctx).pre = pre();

						((PresContext)_localctx).ps =  new Precondition[1];
						_localctx.ps[0] = ((PresContext)_localctx).pre.p;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				((PresContext)_localctx).pre = pre();
				setState(36);
				((PresContext)_localctx).tail = tail();

						int length = ((PresContext)_localctx).tail.ps.length + 1;
						((PresContext)_localctx).ps =  new Precondition[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ps[0] = ((PresContext)_localctx).pre.p;
							else _localctx.ps[i] = ((PresContext)_localctx).tail.ps[i - 1];
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
		public Precondition[] ps;
		public PreContext pre;
		public TailContext tail;
		public TerminalNode SM() { return getToken(PreconditionParser.SM, 0); }
		public PreContext pre() {
			return getRuleContext(PreContext.class,0);
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
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitTail(this);
		}
	}

	public final TailContext tail() throws RecognitionException {
		TailContext _localctx = new TailContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tail);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(SM);
				setState(42);
				((TailContext)_localctx).pre = pre();

						((TailContext)_localctx).ps =  new Precondition[1];
						_localctx.ps[0] = ((TailContext)_localctx).pre.p;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				match(SM);
				setState(46);
				((TailContext)_localctx).pre = pre();
				setState(47);
				((TailContext)_localctx).tail = tail();

						int length = ((TailContext)_localctx).tail.ps.length + 1;
						((TailContext)_localctx).ps =  new Precondition[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ps[0] = ((TailContext)_localctx).pre.p;
							else _localctx.ps[i] = ((TailContext)_localctx).tail.ps[i - 1];
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

	public static class PreContext extends ParserRuleContext {
		public Precondition p;
		public Token ID;
		public FormulaContext formula;
		public TerminalNode PRE() { return getToken(PreconditionParser.PRE, 0); }
		public TerminalNode ID() { return getToken(PreconditionParser.ID, 0); }
		public TerminalNode EQEQ() { return getToken(PreconditionParser.EQEQ, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public PreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pre; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterPre(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitPre(this);
		}
	}

	public final PreContext pre() throws RecognitionException {
		PreContext _localctx = new PreContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pre);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(PRE);
			setState(53);
			((PreContext)_localctx).ID = match(ID);
			setState(54);
			match(EQEQ);
			setState(55);
			((PreContext)_localctx).formula = formula();

					Formula f = ((PreContext)_localctx).formula.f;
					
					((PreContext)_localctx).p =  new Precondition((((PreContext)_localctx).ID!=null?((PreContext)_localctx).ID.getText():null), f);
				
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
		public TerminalNode CM() { return getToken(PreconditionParser.CM, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((ParamsContext)_localctx).param = param();

						((ParamsContext)_localctx).vars =  new Variable[1];
						_localctx.vars[0] = ((ParamsContext)_localctx).param.var;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				((ParamsContext)_localctx).param = param();
				setState(62);
				match(CM);
				setState(63);
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
		public TerminalNode ID() { return getToken(PreconditionParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((ParamContext)_localctx).ID = match(ID);

					((ParamContext)_localctx).var =  new Variable((((ParamContext)_localctx).ID!=null?((ParamContext)_localctx).ID.getText():null), "");
				
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
		public TerminalNode AND() { return getToken(PreconditionParser.AND, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formula);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				((FormulaContext)_localctx).heapTerms = heapTerms();

						HeapFormula hFormula = ((FormulaContext)_localctx).heapTerms.hFormula;
						PureFormula pFormula = new PureFormula();
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				((FormulaContext)_localctx).pureTerms = pureTerms();

						HeapFormula hFormula = new HeapFormula();
						PureFormula pFormula = ((FormulaContext)_localctx).pureTerms.pFormula;
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				((FormulaContext)_localctx).heapTerms = heapTerms();
				setState(78);
				match(AND);
				setState(79);
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
		public TerminalNode STAR() { return getToken(PreconditionParser.STAR, 0); }
		public HeapTermsContext heapTerms() {
			return getRuleContext(HeapTermsContext.class,0);
		}
		public HeapTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heapTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterHeapTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitHeapTerms(this);
		}
	}

	public final HeapTermsContext heapTerms() throws RecognitionException {
		HeapTermsContext _localctx = new HeapTermsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_heapTerms);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();

						((HeapTermsContext)_localctx).hFormula =  new HeapFormula(((HeapTermsContext)_localctx).heapTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();
				setState(88);
				match(STAR);
				setState(89);
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
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterHeapTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitHeapTerm(this);
		}
	}

	public final HeapTermContext heapTerm() throws RecognitionException {
		HeapTermContext _localctx = new HeapTermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_heapTerm);
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				((HeapTermContext)_localctx).pointToTerm = pointToTerm();

						((HeapTermContext)_localctx).term =  ((HeapTermContext)_localctx).pointToTerm.term;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
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
		public TerminalNode PT() { return getToken(PreconditionParser.PT, 0); }
		public TerminalNode LT() { return getToken(PreconditionParser.LT, 0); }
		public TerminalNode GT() { return getToken(PreconditionParser.GT, 0); }
		public List<TerminalNode> ID() { return getTokens(PreconditionParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PreconditionParser.ID, i);
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
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterPointToTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitPointToTerm(this);
		}
	}

	public final PointToTermContext pointToTerm() throws RecognitionException {
		PointToTermContext _localctx = new PointToTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pointToTerm);
		try {
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				((PointToTermContext)_localctx).root = match(ID);
				setState(103);
				match(PT);
				setState(104);
				((PointToTermContext)_localctx).type = match(ID);
				setState(105);
				match(LT);
				setState(106);
				match(GT);

						Variable[] vars = new Variable[1];
						vars[0] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null), "");
						((PointToTermContext)_localctx).term =  new PointToTerm((((PointToTermContext)_localctx).type!=null?((PointToTermContext)_localctx).type.getText():null), vars);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				((PointToTermContext)_localctx).root = match(ID);
				setState(109);
				match(PT);
				setState(110);
				((PointToTermContext)_localctx).type = match(ID);
				setState(111);
				match(LT);
				setState(112);
				((PointToTermContext)_localctx).params = params();
				setState(113);
				match(GT);

						int length = ((PointToTermContext)_localctx).params.vars.length + 1;
						
						Variable[] vars = new Variable[length];
						for (int i = 0; i < length; i++) {
							if (i == 0) vars[i] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null), "");
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
		public TerminalNode ID() { return getToken(PreconditionParser.ID, 0); }
		public TerminalNode LB() { return getToken(PreconditionParser.LB, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RB() { return getToken(PreconditionParser.RB, 0); }
		public InductiveTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inductiveTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterInductiveTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitInductiveTerm(this);
		}
	}

	public final InductiveTermContext inductiveTerm() throws RecognitionException {
		InductiveTermContext _localctx = new InductiveTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_inductiveTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((InductiveTermContext)_localctx).ID = match(ID);
			setState(119);
			match(LB);
			setState(120);
			((InductiveTermContext)_localctx).params = params();
			setState(121);
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
		public TerminalNode AND() { return getToken(PreconditionParser.AND, 0); }
		public PureTermsContext pureTerms() {
			return getRuleContext(PureTermsContext.class,0);
		}
		public PureTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterPureTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitPureTerms(this);
		}
	}

	public final PureTermsContext pureTerms() throws RecognitionException {
		PureTermsContext _localctx = new PureTermsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pureTerms);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				((PureTermsContext)_localctx).pureTerm = pureTerm();

						((PureTermsContext)_localctx).pFormula =  new PureFormula(((PureTermsContext)_localctx).pureTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				((PureTermsContext)_localctx).pureTerm = pureTerm();
				setState(128);
				match(AND);
				setState(129);
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
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterPureTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitPureTerm(this);
		}
	}

	public final PureTermContext pureTerm() throws RecognitionException {
		PureTermContext _localctx = new PureTermContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pureTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
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
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterComparisonTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitComparisonTerm(this);
		}
	}

	public final ComparisonTermContext comparisonTerm() throws RecognitionException {
		ComparisonTermContext _localctx = new ComparisonTermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comparisonTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((ComparisonTermContext)_localctx).exp1 = exp(0);
			setState(138);
			((ComparisonTermContext)_localctx).comp = comp();
			setState(139);
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
		public TerminalNode EQ() { return getToken(PreconditionParser.EQ, 0); }
		public TerminalNode NE() { return getToken(PreconditionParser.NE, 0); }
		public TerminalNode GE() { return getToken(PreconditionParser.GE, 0); }
		public TerminalNode GT() { return getToken(PreconditionParser.GT, 0); }
		public TerminalNode LE() { return getToken(PreconditionParser.LE, 0); }
		public TerminalNode LT() { return getToken(PreconditionParser.LT, 0); }
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitComp(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comp);
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(EQ);

						((CompContext)_localctx).c =  Comparator.EQ;
					
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(NE);

						((CompContext)_localctx).c =  Comparator.NE;
					
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				match(GE);

						((CompContext)_localctx).c =  Comparator.GE;
					
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(148);
				match(GT);

						((CompContext)_localctx).c =  Comparator.GT;
					
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				match(LE);

						((CompContext)_localctx).c =  Comparator.LE;
					
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 6);
				{
				setState(152);
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
		public TerminalNode NULL() { return getToken(PreconditionParser.NULL, 0); }
		public TerminalNode PLUS() { return getToken(PreconditionParser.PLUS, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(PreconditionParser.MINUS, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitExp(this);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case ID:
			case INT:
			case DOUBLE:
				{
				setState(157);
				((ExpContext)_localctx).ter = ter();

						((ExpContext)_localctx).e =  ((ExpContext)_localctx).ter.e;
					
				}
				break;
			case NULL:
				{
				setState(160);
				match(NULL);
					
						((ExpContext)_localctx).e =  NullExpression.getInstance();
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(174);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(164);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(165);
						match(PLUS);
						setState(166);
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
						setState(169);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(170);
						match(MINUS);
						setState(171);
						((ExpContext)_localctx).ter = ter();

						          		Expression exp1 = ((ExpContext)_localctx).exp1.e;
						          		Expression exp2 = ((ExpContext)_localctx).ter.e;
						          		
						          		((ExpContext)_localctx).e =  new BinaryExpression(Operator.MINUS, exp1, exp2);
						          	
						}
						break;
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		public TerminalNode MUL() { return getToken(PreconditionParser.MUL, 0); }
		public List<TerminalNode> ID() { return getTokens(PreconditionParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PreconditionParser.ID, i);
		}
		public TerminalNode DIV() { return getToken(PreconditionParser.DIV, 0); }
		public TerminalNode INT() { return getToken(PreconditionParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(PreconditionParser.DOUBLE, 0); }
		public TerminalNode LB() { return getToken(PreconditionParser.LB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(PreconditionParser.RB, 0); }
		public TerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).enterTer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreconditionListener ) ((PreconditionListener)listener).exitTer(this);
		}
	}

	public final TerContext ter() throws RecognitionException {
		TerContext _localctx = new TerContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ter);
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				((TerContext)_localctx).var1 = match(ID);
				setState(180);
				match(MUL);
				setState(181);
				((TerContext)_localctx).var2 = match(ID);

						Expression exp1 = new Variable((((TerContext)_localctx).var1!=null?((TerContext)_localctx).var1.getText():null));
						Expression exp2 = new Variable((((TerContext)_localctx).var2!=null?((TerContext)_localctx).var2.getText():null));
						
						((TerContext)_localctx).e =  new BinaryExpression(Operator.MUL, exp1, exp2);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				((TerContext)_localctx).var1 = match(ID);
				setState(184);
				match(DIV);
				setState(185);
				((TerContext)_localctx).var2 = match(ID);

						Expression exp1 = new Variable((((TerContext)_localctx).var1!=null?((TerContext)_localctx).var1.getText():null));
						Expression exp2 = new Variable((((TerContext)_localctx).var2!=null?((TerContext)_localctx).var2.getText():null));
					
						((TerContext)_localctx).e =  new BinaryExpression(Operator.DIV, exp1, exp2);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				((TerContext)_localctx).ID = match(ID);

						((TerContext)_localctx).e =  new Variable((((TerContext)_localctx).ID!=null?((TerContext)_localctx).ID.getText():null));
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(189);
				((TerContext)_localctx).INT = match(INT);

						((TerContext)_localctx).e =  new LiteralExpression((((TerContext)_localctx).INT!=null?((TerContext)_localctx).INT.getText():null));
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(191);
				((TerContext)_localctx).DOUBLE = match(DOUBLE);

						((TerContext)_localctx).e =  new LiteralExpression((((TerContext)_localctx).DOUBLE!=null?((TerContext)_localctx).DOUBLE.getText():null));
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(193);
				match(LB);
				setState(194);
				((TerContext)_localctx).exp = exp(0);
				setState(195);
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
		case 14:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00cb\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\5\2*\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"\65\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5E\n"+
		"\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7U\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\tg\n"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nw\n\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0087"+
		"\n\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u009d\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00a5\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u00b1\n\20\f\20\16\20\u00b4\13\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00c9\n\21\3\21\2\3\36\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2"+
		"\2\u00d0\2)\3\2\2\2\4\64\3\2\2\2\6\66\3\2\2\2\bD\3\2\2\2\nF\3\2\2\2\f"+
		"T\3\2\2\2\16^\3\2\2\2\20f\3\2\2\2\22v\3\2\2\2\24x\3\2\2\2\26\u0086\3\2"+
		"\2\2\30\u0088\3\2\2\2\32\u008b\3\2\2\2\34\u009c\3\2\2\2\36\u00a4\3\2\2"+
		"\2 \u00c8\3\2\2\2\"#\5\6\4\2#$\b\2\1\2$*\3\2\2\2%&\5\6\4\2&\'\5\4\3\2"+
		"\'(\b\2\1\2(*\3\2\2\2)\"\3\2\2\2)%\3\2\2\2*\3\3\2\2\2+,\7\23\2\2,-\5\6"+
		"\4\2-.\b\3\1\2.\65\3\2\2\2/\60\7\23\2\2\60\61\5\6\4\2\61\62\5\4\3\2\62"+
		"\63\b\3\1\2\63\65\3\2\2\2\64+\3\2\2\2\64/\3\2\2\2\65\5\3\2\2\2\66\67\7"+
		"\3\2\2\678\7\30\2\289\7\5\2\29:\5\f\7\2:;\b\4\1\2;\7\3\2\2\2<=\5\n\6\2"+
		"=>\b\5\1\2>E\3\2\2\2?@\5\n\6\2@A\7\22\2\2AB\5\b\5\2BC\b\5\1\2CE\3\2\2"+
		"\2D<\3\2\2\2D?\3\2\2\2E\t\3\2\2\2FG\7\30\2\2GH\b\6\1\2H\13\3\2\2\2IJ\5"+
		"\16\b\2JK\b\7\1\2KU\3\2\2\2LM\5\26\f\2MN\b\7\1\2NU\3\2\2\2OP\5\16\b\2"+
		"PQ\7\25\2\2QR\5\26\f\2RS\b\7\1\2SU\3\2\2\2TI\3\2\2\2TL\3\2\2\2TO\3\2\2"+
		"\2U\r\3\2\2\2VW\5\20\t\2WX\b\b\1\2X_\3\2\2\2YZ\5\20\t\2Z[\7\27\2\2[\\"+
		"\5\16\b\2\\]\b\b\1\2]_\3\2\2\2^V\3\2\2\2^Y\3\2\2\2_\17\3\2\2\2`a\5\22"+
		"\n\2ab\b\t\1\2bg\3\2\2\2cd\5\24\13\2de\b\t\1\2eg\3\2\2\2f`\3\2\2\2fc\3"+
		"\2\2\2g\21\3\2\2\2hi\7\30\2\2ij\7\26\2\2jk\7\30\2\2kl\7\13\2\2lm\7\t\2"+
		"\2mw\b\n\1\2no\7\30\2\2op\7\26\2\2pq\7\30\2\2qr\7\13\2\2rs\5\b\5\2st\7"+
		"\t\2\2tu\b\n\1\2uw\3\2\2\2vh\3\2\2\2vn\3\2\2\2w\23\3\2\2\2xy\7\30\2\2"+
		"yz\7\20\2\2z{\5\b\5\2{|\7\21\2\2|}\b\13\1\2}\25\3\2\2\2~\177\5\30\r\2"+
		"\177\u0080\b\f\1\2\u0080\u0087\3\2\2\2\u0081\u0082\5\30\r\2\u0082\u0083"+
		"\7\25\2\2\u0083\u0084\5\26\f\2\u0084\u0085\b\f\1\2\u0085\u0087\3\2\2\2"+
		"\u0086~\3\2\2\2\u0086\u0081\3\2\2\2\u0087\27\3\2\2\2\u0088\u0089\5\32"+
		"\16\2\u0089\u008a\b\r\1\2\u008a\31\3\2\2\2\u008b\u008c\5\36\20\2\u008c"+
		"\u008d\5\34\17\2\u008d\u008e\5\36\20\2\u008e\u008f\b\16\1\2\u008f\33\3"+
		"\2\2\2\u0090\u0091\7\6\2\2\u0091\u009d\b\17\1\2\u0092\u0093\7\7\2\2\u0093"+
		"\u009d\b\17\1\2\u0094\u0095\7\b\2\2\u0095\u009d\b\17\1\2\u0096\u0097\7"+
		"\t\2\2\u0097\u009d\b\17\1\2\u0098\u0099\7\n\2\2\u0099\u009d\b\17\1\2\u009a"+
		"\u009b\7\13\2\2\u009b\u009d\b\17\1\2\u009c\u0090\3\2\2\2\u009c\u0092\3"+
		"\2\2\2\u009c\u0094\3\2\2\2\u009c\u0096\3\2\2\2\u009c\u0098\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009d\35\3\2\2\2\u009e\u009f\b\20\1\2\u009f\u00a0\5 \21"+
		"\2\u00a0\u00a1\b\20\1\2\u00a1\u00a5\3\2\2\2\u00a2\u00a3\7\4\2\2\u00a3"+
		"\u00a5\b\20\1\2\u00a4\u009e\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00b2\3"+
		"\2\2\2\u00a6\u00a7\f\6\2\2\u00a7\u00a8\7\f\2\2\u00a8\u00a9\5 \21\2\u00a9"+
		"\u00aa\b\20\1\2\u00aa\u00b1\3\2\2\2\u00ab\u00ac\f\5\2\2\u00ac\u00ad\7"+
		"\r\2\2\u00ad\u00ae\5 \21\2\u00ae\u00af\b\20\1\2\u00af\u00b1\3\2\2\2\u00b0"+
		"\u00a6\3\2\2\2\u00b0\u00ab\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b2\u00b3\3\2\2\2\u00b3\37\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b6"+
		"\7\30\2\2\u00b6\u00b7\7\16\2\2\u00b7\u00b8\7\30\2\2\u00b8\u00c9\b\21\1"+
		"\2\u00b9\u00ba\7\30\2\2\u00ba\u00bb\7\17\2\2\u00bb\u00bc\7\30\2\2\u00bc"+
		"\u00c9\b\21\1\2\u00bd\u00be\7\30\2\2\u00be\u00c9\b\21\1\2\u00bf\u00c0"+
		"\7\31\2\2\u00c0\u00c9\b\21\1\2\u00c1\u00c2\7\32\2\2\u00c2\u00c9\b\21\1"+
		"\2\u00c3\u00c4\7\20\2\2\u00c4\u00c5\5\36\20\2\u00c5\u00c6\7\21\2\2\u00c6"+
		"\u00c7\b\21\1\2\u00c7\u00c9\3\2\2\2\u00c8\u00b5\3\2\2\2\u00c8\u00b9\3"+
		"\2\2\2\u00c8\u00bd\3\2\2\2\u00c8\u00bf\3\2\2\2\u00c8\u00c1\3\2\2\2\u00c8"+
		"\u00c3\3\2\2\2\u00c9!\3\2\2\2\17)\64DT^fv\u0086\u009c\u00a4\u00b0\u00b2"+
		"\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}