package main.heroes.action;

import main.heroes.unit.Unit;

import java.util.List;

public class Defend implements Action {

    Unit unit;

    public Defend(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void execute(List<Unit> listOfWaitingUnits) {
        unit.setWait(true);
        unit.defend();
    }
}
