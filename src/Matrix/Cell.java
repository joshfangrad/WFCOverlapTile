package Matrix;

import Tiles.Tile;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import static Util.Util.mixColors;

public class Cell {
    private boolean collapsed = false;
    private boolean userDrawn = false;
    private boolean checked = false;
    private final int posX;
    private final int posY;
    private Color color;
    private HashMap<Tile, Integer> options = new HashMap<>();
    private int previousTotalOptions = -1;
    private float entropy;

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
        for (int count : options.values()) {
            totalFrequency += count;
        }

        // Shannon entropy = -sum(p * log2(p))
        float e = 0;
        for (int count : options.values()) {
            float p = count / totalFrequency;
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

    public boolean isUserDrawn() {
        return userDrawn;
    }

    public void setUserDrawn(boolean drawn) {
        userDrawn = drawn;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public HashMap<Tile, Integer> getOptions() {
        return options;
    }

    public void addOption(Tile tile, int frequency) {
        this.options.put(tile, frequency);
    }

    public void setOptions(HashMap<Tile, Integer> options) {
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
            int[] colors = options.keySet().stream().mapToInt(Tile::getCenterColor).toArray();
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
