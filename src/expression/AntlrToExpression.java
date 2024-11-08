package expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.AddSubContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.MultDivModContext;
import antlr.ExprParser.NumberContext;
import antlr.ExprParser.ParensContext;
import antlr.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {
	private List<String> vars;
	private List<String> semanticErrors;
	Map<String, String> varTypes;

	public AntlrToExpression(List<String> semanticErrors) {
		vars = new ArrayList<String>();
		this.semanticErrors = semanticErrors;
		varTypes = new HashMap<>();
	}

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		String id = ctx.ID().getText();
		if (vars.contains(id)) {
			String error = String.format(
					"Error: variable %s already declared (%d:%d)", id, line, column);
			semanticErrors.add(error);
		} else {
			vars.add(id);
			 String type =  ctx.getChild(0).getText();
	         	 varTypes.put(id, type);
		}
		Expression expr = visit(ctx.expr());
		String varType = varTypes.get(id);
        	String exprType = getExpressionType(expr);
        	if (!varType.equals(exprType)) {
           		 String error = String.format(
                   		 "Error: incompatible types: variable %s is of type %s but assigned expression of type %s (%d:%d)",
                   		  id, varType, exprType, line, column);
           		 semanticErrors.add(error);
        	}
		return new VariableDeclaration(id, varTypes.get(id), expr);
	}

	@Override
	public Expression visitMultDivMod(MultDivModContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new MultDivMod(left, right, operator);
	}

	@Override
	public Expression visitAddSub(AddSubContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		String operator = ctx.getChild(1).getText();
		return new AddSub(left, right, operator);
	}

	@Override
	public Expression visitParens(ParensContext ctx) {
		Expression expr = visit(ctx.expr());
		return new Parens(expr);
	}

	@Override
	public Expression visitVariable(VariableContext ctx) {
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;

		String id = ctx.ID().getText();
		if (!vars.contains(id)) {
			String error = String.format(
					"Error: variable %s not declared (%d:%d)", id, line, column);
			semanticErrors.add(error);
		}
		return new Variable(id);
	}

	@Override
	public Expression visitNumber(NumberContext ctx) {
		int num = Integer.parseInt(ctx.NUM().getText());
		return new Number(num);
	}
	
	private String getExpressionType(Expression expr) {
		if (expr instanceof Number) {
            return "int"; //suponiendo que solo usaremos el int
        } else if (expr instanceof Variable) {
            String varName = ((Variable) expr).getId();
            return varTypes.getOrDefault(varName, "unknown");
        } else if (expr instanceof MultDivMod || expr instanceof AddSub) {
            return "int";
        } else if (expr instanceof Parens) {
            return getExpressionType(((Parens) expr).getExpression());
        }
        return "unknown";
    }
	/* SI TENEMOS LA EXPRESION STRING 
    public Expression visitString(StringContext ctx) {
        String str = ctx.STRING().getText().replace("\"", "");
        return new String(str);
    }
	*/

}
