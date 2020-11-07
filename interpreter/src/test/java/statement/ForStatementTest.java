package statement;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.Condition;
import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.AssignStatement;
import edu.csc413.statement.ForStatement;
import edu.csc413.statement.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForStatementTest {



    @Test
    void runInitialization1() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();

        Expression expression1 = Expression.create("2");
        Statement leftHandSide = new AssignStatement("y", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("y");
        Expression rhs = Expression.create("3");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression2 = Expression.create("3");
        Statement initalizedStatement = new AssignStatement("x", expression2);
        Expression expression3 = Expression.create("3");
        Statement updateStatement = new AssignStatement("y", expression3);
        ForStatement forStatement = new ForStatement(loopBlock, condition, initalizedStatement, updateStatement);
        forStatement.run(programState);

        //not equal
        assertEquals(3, programState.getVariable("x"));
    }
    @Test
    void runInitialization2() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();

        Expression expression1 = Expression.create("3");
        Statement leftHandSide = new AssignStatement("y", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("y");
        Expression rhs = Expression.create("4");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression2 = Expression.create("4");
        Statement initalizedStatement = new AssignStatement("x", expression2);
        Expression expression3 = Expression.create("4");
        Statement updateStatement = new AssignStatement("y", expression3);
        ForStatement forStatement = new ForStatement(loopBlock, condition, initalizedStatement, updateStatement);
        forStatement.run(programState);

        //not equal
        assertNotEquals(3, programState.getVariable("x"));
    }

    @Test
    void runUpdate1() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();

        Expression expression1 = Expression.create("2");
        Statement leftHandSide = new AssignStatement("y", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("y");
        Expression rhs = Expression.create("4");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression2 = Expression.create("4");
        Statement initalizedStatement = new AssignStatement("x", expression2);
        Expression expression3 = Expression.create("4+y");
        Statement updateStatement = new AssignStatement("y", expression3);
        ForStatement forStatement = new ForStatement(loopBlock, condition, initalizedStatement, updateStatement);
        forStatement.run(programState);

        //equals
        assertEquals(6, programState.getVariable("y"));
    }

    @Test
    void runUpdate2() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();

        Expression expression1 = Expression.create("3");
        Statement leftHandSide = new AssignStatement("y", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("y");
        Expression rhs = Expression.create("4");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression2 = Expression.create("4");
        Statement initalizedStatement = new AssignStatement("x", expression2);
        Expression expression3 = Expression.create("4+y");
        Statement updateStatement = new AssignStatement("y", expression3);
        ForStatement forStatement = new ForStatement(loopBlock, condition, initalizedStatement, updateStatement);
        forStatement.run(programState);

        //not equal
        assertNotEquals(3, programState.getVariable("y"));
    }
    @Test
    void runUpdate3() {
        ProgramState programState = new ProgramState();
        List<Statement> loopBlock = new ArrayList<>();

        Expression expression1 = Expression.create("3");
        Statement leftHandSide = new AssignStatement("z", expression1);
        leftHandSide.run(programState);
        Expression lhs = Expression.create("z");
        Expression rhs = Expression.create("5");
        Condition condition = new Condition(Condition.Operator.LESS_THAN, lhs, rhs);
        Expression expression2 = Expression.create("10");
        Statement initalizedStatement = new AssignStatement("x", expression2);
        Expression expression3 = Expression.create("z+2");
        Statement updateStatement = new AssignStatement("z", expression3);
        ForStatement forStatement = new ForStatement(loopBlock, condition, initalizedStatement, updateStatement);
        forStatement.run(programState);

        //equals
        assertEquals(5, programState.getVariable("z"));
    }
}