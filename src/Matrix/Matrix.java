package Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import Tiles.Direction;
import Util.Settings;
import Util.Tuple;

public class Matrix {
    private final int width;
    private final int height;
    private final Cell[][] matrix;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = createMatrix(width, height);
    }

    public void drawMatrix(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = matrix[x][y];
                g.setColor(cell.getColor());
                g.fillRect(x * Settings.PIXEL_SIZE, y * Settings.PIXEL_SIZE, Settings.PIXEL_SIZE, Settings.PIXEL_SIZE);
            }
        }
    }

    public HashSet<Cell> getCells() {
        return Arrays.stream(matrix).flatMap(Arrays::stream).collect(Collectors.toCollection(HashSet::new));
    }

    public boolean isEncased(int x, int y) {
        if (!matrix[x][y].isCollapsed()) return false;

       Tuple<Cell, Direction>[] neighbours = getNeighbours(x, y);
       for (Tuple<Cell, Direction> neighbour : neighbours) {
           if (!neighbour.getFirst().isCollapsed()) {
               return false;
           }
       }
       return true;
    }

    public Tuple<Cell, Direction>[] getNeighbours(int x, int y) {
        List<Tuple<Cell, Direction>> neighbours = new ArrayList<>();

        if (x > 0) neighbours.add(new Tuple<>(matrix[x - 1][y], Direction.WEST));
        if (x < width - 1) neighbours.add(new Tuple<>(matrix[x + 1][y], Direction.EAST));
        if (y > 0) neighbours.add(new Tuple<>(matrix[x][y - 1], Direction.NORTH));
        if (y < height - 1) neighbours.add(new Tuple<>(matrix[x][y + 1], Direction.SOUTH));

        return neighbours.toArray(new Tuple[0]);
    }

    private Cell[][] createMatrix(int width, int height) {
        Cell[][] newMatrix = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                newMatrix[x][y] = new Cell(x, y);
            }
        }
        return newMatrix;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
