package edu.csc413.interpreter;

import edu.csc413.expression.*;
import edu.csc413.statement.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Interpreter {
    // Assorted regular expression patterns for statements we'll be parsing.
    private static final Pattern PRINT_PATTERN = Pattern.compile("^print\\((.+)\\)$");
    private static final Pattern ASSIGN_PATTERN = Pattern.compile("^(.+) = (.+)$");

    private static final Pattern IF_PATTERN = Pattern.compile("^if \\((.+)\\) \\{$");
    private static final Pattern WHILE_PATTERN = Pattern.compile("^while \\((.+)\\) \\{$");
    private static final Pattern FOR_PATTERN = Pattern.compile("^for \\(([^;]+);([^;]+);([^;]+)\\) \\{$");
    private static final Pattern END_BLOCK_PATTERN = Pattern.compile("}");

    private static final Pattern DEFINE_FUNCTION_PATTERN =
            Pattern.compile("^define ([A-Za-z][A-Za-z0-9_]*)\\(([A-Za-z0-9_,\\s]*)\\) \\{$");
    private static final Pattern RETURN_PATTERN = Pattern.compile("^return (.+)$");

    // The list of Statements created from parsing the Strings comprising the program.
    private final List<Statement> statements = new ArrayList<>();

    /**
     * Creates the Interpreter object from an array of Strings representing a program to be run. The constructor will
     * parse the lines into executable Statements, which can be invoked with the runProgram method.
     */
    public Interpreter(List<String> program) {
        Queue<String> lines =
                program.stream()
                        .map(String::strip)
                        .filter(Predicate.not(String::isEmpty))
                        .collect(Collectors.toCollection(LinkedList::new));
        while (!lines.isEmpty()) {
            Statement statement = parseStatement(lines);
            statements.add(statement);
        }
    }

    // Parse a single Statement from the front of the provided deque. Note that with block statements (if statements,
    // while loops), a single Statement may involve multiple lines with multiple contained statements.
    private Statement parseStatement(Queue<String> lines) {
        String line = lines.remove();

        // Multi-line statements.
        Statement statement = parseWhileStatement(line, lines);
        if (statement != null) {
            return statement;
        }
        statement = parseForStatement(line, lines);
        if (statement != null) {
            return statement;
        }
        statement = parseIfStatement(line, lines);
        if (statement != null) {
            return statement;
        }
        statement = parseDefineFunctionStatement(line, lines);
        if (statement != null) {
            return statement;
        }

        // Single line statements.
        statement = parsePrintStatement(line);
        if (statement != null) {
            return statement;
        }
        statement = parseAssignStatement(line);
        if (statement != null) {
            return statement;
        }
        statement = parseReturnStatement(line);
        if (statement != null) {
            return statement;
        }

        throw new RuntimeException("Unrecognized statement: " + line);
    }

    // Attempts to parse the line as a print statement. Returns null if it's not a match.
    private Statement parsePrintStatement(String line) {
        Matcher printMatcher = PRINT_PATTERN.matcher(line);
        if (printMatcher.matches()) {
            String expressionLine = printMatcher.group(1).strip();
            Expression expression = Expression.create(expressionLine);
            return new PrintStatement(expression);
        }

        return null;
    }

    // Attempts to parse the line as an assign statement. Returns null if it's not a match.
    private Statement parseAssignStatement(String line) {
        Matcher assignMatcher = ASSIGN_PATTERN.matcher(line);
        if (assignMatcher.matches()) {
            String variableName = assignMatcher.group(1).strip();
            String expressionLine = assignMatcher.group(2).strip();
            Expression expression = Expression.create(expressionLine);

            return new AssignStatement(variableName, expression);
        }

        return null;
    }

    // Attempts to parse the line as an if statement. Returns null if it's not a match.
    private Statement parseIfStatement(String line, Queue<String> lines) {
        Matcher ifMatcher = IF_PATTERN.matcher(line);
        if (ifMatcher.matches()) {
            String conditionLine = ifMatcher.group(1);
            List<Statement> blockStatements = parseBlockStatements(lines);
            Condition condition = Condition.create(conditionLine);
            return new IfStatement(blockStatements, condition);
        }

        return null;
    }

    // Attempts to parse the line as a while statement. Returns null if it's not a match.
    private Statement parseWhileStatement(String line, Queue<String> lines) {
        Matcher whileMatcher = WHILE_PATTERN.matcher(line);
        if (whileMatcher.matches()) {
            String conditionLine = whileMatcher.group(1);
            List<Statement> loopStatements = parseBlockStatements(lines);
            Condition condition = Condition.create(conditionLine);
            return new WhileStatement(loopStatements, condition);
        }

        return null;
    }

    // Attempts to parse the line as a for statement. Returns null if it's not a match.
    private Statement parseForStatement(String line, Queue<String> lines) {

        Matcher forMatcher = FOR_PATTERN.matcher(line);
        if (forMatcher.matches()) {
            String initializationLine = forMatcher.group(1);
            String conditionLine = forMatcher.group(2);
            String updateLine = forMatcher.group(3);
            List<Statement> loopStatements = parseBlockStatements(lines);

           Statement initializationLineStatement= parseAssignStatement(initializationLine);
           Statement updateLineStatement = parseAssignStatement(updateLine);
           Condition condition = Condition.create(conditionLine);
           return new ForStatement(loopStatements, condition, initializationLineStatement, updateLineStatement);
        }

        return null;
    }

    // Attempts to parse the line as a function definition. Returns null if it's not a match.
    private Statement parseDefineFunctionStatement(String line, Queue<String> lines) {
        Matcher defineFunctionMatcher = DEFINE_FUNCTION_PATTERN.matcher(line);
        if (defineFunctionMatcher.matches()) {
            String functionName = defineFunctionMatcher.group(1);
            List<String> parameterNames =
                    Arrays.stream(defineFunctionMatcher.group(2).split(","))
                            .map(String::strip)
                            .collect(Collectors.toList());
            if (parameterNames.size() == 1 && parameterNames.get(0).isEmpty()) {
                parameterNames = new ArrayList<>();
            }
            for (String parameterName: parameterNames) {
                if (!parameterName.matches(Expression.VARIABLE_NAME_PATTERN.pattern())) {
                    throw new RuntimeException("Invalid parameter name: " + parameterName);
                }
            }

            List<Statement> functionStatements = parseBlockStatements(lines);
            return new DefineFunctionStatement(functionName, parameterNames, functionStatements);
        }

        return null;
    }

    // Attempts to parse the line as a return statement. Returns null if it's not a match.
    private Statement parseReturnStatement(String line) {
        Matcher returnMatcher = RETURN_PATTERN.matcher(line);
        if (returnMatcher.matches()) {
            String returnLine = returnMatcher.group(1);
            Expression expression = Expression.create(returnLine);
            return new ReturnStatement(expression);
        }
        return null;
    }

    // parseBlockStatements is called when parsing any statement type with multiple lines. It will keep converting lines
    // into Statements and collecting them in a List until it encounters an end block line "}".
    private List<Statement> parseBlockStatements(Queue<String> lines) {
        List<Statement> blockStatements = new ArrayList<>();
        while (!lines.isEmpty()) {
            // We peek at the next line before attempting to parse it, in case it is the end of the block.
            String nextLine = lines.peek();
            if (nextLine.matches(END_BLOCK_PATTERN.pattern())) {
                lines.remove();
                return blockStatements;
            }

            // If not, we parse it as a normal Statement. Note that the next line can itself be a multi-line statement,
            // like an if statement or a while loop. The recursive call to parseStatement will handle the intricacies of
            // checking for the correct balancing of block delimiters ("{" and "}").
            blockStatements.add(parseStatement(lines));
        }

        // If the while loop exits without triggering the return statement inside, then we ran out of program lines
        // before finding the end of the current block. This signifies invalid syntax.
        throw new RuntimeException("Block was started but never finished.");
    }

    /** Run the parsed program Statements. */
    public void runProgram() {
        ProgramState programState = new ProgramState();
        for (Statement statement: statements) {
            statement.run(programState);
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Must provide the program file name to run.");
        }
        if (args.length > 1) {
            throw new RuntimeException("Only one argument expected (program file name).");
        }

        ArrayList<String> programLines = new ArrayList<>();
        try {
            String programFileName = args[0];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(programFileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                programLines.add(line);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        Interpreter interpreter = new Interpreter(programLines);
        interpreter.runProgram();
    }
}
