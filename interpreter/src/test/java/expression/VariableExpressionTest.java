package expression;

import edu.csc413.expression.VariableExpression;
import edu.csc413.interpreter.ProgramState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {
    ProgramState programState = new ProgramState();
    @Test
    void evaluate1() {
        programState.setVariable("x", 21);
        VariableExpression variableExpression = new VariableExpression("x");

        assertEquals(21, variableExpression.evaluate(programState));
    }


    @Test
    void evaluate2() {
        programState.setVariable("y", 9-2);
        VariableExpression variableExpression = new VariableExpression("y");

        assertEquals(7, variableExpression.evaluate(programState));
    }
    @Test
    void evaluate3() {
        programState.setVariable("z", 3*2);
        VariableExpression variableExpression = new VariableExpression("z");

        assertEquals(6, variableExpression.evaluate(programState));
    }
    @Test
    void evaluate4() {
        programState.setVariable("k", 1+10);
        VariableExpression variableExpression = new VariableExpression("k");

        assertEquals(11, variableExpression.evaluate(programState));
    }

    @Test
    void evaluate5() {
        programState.setVariable("x", 22);
        VariableExpression variableExpression = new VariableExpression("x");

        //not equal
        assertNotEquals(21, variableExpression.evaluate(programState));
    }

}