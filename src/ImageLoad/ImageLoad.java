package ImageLoad;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoad {

    public static int[][] convertImgTo2DRGB(String path) {
        BufferedImage image;
        try {
            image = loadImageFromPath(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[][] result = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[y][x] = image.getRGB(x, y);
            }
        }
        return result;
    }

    public static BufferedImage loadImageFromPath(String path) throws IOException {
        try {
            File imageFile = new File(path);
            return ImageIO.read(imageFile);
        } catch(IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
