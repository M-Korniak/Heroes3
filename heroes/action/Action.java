package main.heroes.action;

import main.heroes.unit.Unit;

import java.util.List;

public interface Action {

    void execute(List<Unit> listOfWaitingUnits);


}
