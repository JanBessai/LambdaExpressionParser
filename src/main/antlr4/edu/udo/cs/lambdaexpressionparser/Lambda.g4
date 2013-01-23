grammar Lambda;

lambda      :   lambdaExpr;

lambdaExpr  :   '\\' ID+ '->' lambdaExpr    # abstraction
            |   lambdaExpr lambdaExpr       # application
            |   ID                          # variable
            |   '(' lambdaExpr ')'          # parenthesis
            ;

ID          :   [a-zA-Z]([a-zA-Z0-9]*);
WHITESPACE  :   [ \t\n\r]   ->  skip;