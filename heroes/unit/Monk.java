package main.heroes.unit;

import main.heroes.fraction.Castle;
import main.heroes.player.Player;

public class Monk extends AbstractUnit {
    public Monk(int numberOfUnits, Player player) {
        super(12, 7, 12, 30,
                10, 12, numberOfUnits,
                5, false, true, player, "Mo", new Castle());
    }
}
