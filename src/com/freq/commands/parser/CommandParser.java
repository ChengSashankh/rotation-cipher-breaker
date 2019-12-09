package com.freq.commands.parser;

import com.freq.commands.AnalyseCommand;
import com.freq.commands.BreakCommand;
import com.freq.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implements the command parser to interpret user commands.
 */
public class CommandParser {
    public static final String ANALYSE_COMMAND = "analyse";
    public static final String BREAK_COMMAND = "break";
    public static final String EXIT_COMMAND = "exit";
    public static final String COMMAND_DELIMITER = " ";

    public static final int COMMAND_INDEX = 0;
    public static final int SUCCESSFUL_EXIT_CODE = 0;

    /**
     * Processes user input to remove leading and trailing spaces.
     * @param inputString - String representing user's input command.
     * @return String representing the preprocessed command.
     */
    String preProcessCommandString(String inputString) {
        String trimmedString = inputString.trim();
        return trimmedString;
    }

    /**
     * Tokenizes command string to separate command word and arguments.
     * @param preProcessedString - String representing pre-processed user input.
     * @return - ArrayList of String representing the tokenized user input.
     */
    List<String> getTokenizedCommand(String preProcessedString) {
        String[] tokenizedCommand = preProcessedString.split(COMMAND_DELIMITER);
        return new ArrayList<> (Arrays.asList(tokenizedCommand));
    }

    /**
     * Creates suitable command object for user's command.
     * @param tokenizedCommand - Tokenized command from user.
     * @return Command object to handle user's command.
     * @throws Exception if illegal command word is used.
     * TODO: Implement help command and display when illegal command is input.
     */
    Command getCommandType(List<String> tokenizedCommand) throws Exception {
        String commandWord = tokenizedCommand.get(COMMAND_INDEX);

        switch (commandWord) {
            case ANALYSE_COMMAND: return new AnalyseCommand(tokenizedCommand);
            case BREAK_COMMAND: return new BreakCommand(tokenizedCommand);
            case EXIT_COMMAND: System.exit(SUCCESSFUL_EXIT_CODE);

            default: throw new Exception("Illegal command word");
        }
    }

    /**
     * Command parsing wrapper to take input String and return Command.
     * @param inputString - String representing user's input command.
     * @return Command object to handle user's request.
     * @throws Exception if illegal command word is used.
     */
    public Command getCommand(String inputString) throws Exception {
        String preProcessedString = this.preProcessCommandString(inputString);
        List<String> tokenizedCommand = this.getTokenizedCommand(preProcessedString);
        Command command = getCommandType(tokenizedCommand);
        return command;
    }
}
