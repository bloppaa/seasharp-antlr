package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
	List<Expression> list;
	public Map<String, Integer> values;

	public ExpressionProcessor(List<Expression> list) {
		this.list = list;
		values = new HashMap<String, Integer>();
	}

	public List<String> getEvaluationResults() {
		List<String> evaluations = new ArrayList<String>();

		for (Expression e : list) {
			if (e instanceof VariableDeclaration) {
				VariableDeclaration decl = (VariableDeclaration) e;
				Expression expr = decl.expr;
				int result = getEvalResult(expr);
				values.put(decl.id, result);
			} else {
				String input = e.toString();
				int result = getEvalResult(e);
				evaluations.add(input + " is " + result);
			}
		}

		return evaluations;
	}

	private int getEvalResult(Expression e) {
		int result = 0;

		if (e instanceof Number) {
			Number num = (Number) e;
			result = num.num;
		} else if (e instanceof Variable) {
			Variable var = (Variable) e;
			result = values.get(var.id);
		} else if (e instanceof Addition) {
			Addition add = (Addition) e;
			int left = getEvalResult(add.left);
			int right = getEvalResult(add.right);
			result = left + right;
		} else if (e instanceof Multiplication) {
			Multiplication mult = (Multiplication) e;
			int left = getEvalResult(mult.left);
			int right = getEvalResult(mult.right);
			result = left * right;
		}

		return result;
	}
}
