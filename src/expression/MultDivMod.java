package expression;

public class MultDivMod extends Expression {
	Expression left;
	Expression right;
	String operator;

	public MultDivMod(Expression left, Expression right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return String.format(
				"%s %s %s", left.toString(), operator, right.toString());
	}
}
