package main.heroes.unit;

import main.heroes.fraction.Castle;
import main.heroes.player.Player;

public class Halberdier extends AbstractUnit {
    public Halberdier(int numberOfUnits, Player player) {
        super(6, 5, 0, 10,
                2, 3, numberOfUnits,
                5, false, false, player, "Hb", new Castle());
    }
}
