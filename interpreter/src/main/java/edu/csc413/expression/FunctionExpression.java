package edu.csc413.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class FunctionExpression extends Expression {
    private final String functionName;
    private final List<Expression> parameterValues;

    public FunctionExpression(String functionName, List<Expression> parameterValues) {
        this.functionName = functionName;
        this.parameterValues = parameterValues;
    }

    @Override
    public int evaluate(ProgramState programState) {

        List<String> getFunPar = programState.getFunctionParameter(functionName);

        List<Statement> functionStatement = programState.getFunctionStatements(functionName);

        List<Integer> getParameterValues = new ArrayList<>();


        for (int i = 0; i < parameterValues.size(); i++) {
            getParameterValues.add(parameterValues.get(i).evaluate(programState));
        }

        programState.addNewCallFrame();

        for (int i = 0; i < parameterValues.size(); i++) {

            programState.setVariable(getFunPar.get(i), getParameterValues.get(i));
        }

        functionStatement.forEach(statement ->
        {
            if (!programState.hasReturnValue()) {
                statement.run(programState);
            }
        });

        int returnValue = programState.getReturnValue();
        boolean tempReturnValue = programState.hasReturnValue();
        programState.removeCurrentCallFrame();
        programState.clearReturnValue();

        if (tempReturnValue) {
            return returnValue;
        }

        return 0;
    }

}
