package edu.csc413.expression;

import edu.csc413.interpreter.ProgramState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A conditional comparison that can evaluate to true or false. */
public class Condition {
    /** The supported comparison operators. */
    public enum Operator {
        EQUALS("=="),
        NOT_EQUALS("!="),
        LESS_THAN_EQUALS("<="),
        LESS_THAN("<"),
        GREATER_THAN_EQUALS(">="),
        GREATER_THAN(">");

        private final String symbol;

        Operator(String symbol) {
            this.symbol = symbol;
        }

        String getSymbol() {
            return symbol;
        }
    }

    private final Operator operator;
    private final Expression lhs;
    private final Expression rhs;

    public Condition(Operator operator, Expression lhs, Expression rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** Resolves the comparison to true or false based on the lhs and rhs expressions and the operator. */
    public boolean evaluate(ProgramState programState) {
        int lhsValue = lhs.evaluate(programState);
        int rhsValue = rhs.evaluate(programState);
        return switch (operator) {
            case EQUALS -> lhsValue == rhsValue;
            case NOT_EQUALS -> lhsValue != rhsValue;
            case LESS_THAN_EQUALS -> lhsValue <= rhsValue;
            case LESS_THAN -> lhsValue < rhsValue;
            case GREATER_THAN_EQUALS -> lhsValue >= rhsValue;
            case GREATER_THAN -> lhsValue > rhsValue;
        };
    }

    /**
     * Creates a Condition object from the provided String. If no valid comparison operator is found, a RuntimeException
     * is thrown.
     */
    public static Condition create(String conditionString) {
        for (Operator operator: Operator.values()) {
            Matcher matcher =
                    Pattern.compile(String.format("^(.+)%s(.+)$", operator.getSymbol())).matcher(conditionString);
            if (matcher.matches()) {
                return new Condition(
                        operator,
                        Expression.create(matcher.group(1).strip()),
                        Expression.create(matcher.group(2).strip()));
            }
        }
        throw new RuntimeException("Invalid condition: " + conditionString);
    }
}
