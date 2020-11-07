package edu.csc413.statement;

import edu.csc413.expression.Condition;
import edu.csc413.interpreter.ProgramState;
import java.util.List;
/**
 * An abstract type of BlockStatement which represents a looping statement. Like other BlockStatements, a LoopStatement
 * has a list of Statements representing its body. However, LoopStatements execute by repeatedly running the entire
 * block as long as a condition holds true.
 */
public abstract class LoopStatement extends BlockStatement {
    // TODO: Implement. Add whatever instance variables are needed. Hint: consider what is common to all loop types.
    private final Condition condition;

    public LoopStatement(List<Statement> loopBlock, Condition condition) {
        super(loopBlock, condition);
        this.condition = condition;

    }

    @Override
    public void run(ProgramState programState) {
        // TODO: Implement. Hint: you can call BlockStatement's runBlock method, as well as the abstract methods
        //       runInitialization and runUpdate.
        runInitialization(programState);
        while (condition.evaluate(programState)) {
            runBlock(programState);
            runUpdate(programState);
        }

    }

    /**
     * An abstract method which runs any initialization step that happens before the loop executes. Each subclass of
     * LoopStatement will provide its own implementation of this based on how that LoopStatement is defined.
     */
    protected abstract void runInitialization(ProgramState programState);

    /**
     * An abstract method which runs any update step that happens after each run of the loop body. Each subclass of
     * LoopStatement will provide its own implementation of this based on how that LoopStatement is defined.
     */
    protected abstract void runUpdate(ProgramState programState);
}
