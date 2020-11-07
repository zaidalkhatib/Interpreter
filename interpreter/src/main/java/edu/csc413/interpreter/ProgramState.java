package edu.csc413.interpreter;

import edu.csc413.statement.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * A class which tracks the state of a running program. ProgramState should maintain a call stack, with each call frame
 * tracking variable names and their corresponding values. ProgramState should also keep track of function definitions,
 * so that when function calls are made, they can be run and evaluated.
 */
public class ProgramState {

    public ProgramState()
    {
        addNewCallFrame();
    }


    private final Map<String, List<String>> functionsParameters = new HashMap<>();


    private final Map<String, List<Statement>> functionsStatements = new HashMap<>();


    private final Stack<Map<String, Integer>> state = new Stack<>();

    private int value = 0;

    private Boolean hasReturnValue  = false;

    public Stack<Map<String, Integer>> getState() {
        return state;
    }

    public int getVariable(String variable) {
        if (!state.peek().containsKey(variable)) {

            throw new RuntimeException("Undefined Variable " + variable);
        }

        return state.peek().get(variable);
    }

    public void setVariable(String variable, int value) {

        state.peek().put(variable, value);
    }

    public void addNewCallFrame() {
          Map<String, Integer>  map = new HashMap<>();
          state.push(map);
    }

    public void removeCurrentCallFrame() {
        state.pop();
    }


    public void setFunctionParameter(String name, List <String> parameterNames) {
        if (!functionsParameters.containsKey(name)) {
        functionsParameters.put(name, parameterNames);
        }
    }

    public List<String>getFunctionParameter(String name) {

        return functionsParameters.get(name);
    }


    public void setFunctionStatements(String name, List<Statement> Statements) {

        if (!functionsStatements.containsKey(name)) {
        functionsStatements.put(name, Statements);
        }
    }

    public List<Statement> getFunctionStatements(String name) {
        return functionsStatements.get(name);

    }

    public boolean hasReturnValue() {
        return hasReturnValue;
    }

    public int getReturnValue() {

        return value;
    }

    public void setReturnValue(int value) {

        this.value = value;
        hasReturnValue = true;
    }

    public void clearReturnValue() {

        this.value=0;
        hasReturnValue = false;
    }
}
