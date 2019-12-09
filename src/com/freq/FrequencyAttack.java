package com.freq;

import com.freq.commands.Command;
import com.freq.commands.Result;
import com.freq.commands.parser.CommandParser;

import java.util.Scanner;

/**
 * Implements the FrequencyAttack tool.
 * Main class for project.
 */
public class FrequencyAttack {

    /**
     * Prints welcome message to screen on start up.
     */
    public static void showWelcomeMessage() {
        System.out.println("Welcome to FrequencyAttack tool.");
        System.out.println("This tool will assist you in executing a frequency analysis attack on an English language input text file.");
        showWelcomeMessage();
    }

    public static void showCommandList() {
        System.out.println("Commands:\n\t1. analyse PATH_TO_FILE\n\t2. break PATH_TO_FILE\n\t3. exit");
    }

    /**
     * Receives and executes command from user.
     * @param commandParser
     * @param userInputScanner
     */
    public static void acceptNextCommand(CommandParser commandParser, Scanner userInputScanner) {
        System.out.print("> ");
        String commandString = userInputScanner.nextLine();

        try {
            Command command = commandParser.getCommand(commandString);
            Result commandResult = command.run();
            commandResult.show();
        } catch (Exception e) {
            System.out.println("Encountered command execution error: " + e.getMessage());
        }
    }

    /**
     * Default main entry point for binary.
     * @param args - (Unused) Command line arguments to binary.
     */
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        Scanner userInputScanner = new Scanner(System.in);

        showWelcomeMessage();

        // Accept user inputs until exit is triggered.
        while (true) {
            acceptNextCommand(commandParser, userInputScanner);
        }
    }
}
