package com.freq.analysis;

import java.util.concurrent.Callable;

/**
 * Implements loss evaluation for concurrent execution.
 */
public class LossEvaluation implements Callable<Double> {
    public String taskIdentifier;

    double[] characterFrequencies;
    double[] expectedFrequencies;
    int rotation;

    public LossEvaluation(String taskIdentifier, double[] characterFrequencies, int rotation) {
        this.taskIdentifier = taskIdentifier;
        this.characterFrequencies = characterFrequencies;
        this.rotation = rotation;

        this.expectedFrequencies = new CharacterFrequencies().getExpectedFrequencies();
    }

    /**
     * Evaluates L1 loss between expected and observed frequencies at a given rotation.
     * @return Double representing the L1 loss with this rotation shift.
     */
    @Override
    public Double call() {
        double loss = 0;

        for (int i = 0; i < 26; i++) {
            loss += Math.abs(this.expectedFrequencies[i] - this.characterFrequencies[(i + this.rotation) % 26]);
        }

        return loss;
    }
}
