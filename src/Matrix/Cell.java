package Matrix;

import Tiles.Tile;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;

import static Util.Util.mixColors;

public class Cell {
    private boolean collapsed = false;
    private boolean checked = false;
    private final int posX;
    private final int posY;
    private Color color;
    private HashSet<Tile> options = new HashSet<>();
    private int previousTotalOptions = -1;
    private float entropy = Float.MAX_VALUE;

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
        color = Color.BLACK;
    }

    public void calculateEntropy() {
        if (previousTotalOptions == options.size()) {
            return;
        }

        previousTotalOptions = options.size();
        float totalFrequency = 0;
        for (Tile tile : options) {
            totalFrequency += tile.getFrequency();
        }

        // Shannon entropy = -sum(p * log2(p))
        float e = 0;
        for (Tile tile : options) {
            float p = tile.getFrequency() / totalFrequency;
            if (p > 0) {
                e -= p * (Math.log(p) / Math.log(2));
            }
        }
        entropy = e;
        if (!collapsed) calculateColor();
    }

    public float getEntropy() {
        return entropy;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        calculateColor();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public HashSet<Tile> getOptions() {
        return options;
    }

    public void addOption(Tile tile) {
        this.options.add(tile);
    }

    public void setOptions(HashSet<Tile> options) {
        this.options = options;
    }
    public void clearOptions() {
        options.clear();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Color getColor() {
        return color;
    }

    private void calculateColor() {
        if (options.isEmpty()) {
            color = Color.PINK;
        } else {
            int[] colors = options.stream().mapToInt(Tile::getCenterColor).toArray();
            color = new Color(mixColors(colors));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cell p = (Cell) obj;

        return (p.collapsed == this.collapsed
                && p.getPosX() == this.posX
                && p.getPosY() == this.posY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                collapsed,
                posX,
                posY
        );
    }

}
