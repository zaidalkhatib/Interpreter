package edu.csc413.statement;

import edu.csc413.interpreter.ProgramState;

/**
 * A single runnable program statement. All Statements must support the run method, which defines the behavior carried
 * out when the statement is reached during the course of program execution. Running a statement may lead to side
 * effects, such as variables being updated or values being printed to the console. A statement may also be a compound
 * statement, where the execution of that statement leads to several statements being executed (for example, an if
 * statement with multiple statements in the if-block).
 */
public abstract class Statement {
    /**
     * Runs the statement.
     *
     * @param programState An object representing the state of the running program. Statements require knowledge of
     *                     program state in order to execute.
     */
    public abstract void run(ProgramState programState);
}
