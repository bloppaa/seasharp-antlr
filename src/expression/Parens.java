package expression;

public class Parens extends Expression {
    Expression expr;

    public Parens(Expression expr) {
        this.expr = expr;
    }
    
    public Expression getExpression() {
        return expr;
    }

    @Override
    public String toString() {
        return "(" + expr.toString() + ")";
    }
}
