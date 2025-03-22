package Tiles;

import ImageLoad.ImageLoad;
import Util.Settings;
import Util.TileException;
import Util.Util;

import java.util.ArrayList;
import java.util.List;

public class TileSetGenerator {

    public TileSetGenerator() {
    }

    public TileSet generateTileSetFromImage(String path, boolean allowRotation) throws TileException {
        int[][] image = ImageLoad.convertImgTo2DRGB(path);

        List<Tile> tiles = new ArrayList<>();

        if (image.length < Settings.TILE_WIDTH || image[0].length < Settings.TILE_HEIGHT) {
            throw new TileException("Image is smaller than base tile size");
        }

        //we offset our indexes when looping over the image so we can generate tiles that wrap around the edges
        for (int i = -1; i < image.length + 1; i++) {
            for (int j = -1; j < image[0].length + 1; j++) {
                List<int[][]> tilesToAdd = new ArrayList<>();

                tilesToAdd.add(grabTile(i, j, image));
                if (allowRotation) {
                    List<int[][]> rotations = generateRotations(grabTile(i, j, image));
                    tilesToAdd.addAll(rotations);
                }

                for (int[][] tile : tilesToAdd) {
                    Tile newTile = new Tile(tile);
                    if (tiles.contains(newTile)) {
                        tiles.get(tiles.indexOf(newTile)).incrementFrequency();
                    } else {
                        tiles.add(newTile);
                    }
                }
            }
        }

        calculateNeighbours(tiles);

        return new TileSet(tiles);
    }

    private void calculateNeighbours(List<Tile> tiles) {
        for (Tile tile : tiles) {
            for (Tile tile2 : tiles) {
                for (Direction dir : Direction.values()) {
                    if (tilesMatch(tile, tile2, dir)) {
                        tile.addNeighbour(dir, tile2);
                    }
                }
            }
        }
    }

    private boolean tilesMatch(Tile tile1, Tile tile2, Direction dir) {
        int[][] pixels1 = tile1.getPixels();
        int[][] pixels2 = tile2.getPixels();

        int size = pixels1.length; // Assuming 3x3 tiles consistently

        switch (dir) {
            case NORTH:
                // Compare the top 2 rows of tile1 to the bottom 2 rows of tile2
                for (int i = 0; i < size; i++) {
                    if (pixels1[0][i] != pixels2[size - 2][i] || pixels1[1][i] != pixels2[size - 1][i]) {
                        return false;
                    }
                }
                break;

            case SOUTH:
                // Compare the bottom 2 rows of tile1 to the top 2 rows of tile2
                for (int i = 0; i < size; i++) {
                    if (pixels1[size - 2][i] != pixels2[0][i] || pixels1[size - 1][i] != pixels2[1][i]) {
                        return false;
                    }
                }
                break;

            case EAST:
                // Compare the right 2 columns of tile1 to the left 2 columns of tile2
                for (int i = 0; i < size; i++) {
                    if (pixels1[i][size - 2] != pixels2[i][0] || pixels1[i][size - 1] != pixels2[i][1]) {
                        return false;
                    }
                }
                break;

            case WEST:
                // Compare the left 2 columns of tile1 to the right 2 columns of tile2
                for (int i = 0; i < size; i++) {
                    if (pixels1[i][0] != pixels2[i][size - 2] || pixels1[i][1] != pixels2[i][size - 1]) {
                        return false;
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid direction");
        }

        return true;
    }


    private int[][] grabTile(int posX, int posY, int[][] img) {
        int[][] returnArray = new int[3][3];
        for (int i = 0; i < 3; i++) {
            int x = Util.indexWrap(posX + i, img.length);
            for (int j = 0; j < 3; j++) {
                int y = Util.indexWrap(posY + j, img[0].length);
                returnArray[i][j] = img[x][y];
            }
        }
        return returnArray;
    }

    private List<int[][]> generateRotations(int[][] tile) {
        List<int[][]> returnList = new ArrayList<>();
        returnList.add(rotateCW(tile));
        returnList.add(rotateCW(returnList.get(0)));
        returnList.add(rotateCW(returnList.get(1)));
        return returnList;
    }

    static int[][] rotateCW(int[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M - 1 - r] = mat[r][c];
            }
        }
        return ret;
    }
}
