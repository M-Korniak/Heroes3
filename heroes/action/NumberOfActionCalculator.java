package main.heroes.action;

import main.heroes.unit.Unit;

import java.util.Random;

public class NumberOfActionCalculator {
    public int calculateNumberOfActions(Unit unit) {
        Random rand = new Random();
        int numberOfActions = 1;
        double randomNumber = rand.nextDouble();

        int morale = unit.getPlayer().getMorale();

        if (morale == 3) {
            if (randomNumber < 0.125)
                numberOfActions++;
        }
        else if (morale == 2) {
            if (randomNumber < 0.083)
                numberOfActions++;
        }
        else if (morale == 1) {
            if (randomNumber < 0.042)
                numberOfActions++;
        }
        else if (morale == -1) {
            if (randomNumber < 0.083)
                numberOfActions--;
        }
        else if (morale == -2) {
            if (randomNumber < 0.125)
                numberOfActions--;
        }
        else if (morale == -3) {
            if (randomNumber < 0.25)
                numberOfActions--;
        }
        return numberOfActions;

    }
}
