package com.freq.commands;

import com.freq.analysis.CharacterFrequencies;
import com.freq.analysis.RotationEngine;

import java.util.ArrayList;
import java.util.List;

public class BreakCommand extends Command {

    public BreakCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    List<String> preprocessArguments() {
        return null;
    }

    String decipherByRotation (int rotationSize, String inputText) {
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            if (Character.isAlphabetic(inputText.charAt(i))){
                int cipherCharacterASCII = (int) inputText.charAt(i);
                int rotatedCharacterASCII = ((cipherCharacterASCII - 97 + 26 - rotationSize) % 26) + 97;

                plainText.append((char) rotatedCharacterASCII);
            } else {
                plainText.append(inputText.charAt(i));
            }
        }

        return plainText.toString();
    }

    List<String> getOutputMessages(int bestRotation, String plaintext) {
        List<String> outputStrings = new ArrayList<>();

        String breakSuccessMessage = new String("Rotation cipher break results: ");
        String bestRotationMessage = new String("Best rotation: " + bestRotation);
        String plainTextMessage = new String("Deciphered plaintext:\n" + plaintext);

        outputStrings.add(breakSuccessMessage);
        outputStrings.add(bestRotationMessage);
        outputStrings.add(plainTextMessage);

        return outputStrings;
    }

    @Override
    public Result run() throws Exception {
        String inputText = this.getTextFromFile();
        double[] characterFrequencies = CharacterFrequencies.getCharacterFrequencies(inputText);

        RotationEngine rotationEngine = new RotationEngine(characterFrequencies);
        int bestRotation = rotationEngine.minimizeRotationLoss();

        if (bestRotation == -1) {
            throw new Exception("Rotation and loss evaluation failed");
        }

        String plaintext = decipherByRotation(bestRotation, inputText);
        List<String> outputMessages = getOutputMessages(bestRotation, plaintext);

        return new Result(true, outputMessages);
    }
}
