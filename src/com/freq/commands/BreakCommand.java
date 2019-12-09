package com.freq.commands;

import java.util.ArrayList;
import java.util.List;

// TODO: Implement this class
public class BreakCommand extends Command {
    public BreakCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    List<String> preprocessArguments() {
        return null;
    }

    @Override
    public Result run() {
        return new Result(true, new ArrayList<String>());
    }
}
