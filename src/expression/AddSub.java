package expression;

public class AddSub extends Expression {
	Expression left;
	Expression right;
	String operator = "+";

	public AddSub(Expression left, Expression right, String operator) {
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
