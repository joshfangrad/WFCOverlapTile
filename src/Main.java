import Matrix.Matrix;
import Matrix.Solver;
import Swing.MainWindow;
import Tiles.TileSet;
import Tiles.TileSetGenerator;


public class Main {
    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "/assets/bigislands2.png";
//        String path = System.getProperty("user.dir") + "/assets/lava1.png";
//        String path = System.getProperty("user.dir") + "/assets/3bricks.png";
//        String path = System.getProperty("user.dir") + "/assets/flowers.png";

        TileSetGenerator tg = new TileSetGenerator();
        TileSet tiles = tg.generateTileSetFromImage(path);

        Matrix matrix = new Matrix(100,80);
        MainWindow window = new MainWindow(matrix);

        Solver solver = new Solver(matrix, tiles);

        boolean done = false;
        do {
            done = solver.step();
            window.repaint();
        } while(!done);
    }
}
