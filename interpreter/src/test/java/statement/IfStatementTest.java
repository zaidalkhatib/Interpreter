package statement;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.Condition;
import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IfStatementTest {
    ProgramState programState = new ProgramState();

    @Test
    void run1() {
        Expression value1 = Expression.create("10");
        Expression value2 = Expression.create("20");
        Statement x = new AssignStatement("x", value1);
        x.run(programState);
        Condition condition = new Condition(Condition.Operator.LESS_THAN, value1, value2);
        Expression value3 = Expression.create("4+x");
        Statement updateX = new AssignStatement("x", value3);
        value3.evaluate(programState);
        List<Statement> loopBlock = new ArrayList<>();
        loopBlock.add(x);
        loopBlock.add(updateX);
        IfStatement ifStatement = new IfStatement(loopBlock, condition);
        ifStatement.run(programState);

        //equals
        assertEquals(14, programState.getVariable("x"));

    }
    @Test
    void run2() {
        Expression value1 = Expression.create("10");
        Expression value2 = Expression.create("20");
        Statement x = new AssignStatement("x", value1);
        x.run(programState);
        Condition condition = new Condition(Condition.Operator.GREATER_THAN, value1, value2);
        Expression value3 = Expression.create("4+x");
        Statement updateX = new AssignStatement("x", value3);
        value3.evaluate(programState);
        List<Statement> loopBlock = new ArrayList<>();
        loopBlock.add(x);
        loopBlock.add(updateX);
        IfStatement ifStatement = new IfStatement(loopBlock, condition);
        ifStatement.run(programState);

        //not equal
        assertNotEquals(14, programState.getVariable("x"));

    }


    @Test
    void run3() {
        Expression value1 = Expression.create("5+2");
        Expression value2 = Expression.create("4+3");
        Statement x = new AssignStatement("x", value1);
        x.run(programState);
        Condition condition = new Condition(Condition.Operator.EQUALS, value1, value2);
        Expression value3 = Expression.create("4*x");
        Statement updateX = new AssignStatement("x", value3);
        value3.evaluate(programState);
        List<Statement> loopBlock = new ArrayList<>();
        loopBlock.add(x);
        loopBlock.add(updateX);
        IfStatement ifStatement = new IfStatement(loopBlock, condition);
        ifStatement.run(programState);

        //equals
        assertEquals(28, programState.getVariable("x"));

    }
    @Test
    void run4() {
        Expression value1 = Expression.create("5+2");
        Expression value2 = Expression.create("4+3");
        Statement x = new AssignStatement("x", value1);
        x.run(programState);
        Condition condition = new Condition(Condition.Operator.EQUALS, value1, value2);
        Expression value3 = Expression.create("4*x");
        Statement updateX = new AssignStatement("x", value3);
        value3.evaluate(programState);
        List<Statement> loopBlock = new ArrayList<>();
        loopBlock.add(x);
        loopBlock.add(updateX);
        IfStatement ifStatement = new IfStatement(loopBlock, condition);
        ifStatement.run(programState);

        //not equal
        assertNotEquals(22, programState.getVariable("x"));

    }
}