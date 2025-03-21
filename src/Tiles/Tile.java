package Tiles;

import java.util.*;

public class Tile {
    private final int[][] pixels;
    private final HashMap<Direction, List<Tile>> possibleNeighbours = new HashMap<>();
    private int frequency;

    public Tile(int[][] pixels) {
        this.pixels = pixels;
        frequency = 1;
        for (Direction dir : Direction.values()) {
            possibleNeighbours.put(dir, new ArrayList<>());
        }
    }

    public void addNeighbour(Direction dir, Tile tile) {
       possibleNeighbours.get(dir).add(tile);
    }

    public List<Tile> getPossibleInDir(Direction dir) {
        return possibleNeighbours.get(dir);
    }

    public int getCenterColor() {
        return pixels[1][1];
    }

    public int[][] getPixels() {
        return pixels;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(pixels);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tile t = (Tile)obj;
        return Arrays.deepEquals(pixels, t.pixels);
    }
}
