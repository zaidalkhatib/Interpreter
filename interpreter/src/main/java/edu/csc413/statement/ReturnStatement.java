package edu.csc413.statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;

public class ReturnStatement extends Statement {
    private final Expression returnStatement;

    public ReturnStatement(Expression returnStatement) {
        this.returnStatement = returnStatement;
    }

    @Override
    public void run(ProgramState programState) {
        programState.setReturnValue(returnStatement.evaluate(programState));
    }
}
