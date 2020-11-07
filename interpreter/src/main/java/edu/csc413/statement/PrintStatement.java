package edu.csc413.statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;

public class PrintStatement extends Statement{
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void run(ProgramState programState) {
        System.out.println(expression.evaluate(programState));
    }
}
