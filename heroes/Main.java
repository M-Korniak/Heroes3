package main.heroes;

import main.heroes.board.Board;
import main.heroes.game.*;
import main.heroes.player.RandomPlayer;
import main.heroes.unit.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 5);

        List<Unit> player1Units = new ArrayList<>();
        RandomPlayer randomPlayer1 = new RandomPlayer("1", 3);
        player1Units.add(new Halberdier(10, randomPlayer1));
        player1Units.add(new Archer(10, randomPlayer1));
        player1Units.add(new Marksman(10, randomPlayer1));
        player1Units.add(new Pikeman(10, randomPlayer1));
        player1Units.add(new Monk(10, randomPlayer1));
        randomPlayer1.setUnits(player1Units);

        List<Unit> player2Units = new ArrayList<>();
        RandomPlayer randomPlayer2 = new RandomPlayer("2", 3);
        player2Units.add(new Halberdier(10, randomPlayer2));
        player2Units.add(new Archer(10, randomPlayer2));
        player2Units.add(new Marksman(10, randomPlayer2));
        player2Units.add(new Pikeman(10, randomPlayer2));
        player2Units.add(new Monk(10, randomPlayer2));
        randomPlayer2.setUnits(player2Units);

        board.setUnitsOnBoard(player1Units, player2Units);
        board.print();

        Game game = new Game(randomPlayer1, randomPlayer2, board);
        game.simulateGame();

    }
}
