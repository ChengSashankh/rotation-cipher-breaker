package com.freq.commands;

import java.util.List;

public class Result {
    Boolean success;
    List<String> output;

    public Result(Boolean success, List<String> outputString) {
        this.success = success;
        this.output = outputString;
    }

    public void show() {
        for (String outputString : this.output) {
            System.out.println(outputString);
        }
    }
}
