package main.heroes.game;

import main.heroes.action.Action;
import main.heroes.action.ActionCalculator;
import main.heroes.action.NumberOfActionCalculator;
import main.heroes.action.UnitsToAttackCalculator;
import main.heroes.board.BFSCalculator;
import main.heroes.board.Board;
import main.heroes.board.Field;
import main.heroes.player.Player;
import main.heroes.unit.Unit;

import java.util.*;

public class Game {
    private boolean continueGame = true;

    private Player playerLeft, playerRight;

    private Board board;

    public Game(Player playerLeft, Player playerRight, Board board) {
        this.board = board;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        board.setUnitsOnBoard(playerLeft.getUnits(), playerRight.getUnits());
    }

    public void simulateGame() {
        while (continueGame) {
            oneRound();
        }
        if (playerLeft.getUnits().size() != 0)
            System.out.println("Player " + playerLeft.getSymbol() + " wins!");
        else
            System.out.println("Player " + playerRight.getSymbol() + " wins!");
    }
    private void oneRound() {
        List<Unit> listOfAllUnits = new LinkedList<>();
        listOfAllUnits.addAll(playerLeft.getUnits());
        listOfAllUnits.addAll(playerRight.getUnits());
        listOfAllUnits.sort(Comparator.comparingInt(Unit::getSpeed));

        for (Unit unit : listOfAllUnits) {
            unit.restoreDefence();
            unit.setCanCounterAttack(true);
        }

        List<Unit> listOfWaitingUnits = new LinkedList<>();

        BFSCalculator bfsCalculator = new BFSCalculator();
        ActionCalculator actionCalculator = new ActionCalculator();
        UnitsToAttackCalculator unitsToAttackCalculator = new UnitsToAttackCalculator();
        NumberOfActionCalculator numberOfActionCalculator = new NumberOfActionCalculator();
        for (Unit unit : listOfAllUnits) {
            int numberOfAction = numberOfActionCalculator.calculateNumberOfActions(unit);

            for (int i = 0; i < numberOfAction; i++) {
                if (!unit.isDead()) {
                    Set<Field> possibleMoves = bfsCalculator.calculatePossibleMoves(unit.getField());

                    Set<Action> possibleActions = actionCalculator.calculatePossibleActions(unit, possibleMoves);
                    List<Unit> unitsToAttack = unitsToAttackCalculator.calculatePossibleUnits(unit, possibleMoves);
                    List<Unit> unitsToShoot = otherPlayer(unit.getPlayer()).getUnits();
                    Action action = unit.getPlayer()
                            .chooseAction(possibleActions, unit, possibleMoves, unitsToAttack, unitsToShoot);
                    action.execute(listOfWaitingUnits);
                }
                board.print();
                if (playerLeft.getUnits().size() == 0 || playerRight.getUnits().size() == 0) {
                    continueGame = false;
                    return;
                }
            }
        }

        listOfWaitingUnits.sort(Comparator.comparingInt(Unit::getSpeed));

        for (Unit unit : listOfWaitingUnits) {

            if (!unit.isDead()) {
                Set<Field> possibleMoves = bfsCalculator.calculatePossibleMoves(unit.getField());

                Set<Action> possibleActions = actionCalculator.calculatePossibleActions(unit, possibleMoves);
                List<Unit> unitsToAttack = unitsToAttackCalculator.calculatePossibleUnits(unit, possibleMoves);
                List<Unit> unitsToShoot = otherPlayer(unit.getPlayer()).getUnits();
                Action action = unit.getPlayer().
                        chooseAction(possibleActions, unit, possibleMoves, unitsToAttack, unitsToShoot);
                action.execute(listOfWaitingUnits);
            }
            board.print();
            if (playerLeft.getUnits().size() == 0 || playerRight.getUnits().size() == 0) {
                continueGame = false;
                return;
            }
        }

    }
    private Player otherPlayer(Player player) {
        if (player == playerLeft)
            return playerRight;
        else
            return playerLeft;
    }

}
