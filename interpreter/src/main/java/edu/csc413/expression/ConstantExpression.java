package edu.csc413.expression;

import edu.csc413.interpreter.ProgramState;

public class ConstantExpression extends Expression {
    private final int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(ProgramState programState) {
        return value;
    }
}
