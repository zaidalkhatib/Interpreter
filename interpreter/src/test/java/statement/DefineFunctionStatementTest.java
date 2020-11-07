package statement;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.ConstantExpression;
import edu.csc413.expression.Expression;
import edu.csc413.expression.VariableExpression;
import edu.csc413.interpreter.Interpreter;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.AssignStatement;
import edu.csc413.statement.DefineFunctionStatement;
import edu.csc413.statement.ReturnStatement;
import edu.csc413.statement.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefineFunctionStatementTest {


    @Test
    void run1() {
        ProgramState programState = new ProgramState();
        String functionName = "function";
        Expression expression = new ConstantExpression(2);
        Statement statement = new AssignStatement("x", expression);
        List<Statement> functionStatements = new ArrayList<>();
        functionStatements.add(statement);
        programState.setFunctionStatements(functionName, functionStatements);
        List<String> parameterNames = new ArrayList<>();
        parameterNames.add("x");
        DefineFunctionStatement defineFunctionStatement = new DefineFunctionStatement(functionName,parameterNames , functionStatements);
        defineFunctionStatement.run(programState);
        assertEquals(1, programState.getFunctionStatements("function").size());
        assertTrue(programState.getFunctionStatements("function").contains(statement));

    }
    @Test
    void run2() {
        ProgramState programState = new ProgramState();
        String functionName = "function";
        Expression expression1 = new ConstantExpression(87);
        Expression expression2 = new ConstantExpression(12);
        Statement statement1 = new AssignStatement("x", expression1);
        Statement statement2 = new AssignStatement("y", expression2);
        List<Statement> functionStatements = new ArrayList<>();
        functionStatements.add(statement1);
        functionStatements.add(statement2);
        programState.setFunctionStatements(functionName, functionStatements);
        List<String> parameterNames = new ArrayList<>();
        parameterNames.add("x");
        DefineFunctionStatement defineFunctionStatement = new DefineFunctionStatement(functionName,parameterNames , functionStatements);
        defineFunctionStatement.run(programState);


        assertEquals(2, programState.getFunctionStatements("function").size());
        assertTrue(programState.getFunctionStatements("function").contains(statement1));
        assertTrue(programState.getFunctionStatements("function").contains(statement2));

    }
    @Test
    void run3() {
        ProgramState programState = new ProgramState();
        String functionName = "function";
        Expression lhs = Expression.create("21");
        Expression rhs = Expression.create("322");
        Expression arithmeticExpression = new ArithmeticExpression(ArithmeticExpression.Operator.ADD, lhs, rhs);
        Statement returnStatement = new ReturnStatement(arithmeticExpression);
        Expression expression1 = new ConstantExpression(872);
        Expression expression2 = new ConstantExpression(1);
        Statement statement1 = new AssignStatement("x", expression1);
        Statement statement2 = new AssignStatement("z", expression1);
        List<Statement> functionStatements = new ArrayList<>();
        functionStatements.add(returnStatement);
        functionStatements.add(statement1);
        functionStatements.add(statement2);
        programState.setFunctionStatements(functionName, functionStatements);
        List<String> parameterNames = new ArrayList<>();
        parameterNames.add("x");
        parameterNames.add("y");
        DefineFunctionStatement defineFunctionStatement = new DefineFunctionStatement(functionName,parameterNames , functionStatements);
        defineFunctionStatement.run(programState);


        assertEquals(3, programState.getFunctionStatements("function").size());
        assertTrue(programState.getFunctionStatements("function").contains(returnStatement));
        assertTrue(programState.getFunctionStatements("function").contains(statement1));
        assertTrue(programState.getFunctionStatements("function").contains(statement2));


    }
}