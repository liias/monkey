package ee.edio.garmin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static ee.edio.garmin.psi.MonkeyCTypes.*;

%%

%{
  public _MonkeyCLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _MonkeyCLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+

WHITE_SPACE=[ \t\n\x0B\f\r]+
SINGLE_LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?
IDENTIFIER=[a-zA-Z$_][a-zA-Z0-9$_]*
INTEGER=[0-9]+
NUMBER=[0-9]+(\.[0-9]*)?
STRINGLITERAL=(\"([^\"\\]|\\.)*\")
CHARLITERAL=('([^'\\]|\\.)*')

%%
<YYINITIAL> {
  {WHITE_SPACE}              { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "."                        { return DOT; }
  ";"                        { return SEMI; }
  "?"                        { return QUES; }
  ":"                        { return COLON; }
  "class"                    { return CLASS; }
  "function"                 { return FUNCTION; }
  "return"                   { return RETURN; }
  "new"                      { return NEW; }
  "var"                      { return VAR; }
  "const"                    { return CONST; }
  "module"                   { return MODULE; }
  "using"                    { return USING; }
  "as"                       { return AS; }
  "enum"                     { return ENUM; }
  "extends"                  { return EXTENDS; }
  "null"                     { return NULL; }
  "native"                   { return NATIVE; }
  "hidden"                   { return HIDDEN; }
  "static"                   { return STATIC; }
  "instanceof"               { return INSTANCEOF; }
  "has"                      { return HAS; }
  "if"                       { return IF; }
  "else"                     { return ELSE; }
  "do"                       { return DO; }
  "while"                    { return WHILE; }
  "for"                      { return FOR; }
  "break"                    { return BREAK; }
  "continue"                 { return CONTINUE; }
  "switch"                   { return SWITCH; }
  "default"                  { return DEFAULT; }
  "case"                     { return CASE; }
  "try"                      { return TRY; }
  "catch"                    { return CATCH; }
  "finally"                  { return FINALLY; }
  "throw"                    { return THROW; }
  "and"                      { return AND; }
  "or"                       { return OR; }
  "true"                     { return TRUE; }
  "false"                    { return FALSE; }
  "/*"                       { return MULTI_LINE_COMMENT_START; }
  "*/"                       { return MULTI_LINE_COMMENT_END; }
  "\""                       { return STRING_A; }
  "'"                        { return STRING_B; }
  "{"                        { return LBRACE; }
  "}"                        { return RBRACE; }
  "["                        { return LBRACKET; }
  "]"                        { return RBRACKET; }
  "("                        { return LPAREN; }
  ")"                        { return RPAREN; }
  ","                        { return COMMA; }
  "*"                        { return STAR; }
  "|"                        { return BAR; }
  "<"                        { return LT; }
  ">"                        { return GT; }
  "=>"                       { return EQGT; }
  "||"                       { return BARBAR; }
  "&&"                       { return AMPAMP; }
  "++"                       { return PLUSPLUS; }
  "--"                       { return SUBSUB; }
  "="                        { return EQ; }
  "=="                       { return EQEQ; }
  "!="                       { return BANGEQ; }
  "+="                       { return PLUSEQ; }
  "-="                       { return SUBEQ; }
  "*="                       { return STAREQ; }
  "/="                       { return SLASHEQ; }
  "&="                       { return AMPEQ; }
  "|="                       { return CARETEQ; }
  "%="                       { return PERCENTEQ; }
  "^"                        { return CARET; }
  "%"                        { return PERCENT; }
  "~"                        { return TILDE; }
  "!"                        { return BANG; }
  "+"                        { return PLUS; }
  "-"                        { return SUB; }
  "/"                        { return SLASH; }
  "THROWS"                   { return THROWS; }
  "SUPER"                    { return SUPER; }
  "BAREQ"                    { return BAREQ; }
  "AMP"                      { return AMP; }
  "VOID"                     { return VOID; }

  {WHITE_SPACE}              { return WHITE_SPACE; }
  {SINGLE_LINE_COMMENT}      { return SINGLE_LINE_COMMENT; }
  {BLOCK_COMMENT}            { return BLOCK_COMMENT; }
  {IDENTIFIER}               { return IDENTIFIER; }
  {INTEGER}                  { return INTEGER; }
  {NUMBER}                   { return NUMBER; }
  {STRINGLITERAL}            { return STRINGLITERAL; }
  {CHARLITERAL}              { return CHARLITERAL; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
