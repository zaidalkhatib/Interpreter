package edu.csc413.statement;

import edu.csc413.expression.Condition;
import edu.csc413.interpreter.ProgramState;

import java.util.List;

public class WhileStatement extends LoopStatement{

    public WhileStatement(List<Statement> loopBlock, Condition condition) {
        super(loopBlock, condition);
    }


    @Override
    protected void runUpdate(ProgramState programState) {
        runBlock(programState);
    }

    @Override
    protected void runInitialization(ProgramState programState) {
        runBlock(programState);
    }
}
