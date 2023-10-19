package main.heroes.unit;

import main.heroes.fraction.Castle;
import main.heroes.player.Player;

public class Pikeman extends AbstractUnit {
    public Pikeman(int numberOfUnits, Player player) {
        super(4, 5, 0, 10,
                1, 3, numberOfUnits,
                4, false, false, player, "Pm", new Castle());
    }

}
