package edu.csc413.statement;

import edu.csc413.expression.Condition;
import edu.csc413.interpreter.ProgramState;
import java.util.List;

public class ForStatement extends LoopStatement {
    private final Statement initializationLineStatement;
    private final Statement updateLineStatement;


    public ForStatement(List<Statement> loopBlock, Condition condition , Statement initializationLineStatement, Statement updateLineStatement) {
        super(loopBlock, condition);
        this.initializationLineStatement = initializationLineStatement;
        this.updateLineStatement = updateLineStatement;
    }

    @Override
    protected void runInitialization(ProgramState programState) {
        initializationLineStatement.run(programState);

    }

    @Override
    protected void runUpdate(ProgramState programState) {
        updateLineStatement.run(programState);
    }
}
