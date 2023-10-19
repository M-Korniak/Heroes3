package main.heroes.action;

import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.List;

public class Move implements Action {
    private Unit unit;
    private Field destination;

    public Move(Unit unit) {
        this.unit = unit;
    }
    public void setField(Field destination) {
        this.destination = destination;
    }
    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(true);
        unit.doMove(destination);
    }
}
