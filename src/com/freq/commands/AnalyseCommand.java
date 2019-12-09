package com.freq.commands;

import com.freq.analysis.CharacterFrequencies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the analyse command.
 */
public class AnalyseCommand extends Command {

    public AnalyseCommand(List<String> arguments) {
        super(arguments);
    }

    /***
     * Generates formatted output using character frequencies.
     * @param characterFrequencies - Array containing frequency of each character.
     * @return Result object containing formatted output.
     */
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

    /***
     * Run trigger for the analyse command.
     * @return Result object containing command execution output.
     * @throws IOException if input file is not found.
     */
    @Override
    public Result run() throws IOException {
        String inputString = this.getTextFromFile();
        double[] characterFrequencies = CharacterFrequencies.getCharacterFrequencies(inputString);
        Result commandExecutionResult = generateAnalysisOutput(characterFrequencies);
        return commandExecutionResult;
    }
}
