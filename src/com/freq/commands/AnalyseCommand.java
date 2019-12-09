package com.freq.commands;

import com.freq.analysis.CharacterFrequencies;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class AnalyseCommand extends Command {

    public AnalyseCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    List<String> preprocessArguments() {
        return null;
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
        double[] characterFrequencies = CharacterFrequencies.getCharacterFrequencies(inputString);
        Result commandExecutionResult = generateAnalysisOutput(characterFrequencies);
        return commandExecutionResult;
    }
}
