package com.freq.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Abstract class for Commands.
 */
public abstract class Command {
    List<String> arguments;

    public Command(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Read text from file.
     * @return String containing the text read.
     * @throws IOException if file is not found / unreadable for other reasons.
     */
    String getTextFromFile() throws IOException {
        File inputTextFile = new File(this.arguments.get(1));
        FileInputStream fileInputStream = new FileInputStream(inputTextFile);

        byte[] data = new byte[(int) inputTextFile.length()];
        fileInputStream.read(data);
        fileInputStream.close();

        return new String(data, "UTF-8");
    }

    abstract public Result run () throws Exception;
}
