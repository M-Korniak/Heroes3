package main.heroes.action;

import lombok.Setter;
import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.List;

public class MoveAndAttack implements Action {
    private Unit unit;
    @Setter
    private Field destination;
    @Setter
    private Unit unitToAttack;
    public MoveAndAttack(Unit unit) {
        this.unit = unit;
    }
    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(true);
        Move move = new Move(unit);
        move.setField(destination);
        move.execute(listOfWaitingUnits);
        Attack attack = new Attack(unit);
        attack.setUnitToAttack(unitToAttack);
        attack.execute(listOfWaitingUnits);
    }



}
