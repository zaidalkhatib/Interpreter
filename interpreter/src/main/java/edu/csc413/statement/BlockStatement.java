package edu.csc413.statement;

import edu.csc413.expression.Condition;
import edu.csc413.interpreter.ProgramState;
import java.util.List;

/**
 * An abstract Statement type that represents a compound Statement, where running the Statement leads to a sequence of
 * other Statements to be run in order.
 */
public abstract class BlockStatement extends Statement {

    private final List<Statement> loopBlock;

    public BlockStatement(List<Statement> loopBlock, Condition condition) {
        this.loopBlock = loopBlock;
    }

    /**
     * Runs every statement in the BlockStatement's block. Note that for certain looping statements, this may be
     * invoked repeatedly.
     */
    protected void runBlock(ProgramState programState) {

        //print(3)
        //return x+y

        loopBlock.forEach((statement) ->{
            if (!programState.hasReturnValue())
            {
                statement.run(programState);
            }
        });
        }

    }

