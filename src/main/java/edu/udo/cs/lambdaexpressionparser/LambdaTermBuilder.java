package edu.udo.cs.lambdaexpressionparser;

import edu.udo.cs.lambdaexpressionparser.LambdaParser.AbstractionContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.ApplicationContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.ParenthesisContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.PassDownContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.VariableLevelContext;
import edu.udo.cs.lambdaexpressionparser.LambdaParser.VariableContext;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;


/**
 *
 * @author Jan
 */
public class LambdaTermBuilder extends LambdaBaseVisitor<String> {

    @Override
    public String visitApplication(ApplicationContext ctx) {
        StringBuilder resultBuilder = new StringBuilder();
        List<LambdaParser.VariableLevelContext> apps = ctx.variableLevel();
        resultBuilder.append(visit(apps.get(0)));
        apps.remove(0);
        for (VariableLevelContext vc : apps) {
            resultBuilder.insert(0, "(");
            resultBuilder.append(") ");
            resultBuilder.append(visit(vc));
        }
        return resultBuilder.toString();
    }

    @Override
    public String visitAbstraction(AbstractionContext ctx) {
        StringBuilder vars = new StringBuilder();
        for (TerminalNode var : ctx.ID()) {
            vars.append(" ");
            vars.append(var.getText());
        }
        return String.format("\\ %s -> %s", vars.toString(), visit(ctx.applicationLevel()));
    }
    
    @Override
    public String visitPassDown(PassDownContext ctx) {
        return visit(ctx.applicationLevel());
    }

    @Override
    public String visitParenthesis(ParenthesisContext ctx) {
        return String.format("(%s)", visit(ctx.abstractionLevel()));
    }

    @Override
    public String visitVariable(VariableContext ctx) {
        return ctx.getText();
    }    
}
