// Generated from src/main/java/mule/v4/dataweave/parser/DataWeave.g4 by ANTLR 4.13.2
package mule.v4.dataweave.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DataWeaveParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, VAR=14, FUNCTION=15, IMPORT=16, 
		NAMESPACE=17, OUTPUT=18, INPUT=19, DW=20, TYPE=21, ASSIGN=22, ARROW=23, 
		BOOLEAN=24, AND=25, OR=26, NOT=27, IF=28, ELSE=29, UNLESS=30, USING=31, 
		AS=32, IS=33, NULL=34, DEFAULT=35, CASE=36, THROW=37, DO=38, FOR=39, YIELD=40, 
		ENUM=41, PRIVATE=42, ASYNC=43, MAP=44, FILTER=45, GROUP_BY=46, SIZE_OF=47, 
		UPPER=48, LOWER=49, REPLACE=50, WITH=51, FROM=52, NOW=53, OPERATOR_EQUALITY=54, 
		OPERATOR_RELATIONAL=55, OPERATOR_MULTIPLICATIVE=56, OPERATOR_ADDITIVE=57, 
		MINUS=58, OPERATOR_RANGE=59, CONCAT=60, IDENTIFIER=61, INDEX_IDENTIFIER=62, 
		VALUE_IDENTIFIER=63, URL=64, MEDIA_TYPE=65, NUMBER=66, BLOCK_COMMENT=67, 
		STRING=68, DATE=69, REGEX=70, DOT=71, DOUBLE_COLON=72, COLON=73, COMMA=74, 
		LCURLY=75, RCURLY=76, LSQUARE=77, RSQUARE=78, LPAREN=79, RPAREN=80, SEPARATOR=81, 
		WS=82, NEWLINE=83, COMMENT=84, STAR=85, AT=86, QUESTION=87;
	public static final int
		RULE_script = 0, RULE_header = 1, RULE_directive = 2, RULE_dwVersion = 3, 
		RULE_outputDirective = 4, RULE_inputDirective = 5, RULE_importDirective = 6, 
		RULE_importSpec = 7, RULE_namespaceDirective = 8, RULE_variableDeclaration = 9, 
		RULE_functionDeclaration = 10, RULE_typeDeclaration = 11, RULE_body = 12, 
		RULE_expression = 13, RULE_operationExpression = 14, RULE_defaultExpression = 15, 
		RULE_implicitLambdaExpression = 16, RULE_inlineLambda = 17, RULE_functionParameters = 18, 
		RULE_functionParameter = 19, RULE_logicalOrExpression = 20, RULE_logicalAndExpression = 21, 
		RULE_equalityExpression = 22, RULE_relationalExpression = 23, RULE_additiveExpression = 24, 
		RULE_additiveOperator = 25, RULE_multiplicativeExpression = 26, RULE_typeCoercionExpression = 27, 
		RULE_formatOption = 28, RULE_unaryExpression = 29, RULE_primaryExpression = 30, 
		RULE_builtInFunction = 31, RULE_grouped = 32, RULE_doBlock = 33, RULE_selectorExpression = 34, 
		RULE_literal = 35, RULE_array = 36, RULE_object = 37, RULE_objectField = 38, 
		RULE_qualifiedIdentifier = 39, RULE_functionCall = 40, RULE_typeExpression = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "header", "directive", "dwVersion", "outputDirective", "inputDirective", 
			"importDirective", "importSpec", "namespaceDirective", "variableDeclaration", 
			"functionDeclaration", "typeDeclaration", "body", "expression", "operationExpression", 
			"defaultExpression", "implicitLambdaExpression", "inlineLambda", "functionParameters", 
			"functionParameter", "logicalOrExpression", "logicalAndExpression", "equalityExpression", 
			"relationalExpression", "additiveExpression", "additiveOperator", "multiplicativeExpression", 
			"typeCoercionExpression", "formatOption", "unaryExpression", "primaryExpression", 
			"builtInFunction", "grouped", "doBlock", "selectorExpression", "literal", 
			"array", "object", "objectField", "qualifiedIdentifier", "functionCall", 
			"typeExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'String'", "'Boolean'", "'Number'", "'Regex'", "'Null'", "'Date'", 
			"'DateTime'", "'LocalDateTime'", "'LocalTime'", "'Time'", "'Period'", 
			"'Object'", "'Any'", "'var'", "'fun'", "'import'", "'ns'", "'output'", 
			"'input'", "'%dw'", "'type'", "'='", "'->'", null, "'and'", "'or'", null, 
			"'if'", "'else'", "'unless'", "'using'", "'as'", "'is'", "'null'", "'default'", 
			"'case'", "'throw'", "'do'", "'for'", "'yield'", "'enum'", "'private'", 
			"'async'", "'map'", "'filter'", "'groupBy'", "'sizeOf'", "'upper'", "'lower'", 
			"'replace'", "'with'", "'from'", "'now'", null, null, null, null, "'-'", 
			"'..'", "'++'", null, "'$$'", "'$'", null, null, null, null, null, null, 
			null, "'.'", "'::'", "':'", "','", "'{'", "'}'", "'['", "']'", "'('", 
			"')'", "'---'", null, null, null, "'*'", "'@'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "VAR", "FUNCTION", "IMPORT", "NAMESPACE", "OUTPUT", "INPUT", 
			"DW", "TYPE", "ASSIGN", "ARROW", "BOOLEAN", "AND", "OR", "NOT", "IF", 
			"ELSE", "UNLESS", "USING", "AS", "IS", "NULL", "DEFAULT", "CASE", "THROW", 
			"DO", "FOR", "YIELD", "ENUM", "PRIVATE", "ASYNC", "MAP", "FILTER", "GROUP_BY", 
			"SIZE_OF", "UPPER", "LOWER", "REPLACE", "WITH", "FROM", "NOW", "OPERATOR_EQUALITY", 
			"OPERATOR_RELATIONAL", "OPERATOR_MULTIPLICATIVE", "OPERATOR_ADDITIVE", 
			"MINUS", "OPERATOR_RANGE", "CONCAT", "IDENTIFIER", "INDEX_IDENTIFIER", 
			"VALUE_IDENTIFIER", "URL", "MEDIA_TYPE", "NUMBER", "BLOCK_COMMENT", "STRING", 
			"DATE", "REGEX", "DOT", "DOUBLE_COLON", "COLON", "COMMA", "LCURLY", "RCURLY", 
			"LSQUARE", "RSQUARE", "LPAREN", "RPAREN", "SEPARATOR", "WS", "NEWLINE", 
			"COMMENT", "STAR", "AT", "QUESTION"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "DataWeave.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DataWeaveParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DataWeaveParser.EOF, 0); }
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(DataWeaveParser.SEPARATOR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DataWeaveParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DataWeaveParser.NEWLINE, i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4177920L) != 0)) {
				{
				setState(84);
				header();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEPARATOR) {
				{
				setState(87);
				match(SEPARATOR);
				}
			}

			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 47416319284364313L) != 0)) {
				{
				setState(90);
				body();
				}
			}

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(93);
				match(NEWLINE);
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HeaderContext extends ParserRuleContext {
		public List<DirectiveContext> directive() {
			return getRuleContexts(DirectiveContext.class);
		}
		public DirectiveContext directive(int i) {
			return getRuleContext(DirectiveContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DataWeaveParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DataWeaveParser.NEWLINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(DataWeaveParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(DataWeaveParser.WS, i);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				directive();
				setState(105);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(102);
						_la = _input.LA(1);
						if ( !(_la==WS || _la==NEWLINE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(107);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				}
				}
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4177920L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class DirectiveContext extends ParserRuleContext {
		public DwVersionContext dwVersion() {
			return getRuleContext(DwVersionContext.class,0);
		}
		public OutputDirectiveContext outputDirective() {
			return getRuleContext(OutputDirectiveContext.class,0);
		}
		public InputDirectiveContext inputDirective() {
			return getRuleContext(InputDirectiveContext.class,0);
		}
		public ImportDirectiveContext importDirective() {
			return getRuleContext(ImportDirectiveContext.class,0);
		}
		public NamespaceDirectiveContext namespaceDirective() {
			return getRuleContext(NamespaceDirectiveContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public DirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectiveContext directive() throws RecognitionException {
		DirectiveContext _localctx = new DirectiveContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_directive);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DW:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				dwVersion();
				}
				break;
			case OUTPUT:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				outputDirective();
				}
				break;
			case INPUT:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				inputDirective();
				}
				break;
			case IMPORT:
				enterOuterAlt(_localctx, 4);
				{
				setState(115);
				importDirective();
				}
				break;
			case NAMESPACE:
				enterOuterAlt(_localctx, 5);
				{
				setState(116);
				namespaceDirective();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(117);
				variableDeclaration();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 7);
				{
				setState(118);
				functionDeclaration();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(119);
				typeDeclaration();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DwVersionContext extends ParserRuleContext {
		public TerminalNode DW() { return getToken(DataWeaveParser.DW, 0); }
		public TerminalNode NUMBER() { return getToken(DataWeaveParser.NUMBER, 0); }
		public DwVersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dwVersion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDwVersion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDwVersion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDwVersion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DwVersionContext dwVersion() throws RecognitionException {
		DwVersionContext _localctx = new DwVersionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dwVersion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(DW);
			setState(123);
			match(NUMBER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OutputDirectiveContext extends ParserRuleContext {
		public TerminalNode OUTPUT() { return getToken(DataWeaveParser.OUTPUT, 0); }
		public TerminalNode MEDIA_TYPE() { return getToken(DataWeaveParser.MEDIA_TYPE, 0); }
		public OutputDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterOutputDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitOutputDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitOutputDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputDirectiveContext outputDirective() throws RecognitionException {
		OutputDirectiveContext _localctx = new OutputDirectiveContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_outputDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(OUTPUT);
			setState(126);
			match(MEDIA_TYPE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class InputDirectiveContext extends ParserRuleContext {
		public TerminalNode INPUT() { return getToken(DataWeaveParser.INPUT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode MEDIA_TYPE() { return getToken(DataWeaveParser.MEDIA_TYPE, 0); }
		public InputDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterInputDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitInputDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitInputDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputDirectiveContext inputDirective() throws RecognitionException {
		InputDirectiveContext _localctx = new InputDirectiveContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inputDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(INPUT);
			setState(129);
			match(IDENTIFIER);
			setState(130);
			match(MEDIA_TYPE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDirectiveContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(DataWeaveParser.IMPORT, 0); }
		public List<ImportSpecContext> importSpec() {
			return getRuleContexts(ImportSpecContext.class);
		}
		public ImportSpecContext importSpec(int i) {
			return getRuleContext(ImportSpecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DataWeaveParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DataWeaveParser.COMMA, i);
		}
		public TerminalNode FROM() { return getToken(DataWeaveParser.FROM, 0); }
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TerminalNode STRING() { return getToken(DataWeaveParser.STRING, 0); }
		public ImportDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterImportDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitImportDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitImportDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDirectiveContext importDirective() throws RecognitionException {
		ImportDirectiveContext _localctx = new ImportDirectiveContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_importDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(IMPORT);
			setState(133);
			importSpec();
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(134);
				match(COMMA);
				setState(135);
				importSpec();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(141);
				match(FROM);
				setState(144);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(142);
					qualifiedIdentifier();
					}
					break;
				case STRING:
					{
					setState(143);
					match(STRING);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class ImportSpecContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(DataWeaveParser.STAR, 0); }
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TerminalNode AS() { return getToken(DataWeaveParser.AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public ImportSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterImportSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitImportSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitImportSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportSpecContext importSpec() throws RecognitionException {
		ImportSpecContext _localctx = new ImportSpecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importSpec);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(STAR);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				qualifiedIdentifier();
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(150);
					match(AS);
					setState(151);
					match(IDENTIFIER);
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class NamespaceDirectiveContext extends ParserRuleContext {
		public TerminalNode NAMESPACE() { return getToken(DataWeaveParser.NAMESPACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode URL() { return getToken(DataWeaveParser.URL, 0); }
		public NamespaceDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNamespaceDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNamespaceDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNamespaceDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceDirectiveContext namespaceDirective() throws RecognitionException {
		NamespaceDirectiveContext _localctx = new NamespaceDirectiveContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_namespaceDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(NAMESPACE);
			setState(157);
			match(IDENTIFIER);
			setState(158);
			match(URL);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(DataWeaveParser.VAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(DataWeaveParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(VAR);
			setState(161);
			match(IDENTIFIER);
			setState(162);
			match(ASSIGN);
			setState(163);
			expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(DataWeaveParser.FUNCTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public TerminalNode ASSIGN() { return getToken(DataWeaveParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionParametersContext functionParameters() {
			return getRuleContext(FunctionParametersContext.class,0);
		}
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(FUNCTION);
			setState(166);
			match(IDENTIFIER);
			setState(167);
			match(LPAREN);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(168);
				functionParameters();
				}
			}

			setState(171);
			match(RPAREN);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(172);
				match(COLON);
				setState(173);
				typeExpression();
				}
			}

			setState(176);
			match(ASSIGN);
			setState(177);
			expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDeclarationContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(DataWeaveParser.TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(DataWeaveParser.ASSIGN, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(TYPE);
			setState(180);
			match(IDENTIFIER);
			setState(181);
			match(ASSIGN);
			setState(182);
			typeExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DataWeaveParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DataWeaveParser.NEWLINE, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_body);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			expression();
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(185);
					match(NEWLINE);
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			operationExpression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OperationExpressionContext extends ParserRuleContext {
		public OperationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationExpression; }
	 
		public OperationExpressionContext() { }
		public void copyFrom(OperationExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MapExpressionContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode MAP() { return getToken(DataWeaveParser.MAP, 0); }
		public ImplicitLambdaExpressionContext implicitLambdaExpression() {
			return getRuleContext(ImplicitLambdaExpressionContext.class,0);
		}
		public MapExpressionContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterMapExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitMapExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitMapExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixFunctionCallContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public DefaultExpressionContext defaultExpression() {
			return getRuleContext(DefaultExpressionContext.class,0);
		}
		public InfixFunctionCallContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterInfixFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitInfixFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitInfixFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OperationExpressionWrapperContext extends OperationExpressionContext {
		public DefaultExpressionContext defaultExpression() {
			return getRuleContext(DefaultExpressionContext.class,0);
		}
		public OperationExpressionWrapperContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterOperationExpressionWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitOperationExpressionWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitOperationExpressionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FilterExpressionContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode FILTER() { return getToken(DataWeaveParser.FILTER, 0); }
		public ImplicitLambdaExpressionContext implicitLambdaExpression() {
			return getRuleContext(ImplicitLambdaExpressionContext.class,0);
		}
		public FilterExpressionContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFilterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFilterExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFilterExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GroupByExpressionContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode GROUP_BY() { return getToken(DataWeaveParser.GROUP_BY, 0); }
		public ImplicitLambdaExpressionContext implicitLambdaExpression() {
			return getRuleContext(ImplicitLambdaExpressionContext.class,0);
		}
		public GroupByExpressionContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterGroupByExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitGroupByExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitGroupByExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReplaceExpressionContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode REPLACE() { return getToken(DataWeaveParser.REPLACE, 0); }
		public TerminalNode REGEX() { return getToken(DataWeaveParser.REGEX, 0); }
		public TerminalNode WITH() { return getToken(DataWeaveParser.WITH, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReplaceExpressionContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterReplaceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitReplaceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitReplaceExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConcatExpressionContext extends OperationExpressionContext {
		public OperationExpressionContext operationExpression() {
			return getRuleContext(OperationExpressionContext.class,0);
		}
		public TerminalNode CONCAT() { return getToken(DataWeaveParser.CONCAT, 0); }
		public DefaultExpressionContext defaultExpression() {
			return getRuleContext(DefaultExpressionContext.class,0);
		}
		public ConcatExpressionContext(OperationExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterConcatExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitConcatExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitConcatExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationExpressionContext operationExpression() throws RecognitionException {
		return operationExpression(0);
	}

	private OperationExpressionContext operationExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OperationExpressionContext _localctx = new OperationExpressionContext(_ctx, _parentState);
		OperationExpressionContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_operationExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new OperationExpressionWrapperContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(194);
			defaultExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new FilterExpressionContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(196);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(197);
						match(FILTER);
						setState(198);
						implicitLambdaExpression();
						}
						break;
					case 2:
						{
						_localctx = new MapExpressionContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(199);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(200);
						match(MAP);
						setState(201);
						implicitLambdaExpression();
						}
						break;
					case 3:
						{
						_localctx = new GroupByExpressionContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(202);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(203);
						match(GROUP_BY);
						setState(204);
						implicitLambdaExpression();
						}
						break;
					case 4:
						{
						_localctx = new ReplaceExpressionContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(205);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(206);
						match(REPLACE);
						setState(207);
						match(REGEX);
						setState(208);
						match(WITH);
						setState(209);
						expression();
						}
						break;
					case 5:
						{
						_localctx = new ConcatExpressionContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(210);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(211);
						match(CONCAT);
						setState(212);
						defaultExpression();
						}
						break;
					case 6:
						{
						_localctx = new InfixFunctionCallContext(new OperationExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_operationExpression);
						setState(213);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(214);
						match(IDENTIFIER);
						setState(215);
						defaultExpression();
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultExpressionContext extends ParserRuleContext {
		public List<LogicalOrExpressionContext> logicalOrExpression() {
			return getRuleContexts(LogicalOrExpressionContext.class);
		}
		public LogicalOrExpressionContext logicalOrExpression(int i) {
			return getRuleContext(LogicalOrExpressionContext.class,i);
		}
		public TerminalNode DEFAULT() { return getToken(DataWeaveParser.DEFAULT, 0); }
		public DefaultExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDefaultExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDefaultExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDefaultExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultExpressionContext defaultExpression() throws RecognitionException {
		DefaultExpressionContext _localctx = new DefaultExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_defaultExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			logicalOrExpression();
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(222);
				match(DEFAULT);
				setState(223);
				logicalOrExpression();
				}
				break;
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImplicitLambdaExpressionContext extends ParserRuleContext {
		public InlineLambdaContext inlineLambda() {
			return getRuleContext(InlineLambdaContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ImplicitLambdaExpressionContext implicitLambdaExpression() {
			return getRuleContext(ImplicitLambdaExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public ImplicitLambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicitLambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterImplicitLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitImplicitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitImplicitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplicitLambdaExpressionContext implicitLambdaExpression() throws RecognitionException {
		ImplicitLambdaExpressionContext _localctx = new ImplicitLambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_implicitLambdaExpression);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				inlineLambda();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
				match(LPAREN);
				setState(229);
				implicitLambdaExpression();
				setState(230);
				match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class InlineLambdaContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public FunctionParametersContext functionParameters() {
			return getRuleContext(FunctionParametersContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public TerminalNode ARROW() { return getToken(DataWeaveParser.ARROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InlineLambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineLambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterInlineLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitInlineLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitInlineLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineLambdaContext inlineLambda() throws RecognitionException {
		InlineLambdaContext _localctx = new InlineLambdaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_inlineLambda);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(LPAREN);
			setState(235);
			functionParameters();
			setState(236);
			match(RPAREN);
			setState(237);
			match(ARROW);
			setState(238);
			expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionParametersContext extends ParserRuleContext {
		public List<FunctionParameterContext> functionParameter() {
			return getRuleContexts(FunctionParameterContext.class);
		}
		public FunctionParameterContext functionParameter(int i) {
			return getRuleContext(FunctionParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DataWeaveParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DataWeaveParser.COMMA, i);
		}
		public FunctionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFunctionParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFunctionParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFunctionParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParametersContext functionParameters() throws RecognitionException {
		FunctionParametersContext _localctx = new FunctionParametersContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			functionParameter();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(241);
				match(COMMA);
				setState(242);
				functionParameter();
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(DataWeaveParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFunctionParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFunctionParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFunctionParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParameterContext functionParameter() throws RecognitionException {
		FunctionParameterContext _localctx = new FunctionParameterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(IDENTIFIER);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(249);
				match(COLON);
				setState(250);
				typeExpression();
				}
			}

			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(253);
				match(ASSIGN);
				setState(254);
				expression();
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public List<LogicalAndExpressionContext> logicalAndExpression() {
			return getRuleContexts(LogicalAndExpressionContext.class);
		}
		public LogicalAndExpressionContext logicalAndExpression(int i) {
			return getRuleContext(LogicalAndExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(DataWeaveParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(DataWeaveParser.OR, i);
		}
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLogicalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_logicalOrExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			logicalAndExpression();
			setState(262);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(258);
					match(OR);
					setState(259);
					logicalAndExpression();
					}
					} 
				}
				setState(264);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(DataWeaveParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(DataWeaveParser.AND, i);
		}
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLogicalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_logicalAndExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			equalityExpression();
			setState(270);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(266);
					match(AND);
					setState(267);
					equalityExpression();
					}
					} 
				}
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> OPERATOR_EQUALITY() { return getTokens(DataWeaveParser.OPERATOR_EQUALITY); }
		public TerminalNode OPERATOR_EQUALITY(int i) {
			return getToken(DataWeaveParser.OPERATOR_EQUALITY, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_equalityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			relationalExpression();
			setState(278);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(274);
					match(OPERATOR_EQUALITY);
					setState(275);
					relationalExpression();
					}
					} 
				}
				setState(280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends ParserRuleContext {
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	 
		public RelationalExpressionContext() { }
		public void copyFrom(RelationalExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsExpressionContext extends RelationalExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(DataWeaveParser.IS, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public IsExpressionContext(RelationalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterIsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitIsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitIsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalComparisonContext extends RelationalExpressionContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<TerminalNode> OPERATOR_RELATIONAL() { return getTokens(DataWeaveParser.OPERATOR_RELATIONAL); }
		public TerminalNode OPERATOR_RELATIONAL(int i) {
			return getToken(DataWeaveParser.OPERATOR_RELATIONAL, i);
		}
		public RelationalComparisonContext(RelationalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterRelationalComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitRelationalComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitRelationalComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_relationalExpression);
		try {
			int _alt;
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new RelationalComparisonContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				additiveExpression();
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(282);
						match(OPERATOR_RELATIONAL);
						setState(283);
						additiveExpression();
						}
						} 
					}
					setState(288);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new IsExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				additiveExpression();
				setState(290);
				match(IS);
				setState(291);
				typeExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<AdditiveOperatorContext> additiveOperator() {
			return getRuleContexts(AdditiveOperatorContext.class);
		}
		public AdditiveOperatorContext additiveOperator(int i) {
			return getRuleContext(AdditiveOperatorContext.class,i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_additiveExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			multiplicativeExpression();
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(296);
					additiveOperator();
					setState(297);
					multiplicativeExpression();
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveOperatorContext extends ParserRuleContext {
		public TerminalNode OPERATOR_ADDITIVE() { return getToken(DataWeaveParser.OPERATOR_ADDITIVE, 0); }
		public TerminalNode MINUS() { return getToken(DataWeaveParser.MINUS, 0); }
		public AdditiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterAdditiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitAdditiveOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitAdditiveOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			_la = _input.LA(1);
			if ( !(_la==OPERATOR_ADDITIVE || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<TypeCoercionExpressionContext> typeCoercionExpression() {
			return getRuleContexts(TypeCoercionExpressionContext.class);
		}
		public TypeCoercionExpressionContext typeCoercionExpression(int i) {
			return getRuleContext(TypeCoercionExpressionContext.class,i);
		}
		public List<TerminalNode> OPERATOR_MULTIPLICATIVE() { return getTokens(DataWeaveParser.OPERATOR_MULTIPLICATIVE); }
		public TerminalNode OPERATOR_MULTIPLICATIVE(int i) {
			return getToken(DataWeaveParser.OPERATOR_MULTIPLICATIVE, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_multiplicativeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			typeCoercionExpression(0);
			setState(311);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(307);
					match(OPERATOR_MULTIPLICATIVE);
					setState(308);
					typeCoercionExpression(0);
					}
					} 
				}
				setState(313);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeCoercionExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TypeCoercionExpressionContext typeCoercionExpression() {
			return getRuleContext(TypeCoercionExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(DataWeaveParser.AS, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public FormatOptionContext formatOption() {
			return getRuleContext(FormatOptionContext.class,0);
		}
		public TypeCoercionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeCoercionExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterTypeCoercionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitTypeCoercionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitTypeCoercionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeCoercionExpressionContext typeCoercionExpression() throws RecognitionException {
		return typeCoercionExpression(0);
	}

	private TypeCoercionExpressionContext typeCoercionExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeCoercionExpressionContext _localctx = new TypeCoercionExpressionContext(_ctx, _parentState);
		TypeCoercionExpressionContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_typeCoercionExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(315);
			unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(325);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeCoercionExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_typeCoercionExpression);
					setState(317);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(318);
					match(AS);
					setState(319);
					typeExpression();
					setState(321);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(320);
						formatOption();
						}
						break;
					}
					}
					} 
				}
				setState(327);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FormatOptionContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(DataWeaveParser.LCURLY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public TerminalNode STRING() { return getToken(DataWeaveParser.STRING, 0); }
		public TerminalNode RCURLY() { return getToken(DataWeaveParser.RCURLY, 0); }
		public FormatOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFormatOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFormatOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFormatOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormatOptionContext formatOption() throws RecognitionException {
		FormatOptionContext _localctx = new FormatOptionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_formatOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(LCURLY);
			setState(329);
			match(IDENTIFIER);
			setState(330);
			match(COLON);
			setState(331);
			match(STRING);
			setState(332);
			match(RCURLY);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	 
		public UnaryExpressionContext() { }
		public void copyFrom(UnaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionWrapperContext extends UnaryExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public PrimaryExpressionWrapperContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterPrimaryExpressionWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitPrimaryExpressionWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitPrimaryExpressionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegativeExpressionContext extends UnaryExpressionContext {
		public TerminalNode MINUS() { return getToken(DataWeaveParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegativeExpressionContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNegativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNegativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNegativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SizeOfExpressionWithParenthesesContext extends UnaryExpressionContext {
		public TerminalNode SIZE_OF() { return getToken(DataWeaveParser.SIZE_OF, 0); }
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public SizeOfExpressionWithParenthesesContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSizeOfExpressionWithParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSizeOfExpressionWithParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSizeOfExpressionWithParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UpperExpressionWithParenthesesContext extends UnaryExpressionContext {
		public TerminalNode UPPER() { return getToken(DataWeaveParser.UPPER, 0); }
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public UpperExpressionWithParenthesesContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterUpperExpressionWithParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitUpperExpressionWithParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitUpperExpressionWithParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SizeOfExpressionContext extends UnaryExpressionContext {
		public TerminalNode SIZE_OF() { return getToken(DataWeaveParser.SIZE_OF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SizeOfExpressionContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSizeOfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSizeOfExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSizeOfExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UpperExpressionContext extends UnaryExpressionContext {
		public TerminalNode UPPER() { return getToken(DataWeaveParser.UPPER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UpperExpressionContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterUpperExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitUpperExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitUpperExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LowerExpressionContext extends UnaryExpressionContext {
		public TerminalNode LOWER() { return getToken(DataWeaveParser.LOWER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LowerExpressionContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLowerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLowerExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLowerExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LowerExpressionWithParenthesesContext extends UnaryExpressionContext {
		public TerminalNode LOWER() { return getToken(DataWeaveParser.LOWER, 0); }
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public LowerExpressionWithParenthesesContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLowerExpressionWithParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLowerExpressionWithParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLowerExpressionWithParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends UnaryExpressionContext {
		public TerminalNode NOT() { return getToken(DataWeaveParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_unaryExpression);
		try {
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new SizeOfExpressionWithParenthesesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				match(SIZE_OF);
				setState(335);
				match(LPAREN);
				setState(336);
				expression();
				setState(337);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new SizeOfExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				match(SIZE_OF);
				setState(340);
				expression();
				}
				break;
			case 3:
				_localctx = new UpperExpressionWithParenthesesContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(341);
				match(UPPER);
				setState(342);
				match(LPAREN);
				setState(343);
				expression();
				setState(344);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new UpperExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(346);
				match(UPPER);
				setState(347);
				expression();
				}
				break;
			case 5:
				_localctx = new LowerExpressionWithParenthesesContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(348);
				match(LOWER);
				setState(349);
				match(LPAREN);
				setState(350);
				expression();
				setState(351);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new LowerExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(353);
				match(LOWER);
				setState(354);
				expression();
				}
				break;
			case 7:
				_localctx = new NotExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(355);
				match(NOT);
				setState(356);
				expression();
				}
				break;
			case 8:
				_localctx = new NegativeExpressionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(357);
				match(MINUS);
				setState(358);
				expression();
				}
				break;
			case 9:
				_localctx = new PrimaryExpressionWrapperContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(359);
				primaryExpression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LambdaExpressionContext extends PrimaryExpressionContext {
		public InlineLambdaContext inlineLambda() {
			return getRuleContext(InlineLambdaContext.class,0);
		}
		public LambdaExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayExpressionContext extends PrimaryExpressionContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterArrayExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitArrayExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitArrayExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectorExpressionWrapperWithDefaultContext extends PrimaryExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public SelectorExpressionContext selectorExpression() {
			return getRuleContext(SelectorExpressionContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(DataWeaveParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SelectorExpressionWrapperWithDefaultContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSelectorExpressionWrapperWithDefault(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSelectorExpressionWrapperWithDefault(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSelectorExpressionWrapperWithDefault(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierExpressionContext extends PrimaryExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public IdentifierExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectorExpressionWrapperContext extends PrimaryExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public SelectorExpressionContext selectorExpression() {
			return getRuleContext(SelectorExpressionContext.class,0);
		}
		public SelectorExpressionWrapperContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSelectorExpressionWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSelectorExpressionWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSelectorExpressionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexIdentifierExpressionContext extends PrimaryExpressionContext {
		public TerminalNode INDEX_IDENTIFIER() { return getToken(DataWeaveParser.INDEX_IDENTIFIER, 0); }
		public IndexIdentifierExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterIndexIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitIndexIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitIndexIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GroupedExpressionContext extends PrimaryExpressionContext {
		public GroupedContext grouped() {
			return getRuleContext(GroupedContext.class,0);
		}
		public GroupedExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterGroupedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitGroupedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitGroupedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjectExpressionContext extends PrimaryExpressionContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ObjectExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterObjectExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitObjectExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitObjectExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseConditionContext extends PrimaryExpressionContext {
		public List<TerminalNode> IF() { return getTokens(DataWeaveParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(DataWeaveParser.IF, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(DataWeaveParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(DataWeaveParser.LPAREN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(DataWeaveParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(DataWeaveParser.RPAREN, i);
		}
		public List<TerminalNode> ELSE() { return getTokens(DataWeaveParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(DataWeaveParser.ELSE, i);
		}
		public IfElseConditionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterIfElseCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitIfElseCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitIfElseCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BuiltInFunctionExpressionContext extends PrimaryExpressionContext {
		public BuiltInFunctionContext builtInFunction() {
			return getRuleContext(BuiltInFunctionContext.class,0);
		}
		public BuiltInFunctionExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterBuiltInFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitBuiltInFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitBuiltInFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoBlockExpressionContext extends PrimaryExpressionContext {
		public DoBlockContext doBlock() {
			return getRuleContext(DoBlockContext.class,0);
		}
		public DoBlockExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDoBlockExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDoBlockExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDoBlockExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExpressionContext extends PrimaryExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFunctionCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExpressionContext extends PrimaryExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ValueIdentifierExpressionContext extends PrimaryExpressionContext {
		public TerminalNode VALUE_IDENTIFIER() { return getToken(DataWeaveParser.VALUE_IDENTIFIER, 0); }
		public ValueIdentifierExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterValueIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitValueIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitValueIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		return primaryExpression(0);
	}

	private PrimaryExpressionContext primaryExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, _parentState);
		PrimaryExpressionContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_primaryExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				_localctx = new IfElseConditionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(363);
				match(IF);
				setState(364);
				match(LPAREN);
				setState(365);
				expression();
				setState(366);
				match(RPAREN);
				setState(367);
				expression();
				setState(377);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(368);
						match(ELSE);
						setState(369);
						match(IF);
						setState(370);
						match(LPAREN);
						setState(371);
						expression();
						setState(372);
						match(RPAREN);
						setState(373);
						expression();
						}
						} 
					}
					setState(379);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
				setState(382);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(380);
					match(ELSE);
					setState(381);
					expression();
					}
					break;
				}
				}
				break;
			case 2:
				{
				_localctx = new DoBlockExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(384);
				doBlock();
				}
				break;
			case 3:
				{
				_localctx = new LambdaExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(385);
				inlineLambda();
				}
				break;
			case 4:
				{
				_localctx = new GroupedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(386);
				grouped();
				}
				break;
			case 5:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(387);
				literal();
				}
				break;
			case 6:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(388);
				functionCall();
				}
				break;
			case 7:
				{
				_localctx = new ArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(389);
				array();
				}
				break;
			case 8:
				{
				_localctx = new ObjectExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(390);
				object();
				}
				break;
			case 9:
				{
				_localctx = new BuiltInFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(391);
				builtInFunction();
				}
				break;
			case 10:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(392);
				match(IDENTIFIER);
				}
				break;
			case 11:
				{
				_localctx = new ValueIdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(393);
				match(VALUE_IDENTIFIER);
				}
				break;
			case 12:
				{
				_localctx = new IndexIdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(394);
				match(INDEX_IDENTIFIER);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(406);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(404);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						_localctx = new SelectorExpressionWrapperContext(new PrimaryExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_primaryExpression);
						setState(397);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(398);
						selectorExpression();
						}
						break;
					case 2:
						{
						_localctx = new SelectorExpressionWrapperWithDefaultContext(new PrimaryExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_primaryExpression);
						setState(399);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(400);
						selectorExpression();
						setState(401);
						match(DEFAULT);
						setState(402);
						expression();
						}
						break;
					}
					} 
				}
				setState(408);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BuiltInFunctionContext extends ParserRuleContext {
		public BuiltInFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtInFunction; }
	 
		public BuiltInFunctionContext() { }
		public void copyFrom(BuiltInFunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NowFunctionContext extends BuiltInFunctionContext {
		public TerminalNode NOW() { return getToken(DataWeaveParser.NOW, 0); }
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public NowFunctionContext(BuiltInFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNowFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNowFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNowFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltInFunctionContext builtInFunction() throws RecognitionException {
		BuiltInFunctionContext _localctx = new BuiltInFunctionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_builtInFunction);
		try {
			_localctx = new NowFunctionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			match(NOW);
			setState(410);
			match(LPAREN);
			setState(411);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GroupedContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public GroupedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grouped; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterGrouped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitGrouped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitGrouped(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupedContext grouped() throws RecognitionException {
		GroupedContext _localctx = new GroupedContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_grouped);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(LPAREN);
			setState(414);
			expression();
			setState(415);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DoBlockContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(DataWeaveParser.DO, 0); }
		public TerminalNode LCURLY() { return getToken(DataWeaveParser.LCURLY, 0); }
		public TerminalNode SEPARATOR() { return getToken(DataWeaveParser.SEPARATOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(DataWeaveParser.RCURLY, 0); }
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public DoBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDoBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDoBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDoBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoBlockContext doBlock() throws RecognitionException {
		DoBlockContext _localctx = new DoBlockContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_doBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(DO);
			setState(418);
			match(LCURLY);
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4177920L) != 0)) {
				{
				setState(419);
				header();
				}
			}

			setState(422);
			match(SEPARATOR);
			setState(423);
			expression();
			setState(424);
			match(RCURLY);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectorExpressionContext extends ParserRuleContext {
		public SelectorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectorExpression; }
	 
		public SelectorExpressionContext() { }
		public void copyFrom(SelectorExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExistenceQuerySelectorContext extends SelectorExpressionContext {
		public TerminalNode QUESTION() { return getToken(DataWeaveParser.QUESTION, 0); }
		public ExistenceQuerySelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterExistenceQuerySelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitExistenceQuerySelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitExistenceQuerySelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SingleValueSelectorContext extends SelectorExpressionContext {
		public TerminalNode DOT() { return getToken(DataWeaveParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public SingleValueSelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSingleValueSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSingleValueSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSingleValueSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KeySelectorContext extends SelectorExpressionContext {
		public TerminalNode DOT() { return getToken(DataWeaveParser.DOT, 0); }
		public TerminalNode STRING() { return getToken(DataWeaveParser.STRING, 0); }
		public KeySelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterKeySelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitKeySelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitKeySelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexedSelectorContext extends SelectorExpressionContext {
		public TerminalNode LSQUARE() { return getToken(DataWeaveParser.LSQUARE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RSQUARE() { return getToken(DataWeaveParser.RSQUARE, 0); }
		public IndexedSelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterIndexedSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitIndexedSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitIndexedSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiValueSelectorContext extends SelectorExpressionContext {
		public TerminalNode DOT() { return getToken(DataWeaveParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(DataWeaveParser.STAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public MultiValueSelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterMultiValueSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitMultiValueSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitMultiValueSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AttributeSelectorContext extends SelectorExpressionContext {
		public TerminalNode DOT() { return getToken(DataWeaveParser.DOT, 0); }
		public TerminalNode AT() { return getToken(DataWeaveParser.AT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public AttributeSelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterAttributeSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitAttributeSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitAttributeSelector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DescendantsSelectorContext extends SelectorExpressionContext {
		public TerminalNode OPERATOR_RANGE() { return getToken(DataWeaveParser.OPERATOR_RANGE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public DescendantsSelectorContext(SelectorExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDescendantsSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDescendantsSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDescendantsSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectorExpressionContext selectorExpression() throws RecognitionException {
		SelectorExpressionContext _localctx = new SelectorExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_selectorExpression);
		try {
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				_localctx = new SingleValueSelectorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(426);
				match(DOT);
				setState(427);
				match(IDENTIFIER);
				}
				break;
			case 2:
				_localctx = new KeySelectorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(428);
				match(DOT);
				setState(429);
				match(STRING);
				}
				break;
			case 3:
				_localctx = new MultiValueSelectorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(430);
				match(DOT);
				setState(431);
				match(STAR);
				setState(432);
				match(IDENTIFIER);
				}
				break;
			case 4:
				_localctx = new DescendantsSelectorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				match(OPERATOR_RANGE);
				setState(434);
				match(IDENTIFIER);
				}
				break;
			case 5:
				_localctx = new IndexedSelectorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(435);
				match(LSQUARE);
				setState(436);
				expression();
				setState(437);
				match(RSQUARE);
				}
				break;
			case 6:
				_localctx = new AttributeSelectorContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(439);
				match(DOT);
				setState(440);
				match(AT);
				setState(441);
				match(IDENTIFIER);
				}
				break;
			case 7:
				_localctx = new ExistenceQuerySelectorContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(442);
				match(QUESTION);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(DataWeaveParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(DataWeaveParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(DataWeaveParser.BOOLEAN, 0); }
		public TerminalNode DATE() { return getToken(DataWeaveParser.DATE, 0); }
		public TerminalNode REGEX() { return getToken(DataWeaveParser.REGEX, 0); }
		public TerminalNode NULL() { return getToken(DataWeaveParser.NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !(((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 127543348823041L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LSQUARE() { return getToken(DataWeaveParser.LSQUARE, 0); }
		public TerminalNode RSQUARE() { return getToken(DataWeaveParser.RSQUARE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DataWeaveParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DataWeaveParser.COMMA, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(LSQUARE);
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 47416319284364313L) != 0)) {
				{
				setState(448);
				expression();
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(449);
					match(COMMA);
					setState(450);
					expression();
					}
					}
					setState(455);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(458);
			match(RSQUARE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectContext extends ParserRuleContext {
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
	 
		public ObjectContext() { }
		public void copyFrom(ObjectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiFieldObjectContext extends ObjectContext {
		public TerminalNode LCURLY() { return getToken(DataWeaveParser.LCURLY, 0); }
		public List<ObjectFieldContext> objectField() {
			return getRuleContexts(ObjectFieldContext.class);
		}
		public ObjectFieldContext objectField(int i) {
			return getRuleContext(ObjectFieldContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(DataWeaveParser.RCURLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DataWeaveParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DataWeaveParser.COMMA, i);
		}
		public MultiFieldObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterMultiFieldObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitMultiFieldObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitMultiFieldObject(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyObjectContext extends ObjectContext {
		public TerminalNode LCURLY() { return getToken(DataWeaveParser.LCURLY, 0); }
		public TerminalNode RCURLY() { return getToken(DataWeaveParser.RCURLY, 0); }
		public EmptyObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterEmptyObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitEmptyObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitEmptyObject(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SingleFieldObjectContext extends ObjectContext {
		public TerminalNode LCURLY() { return getToken(DataWeaveParser.LCURLY, 0); }
		public ObjectFieldContext objectField() {
			return getRuleContext(ObjectFieldContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(DataWeaveParser.RCURLY, 0); }
		public SingleFieldObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterSingleFieldObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitSingleFieldObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitSingleFieldObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_object);
		int _la;
		try {
			setState(479);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				_localctx = new MultiFieldObjectContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				match(LCURLY);
				setState(461);
				objectField();
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 270465L) != 0)) {
					{
					{
					setState(463);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(462);
						match(COMMA);
						}
					}

					setState(465);
					objectField();
					}
					}
					setState(470);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(471);
				match(RCURLY);
				}
				break;
			case 2:
				_localctx = new SingleFieldObjectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(473);
				match(LCURLY);
				setState(474);
				objectField();
				setState(475);
				match(RCURLY);
				}
				break;
			case 3:
				_localctx = new EmptyObjectContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(477);
				match(LCURLY);
				setState(478);
				match(RCURLY);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectFieldContext extends ParserRuleContext {
		public ObjectFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectField; }
	 
		public ObjectFieldContext() { }
		public void copyFrom(ObjectFieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalFieldContext extends ObjectFieldContext {
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public ObjectFieldContext objectField() {
			return getRuleContext(ObjectFieldContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public TerminalNode IF() { return getToken(DataWeaveParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionalFieldContext(ObjectFieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterConditionalField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitConditionalField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitConditionalField(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnquotedKeyFieldContext extends ObjectFieldContext {
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnquotedKeyFieldContext(ObjectFieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterUnquotedKeyField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitUnquotedKeyField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitUnquotedKeyField(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DynamicKeyFieldContext extends ObjectFieldContext {
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public DynamicKeyFieldContext(ObjectFieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDynamicKeyField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDynamicKeyField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDynamicKeyField(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class QuotedKeyFieldContext extends ObjectFieldContext {
		public TerminalNode STRING() { return getToken(DataWeaveParser.STRING, 0); }
		public TerminalNode COLON() { return getToken(DataWeaveParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuotedKeyFieldContext(ObjectFieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterQuotedKeyField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitQuotedKeyField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitQuotedKeyField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectFieldContext objectField() throws RecognitionException {
		ObjectFieldContext _localctx = new ObjectFieldContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_objectField);
		try {
			setState(499);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				_localctx = new UnquotedKeyFieldContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(481);
				match(IDENTIFIER);
				setState(482);
				match(COLON);
				setState(483);
				expression();
				}
				break;
			case 2:
				_localctx = new QuotedKeyFieldContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(484);
				match(STRING);
				setState(485);
				match(COLON);
				setState(486);
				expression();
				}
				break;
			case 3:
				_localctx = new DynamicKeyFieldContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(487);
				match(LPAREN);
				setState(488);
				expression();
				setState(489);
				match(RPAREN);
				setState(490);
				match(COLON);
				setState(491);
				expression();
				}
				break;
			case 4:
				_localctx = new ConditionalFieldContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(493);
				match(LPAREN);
				setState(494);
				objectField();
				setState(495);
				match(RPAREN);
				setState(496);
				match(IF);
				setState(497);
				expression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedIdentifierContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(DataWeaveParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(DataWeaveParser.IDENTIFIER, i);
		}
		public List<TerminalNode> DOUBLE_COLON() { return getTokens(DataWeaveParser.DOUBLE_COLON); }
		public TerminalNode DOUBLE_COLON(int i) {
			return getToken(DataWeaveParser.DOUBLE_COLON, i);
		}
		public QualifiedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterQualifiedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitQualifiedIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitQualifiedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_qualifiedIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			match(IDENTIFIER);
			setState(506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOUBLE_COLON) {
				{
				{
				setState(502);
				match(DOUBLE_COLON);
				setState(503);
				match(IDENTIFIER);
				}
				}
				setState(508);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(DataWeaveParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(DataWeaveParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DataWeaveParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DataWeaveParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			qualifiedIdentifier();
			setState(510);
			match(LPAREN);
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 47416319284364313L) != 0)) {
				{
				setState(511);
				expression();
				setState(516);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(512);
					match(COMMA);
					setState(513);
					expression();
					}
					}
					setState(518);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(521);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeExpressionContext extends ParserRuleContext {
		public TypeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpression; }
	 
		public TypeExpressionContext() { }
		public void copyFrom(TypeExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanTypeContext extends TypeExpressionContext {
		public BooleanTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterBooleanType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitBooleanType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberTypeContext extends TypeExpressionContext {
		public NumberTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNumberType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNumberType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNumberType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AnyTypeContext extends TypeExpressionContext {
		public AnyTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterAnyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitAnyType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitAnyType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TimeTypeContext extends TypeExpressionContext {
		public TimeTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterTimeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitTimeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitTimeType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LocalTimeTypeContext extends TypeExpressionContext {
		public LocalTimeTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLocalTimeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLocalTimeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLocalTimeType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DateTimeTypeContext extends TypeExpressionContext {
		public DateTimeTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDateTimeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDateTimeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDateTimeType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RegexTypeContext extends TypeExpressionContext {
		public RegexTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterRegexType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitRegexType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitRegexType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjectTypeContext extends TypeExpressionContext {
		public ObjectTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterObjectType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitObjectType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitObjectType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NamedTypeContext extends TypeExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(DataWeaveParser.IDENTIFIER, 0); }
		public NamedTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterNamedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitNamedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitNamedType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DateTypeContext extends TypeExpressionContext {
		public DateTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterDateType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitDateType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitDateType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PeriodTypeContext extends TypeExpressionContext {
		public PeriodTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterPeriodType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitPeriodType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitPeriodType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringTypeContext extends TypeExpressionContext {
		public StringTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitStringType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LocalDateTimeTypeContext extends TypeExpressionContext {
		public LocalDateTimeTypeContext(TypeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).enterLocalDateTimeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DataWeaveListener ) ((DataWeaveListener)listener).exitLocalDateTimeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DataWeaveVisitor ) return ((DataWeaveVisitor<? extends T>)visitor).visitLocalDateTimeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeExpressionContext typeExpression() throws RecognitionException {
		TypeExpressionContext _localctx = new TypeExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_typeExpression);
		try {
			setState(537);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new NamedTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(523);
				match(IDENTIFIER);
				}
				break;
			case T__0:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(524);
				match(T__0);
				}
				break;
			case T__1:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(525);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new NumberTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(526);
				match(T__2);
				}
				break;
			case T__3:
				_localctx = new RegexTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(527);
				match(T__3);
				}
				break;
			case T__4:
				_localctx = new RegexTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(528);
				match(T__4);
				}
				break;
			case T__5:
				_localctx = new DateTypeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(529);
				match(T__5);
				}
				break;
			case T__6:
				_localctx = new DateTimeTypeContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(530);
				match(T__6);
				}
				break;
			case T__7:
				_localctx = new LocalDateTimeTypeContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(531);
				match(T__7);
				}
				break;
			case T__8:
				_localctx = new LocalTimeTypeContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(532);
				match(T__8);
				}
				break;
			case T__9:
				_localctx = new TimeTypeContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(533);
				match(T__9);
				}
				break;
			case T__10:
				_localctx = new PeriodTypeContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(534);
				match(T__10);
				}
				break;
			case T__11:
				_localctx = new ObjectTypeContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(535);
				match(T__11);
				}
				break;
			case T__12:
				_localctx = new AnyTypeContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(536);
				match(T__12);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return operationExpression_sempred((OperationExpressionContext)_localctx, predIndex);
		case 27:
			return typeCoercionExpression_sempred((TypeCoercionExpressionContext)_localctx, predIndex);
		case 30:
			return primaryExpression_sempred((PrimaryExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean operationExpression_sempred(OperationExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean typeCoercionExpression_sempred(TypeCoercionExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean primaryExpression_sempred(PrimaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001W\u021c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0003\u0000V\b\u0000\u0001\u0000\u0003"+
		"\u0000Y\b\u0000\u0001\u0000\u0003\u0000\\\b\u0000\u0001\u0000\u0005\u0000"+
		"_\b\u0000\n\u0000\f\u0000b\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0005\u0001h\b\u0001\n\u0001\f\u0001k\t\u0001\u0004\u0001"+
		"m\b\u0001\u000b\u0001\f\u0001n\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002y\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0089\b\u0006\n\u0006\f\u0006"+
		"\u008c\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0091\b"+
		"\u0006\u0003\u0006\u0093\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0099\b\u0007\u0003\u0007\u009b\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00aa\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00af"+
		"\b\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0005\f\u00bb\b\f\n\f\f\f\u00be\t\f"+
		"\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u00d9\b\u000e\n\u000e\f\u000e\u00dc\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00e1\b\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00e9\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00f4\b\u0012\n\u0012"+
		"\f\u0012\u00f7\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u00fc\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0100\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0105\b\u0014\n\u0014\f\u0014"+
		"\u0108\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u010d\b"+
		"\u0015\n\u0015\f\u0015\u0110\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0005\u0016\u0115\b\u0016\n\u0016\f\u0016\u0118\t\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0005\u0017\u011d\b\u0017\n\u0017\f\u0017\u0120\t\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0126\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u012c\b\u0018"+
		"\n\u0018\f\u0018\u012f\t\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0005\u001a\u0136\b\u001a\n\u001a\f\u001a\u0139\t\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0142\b\u001b\u0005\u001b\u0144\b\u001b\n\u001b"+
		"\f\u001b\u0147\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u0169\b\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0005\u001e\u0178\b\u001e\n\u001e\f\u001e\u017b\t\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u017f\b\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u018c\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005"+
		"\u001e\u0195\b\u001e\n\u001e\f\u001e\u0198\t\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001"+
		"!\u0003!\u01a5\b!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u01bc\b\"\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0001$\u0005$\u01c4\b$\n$\f$\u01c7\t$\u0003$\u01c9"+
		"\b$\u0001$\u0001$\u0001%\u0001%\u0001%\u0003%\u01d0\b%\u0001%\u0005%\u01d3"+
		"\b%\n%\f%\u01d6\t%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0003%\u01e0\b%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0003&\u01f4\b&\u0001\'\u0001\'\u0001\'\u0005\'\u01f9\b\'\n\'\f\'\u01fc"+
		"\t\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0005(\u0203\b(\n(\f(\u0206\t"+
		"(\u0003(\u0208\b(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u021a"+
		"\b)\u0001)\u0000\u0003\u001c6<*\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"\u0000\u0003\u0001\u0000RS\u0001\u00009:\u0004\u0000\u0018\u0018\"\"B"+
		"BDF\u0252\u0000U\u0001\u0000\u0000\u0000\u0002l\u0001\u0000\u0000\u0000"+
		"\u0004x\u0001\u0000\u0000\u0000\u0006z\u0001\u0000\u0000\u0000\b}\u0001"+
		"\u0000\u0000\u0000\n\u0080\u0001\u0000\u0000\u0000\f\u0084\u0001\u0000"+
		"\u0000\u0000\u000e\u009a\u0001\u0000\u0000\u0000\u0010\u009c\u0001\u0000"+
		"\u0000\u0000\u0012\u00a0\u0001\u0000\u0000\u0000\u0014\u00a5\u0001\u0000"+
		"\u0000\u0000\u0016\u00b3\u0001\u0000\u0000\u0000\u0018\u00b8\u0001\u0000"+
		"\u0000\u0000\u001a\u00bf\u0001\u0000\u0000\u0000\u001c\u00c1\u0001\u0000"+
		"\u0000\u0000\u001e\u00dd\u0001\u0000\u0000\u0000 \u00e8\u0001\u0000\u0000"+
		"\u0000\"\u00ea\u0001\u0000\u0000\u0000$\u00f0\u0001\u0000\u0000\u0000"+
		"&\u00f8\u0001\u0000\u0000\u0000(\u0101\u0001\u0000\u0000\u0000*\u0109"+
		"\u0001\u0000\u0000\u0000,\u0111\u0001\u0000\u0000\u0000.\u0125\u0001\u0000"+
		"\u0000\u00000\u0127\u0001\u0000\u0000\u00002\u0130\u0001\u0000\u0000\u0000"+
		"4\u0132\u0001\u0000\u0000\u00006\u013a\u0001\u0000\u0000\u00008\u0148"+
		"\u0001\u0000\u0000\u0000:\u0168\u0001\u0000\u0000\u0000<\u018b\u0001\u0000"+
		"\u0000\u0000>\u0199\u0001\u0000\u0000\u0000@\u019d\u0001\u0000\u0000\u0000"+
		"B\u01a1\u0001\u0000\u0000\u0000D\u01bb\u0001\u0000\u0000\u0000F\u01bd"+
		"\u0001\u0000\u0000\u0000H\u01bf\u0001\u0000\u0000\u0000J\u01df\u0001\u0000"+
		"\u0000\u0000L\u01f3\u0001\u0000\u0000\u0000N\u01f5\u0001\u0000\u0000\u0000"+
		"P\u01fd\u0001\u0000\u0000\u0000R\u0219\u0001\u0000\u0000\u0000TV\u0003"+
		"\u0002\u0001\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"VX\u0001\u0000\u0000\u0000WY\u0005Q\u0000\u0000XW\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000Y[\u0001\u0000\u0000\u0000Z\\\u0003\u0018\f"+
		"\u0000[Z\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\`\u0001\u0000"+
		"\u0000\u0000]_\u0005S\u0000\u0000^]\u0001\u0000\u0000\u0000_b\u0001\u0000"+
		"\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ac\u0001"+
		"\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005\u0000\u0000\u0001"+
		"d\u0001\u0001\u0000\u0000\u0000ei\u0003\u0004\u0002\u0000fh\u0007\u0000"+
		"\u0000\u0000gf\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001"+
		"\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000"+
		"ki\u0001\u0000\u0000\u0000le\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000"+
		"\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000o\u0003\u0001"+
		"\u0000\u0000\u0000py\u0003\u0006\u0003\u0000qy\u0003\b\u0004\u0000ry\u0003"+
		"\n\u0005\u0000sy\u0003\f\u0006\u0000ty\u0003\u0010\b\u0000uy\u0003\u0012"+
		"\t\u0000vy\u0003\u0014\n\u0000wy\u0003\u0016\u000b\u0000xp\u0001\u0000"+
		"\u0000\u0000xq\u0001\u0000\u0000\u0000xr\u0001\u0000\u0000\u0000xs\u0001"+
		"\u0000\u0000\u0000xt\u0001\u0000\u0000\u0000xu\u0001\u0000\u0000\u0000"+
		"xv\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000y\u0005\u0001\u0000"+
		"\u0000\u0000z{\u0005\u0014\u0000\u0000{|\u0005B\u0000\u0000|\u0007\u0001"+
		"\u0000\u0000\u0000}~\u0005\u0012\u0000\u0000~\u007f\u0005A\u0000\u0000"+
		"\u007f\t\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0013\u0000\u0000\u0081"+
		"\u0082\u0005=\u0000\u0000\u0082\u0083\u0005A\u0000\u0000\u0083\u000b\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005\u0010\u0000\u0000\u0085\u008a\u0003"+
		"\u000e\u0007\u0000\u0086\u0087\u0005J\u0000\u0000\u0087\u0089\u0003\u000e"+
		"\u0007\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000"+
		"\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000"+
		"\u0000\u0000\u008b\u0092\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000"+
		"\u0000\u0000\u008d\u0090\u00054\u0000\u0000\u008e\u0091\u0003N\'\u0000"+
		"\u008f\u0091\u0005D\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090"+
		"\u008f\u0001\u0000\u0000\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092"+
		"\u008d\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093"+
		"\r\u0001\u0000\u0000\u0000\u0094\u009b\u0005U\u0000\u0000\u0095\u0098"+
		"\u0003N\'\u0000\u0096\u0097\u0005 \u0000\u0000\u0097\u0099\u0005=\u0000"+
		"\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000"+
		"\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0094\u0001\u0000\u0000"+
		"\u0000\u009a\u0095\u0001\u0000\u0000\u0000\u009b\u000f\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0005\u0011\u0000\u0000\u009d\u009e\u0005=\u0000\u0000"+
		"\u009e\u009f\u0005@\u0000\u0000\u009f\u0011\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a1\u0005\u000e\u0000\u0000\u00a1\u00a2\u0005=\u0000\u0000\u00a2\u00a3"+
		"\u0005\u0016\u0000\u0000\u00a3\u00a4\u0003\u001a\r\u0000\u00a4\u0013\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0005\u000f\u0000\u0000\u00a6\u00a7\u0005"+
		"=\u0000\u0000\u00a7\u00a9\u0005O\u0000\u0000\u00a8\u00aa\u0003$\u0012"+
		"\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ae\u0005P\u0000\u0000"+
		"\u00ac\u00ad\u0005I\u0000\u0000\u00ad\u00af\u0003R)\u0000\u00ae\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0016\u0000\u0000\u00b1\u00b2"+
		"\u0003\u001a\r\u0000\u00b2\u0015\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005"+
		"\u0015\u0000\u0000\u00b4\u00b5\u0005=\u0000\u0000\u00b5\u00b6\u0005\u0016"+
		"\u0000\u0000\u00b6\u00b7\u0003R)\u0000\u00b7\u0017\u0001\u0000\u0000\u0000"+
		"\u00b8\u00bc\u0003\u001a\r\u0000\u00b9\u00bb\u0005S\u0000\u0000\u00ba"+
		"\u00b9\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc"+
		"\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd"+
		"\u0019\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c0\u0003\u001c\u000e\u0000\u00c0\u001b\u0001\u0000\u0000\u0000\u00c1"+
		"\u00c2\u0006\u000e\uffff\uffff\u0000\u00c2\u00c3\u0003\u001e\u000f\u0000"+
		"\u00c3\u00da\u0001\u0000\u0000\u0000\u00c4\u00c5\n\u0007\u0000\u0000\u00c5"+
		"\u00c6\u0005-\u0000\u0000\u00c6\u00d9\u0003 \u0010\u0000\u00c7\u00c8\n"+
		"\u0006\u0000\u0000\u00c8\u00c9\u0005,\u0000\u0000\u00c9\u00d9\u0003 \u0010"+
		"\u0000\u00ca\u00cb\n\u0005\u0000\u0000\u00cb\u00cc\u0005.\u0000\u0000"+
		"\u00cc\u00d9\u0003 \u0010\u0000\u00cd\u00ce\n\u0004\u0000\u0000\u00ce"+
		"\u00cf\u00052\u0000\u0000\u00cf\u00d0\u0005F\u0000\u0000\u00d0\u00d1\u0005"+
		"3\u0000\u0000\u00d1\u00d9\u0003\u001a\r\u0000\u00d2\u00d3\n\u0003\u0000"+
		"\u0000\u00d3\u00d4\u0005<\u0000\u0000\u00d4\u00d9\u0003\u001e\u000f\u0000"+
		"\u00d5\u00d6\n\u0002\u0000\u0000\u00d6\u00d7\u0005=\u0000\u0000\u00d7"+
		"\u00d9\u0003\u001e\u000f\u0000\u00d8\u00c4\u0001\u0000\u0000\u0000\u00d8"+
		"\u00c7\u0001\u0000\u0000\u0000\u00d8\u00ca\u0001\u0000\u0000\u0000\u00d8"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d8\u00d2\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da"+
		"\u00d8\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"\u001d\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd"+
		"\u00e0\u0003(\u0014\u0000\u00de\u00df\u0005#\u0000\u0000\u00df\u00e1\u0003"+
		"(\u0014\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e1\u001f\u0001\u0000\u0000\u0000\u00e2\u00e9\u0003\"\u0011"+
		"\u0000\u00e3\u00e9\u0003\u001a\r\u0000\u00e4\u00e5\u0005O\u0000\u0000"+
		"\u00e5\u00e6\u0003 \u0010\u0000\u00e6\u00e7\u0005P\u0000\u0000\u00e7\u00e9"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e2\u0001\u0000\u0000\u0000\u00e8\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e4\u0001\u0000\u0000\u0000\u00e9!\u0001"+
		"\u0000\u0000\u0000\u00ea\u00eb\u0005O\u0000\u0000\u00eb\u00ec\u0003$\u0012"+
		"\u0000\u00ec\u00ed\u0005P\u0000\u0000\u00ed\u00ee\u0005\u0017\u0000\u0000"+
		"\u00ee\u00ef\u0003\u001a\r\u0000\u00ef#\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f5\u0003&\u0013\u0000\u00f1\u00f2\u0005J\u0000\u0000\u00f2\u00f4\u0003"+
		"&\u0013\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f4\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f6%\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f8\u00fb\u0005=\u0000\u0000\u00f9\u00fa\u0005I\u0000\u0000\u00fa"+
		"\u00fc\u0003R)\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001"+
		"\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005"+
		"\u0016\u0000\u0000\u00fe\u0100\u0003\u001a\r\u0000\u00ff\u00fd\u0001\u0000"+
		"\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\'\u0001\u0000\u0000"+
		"\u0000\u0101\u0106\u0003*\u0015\u0000\u0102\u0103\u0005\u001a\u0000\u0000"+
		"\u0103\u0105\u0003*\u0015\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105"+
		"\u0108\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106"+
		"\u0107\u0001\u0000\u0000\u0000\u0107)\u0001\u0000\u0000\u0000\u0108\u0106"+
		"\u0001\u0000\u0000\u0000\u0109\u010e\u0003,\u0016\u0000\u010a\u010b\u0005"+
		"\u0019\u0000\u0000\u010b\u010d\u0003,\u0016\u0000\u010c\u010a\u0001\u0000"+
		"\u0000\u0000\u010d\u0110\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f+\u0001\u0000\u0000"+
		"\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0111\u0116\u0003.\u0017\u0000"+
		"\u0112\u0113\u00056\u0000\u0000\u0113\u0115\u0003.\u0017\u0000\u0114\u0112"+
		"\u0001\u0000\u0000\u0000\u0115\u0118\u0001\u0000\u0000\u0000\u0116\u0114"+
		"\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117-\u0001"+
		"\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119\u011e\u0003"+
		"0\u0018\u0000\u011a\u011b\u00057\u0000\u0000\u011b\u011d\u00030\u0018"+
		"\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u0120\u0001\u0000\u0000"+
		"\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000"+
		"\u0000\u011f\u0126\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000"+
		"\u0000\u0121\u0122\u00030\u0018\u0000\u0122\u0123\u0005!\u0000\u0000\u0123"+
		"\u0124\u0003R)\u0000\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0119\u0001"+
		"\u0000\u0000\u0000\u0125\u0121\u0001\u0000\u0000\u0000\u0126/\u0001\u0000"+
		"\u0000\u0000\u0127\u012d\u00034\u001a\u0000\u0128\u0129\u00032\u0019\u0000"+
		"\u0129\u012a\u00034\u001a\u0000\u012a\u012c\u0001\u0000\u0000\u0000\u012b"+
		"\u0128\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000\u012d"+
		"\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e"+
		"1\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0131"+
		"\u0007\u0001\u0000\u0000\u01313\u0001\u0000\u0000\u0000\u0132\u0137\u0003"+
		"6\u001b\u0000\u0133\u0134\u00058\u0000\u0000\u0134\u0136\u00036\u001b"+
		"\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0136\u0139\u0001\u0000\u0000"+
		"\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000"+
		"\u0000\u01385\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000"+
		"\u013a\u013b\u0006\u001b\uffff\uffff\u0000\u013b\u013c\u0003:\u001d\u0000"+
		"\u013c\u0145\u0001\u0000\u0000\u0000\u013d\u013e\n\u0002\u0000\u0000\u013e"+
		"\u013f\u0005 \u0000\u0000\u013f\u0141\u0003R)\u0000\u0140\u0142\u0003"+
		"8\u001c\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000"+
		"\u0000\u0000\u0142\u0144\u0001\u0000\u0000\u0000\u0143\u013d\u0001\u0000"+
		"\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0143\u0001\u0000"+
		"\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u01467\u0001\u0000\u0000"+
		"\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u0149\u0005K\u0000\u0000"+
		"\u0149\u014a\u0005=\u0000\u0000\u014a\u014b\u0005I\u0000\u0000\u014b\u014c"+
		"\u0005D\u0000\u0000\u014c\u014d\u0005L\u0000\u0000\u014d9\u0001\u0000"+
		"\u0000\u0000\u014e\u014f\u0005/\u0000\u0000\u014f\u0150\u0005O\u0000\u0000"+
		"\u0150\u0151\u0003\u001a\r\u0000\u0151\u0152\u0005P\u0000\u0000\u0152"+
		"\u0169\u0001\u0000\u0000\u0000\u0153\u0154\u0005/\u0000\u0000\u0154\u0169"+
		"\u0003\u001a\r\u0000\u0155\u0156\u00050\u0000\u0000\u0156\u0157\u0005"+
		"O\u0000\u0000\u0157\u0158\u0003\u001a\r\u0000\u0158\u0159\u0005P\u0000"+
		"\u0000\u0159\u0169\u0001\u0000\u0000\u0000\u015a\u015b\u00050\u0000\u0000"+
		"\u015b\u0169\u0003\u001a\r\u0000\u015c\u015d\u00051\u0000\u0000\u015d"+
		"\u015e\u0005O\u0000\u0000\u015e\u015f\u0003\u001a\r\u0000\u015f\u0160"+
		"\u0005P\u0000\u0000\u0160\u0169\u0001\u0000\u0000\u0000\u0161\u0162\u0005"+
		"1\u0000\u0000\u0162\u0169\u0003\u001a\r\u0000\u0163\u0164\u0005\u001b"+
		"\u0000\u0000\u0164\u0169\u0003\u001a\r\u0000\u0165\u0166\u0005:\u0000"+
		"\u0000\u0166\u0169\u0003\u001a\r\u0000\u0167\u0169\u0003<\u001e\u0000"+
		"\u0168\u014e\u0001\u0000\u0000\u0000\u0168\u0153\u0001\u0000\u0000\u0000"+
		"\u0168\u0155\u0001\u0000\u0000\u0000\u0168\u015a\u0001\u0000\u0000\u0000"+
		"\u0168\u015c\u0001\u0000\u0000\u0000\u0168\u0161\u0001\u0000\u0000\u0000"+
		"\u0168\u0163\u0001\u0000\u0000\u0000\u0168\u0165\u0001\u0000\u0000\u0000"+
		"\u0168\u0167\u0001\u0000\u0000\u0000\u0169;\u0001\u0000\u0000\u0000\u016a"+
		"\u016b\u0006\u001e\uffff\uffff\u0000\u016b\u016c\u0005\u001c\u0000\u0000"+
		"\u016c\u016d\u0005O\u0000\u0000\u016d\u016e\u0003\u001a\r\u0000\u016e"+
		"\u016f\u0005P\u0000\u0000\u016f\u0179\u0003\u001a\r\u0000\u0170\u0171"+
		"\u0005\u001d\u0000\u0000\u0171\u0172\u0005\u001c\u0000\u0000\u0172\u0173"+
		"\u0005O\u0000\u0000\u0173\u0174\u0003\u001a\r\u0000\u0174\u0175\u0005"+
		"P\u0000\u0000\u0175\u0176\u0003\u001a\r\u0000\u0176\u0178\u0001\u0000"+
		"\u0000\u0000\u0177\u0170\u0001\u0000\u0000\u0000\u0178\u017b\u0001\u0000"+
		"\u0000\u0000\u0179\u0177\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000"+
		"\u0000\u0000\u017a\u017e\u0001\u0000\u0000\u0000\u017b\u0179\u0001\u0000"+
		"\u0000\u0000\u017c\u017d\u0005\u001d\u0000\u0000\u017d\u017f\u0003\u001a"+
		"\r\u0000\u017e\u017c\u0001\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000"+
		"\u0000\u017f\u018c\u0001\u0000\u0000\u0000\u0180\u018c\u0003B!\u0000\u0181"+
		"\u018c\u0003\"\u0011\u0000\u0182\u018c\u0003@ \u0000\u0183\u018c\u0003"+
		"F#\u0000\u0184\u018c\u0003P(\u0000\u0185\u018c\u0003H$\u0000\u0186\u018c"+
		"\u0003J%\u0000\u0187\u018c\u0003>\u001f\u0000\u0188\u018c\u0005=\u0000"+
		"\u0000\u0189\u018c\u0005?\u0000\u0000\u018a\u018c\u0005>\u0000\u0000\u018b"+
		"\u016a\u0001\u0000\u0000\u0000\u018b\u0180\u0001\u0000\u0000\u0000\u018b"+
		"\u0181\u0001\u0000\u0000\u0000\u018b\u0182\u0001\u0000\u0000\u0000\u018b"+
		"\u0183\u0001\u0000\u0000\u0000\u018b\u0184\u0001\u0000\u0000\u0000\u018b"+
		"\u0185\u0001\u0000\u0000\u0000\u018b\u0186\u0001\u0000\u0000\u0000\u018b"+
		"\u0187\u0001\u0000\u0000\u0000\u018b\u0188\u0001\u0000\u0000\u0000\u018b"+
		"\u0189\u0001\u0000\u0000\u0000\u018b\u018a\u0001\u0000\u0000\u0000\u018c"+
		"\u0196\u0001\u0000\u0000\u0000\u018d\u018e\n\u0002\u0000\u0000\u018e\u0195"+
		"\u0003D\"\u0000\u018f\u0190\n\u0001\u0000\u0000\u0190\u0191\u0003D\"\u0000"+
		"\u0191\u0192\u0005#\u0000\u0000\u0192\u0193\u0003\u001a\r\u0000\u0193"+
		"\u0195\u0001\u0000\u0000\u0000\u0194\u018d\u0001\u0000\u0000\u0000\u0194"+
		"\u018f\u0001\u0000\u0000\u0000\u0195\u0198\u0001\u0000\u0000\u0000\u0196"+
		"\u0194\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197"+
		"=\u0001\u0000\u0000\u0000\u0198\u0196\u0001\u0000\u0000\u0000\u0199\u019a"+
		"\u00055\u0000\u0000\u019a\u019b\u0005O\u0000\u0000\u019b\u019c\u0005P"+
		"\u0000\u0000\u019c?\u0001\u0000\u0000\u0000\u019d\u019e\u0005O\u0000\u0000"+
		"\u019e\u019f\u0003\u001a\r\u0000\u019f\u01a0\u0005P\u0000\u0000\u01a0"+
		"A\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005&\u0000\u0000\u01a2\u01a4\u0005"+
		"K\u0000\u0000\u01a3\u01a5\u0003\u0002\u0001\u0000\u01a4\u01a3\u0001\u0000"+
		"\u0000\u0000\u01a4\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a6\u01a7\u0005Q\u0000\u0000\u01a7\u01a8\u0003\u001a\r"+
		"\u0000\u01a8\u01a9\u0005L\u0000\u0000\u01a9C\u0001\u0000\u0000\u0000\u01aa"+
		"\u01ab\u0005G\u0000\u0000\u01ab\u01bc\u0005=\u0000\u0000\u01ac\u01ad\u0005"+
		"G\u0000\u0000\u01ad\u01bc\u0005D\u0000\u0000\u01ae\u01af\u0005G\u0000"+
		"\u0000\u01af\u01b0\u0005U\u0000\u0000\u01b0\u01bc\u0005=\u0000\u0000\u01b1"+
		"\u01b2\u0005;\u0000\u0000\u01b2\u01bc\u0005=\u0000\u0000\u01b3\u01b4\u0005"+
		"M\u0000\u0000\u01b4\u01b5\u0003\u001a\r\u0000\u01b5\u01b6\u0005N\u0000"+
		"\u0000\u01b6\u01bc\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005G\u0000\u0000"+
		"\u01b8\u01b9\u0005V\u0000\u0000\u01b9\u01bc\u0005=\u0000\u0000\u01ba\u01bc"+
		"\u0005W\u0000\u0000\u01bb\u01aa\u0001\u0000\u0000\u0000\u01bb\u01ac\u0001"+
		"\u0000\u0000\u0000\u01bb\u01ae\u0001\u0000\u0000\u0000\u01bb\u01b1\u0001"+
		"\u0000\u0000\u0000\u01bb\u01b3\u0001\u0000\u0000\u0000\u01bb\u01b7\u0001"+
		"\u0000\u0000\u0000\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bcE\u0001\u0000"+
		"\u0000\u0000\u01bd\u01be\u0007\u0002\u0000\u0000\u01beG\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c8\u0005M\u0000\u0000\u01c0\u01c5\u0003\u001a\r\u0000"+
		"\u01c1\u01c2\u0005J\u0000\u0000\u01c2\u01c4\u0003\u001a\r\u0000\u01c3"+
		"\u01c1\u0001\u0000\u0000\u0000\u01c4\u01c7\u0001\u0000\u0000\u0000\u01c5"+
		"\u01c3\u0001\u0000\u0000\u0000\u01c5\u01c6\u0001\u0000\u0000\u0000\u01c6"+
		"\u01c9\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000\u01c8"+
		"\u01c0\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9"+
		"\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0005N\u0000\u0000\u01cbI\u0001"+
		"\u0000\u0000\u0000\u01cc\u01cd\u0005K\u0000\u0000\u01cd\u01d4\u0003L&"+
		"\u0000\u01ce\u01d0\u0005J\u0000\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d0\u0001\u0000\u0000\u0000\u01d0\u01d1\u0001\u0000\u0000\u0000"+
		"\u01d1\u01d3\u0003L&\u0000\u01d2\u01cf\u0001\u0000\u0000\u0000\u01d3\u01d6"+
		"\u0001\u0000\u0000\u0000\u01d4\u01d2\u0001\u0000\u0000\u0000\u01d4\u01d5"+
		"\u0001\u0000\u0000\u0000\u01d5\u01d7\u0001\u0000\u0000\u0000\u01d6\u01d4"+
		"\u0001\u0000\u0000\u0000\u01d7\u01d8\u0005L\u0000\u0000\u01d8\u01e0\u0001"+
		"\u0000\u0000\u0000\u01d9\u01da\u0005K\u0000\u0000\u01da\u01db\u0003L&"+
		"\u0000\u01db\u01dc\u0005L\u0000\u0000\u01dc\u01e0\u0001\u0000\u0000\u0000"+
		"\u01dd\u01de\u0005K\u0000\u0000\u01de\u01e0\u0005L\u0000\u0000\u01df\u01cc"+
		"\u0001\u0000\u0000\u0000\u01df\u01d9\u0001\u0000\u0000\u0000\u01df\u01dd"+
		"\u0001\u0000\u0000\u0000\u01e0K\u0001\u0000\u0000\u0000\u01e1\u01e2\u0005"+
		"=\u0000\u0000\u01e2\u01e3\u0005I\u0000\u0000\u01e3\u01f4\u0003\u001a\r"+
		"\u0000\u01e4\u01e5\u0005D\u0000\u0000\u01e5\u01e6\u0005I\u0000\u0000\u01e6"+
		"\u01f4\u0003\u001a\r\u0000\u01e7\u01e8\u0005O\u0000\u0000\u01e8\u01e9"+
		"\u0003\u001a\r\u0000\u01e9\u01ea\u0005P\u0000\u0000\u01ea\u01eb\u0005"+
		"I\u0000\u0000\u01eb\u01ec\u0003\u001a\r\u0000\u01ec\u01f4\u0001\u0000"+
		"\u0000\u0000\u01ed\u01ee\u0005O\u0000\u0000\u01ee\u01ef\u0003L&\u0000"+
		"\u01ef\u01f0\u0005P\u0000\u0000\u01f0\u01f1\u0005\u001c\u0000\u0000\u01f1"+
		"\u01f2\u0003\u001a\r\u0000\u01f2\u01f4\u0001\u0000\u0000\u0000\u01f3\u01e1"+
		"\u0001\u0000\u0000\u0000\u01f3\u01e4\u0001\u0000\u0000\u0000\u01f3\u01e7"+
		"\u0001\u0000\u0000\u0000\u01f3\u01ed\u0001\u0000\u0000\u0000\u01f4M\u0001"+
		"\u0000\u0000\u0000\u01f5\u01fa\u0005=\u0000\u0000\u01f6\u01f7\u0005H\u0000"+
		"\u0000\u01f7\u01f9\u0005=\u0000\u0000\u01f8\u01f6\u0001\u0000\u0000\u0000"+
		"\u01f9\u01fc\u0001\u0000\u0000\u0000\u01fa\u01f8\u0001\u0000\u0000\u0000"+
		"\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fbO\u0001\u0000\u0000\u0000\u01fc"+
		"\u01fa\u0001\u0000\u0000\u0000\u01fd\u01fe\u0003N\'\u0000\u01fe\u0207"+
		"\u0005O\u0000\u0000\u01ff\u0204\u0003\u001a\r\u0000\u0200\u0201\u0005"+
		"J\u0000\u0000\u0201\u0203\u0003\u001a\r\u0000\u0202\u0200\u0001\u0000"+
		"\u0000\u0000\u0203\u0206\u0001\u0000\u0000\u0000\u0204\u0202\u0001\u0000"+
		"\u0000\u0000\u0204\u0205\u0001\u0000\u0000\u0000\u0205\u0208\u0001\u0000"+
		"\u0000\u0000\u0206\u0204\u0001\u0000\u0000\u0000\u0207\u01ff\u0001\u0000"+
		"\u0000\u0000\u0207\u0208\u0001\u0000\u0000\u0000\u0208\u0209\u0001\u0000"+
		"\u0000\u0000\u0209\u020a\u0005P\u0000\u0000\u020aQ\u0001\u0000\u0000\u0000"+
		"\u020b\u021a\u0005=\u0000\u0000\u020c\u021a\u0005\u0001\u0000\u0000\u020d"+
		"\u021a\u0005\u0002\u0000\u0000\u020e\u021a\u0005\u0003\u0000\u0000\u020f"+
		"\u021a\u0005\u0004\u0000\u0000\u0210\u021a\u0005\u0005\u0000\u0000\u0211"+
		"\u021a\u0005\u0006\u0000\u0000\u0212\u021a\u0005\u0007\u0000\u0000\u0213"+
		"\u021a\u0005\b\u0000\u0000\u0214\u021a\u0005\t\u0000\u0000\u0215\u021a"+
		"\u0005\n\u0000\u0000\u0216\u021a\u0005\u000b\u0000\u0000\u0217\u021a\u0005"+
		"\f\u0000\u0000\u0218\u021a\u0005\r\u0000\u0000\u0219\u020b\u0001\u0000"+
		"\u0000\u0000\u0219\u020c\u0001\u0000\u0000\u0000\u0219\u020d\u0001\u0000"+
		"\u0000\u0000\u0219\u020e\u0001\u0000\u0000\u0000\u0219\u020f\u0001\u0000"+
		"\u0000\u0000\u0219\u0210\u0001\u0000\u0000\u0000\u0219\u0211\u0001\u0000"+
		"\u0000\u0000\u0219\u0212\u0001\u0000\u0000\u0000\u0219\u0213\u0001\u0000"+
		"\u0000\u0000\u0219\u0214\u0001\u0000\u0000\u0000\u0219\u0215\u0001\u0000"+
		"\u0000\u0000\u0219\u0216\u0001\u0000\u0000\u0000\u0219\u0217\u0001\u0000"+
		"\u0000\u0000\u0219\u0218\u0001\u0000\u0000\u0000\u021aS\u0001\u0000\u0000"+
		"\u00001UX[`inx\u008a\u0090\u0092\u0098\u009a\u00a9\u00ae\u00bc\u00d8\u00da"+
		"\u00e0\u00e8\u00f5\u00fb\u00ff\u0106\u010e\u0116\u011e\u0125\u012d\u0137"+
		"\u0141\u0145\u0168\u0179\u017e\u018b\u0194\u0196\u01a4\u01bb\u01c5\u01c8"+
		"\u01cf\u01d4\u01df\u01f3\u01fa\u0204\u0207\u0219";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}