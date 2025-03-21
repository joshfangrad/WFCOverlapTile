package Swing;

import javax.swing.*;

import Matrix.Matrix;

public class MainWindow extends JFrame {

    public MainWindow(Matrix matrix) {
        super("WFCTile");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new DrawPanel(matrix));
        pack();

        setVisible(true);
    }
}
