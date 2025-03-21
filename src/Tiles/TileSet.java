package Tiles;

import java.util.HashMap;
import java.util.HashSet;

public class TileSet {
    private final HashMap<Tile, Integer> tiles = new HashMap<>();

    public TileSet() {
    }

    public void addOrIncrementTile(Tile tile) {
        tiles.put(tile, tiles.getOrDefault(tile, 0) + 1);
    }

    public HashMap<Tile, Integer> getTiles() {
        return tiles;
    }
}
