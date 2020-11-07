package edu.csc413.statement;

import edu.csc413.expression.Expression;
import edu.csc413.interpreter.ProgramState;

import java.util.List;

public class DefineFunctionStatement extends Statement {
    private final String functionName;
    private final List<String> parameterNames;
    private final List <Statement> functionStatements;

    public DefineFunctionStatement(String functionName, List <String>  parameterNames, List  <Statement>  functionStatements) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.functionStatements = functionStatements;
    }

    @Override
    public void run(ProgramState programState) {
        programState.setFunctionParameter(functionName, parameterNames);
        programState.setFunctionStatements(functionName, functionStatements);
    }
}
