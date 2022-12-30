package com.smartnet.mozzarttest.generator;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {

    private int randomNumber;
    private Random randomGenerator;
    private ArrayList<Integer> randomNumberList;
    private final String ACTION_RANDOM = "randomNumbers";
    private final String INTENT_EXTRA = "random";
    private Context context;
    public static RandomGenerator instance;


    public RandomGenerator() {

    }

    public static RandomGenerator getInstance(ArrayList<Integer> randomNumberList, Context context) {
        if (instance == null) {
            instance = new RandomGenerator();
        }
        instance.context = context;
        instance.randomNumberList = randomNumberList;
        return instance;
    }

    /**
     * Helper method to generate random numbers on button click
     */
    public void generateRandomNumbers(int rounds) {
        addNumbersToList(rounds);
    }

    /**
     * Helper method to add random number to list
     */
    private void addNumbersToList(int rounds) {
        for (int i = 0; i < rounds; i++) {
            randomNumber = getRandomGenerator().nextInt(81);
            if (!randomNumberList.contains(randomNumber)) {
                randomNumberList.add(randomNumber);
                sendBroadcast(randomNumber);
            } else {
                while (randomNumberList.contains(randomNumber)) {
                    randomNumber = getRandomGenerator().nextInt(81);
                }
                randomNumberList.add(randomNumber);
                sendBroadcast(randomNumber);
            }

        }
    }

    /**
     * Helper method to send broadcast when random generator is activated and we need round numbers with circle depends of what number is generated
     */
    private void sendBroadcast(int number) {
        Intent intent = new Intent(ACTION_RANDOM);
        intent.putExtra(INTENT_EXTRA, number);
        context.sendBroadcast(intent);
    }

    /**
     * Helper method to create random generator
     *
     * @return instance of random generator
     */
    public Random getRandomGenerator() {
        if (randomGenerator == null) {
            randomGenerator = new Random();
        }
        return randomGenerator;
    }
}
