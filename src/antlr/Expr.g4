grammar Expr;

@header {
package antlr;
}

prog: ((decl | expr) ';')+ EOF # Program;

decl: (INT_TYPE  /*| FLOAT_TYPE*/ ) ID '=' expr # Declaration;


expr:
	'(' expr ')'					# Parens
	| expr ('*' | '/' | '%') expr	# MultDivMod
	| expr ('+' | '-') expr			# AddSub
	| ID							# Variable
	| NUM							# Number;
	//| STRING #String

INT_TYPE: 'int';
//FLOAT_TYPE: 'float'; el double y string

ID: [a-z][a-zA-Z0-9_]*;
NUM: '0' | '-'? [1-9][0-9]*;
//STRING: '"' ( ~[\\"] | '\\.' )* '"';
WS: [ \t\n\r]+ -> skip;
