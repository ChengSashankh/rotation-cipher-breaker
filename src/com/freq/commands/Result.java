package com.freq.commands;

import java.util.List;

/**
 * Encapsulates results from command execution for display.
 */
public class Result {
    Boolean success;
    List<String> output;

    public Result(Boolean success, List<String> outputString) {
        this.success = success;
        this.output = outputString;
    }

    /**
     * Trigger to print Result object to screen.
     */
    public void show() {
        for (String outputString : this.output) {
            System.out.println(outputString);
        }
    }
}
