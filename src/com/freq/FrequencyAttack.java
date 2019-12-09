package com.freq;

import com.freq.commands.Command;
import com.freq.commands.Result;
import com.freq.commands.parser.CommandParser;

import java.util.Scanner;

public class FrequencyAttack {

    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        Scanner userInputScanner = new Scanner(System.in);

        showWelcomeMessage();

        while (true) {
            acceptNextCommand(commandParser, userInputScanner);
        }
    }

    public static void showWelcomeMessage() {
        System.out.println("Welcome to FrequencyAttack tool.");
        System.out.println("This tool will assist you in executing a frequency analysis attack on an English language input text file.");
        System.out.println("At any point, stop the program with Ctrl + C to exit");
        System.out.println("Commands:\n\t1. analyse PATH_TO_FILE\n\t2. break PATH_TO_FILE");
    }

    public static void acceptNextCommand(CommandParser commandParser, Scanner userInputScanner) {
        System.out.print("> ");
        String commandString = userInputScanner.nextLine();

        try {
            Command command = commandParser.getCommand(commandString);
            Result commandResult = command.run();
            commandResult.show();
        } catch (Exception e) {
            System.out.println("Encountered command parsing exception as: " + e.getMessage());
        }
    }
}
