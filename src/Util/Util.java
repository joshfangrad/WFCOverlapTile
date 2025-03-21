package Util;

import java.util.List;

public class Util {
    public static int indexWrap(int i, int max) {
//        if (i < 0) {
//            return max - Math.abs(i % -max);
//        } else {
//            return i % max;
//        }
        return (i % max + max) % max;
    }

    public static int mixColors(int[] colors) {
        if (colors == null || colors.length == 0) throw new IllegalArgumentException("No colors provided");

        int totalRed = 0, totalGreen = 0, totalBlue = 0;
        int n = colors.length;

        for (int color : colors) {
            totalRed += (color >> 16) & 0xFF;
            totalGreen += (color >> 8) & 0xFF;
            totalBlue += color & 0xFF;
        }

        int mixedRed = Math.min(255, totalRed / n);
        int mixedGreen = Math.min(255, totalGreen / n);
        int mixedBlue = Math.min(255, totalBlue / n);

        return (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;
    }

}
