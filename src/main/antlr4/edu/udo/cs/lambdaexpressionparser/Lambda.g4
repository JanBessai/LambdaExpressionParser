grammar Lambda;

lambda      :   lambdaExpr;

lambdaExpr  :   lambdaExpr lambdaExpr       # application
            |   '\\' ID+ '->' lambdaExpr    # abstraction
            |   ID                          # variable
            |   '(' lambdaExpr ')'          # parenthesis
            ;

ID          :   [a-zA-Z]([a-zA-Z0-9]*);
WHITESPACE  :   [ \t\n\r]   ->  skip;