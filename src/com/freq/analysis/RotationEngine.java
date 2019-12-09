package com.freq.analysis;

/**
 * Implements the Rotation Cipher attack.
 * TODO: Using ThreadPool to parallely compute loss for different rotation keys.
 */
public class RotationEngine {
    double[] characterFrequencies;
    double[] expectedFrequencies;

    public RotationEngine(double[] characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
        this.expectedFrequencies = new CharacterFrequencies().getExpectedFrequencies();
    }

    /**
     * Normalizes character frequency array (to sum to 1)
     * @param unnormalizedFrequencies - Double array representing un-normalized character frequencies.
     * @return double array containing normalized character frequencies.
     */
    public double[] getNormalizedRelativeFrequencies(double[] unnormalizedFrequencies) {
        double frequencySum = 0;

        for (int i = 0; i < unnormalizedFrequencies.length; i++) {
            frequencySum += unnormalizedFrequencies[i];
        }

        for (int i = 0; i < unnormalizedFrequencies.length; i++) {
            unnormalizedFrequencies[i] = (unnormalizedFrequencies[i] / frequencySum) * 100;
        }

        return unnormalizedFrequencies;
    }

    /**
     * Evaluates L1 loss between expected and observed frequencies at a given rotation.
     * @param rotation - Integer representing the provided key of the rotation cipher (shift).
     * @return Double representing the L1 loss with this rotation shift.
     */
    public double evaluateRotationLoss(int rotation) {
        double loss = 0;

        for (int i = 0; i < 26; i++) {
            loss += Math.abs(this.expectedFrequencies[i] - this.characterFrequencies[(i + rotation) % 26]);
        }

        return loss;
    }

    /**
     * Finds the best rotation for the current input cipher by minimizing the loss function.
     * @return
     */
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
