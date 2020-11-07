package edu.csc413.statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;

public class AssignStatement extends Statement {
    private final String variableName;
    private final Expression expression;

    public AssignStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public void run(ProgramState programState) {
        programState.setVariable(variableName, expression.evaluate(programState));
    }
}
