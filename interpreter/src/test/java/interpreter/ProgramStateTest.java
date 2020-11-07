package interpreter;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.PrintStatement;
import edu.csc413.statement.Statement;
import org.junit.jupiter.api.Test;

import edu.csc413.expression.Expression;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class ProgramStateTest {
    @Test
    void getVariable() {
        ProgramState programState = new ProgramState();
        programState.setVariable("x", 10);
        programState.getVariable("x");

        assertEquals(10, programState.getVariable("x"));
    }
    @Test
    void getVariable2() {
        ProgramState programState = new ProgramState();
        programState.setVariable("y", 212);
        programState.getVariable("y");

        assertEquals(212, programState.getVariable("y"));
    }


    @Test
    void addNewCallFrame() {
        ProgramState programState = new ProgramState();
        programState.addNewCallFrame();
        programState.addNewCallFrame();

        assertEquals(3, programState.getState().size());
    }
    @Test
    void addNewCallFrame2() {
        ProgramState programState = new ProgramState();
        programState.addNewCallFrame();
        programState.addNewCallFrame();
        programState.addNewCallFrame();

        assertEquals(4, programState.getState().size());
    }
    @Test
    void removeCurrentCallFrame1() {
        ProgramState programState = new ProgramState();
        programState.addNewCallFrame();
        programState.removeCurrentCallFrame();

        assertEquals(1, programState.getState().size());
    }

    @Test
    void removeCurrentCallFrame2() {
        ProgramState programState = new ProgramState();
        programState.addNewCallFrame();
        programState.addNewCallFrame();
        programState.addNewCallFrame();
        programState.removeCurrentCallFrame();
        programState.removeCurrentCallFrame();

        assertEquals(2, programState.getState().size());
    }

    @Test
    void getFunctionParameter() {
        ProgramState programState = new ProgramState();
        List<String> parameterNames = new ArrayList<>();
        String name = "function";
        parameterNames.add("x");
        parameterNames.add("y");
        programState.setFunctionParameter(name, parameterNames);
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");

        assertEquals(list, programState.getFunctionParameter(name));

    }

    @Test
    void getFunctionStatements() {
        ProgramState programState = new ProgramState();
        String name = "function";
        List<Statement> statements = new ArrayList<>();
        programState.setFunctionStatements(name,statements);
        Expression printExpression1 = Expression.create("print(20)");
        Expression printExpression2 = Expression.create("print(20)");
        Statement statement1 = new PrintStatement(printExpression1);
        Statement statement2 = new PrintStatement(printExpression2);
        statements.add(statement1);
        statements.add(statement2);
        programState.setFunctionStatements(name, statements);

        assertEquals(2, programState.getFunctionStatements(name).size());
    }


    @Test
    void hasReturnValue() {
        ProgramState programState = new ProgramState();
        programState.setReturnValue(10);

        assertTrue(programState.hasReturnValue());
    }
    @Test
    void hasReturnValue2() {
        ProgramState programState = new ProgramState();
        programState.setReturnValue(10);
        programState.clearReturnValue();

        assertTrue(!programState.hasReturnValue());
    }


    @Test
    void getReturnValue() {
        ProgramState programState = new ProgramState();
        programState.setReturnValue(10);

        assertEquals(10, programState.getReturnValue());
    }
    @Test
    void getReturnValue2() {
        ProgramState programState = new ProgramState();
        programState.setReturnValue(33);

        assertEquals(33, programState.getReturnValue());
    }

    @Test
    void clearReturnValue() {
        ProgramState programState = new ProgramState();
        programState.clearReturnValue();

        assertEquals(0, programState.getReturnValue());

    }
}