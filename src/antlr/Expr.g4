grammar Expr;

@header {
package antlr;
}

prog: ((decl | expr | assign | print) ';' | cond)+ EOF # Program;

decl: (INT_TYPE | FLOAT_TYPE | BOOL_TYPE | STR_TYPE) ID '=' expr # Declaration;

assign: ID '=' expr # Assignment;

cond:
	'if' '(' expr ')' '{' block '}' ('else' '{' block '}')? # Condition;

block: statement*;

print: 'print(' expr ')';

statement: decl ';' | expr ';' | assign ';' | print ';' | cond;

expr:
	'(' expr ')'							# Parens
	| '-' expr								# UnaryMinus
	| '!' expr								# Not
	| expr ('*' | '/' | '%') expr			# MultDivMod
	| expr ('+' | '-') expr					# AddSub
	| expr ('>' | '>=' | '<' | '<=') expr	# Comparison
	| expr ('==' | '!=') expr				# Equality
	| expr '&&' expr						# And
	| expr '||' expr						# Or
	| ID									# Variable
	| NUM									# Number
	| BOOL									# Boolean
	| STR									# String;

INT_TYPE: 'int';
FLOAT_TYPE: 'float';
BOOL_TYPE: 'bool';
STR_TYPE: 'string';
NUM: ('0' | [1-9][0-9]*) ('.' [0-9]+)?;
BOOL: 'true' | 'false';
STR: '"' .*? '"';
ID: [a-z][a-zA-Z0-9_]*;
WS: [ \t\n\r]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;