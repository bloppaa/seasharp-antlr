package expression;

public class Variable extends Expression {
	String id;
	
	public Variable(String id) {
		this.id = id;
	}

	public String getId() {
        	return id;
    	}
	
	@Override
	public String toString() {
		return id;
	}
}
