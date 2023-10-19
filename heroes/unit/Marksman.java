package main.heroes.unit;

import main.heroes.fraction.Castle;
import main.heroes.player.Player;

public class Marksman extends AbstractUnit {
    public Marksman(int numberOfUnits, Player player) {
        super(6, 3, 24, 10,
                2, 3, numberOfUnits,
                6, false, true, player, "Mm", new Castle());
    }
}
