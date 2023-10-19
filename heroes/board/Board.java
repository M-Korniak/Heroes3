package main.heroes.board;

import main.heroes.unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final int width, height;

    private final Map<Position, Field> fields = new HashMap<>();

    private final List<Position> positions = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position position = new Position(i, j);
                positions.add(position);
                fields.put(position, new Field(position));
            }
        }
        addNeighbors();
    }
    public void setUnitsOnBoard(List<Unit> leftPlayerUnits, List<Unit> rightPlayerUnits) {
        if (leftPlayerUnits.size() != height && rightPlayerUnits.size() != height) {
            System.out.println("Number of units not matching height of board!");
        }

        for (int i = 0; i < height; i++) {
            fields.get(positions.get(i * width)).setUnit(leftPlayerUnits.get(i));
            leftPlayerUnits.get(i).setField(fields.get(positions.get(i * width)));
        }

        for (int i = 0; i < height; i++) {
            fields.get(positions.get(i * width + width - 1)).setUnit(rightPlayerUnits.get(i));
            rightPlayerUnits.get(i).setField(fields.get(positions.get(i * width + width - 1)));
        }

    }
    public void print() {
        int counter = 1;
        int even = 1;
        System.out.print("   |");
        for (Position position : positions) {
            System.out.print(fields.get(position).getSymbol() + "|");
            if (counter == height * width) {
                System.out.print("\n\n");
                return;
            }
            else if (counter % width == 0) {
                System.out.print("\n");
                if (even % 2 == 0) { System.out.print("   |"); }
                else { System.out.print("|"); };
                even = (even + 1) % 2;
            }
            counter++;
        }

    }
    private void addNeighbors() {
        for (Field field : fields.values()) {
            for (Direction direction : Direction.values()) {
                Field neighbor = fields.get(direction.move(field.getPosition()));
                if (neighbor != null)
                    field.addNeighbor(direction, neighbor);
            }
        }
    }
}
