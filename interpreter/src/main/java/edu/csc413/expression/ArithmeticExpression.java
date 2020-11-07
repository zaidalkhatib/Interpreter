package edu.csc413.expression;

import edu.csc413.interpreter.ProgramState;

/** An Expression representing the combination of two smaller expressions, combined with an arithmetic operator. */
public class ArithmeticExpression extends Expression {
    /** The various operators that can be used to combine smaller expressions into an ArithmeticOperator. */
    public enum Operator {
        ADD("+", 1),
        SUBTRACT("-", 1),
        MULTIPLY("*", 2),
        DIVIDE("/", 2),
        REMAINDER("%", 2),
        POWER("^", 3);

        private final String symbol;
        private final int precedence;

        Operator(String symbol, int precedence) {
            this.symbol = symbol;
            this.precedence = precedence;
        }

        String getSymbol() {
            return symbol;
        }

        int getPrecedence() {
            return precedence;
        }
    }

    private final Operator operator;
    private final Expression rhs;
    private final Expression lhs;

    public ArithmeticExpression(Operator operator, Expression rhs, Expression lhs) {
        this.operator = operator;
        this.rhs = rhs;
        this.lhs = lhs;
    }

    @Override
    public int evaluate(ProgramState programState) {
        int lhsValue = lhs.evaluate(programState);
        int rhsValue = rhs.evaluate(programState);

        return switch (operator) {
            case ADD -> lhsValue + rhsValue;
            case SUBTRACT -> lhsValue - rhsValue;
            case DIVIDE -> lhsValue / rhsValue;
            case MULTIPLY -> lhsValue * rhsValue;
            case REMAINDER -> lhsValue % rhsValue;
            case POWER -> (int) Math.pow((int) lhsValue, (int) rhsValue);
        };
    }
}
