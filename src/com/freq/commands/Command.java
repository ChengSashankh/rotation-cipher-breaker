package com.freq.commands;

import java.util.List;

public abstract class Command {
    List<String> arguments;

    public Command(List<String> arguments) {
        this.arguments = arguments;
    }

    abstract List<String> preprocessArguments();

    abstract Result run ();

}
