package expression;

public class Parens extends Expression {
    Expression expr;

    public Parens(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "(" + expr.toString() + ")";
    }
}
