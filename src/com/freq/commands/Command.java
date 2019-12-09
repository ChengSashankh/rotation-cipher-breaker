package com.freq.commands;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public abstract class Command {
    List<String> arguments;

    public Command(List<String> arguments) {
        this.arguments = arguments;
    }

    String getTextFromFile() throws Exception {
        File inputTextFile = new File(this.arguments.get(1));
        FileInputStream fileInputStream = new FileInputStream(inputTextFile);

        byte[] data = new byte[(int) inputTextFile.length()];
        fileInputStream.read(data);
        fileInputStream.close();

        return new String(data, "UTF-8");
    }

    abstract List<String> preprocessArguments();

    abstract public Result run () throws Exception;
}
