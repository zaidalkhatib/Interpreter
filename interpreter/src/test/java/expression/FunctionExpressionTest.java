package expression;

import edu.csc413.expression.ArithmeticExpression;
import edu.csc413.expression.Expression;
import edu.csc413.expression.FunctionExpression;
import edu.csc413.interpreter.ProgramState;
import edu.csc413.statement.AssignStatement;
import edu.csc413.statement.DefineFunctionStatement;
import edu.csc413.statement.ReturnStatement;
import edu.csc413.statement.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionTest {
    ProgramState programState = new ProgramState();

    @Test
    void evaluate1() {

        List<String> stringStatements = new ArrayList<>();
        String functionName = "function";
        Expression value1 = Expression.create("4");
        Expression value2 = Expression.create("100");
        String String_x = "x";
        String String_y = "y";
        stringStatements.add(String_x);
        stringStatements.add(String_y);

        Expression x = Expression.create("x");
        Statement statement1 = new AssignStatement("x", value1);
        statement1.run(programState);
        Statement statement2 = new AssignStatement("y", value2);
        statement2.run(programState);
        List<Statement> statements = new ArrayList<>();
        statements.add(statement1);
        Expression y = Expression.create("y");

        List<Expression> expressions = new ArrayList<>();
        expressions.add(x);
        expressions.add(y);

        programState.setFunctionParameter(functionName, stringStatements);
        programState.setFunctionStatements(functionName, statements);
        FunctionExpression functionExpression = new FunctionExpression(functionName, expressions);
        functionExpression.evaluate(programState);

        assertEquals(true, programState.getFunctionParameter(functionName).contains("y"));
        assertEquals(true, programState.getFunctionParameter(functionName).contains("x"));
        assertEquals(2, programState.getFunctionParameter(functionName).size());

    }

    @Test
    void evaluate2() {

        List<String> stringStatements = new ArrayList<>();
        String functionName = "function";
        Expression value1 = Expression.create("4");
        Expression value2 = Expression.create("100");
        String String_x = "z";
        String String_y = "k";
        stringStatements.add(String_x);
        stringStatements.add(String_y);

        Expression z = Expression.create("z");
        Statement statement1 = new AssignStatement("z", value1);
        statement1.run(programState);
        Statement statement2 = new AssignStatement("k", value2);
        statement2.run(programState);
        List<Statement> statements = new ArrayList<>();
        statements.add(statement1);
        Expression k = Expression.create("k");

        List<Expression> expressions = new ArrayList<>();
        expressions.add(z);
        expressions.add(k);

        programState.setFunctionParameter(functionName, stringStatements);
        programState.setFunctionStatements(functionName, statements);
        FunctionExpression functionExpression = new FunctionExpression(functionName, expressions);
        functionExpression.evaluate(programState);


        assertEquals(false, programState.getFunctionParameter(functionName).contains("a"));
        assertEquals(false, programState.getFunctionParameter(functionName).contains("u"));
        assertEquals(true, programState.getFunctionParameter(functionName).contains("z"));
        assertEquals(2, programState.getFunctionParameter(functionName).size());

    }
}