package com.freq.commands.parser;

import com.freq.commands.AnalyseCommand;
import com.freq.commands.BreakCommand;
import com.freq.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
    AnalyseCommand analyseCommand;
    BreakCommand breakCommand;

    public static final String ANALYSE_COMMAND = "analyse";
    public static final String BREAK_COMMAND = "break";
    public static final String EXIT_COMMAND = "exit";

    public static final int COMMAND_INDEX = 0;

    String preProcessCommandString(String inputString) {
        String trimmedString = inputString.trim();
        return trimmedString;
    }

    List<String> getTokenizedCommand(String preProcessedString) {
        String[] tokenizedCommand = preProcessedString.split(" ");
        return new ArrayList<String> (Arrays.asList(tokenizedCommand));
    }

    Command getCommandType(List<String> tokenizedCommand) throws Exception {
        String commandWord = tokenizedCommand.get(COMMAND_INDEX);

        switch (commandWord) {
            case ANALYSE_COMMAND: return new AnalyseCommand(tokenizedCommand);
            case BREAK_COMMAND: return new BreakCommand(tokenizedCommand);
            case EXIT_COMMAND: System.exit(0);
            default: throw new Exception("Unknown command word");
        }
    }

    public Command getCommand(String inputString) throws Exception {
        String preProcessedString = this.preProcessCommandString(inputString);
        List<String> tokenizedCommand = this.getTokenizedCommand(preProcessedString);
        Command command = getCommandType(tokenizedCommand);
        return command;
    }
}
