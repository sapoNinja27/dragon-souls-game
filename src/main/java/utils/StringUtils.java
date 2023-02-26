package utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String repeat(String repeteable, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(repeteable);
        }
        return stringBuilder.toString();
    }

    public static void write(Graphics g, String descricao, Font font, int x, int y) {
        String[] descricoes = descricao.split("\n");
        for (int i = 0; i < descricoes.length; i++) {
            ImageUtils.draw(g, descricoes[i], x, y + 50 + (i * font.getSize() + font.getSize() / 2), Color.WHITE, font);
        }
    }
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}
