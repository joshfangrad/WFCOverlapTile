import Matrix.Matrix;
import Tiles.TileSet;
import Tiles.TileSetGenerator;
import Matrix.Solver;

public class Benchmark {
    private static final int RUN_COUNT = 25;
    private static final int TEST_SIZE = 10;


    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "/assets/lava1.png";

        TileSetGenerator tg = new TileSetGenerator();
        TileSet tiles = tg.generateTileSetFromImage(path, true);

        long lowestTime = Long.MAX_VALUE;
        long highestTime = Long.MIN_VALUE;
        long avg = 0;

        for (int i = 0; i < RUN_COUNT; i++) {
           long startTime = System.currentTimeMillis();
           runBenchmark(tiles);
           long endTime = System.currentTimeMillis();
           long time = endTime-startTime;
           System.out.println("Run " + (i+1) + ": " + time + "ms");

           if (time < lowestTime) lowestTime = time;
           if (time > highestTime) highestTime = time;
           avg += time;
        }

        avg /= RUN_COUNT;
        System.out.println();
        System.out.println("Longest run: " + highestTime + "ms");
        System.out.println("Shortest run: " + lowestTime + "ms");
        System.out.println("average time: " + avg + "ms");
    }

    private static void runBenchmark(TileSet tiles) {
        Matrix matrix = new Matrix(TEST_SIZE,TEST_SIZE);
        Solver solver = new Solver(matrix, tiles, true, 100);

        boolean done;
        do {
            done = solver.step();
        } while(!done);
    }
}
