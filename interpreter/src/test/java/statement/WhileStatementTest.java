package statement;

import edu.csc413.expression.Condition;
import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhileStatementTest {
    @Test
    void runInitialization1() {
        ProgramState programState = new ProgramState();
        Expression expression1 = Expression.create("3");
        Statement statement = new AssignStatement("y", expression1);
        statement.run(programState);
        //equals
        assertEquals(3, programState.getVariable("y"));
    }
    @Test
    void runInitialization2() {
        ProgramState programState = new ProgramState();
        Expression expression1 = Expression.create("3");
        Statement statement = new AssignStatement("y", expression1);
        statement.run(programState);

        // not equals
        assertNotEquals(4, programState.getVariable("y"));
    }

    @Test
    void runInitialization3() {
        ProgramState programState = new ProgramState();
        Expression expression1 = Expression.create("10*3");
        Statement statement = new AssignStatement("x", expression1);
        statement.run(programState);
        //equals
        assertEquals(30, programState.getVariable("x"));
    }
    @Test
    void runInitialization4() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();
        Expression expression1 = Expression.create("10*3");
        Statement statement = new AssignStatement("x", expression1);
        statement.run(programState);
        //not equal
        assertNotEquals(10, programState.getVariable("x"));
    }


    @Test
    void runUpdate1() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();
        Expression expression1 = Expression.create("1");
        Statement leftHandSide = new AssignStatement("x", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("x");
        Expression rhs = Expression.create("2");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression3 = Expression.create("x+1");
        Statement updateStatement = new AssignStatement("x", expression3);
        updateStatement.run(programState);
        WhileStatement whileStatement = new WhileStatement(loopBlock, condition);
        whileStatement.run(programState);

        //equals
        assertEquals(2, programState.getVariable("x"));

    }
    void runUpdate2() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();
        Expression expression1 = Expression.create("1");
        Statement leftHandSide = new AssignStatement("x", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("x");
        Expression rhs = Expression.create("2");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression3 = Expression.create("x+1");
        Statement updateStatement = new AssignStatement("x", expression3);
        updateStatement.run(programState);
        WhileStatement whileStatement = new WhileStatement(loopBlock, condition);
        whileStatement.run(programState);

        //not equal
        assertNotEquals(1, programState.getVariable("x"));
    }


}