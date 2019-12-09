package com.freq.commands;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement this class
public class AnalyseCommand extends Command {

    public AnalyseCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    List<String> preprocessArguments() {
        return null;
    }

    String getTextFromFile() throws Exception {
        File inputTextFile = new File(this.arguments.get(1));
        FileInputStream fileInputStream = new FileInputStream(inputTextFile);

        byte[] data = new byte[(int) inputTextFile.length()];
        fileInputStream.read(data);
        fileInputStream.close();

        return new String(data, "UTF-8");
    }

    double[] getCharacterFrequencies(String inputString) {
        double[] characterFrequencies = new double[26];
        String lowerCaseString = inputString.toLowerCase();

        for (int i = 0; i < lowerCaseString.length(); i++) {
            char ch = lowerCaseString.charAt(i);
            int characterIndex = ((int) ch) - 97;

            if (Character.isAlphabetic(ch))
                characterFrequencies[characterIndex] += 1;
        }

        return characterFrequencies;
    }

    public Result generateAnalysisOutput(double[] characterFrequencies) {
        List<String> outputStrings = new ArrayList<>();

        outputStrings.add("Analysis results: ");

        for (int i = 0; i < characterFrequencies.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append((char) (97 + i));
            stringBuilder.append(" : ");
            stringBuilder.append(characterFrequencies[i]);

            outputStrings.add(stringBuilder.toString());
        }

        return new Result(true, outputStrings);
    }

    @Override
    public Result run() throws Exception {
        String inputString = this.getTextFromFile();
        double[] characterFrequencies = getCharacterFrequencies(inputString);
        Result commandExecutionResult = generateAnalysisOutput(characterFrequencies);
        return commandExecutionResult;
    }
}
