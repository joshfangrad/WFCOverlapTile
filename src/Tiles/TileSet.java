package Tiles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TileSet {
    private final HashSet<Tile> tiles;

    public TileSet(List<Tile> tileList) {
        tiles = new HashSet<>(tileList);
    }


    public HashSet<Tile> getTiles() {
        return tiles;
    }
}
