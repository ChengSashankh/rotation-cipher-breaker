package com.freq.analysis;

import java.util.concurrent.*;

/**
 * Implements the Rotation Cipher attack.
 * TODO: Using ThreadPool to parallely compute loss for different rotation keys.
 */
public class RotationEngine {
    double[] characterFrequencies;
    double[] expectedFrequencies;
    ThreadPoolExecutor threadPoolExecutor;

    static final int NUM_THREADS = 5;

    public RotationEngine(double[] characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
        this.expectedFrequencies = new CharacterFrequencies().getExpectedFrequencies();

        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUM_THREADS);
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
     * Finds the best rotation for the current input cipher by minimizing the loss function.
     * @return Integer representing best rotation key.
     */
    public int minimizeRotationLoss() throws InterruptedException, ExecutionException {
        // Normalize the relative frequencies
        this.expectedFrequencies = this.getNormalizedRelativeFrequencies(this.expectedFrequencies);
        this.characterFrequencies = this.getNormalizedRelativeFrequencies(this.characterFrequencies);

        // Find most likely rotation
        double minLoss = Double.MAX_VALUE;
        int bestRotation = -1;

        FutureTask[] lossAtIterationFutures = new FutureTask[26];

        for (int i = 0; i < 26; i++) {
            LossEvaluation lossEvaluation = new LossEvaluation("Rotation" + i, this.characterFrequencies, i);
            lossAtIterationFutures[i] = new FutureTask(lossEvaluation);
            threadPoolExecutor.submit(lossAtIterationFutures[i]);
        }

        double[] lossValues = new double[26];

        for (int i = 0; i < 26; i++) {
            lossValues[i] = (Double) lossAtIterationFutures[i].get();

            if (lossValues[i] < minLoss) {
                minLoss = lossValues[i];
                bestRotation = i;
            }
        }

        return bestRotation;
    }

}
