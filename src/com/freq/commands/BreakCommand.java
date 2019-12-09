package com.freq.commands;

import com.freq.analysis.CharacterFrequencies;
import com.freq.analysis.RotationEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/***
 * Implements the break command.
 */
public class BreakCommand extends Command {
    static final String OUTPUT_FILE_PATH = "decrypt.out";

    public BreakCommand(List<String> arguments) {
        super(arguments);
    }

    /**
     * Decrypts ciphertext given plaintext and rotation key.
     * @param rotationKey - Integer representing the shift (key) of rotation cipher.
     * @param inputText - String representing the ciphertext to decrypt.
     * @return String containing the decrypted plaintext.
     */
    String decipherByRotation (int rotationKey, String inputText) {
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < inputText.length(); i++) {
            if (Character.isAlphabetic(inputText.charAt(i))){
                int cipherCharacterASCII = (int) inputText.charAt(i);
                int rotatedCharacterASCII = ((cipherCharacterASCII - 97 + 26 - rotationKey) % 26) + 97;

                plainText.append((char) rotatedCharacterASCII);
            } else {
                plainText.append(inputText.charAt(i));
            }
        }

        return plainText.toString();
    }

    void writeStringToFile (String textToWrite, String fileName) throws IOException {
        Path path = Paths.get(OUTPUT_FILE_PATH);
        Files.write(path, textToWrite.getBytes());
    }

    /**
     * Generate output messages for display given the rotation key and plaintext.
     * @param rotationKey - Integer representing the rotation key of cipher.
     * @return List<String> of output messages to display.
     */
    List<String> getOutputMessages(int rotationKey) {
        List<String> outputStrings = new ArrayList<>();

        String breakSuccessMessage = new String("Rotation cipher break results: ");
        String bestRotationMessage = new String("Best rotation: " + rotationKey);
        String plainTextMessage = new String("Deciphered plaintext written to: " + OUTPUT_FILE_PATH);

        outputStrings.add(breakSuccessMessage);
        outputStrings.add(bestRotationMessage);
        outputStrings.add(plainTextMessage);

        return outputStrings;
    }

    /**
     * Run trigger for the break command.
     * @return Result object containing the results of the break command.
     * @throws Exception if file read or rotation fails.
     */
    @Override
    public Result run() throws Exception {
        String inputText = this.getTextFromFile();
        double[] characterFrequencies = CharacterFrequencies.getCharacterFrequencies(inputText);

        RotationEngine rotationEngine = new RotationEngine(characterFrequencies);
        int bestRotation = rotationEngine.minimizeRotationLoss();

        if (bestRotation == -1) {
            // TODO: Investigate when this might be triggered, and replace with custom Exceptions.
            throw new Exception("Rotation and loss evaluation failed");
        }

        String plaintext = decipherByRotation(bestRotation, inputText);
        writeStringToFile(plaintext, OUTPUT_FILE_PATH);
        List<String> outputMessages = getOutputMessages(bestRotation);

        return new Result(true, outputMessages);
    }
}
