package statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.AssignStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignStatementTest {
    ProgramState programState = new ProgramState();

    @Test
    void run1() {
        Expression expression = Expression.create("2");
        String variableName = "x";
        AssignStatement assignStatement = new AssignStatement(variableName,expression);
        assignStatement.run(programState);

        //equals
        assertEquals(programState.getVariable(variableName), 2);
    }
    @Test
    void run2() {
        Expression expression = Expression.create("32");
        String variableName = "y";
        AssignStatement assignStatement = new AssignStatement(variableName,expression);
        assignStatement.run(programState);

        //equals
        assertEquals(programState.getVariable(variableName), 32);
    }
    @Test
    void run3() {
        Expression expression = Expression.create("22");
        String variableName = "z";
        AssignStatement assignStatement = new AssignStatement(variableName,expression);
        assignStatement.run(programState);

        //not equal
        assertNotEquals(programState.getVariable(variableName), 21);
    }
}