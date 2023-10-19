package main.heroes.player;

import lombok.Getter;
import lombok.Setter;
import main.heroes.action.*;
import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.*;

public class RandomPlayer implements Player {
    public void setUnits(List<Unit> units) {
        this.units = units;
        calculateMorale(units);
    }

    private void calculateMorale(List<Unit> units) {
        int numberOfFractions = 0;
        for (int i = 0; i < units.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (units.get(j).getClass() == units.get(i).getClass()) {
                    numberOfFractions--;
                    break;
                }
            }
            numberOfFractions++;
        }
        morale = 4 - numberOfFractions;
        if (morale < -3) {
            morale = -3;
        }
    }


    private List<Unit> units;


    private String nameOfPlayer;
    @Getter
    private int luck;
    @Getter
    private int morale;
    public RandomPlayer(String nameOfPlayer, int luck) {

        this.nameOfPlayer = nameOfPlayer;
        this.luck = luck;
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }


    @Override
    public Action chooseAction(Set<Action> possibleActions, Unit unit, Set<Field> moves,
                               List<Unit> unitsToAttack, List<Unit> unitsToShoot) {
        List<Action> possibleActionsAsList = new ArrayList<>(possibleActions);
        Collections.shuffle(possibleActionsAsList);
        Action chosenAction = possibleActionsAsList.get(0);

        if (chosenAction instanceof Move) {
            List<Field> possibleMoves = new ArrayList<>(moves);
            Collections.shuffle(possibleMoves);
            ((Move) chosenAction).setField(possibleMoves.get(0));
        }
        else if (chosenAction instanceof Attack) {
            Collections.shuffle(unitsToAttack);
            ((Attack) chosenAction).setUnitToAttack(unitsToAttack.get(0));
        }
        else if (chosenAction instanceof MoveAndAttack) {
            List<Field> possibleMoves = new ArrayList<>(moves);
            Collections.shuffle(possibleMoves);
            ((MoveAndAttack) chosenAction).setDestination(possibleMoves.get(0));
            Collections.shuffle(unitsToAttack);
            ((MoveAndAttack) chosenAction).setUnitToAttack(unitsToAttack.get(0));
        }
        else if (chosenAction instanceof Shoot) {
            Collections.shuffle(unitsToShoot);
            ((Shoot) chosenAction).setUnitToShoot(unitsToShoot.get(0));
        }
        return chosenAction;

    }

    @Override
    public void deleteUnit(Unit unit) {
        units.remove(unit);
    }

    @Override
    public String getSymbol() {
        return nameOfPlayer;
    }
}
