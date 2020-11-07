package edu.csc413.expression;

import edu.csc413.interpreter.ProgramState;

public class VariableExpression extends Expression {

    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int evaluate(ProgramState programState) {
        return programState.getVariable(variableName);
    }

}
