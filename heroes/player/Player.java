package main.heroes.player;

import main.heroes.action.Action;
import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.List;
import java.util.Set;

public interface Player {
    List<Unit> getUnits();
    void setUnits(List<Unit> units);

    Action chooseAction(Set<Action> possibleActions, Unit unit, Set<Field> moves,
                        List<Unit> unitsToAttack, List<Unit> unitsToShoot);
    void deleteUnit(Unit unit);
    String getSymbol();
    int getLuck();
    int getMorale();
}
