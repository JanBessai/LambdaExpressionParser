package edu.udo.cs.lambdaexpressionparser;

import edu.udo.cs.lambdaexpressionparser.LambdaParser.AbstractionContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.ApplicationContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.ParenthesisContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.VariableContext;
import org.antlr.v4.runtime.tree.TerminalNode;


/**
 *
 * @author Jan
 */
public class LambdaTermBuilder extends LambdaBaseVisitor<String> {

    @Override
    public String visitApplication(ApplicationContext ctx) {
        return String.format("%s %s", visit(ctx.lambdaExpr(0)), visit(ctx.lambdaExpr(1)));
    }

    @Override
    public String visitAbstraction(AbstractionContext ctx) {
        StringBuilder vars = new StringBuilder();
        for (TerminalNode var : ctx.ID()) {
            vars.append(" ");
            vars.append(var.getText());
        }
        return String.format("\\ %s -> %s", vars.toString(), visit(ctx.lambdaExpr()));
    }

    @Override
    public String visitParenthesis(ParenthesisContext ctx) {
        return String.format("(%s)", visit(ctx.lambdaExpr()));
    }

    @Override
    public String visitVariable(VariableContext ctx) {
        return ctx.getText();
    }    
}
