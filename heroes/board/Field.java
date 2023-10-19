package main.heroes.board;

import main.heroes.unit.Unit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Field {

    private final Position position;

    private Map<Direction, Field> neighbors = new HashMap<>();

    private Unit unit;

    public Field(Position position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return unit == null;
    }

    public void addNeighbor(Direction direction, Field field) {
        neighbors.put(direction, field);
    }

    public Position getPosition() {
        return position;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public String getSymbol() {
        if (this.unit == null)
            return "     ";
        else {
            return this.unit.getSymbol();
        }
    }
    public Collection<Field> getNeighbors() {
        return neighbors.values();
    }

}
