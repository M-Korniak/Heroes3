package main.heroes.action;

import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UnitsToAttackCalculator {
    public List<Unit> calculatePossibleUnits(Unit unit, Set<Field> possibleMoves) {
        List<Unit> unitsToAttack = new ArrayList<>();
        for (Field field : possibleMoves) {
            if (field.getUnit() != null &&
                    unit.getPlayer() != field.getUnit().getPlayer()) {
                unitsToAttack.add(field.getUnit());
            }
        }
        return unitsToAttack;
    }
}
