package test.java.expression;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.Condition;
import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantExpressionTest {


    ProgramState programState = new ProgramState();

    @Test
    void evaluate1() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("NOT_EQUALS");
        Condition condition = new Condition(operator,expression,expression2);
        assertEquals(true, condition.evaluate(programState));
    }

    @Test
    void evaluate2() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("EQUALS");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(false, condition.evaluate(programState));
    }
    @Test
    void evaluate3() {
        Expression expression = Expression.create("5");
        Expression expression2 = Expression.create("5");
        Condition.Operator operator = Condition.Operator.valueOf("EQUALS");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(true, condition.evaluate(programState));
    }


    @Test
    void evaluate4() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("LESS_THAN_EQUALS");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(true, condition.evaluate(programState));
    }

    @Test
    void evaluate5() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("LESS_THAN");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(true, condition.evaluate(programState));
    }

    @Test
    void evaluate6() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("GREATER_THAN_EQUALS");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(false, condition.evaluate(programState));
    }

    @Test
    void evaluate7() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("20");
        Condition.Operator operator = Condition.Operator.valueOf("GREATER_THAN");
        Condition condition = new Condition(operator,expression,expression2);

        assertEquals(false, condition.evaluate(programState));
    }
    @Test
    void evaluate8() {
        Expression expression = Expression.create("10");
        Expression expression2 = Expression.create("2");
        Condition.Operator operator = Condition.Operator.valueOf("LESS_THAN_EQUALS");
        Condition condition = new Condition(operator,expression,expression2);


        //not equal
        assertNotEquals(true, condition.evaluate(programState));
    }
}