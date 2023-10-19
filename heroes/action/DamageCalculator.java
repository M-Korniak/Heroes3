package main.heroes.action;

import main.heroes.unit.Unit;

import java.util.Random;

public class DamageCalculator {
    int calculateDamage(Unit unit, Unit unitToAttack) {
        int damage = 0;
        Random random = new Random();
        if (unit.getNumberOfUnits() < 10) {
            for (int i = 0; i < unit.getNumberOfUnits(); i++) {
                damage += unit.getMinimalDamage() +
                        random.nextInt(unit.getMaximalDamage()
                                - unit.getMinimalDamage());
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                damage += unit.getMinimalDamage() +
                        random.nextInt(unit.getMaximalDamage()
                                - unit.getMinimalDamage());
                damage = damage * unit.getNumberOfUnits() / 10;
            }
        }
        double bonus = 1;
        if (unit.getAttack() - unitToAttack.getRealDefence() >= 0) {
            bonus = 1 + 0.05 * (unit.getAttack() - unitToAttack.getRealDefence());
            if (bonus > 3)
                bonus = 3;
        }
        else {
            bonus = 1 - 0.025 * (unitToAttack.getRealDefence() - unit.getAttack());
            if (bonus < 0.7)
                bonus = 0.7;
        }
        damage = (int) (damage * bonus);

        Random rand = new Random();
        double randomNumber = rand.nextDouble();
        int luck = unit.getPlayer().getLuck();
        if (luck == 3) {
            if (randomNumber < 0.125) {
                damage = damage * 2;
            }
        }
        else if (luck == 2) {
            if (randomNumber < 0.083) {
                damage = damage * 2;
            }
        }
        else if (luck == 1) {
            if (randomNumber < 0.042) {
                damage = damage * 2;
            }
        }
        return damage;
    }
}
