package com.freq.analysis;

public class RotationEngine {
    double[] characterFrequencies;
    double[] expectedFrequencies;

    public RotationEngine(double[] characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
        this.expectedFrequencies = new CharacterFrequencies().getExpectedFrequencies();
    }

    public double[] getRelativeFrequencies(double[] frequencyList) {
        double frequencySum = 0;

        for (int i = 0; i < frequencyList.length; i++) {
            frequencySum += frequencyList[i];
        }

        for (int i = 0; i < frequencyList.length; i++) {
            frequencyList[i] = frequencyList[i] / frequencySum;
        }

        return frequencyList;
    }

    public double evaluateRotationLoss(int rotation) {
        double loss = 0;

        for (int i = 0; i < 26; i++) {
            loss += (this.expectedFrequencies[i] - this.characterFrequencies[(i + rotation) % 26]);
        }

        return loss;
    }

    public int minimizeRotationLoss() {
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
