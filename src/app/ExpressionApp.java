package app;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ExprLexer;
import antlr.ExprParser;
import expression.AntlrToProgram;
import expression.ExpressionProcessor;
import expression.Program;
import expression.SyntaxErrorListener;

public class ExpressionApp {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Error: no input file provided");
		} else {
			try {
				String fileName = args[0];
				ExprParser parser = getParser(fileName);

				ParseTree antlrAST = parser.prog();

				if (SyntaxErrorListener.hasError) {
					return;
				}

				AntlrToProgram progVisitor = new AntlrToProgram();
				Program prog = progVisitor.visit(antlrAST);
				ExpressionProcessor ep = new ExpressionProcessor(prog.expressions);

				ep.proccessExpressions();
			} catch (Error e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private static ExprParser getParser(String fileName) {
		ExprParser parser = null;

		try {
			CharStream input = CharStreams.fromFileName(fileName);
			ExprLexer lexer = new ExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new ExprParser(tokens);

			parser.removeErrorListeners();
			parser.addErrorListener(new SyntaxErrorListener());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return parser;
	}
}
