package expression;

public class VariableDeclaration extends Expression {
	public String id;
	public String type;
	public Expression expr;

	public VariableDeclaration(String id, String type, Expression expr) {
		this.id = id;
		this.type = type;
		this.expr = expr;
	}

}
