package Tiles;

import java.util.*;

public class Tile {
    private final int[][] pixels;
    private final EnumMap<Direction, HashSet<Tile>> possibleNeighbours = new EnumMap<>(Direction.class);
    private int frequency;

    public Tile(int[][] pixels) {
        this.pixels = pixels;
        frequency = 1;
        for (Direction dir : Direction.values()) {
            possibleNeighbours.put(dir, new HashSet<>());
        }
    }

    public void addNeighbour(Direction dir, Tile tile) {
       possibleNeighbours.get(dir).add(tile);
    }

    public HashSet<Tile> getPossibleInDir(Direction dir) {
        return possibleNeighbours.get(dir);
    }

    public int getCenterColor() {
        return pixels[1][1];
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tile t = (Tile)obj;
        return Arrays.deepEquals(pixels, t.pixels);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(pixels);
    }
}
