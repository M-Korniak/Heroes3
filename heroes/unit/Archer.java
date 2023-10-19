package main.heroes.unit;

import main.heroes.fraction.Castle;
import main.heroes.player.Player;

public class Archer extends AbstractUnit {
    public Archer(int numberOfUnits, Player player) {
        super(6, 3, 12, 10,
                2, 3, numberOfUnits,
                4, false, true, player, "Ar", new Castle());

    }
}
