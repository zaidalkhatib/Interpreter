package statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.ReturnStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnStatementTest {
    Expression expression = Expression.create("1+1");
    ReturnStatement returnStatement = new ReturnStatement(expression);
    ProgramState programState = new ProgramState();
    @Test
    void run1() {
        Expression expression = Expression.create("1+132");
        ReturnStatement returnStatement = new ReturnStatement(expression);
        returnStatement.run(programState);

        //equals
        assertEquals(133, programState.getReturnValue());
    }
    @Test
    void run2() {
        Expression expression = Expression.create("50-10");
        ReturnStatement returnStatement = new ReturnStatement(expression);
        returnStatement.run(programState);

        //equals
        assertEquals(40, programState.getReturnValue());
    }    @Test
    void run3() {
        Expression expression = Expression.create("2*10");
        ReturnStatement returnStatement = new ReturnStatement(expression);
        returnStatement.run(programState);

        //equals
        assertEquals(20, programState.getReturnValue());
    }
    @Test
    void run4() {
        Expression expression = Expression.create("32/32");
        ReturnStatement returnStatement = new ReturnStatement(expression);
        returnStatement.run(programState);

        //equals
        assertEquals(1, programState.getReturnValue());
    }
    @Test
    void run5() {
        Expression expression = Expression.create("32/32");
        ReturnStatement returnStatement = new ReturnStatement(expression);
        returnStatement.run(programState);

        //not equal
        assertNotEquals(10, programState.getReturnValue());
    }
}