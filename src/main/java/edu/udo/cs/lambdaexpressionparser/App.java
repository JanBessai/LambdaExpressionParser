package edu.udo.cs.lambdaexpressionparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        LambdaLexer lexer = new LambdaLexer(new ANTLRInputStream("(\\ x z -> y z) a (b)"));
        LambdaParser parser = new LambdaParser(new BufferedTokenStream(lexer));
        LambdaTermBuilder builder = new LambdaTermBuilder();
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        lexer.addErrorListener(new ExceptionErrorListener());
        parser.addErrorListener(new ExceptionErrorListener());
        
        try {
            System.out.println(builder.visit(parser.lambda()));
        } catch (RuntimeException ex) {
            System.out.println("error:");
            System.out.println(ex.getMessage());
        }
    }
}
