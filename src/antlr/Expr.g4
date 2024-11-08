grammar Expr;

@header {
package antlr;
}

prog: ((decl | expr) ';')+ EOF # Program;

decl: INT_TYPE ID '=' expr # Declaration;
//  | FLOAT_TYPE ID '=' expr #Declaration;

expr:
	'(' expr ')'					# Parens
	| expr ('*' | '/' | '%') expr	# MultDivMod
	| expr ('+' | '-') expr			# AddSub
	| ID							# Variable
	| NUM							# Number;

INT_TYPE: 'int';
//FLOAT_TYPE: 'float';

ID: [a-z][a-zA-Z0-9_]*;
NUM: '0' | '-'? [1-9][0-9]*;
WS: [ \t\n\r]+ -> skip;
