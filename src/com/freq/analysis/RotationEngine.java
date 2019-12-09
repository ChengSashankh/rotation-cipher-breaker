package com.freq.analysis;

import java.util.Arrays;

public class RotationEngine {
    double[] characterFrequencies;
    double[] expectedFrequencies;

    public RotationEngine(double[] characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
        this.expectedFrequencies = new CharacterFrequencies().getExpectedFrequencies();
    }

    public double[] getNormalizedRelativeFrequencies(double[] frequencyList) {
        double frequencySum = 0;

        for (int i = 0; i < frequencyList.length; i++) {
            frequencySum += frequencyList[i];
        }

        for (int i = 0; i < frequencyList.length; i++) {
            frequencyList[i] = (frequencyList[i] / frequencySum) * 100;
        }

        return frequencyList;
    }

    public double evaluateRotationLoss(int rotation) {
        double loss = 0;

        for (int i = 0; i < 26; i++) {
            loss += Math.abs(this.expectedFrequencies[i] - this.characterFrequencies[(i + rotation) % 26]);
        }

        return loss;
    }

    public int minimizeRotationLoss() {
        // Normalize the relative frequencies
        this.expectedFrequencies = this.getNormalizedRelativeFrequencies(this.expectedFrequencies);
        this.characterFrequencies = this.getNormalizedRelativeFrequencies(this.characterFrequencies);

        // Find most likely rotation
        double minLoss = Double.MAX_VALUE;
        int bestRotation = -1;

        for (int i = 0; i < 26; i++) {
            double lossAtCurrentIteration = this.evaluateRotationLoss(i);
            if (lossAtCurrentIteration < minLoss) {
                minLoss = lossAtCurrentIteration;
                bestRotation = i;
            }
        }

        return bestRotation;
    }

}
