package Swing;
import Util.Settings;
import Matrix.Matrix;

import javax.swing.*;
import java.awt.*;


public class DrawPanel extends JPanel {
    private final Matrix matrix;


    public DrawPanel(Matrix matrix) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.matrix = matrix;

        this.setPreferredSize(getPreferredSize());
    }

    public Dimension getPreferredSize() {
        return new Dimension(matrix.getWidth() * Settings.PIXEL_SIZE, matrix.getHeight() * Settings.PIXEL_SIZE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        matrix.drawMatrix(g);
    }


}
