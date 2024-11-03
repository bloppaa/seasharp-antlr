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

		for (int i = 0; i < list.size(); i += 2) {
			Expression e = list.get(i);
			if (e instanceof VariableDeclaration) {
				VariableDeclaration decl = (VariableDeclaration) e;
				int result = getEvalResult(decl.expr);
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
		} else if (e instanceof Parens) {
			Parens parens = (Parens) e;
			result = getEvalResult(parens.expr);
		} else if (e instanceof AddSub) {
			AddSub add = (AddSub) e;
			int left = getEvalResult(add.left);
			int right = getEvalResult(add.right);
			switch (add.operator) {
				case "+":
					result = left + right;
					break;
				case "-":
					result = left - right;
					break;
			}
		} else if (e instanceof MultDivMod) {
			MultDivMod mult = (MultDivMod) e;
			int left = getEvalResult(mult.left);
			int right = getEvalResult(mult.right);
			switch (mult.operator) {
				case "*":
					result = left * right;
					break;
				case "/":
					result = left / right;
					break;
				case "%":
					result = left % right;
					break;
			}
		}
		return result;
	}
}
