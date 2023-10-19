package main.heroes.action;

import main.heroes.unit.Unit;

import java.util.List;

public class Wait implements Action {
    Unit unit;
    public Wait(Unit unit) {
        this.unit = unit;
    }
    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(false);
        listOfWaitingUnits.add(unit);
    }
}
