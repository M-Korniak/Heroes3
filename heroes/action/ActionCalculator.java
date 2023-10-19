package main.heroes.action;

import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.HashSet;
import java.util.Set;

public class ActionCalculator {
    public Set<Action> calculatePossibleActions(Unit unit, Set<Field> possibleMoves) {
        Set<Action> possibleActions = new HashSet<>();
        possibleActions.add(new Move(unit));
        possibleActions.add(new Defend(unit));
        if (unit.canWait()) { possibleActions.add(new Wait(unit)); }
        if (unit.isShooting()) { possibleActions.add(new Shoot(unit)); }

        UnitsToAttackCalculator unitsToAttackCalculator = new UnitsToAttackCalculator();
        if (unitsToAttackCalculator.calculatePossibleUnits(unit, possibleMoves).size() != 0) {
                possibleActions.add(new Attack(unit));
        }

        for (Field field : possibleMoves) {
            if (field .getUnit() != null && field.getUnit().getPlayer() != unit.getPlayer()) {
                possibleActions.add(new MoveAndAttack(unit));
                break;
            }
        }
        return possibleActions;
    }
}
