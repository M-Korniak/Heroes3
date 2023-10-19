package main.heroes.action;

import main.heroes.board.Field;
import main.heroes.unit.Unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DistanceCalculator {
    public int calculateDistance(Unit unit, Unit unitToShoot) {
        int distance = 1;
        Field start = unit.getField();
        Set<Field> visited = new HashSet<>();
        List<Field> currentLevel = new ArrayList<>();
        List<Field> nextLevel = new ArrayList<>();

        visited.add(start);
        currentLevel.add(start);
        while(true) {
            for (Field field : currentLevel) {
                for (Field neighbor : field.getNeighbors()) {
                    if (field.getUnit() != null && field.getUnit() == unitToShoot)
                        return distance;
                    visited.add(neighbor);
                    nextLevel.add(neighbor);

                }
            }
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
            distance++;
        }
    }
}
