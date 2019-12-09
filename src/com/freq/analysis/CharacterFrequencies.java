package com.freq.analysis;

public class CharacterFrequencies {

    public final double[] relativeFrequencies =  {
            8.12,
            1.49,
            2.71,
            4.32,
            12.02,
            2.3,
            2.03,
            5.92,
            7.31,
            0.1,
            0.69,
            3.98,
            2.61,
            6.95,
            7.68,
            1.82,
            0.11,
            6.02,
            6.28,
            9.1,
            2.88,
            1.11,
            2.09,
            0.17,
            2.11,
            0.07
    };

    public double[] getExpectedFrequencies() {
        return relativeFrequencies;
    }

    public static double[] getCharacterFrequencies(String inputText) {
        double[] characterFrequencies = new double[26];
        String lowerCaseString = inputText.toLowerCase();

        for (int i = 0; i < lowerCaseString.length(); i++) {
            char ch = lowerCaseString.charAt(i);
            int characterIndex = ((int) ch) - 97;

            if (characterIndex > 0 && characterIndex < 26)
                characterFrequencies[characterIndex] += 1;
        }

        return characterFrequencies;
    }
}
