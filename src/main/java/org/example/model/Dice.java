package org.example.model;

import lombok.Getter;

import java.util.Random;

@Getter
public class Dice {

    int rollingDice(int noOfDices)
    {
        Random random = new Random();
        return (noOfDices*random.nextInt(1,6));
    }

}
