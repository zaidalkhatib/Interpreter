package expression;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionTest {

    ProgramState programState = new ProgramState();
    @Test
    void evaluate1() {
        Expression expression = Expression.create("4");
        Expression expression2 = Expression.create("9");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("ADD");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(13,arithmeticExpression.evaluate(programState));
    }
    @Test
    void evaluate2() {
        Expression expression = Expression.create("30");
        Expression expression2 = Expression.create("40");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("SUBTRACT");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(10,arithmeticExpression.evaluate(programState));
    }


    @Test
    void evaluate3() {
        Expression expression = Expression.create("2");
        Expression expression2 = Expression.create("2");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("MULTIPLY");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(4,arithmeticExpression.evaluate(programState));
    }
    @Test
    void evaluate4() {
        Expression expression = Expression.create("30");
        Expression expression2 = Expression.create("60");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("DIVIDE");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(2,arithmeticExpression.evaluate(programState));
    }
    @Test
    void evaluate5() {
        Expression expression = Expression.create("2");
        Expression expression2 = Expression.create("2");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("REMAINDER");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(0,arithmeticExpression.evaluate(programState));
    }
    @Test
    void evaluate6() {
        Expression expression = Expression.create("4");
        Expression expression2 = Expression.create("2");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("POWER");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));

        assertEquals(16,arithmeticExpression.evaluate(programState));
    }

    void evaluate7() {
        Expression expression = Expression.create("30");
        Expression expression2 = Expression.create("40");
        ArithmeticExpression.Operator operator = ArithmeticExpression.Operator.valueOf("SUBTRACT");
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression(operator, expression, expression2);
        System.out.println(arithmeticExpression.evaluate(programState));


        //not equal
        assertNotEquals(20,arithmeticExpression.evaluate(programState));
    }
}