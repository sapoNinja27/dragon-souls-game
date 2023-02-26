package utils;

import enums.TipoFonte;

import java.awt.*;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

public class FonteUtils {

    private static final Set<Font> cache = new HashSet<>(0);

    public static Font CrimsonText(TipoFonte style, int size) {
        Font fonte = cache
                .stream()
                .filter(font -> font.getName().contains("Crimson") && font.getName().contains(style.getCod()) && font.getSize() == size)
                .findFirst()
                .orElse(null);
        if (nonNull(fonte)) {
            return fonte;
        }
        fonte = getCustomFont("CrimsonText", style, size);
        cache.add(fonte);
        return fonte;
    }

    /**
     * Busca uma fonte customizada dos resources
     * Muito pesado para ser usado diretamente, ao inves disso, criar um get separado pra cada fonte e salvar em cache
     *
     * @param path  nome da fonte
     * @param style estilo da fonte
     * @param size  tamanho da fonte
     */
    private static Font getCustomFont(String path, TipoFonte style, int size) {
        Font font = null;
        try {
            ClassLoader classLoader = StringUtils.class.getClassLoader();
            InputStream fontStream = classLoader.getResourceAsStream(String.format("fonts/%s-%s.ttf", path, style.getCod()));
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, size);
        } catch (Exception ignored) {}
        return font;
    }
}
