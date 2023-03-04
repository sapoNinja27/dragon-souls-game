package main.utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImageUtils {

    public static void drawPolygon(Color color, Graphics g, Polygon polygon) {
        g.setColor(color);
        g.fillPolygon(polygon);
    }

    public static Polygon createPolygon(int... posicoes) {
        if (posicoes.length % 2 != 0) {
            throw new IllegalArgumentException("É obrigatório informar as posições de forma adequada");
        }
        int qtnPosicoes = posicoes.length / 2;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < posicoes.length; i += 2) {
            x.add(posicoes[i]);
            y.add(posicoes[i + 1]);
        }
        return new Polygon(x.stream().mapToInt(i -> i).toArray(), y.stream().mapToInt(i -> i).toArray(), qtnPosicoes);
    }


    public static void draw(Graphics g, BufferedImage image, int x, int y, int size) {
        draw(g, image, x, y, size, size);
    }

    public static void draw(Graphics g, String texto, int x, int y, Color color, int font) {
        draw(g, texto, x, y, color, new Font("arial", Font.BOLD, font));
    }

    public static void draw(Graphics g, String texto, int x, int y, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(texto, x, y);
    }

    public static void draw(Graphics g, int x, int y, Color color, int w, int h) {
        g.setColor(color);
        g.drawRect(x, y, w, h);
    }

    public static void fill(Graphics g, int x, int y, Color color, int w, int h) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public static void draw(Graphics g, BufferedImage image, int x, int y, int w, int h) {
        g.drawImage(image, x, y, w, h, null);
    }

    private static BufferedImage inverterV(BufferedImage image) {
        if (image != null) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            int[] imageP = ((DataBufferInt) newImage.getAlphaRaster().getDataBuffer()).getData();
            for (int xx = 0; xx < image.getTileWidth(); xx++) {
                for (int yy = image.getHeight(); yy > 0; yy--) {
                    int y2 = (yy - image.getHeight()) * (-1);
                    Color color = new Color(image.getRGB(xx, y2), true);
                    if (xx + (yy * image.getWidth()) - 1 >= 4096) {
                        continue;
                    }
                    imageP[xx + (yy * image.getWidth()) - 1] = color.hashCode();
                }
            }
            return newImage;
        }
        return null;
    }

    public static List<BufferedImage> inverter(List<BufferedImage> imageList) {
        return imageList.stream().map(ImageUtils::inverter).collect(Collectors.toList());
    }

    public static BufferedImage inverter(BufferedImage image) {
        if (image != null) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            int[] imageP = ((DataBufferInt) newImage.getAlphaRaster().getDataBuffer()).getData();
            for (int xx = image.getTileWidth(); xx > 0; xx--) {
                for (int yy = 0; yy < image.getHeight(); yy++) {
                    int x2 = (xx - image.getWidth()) * (-1);
                    Color color = new Color(image.getRGB(x2, yy), true);
                    imageP[xx - 1 + (yy * image.getWidth())] = color.hashCode();
                }
            }
            return newImage;
        }
        return null;
    }

    public static BufferedImage sombreamento(BufferedImage image) {
        return sombreamento(image, new Color(0, 0, 0));
    }

    private static BufferedImage sombreamento(BufferedImage image, Color color) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int[] imageP = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
        for (int xx = 0; xx < newImage.getWidth(); xx++) {
            for (int yy = 0; yy < newImage.getHeight(); yy++) {
                Color transparente = new Color(255, 255, 255, 0);
                if (imageP[xx + (yy * image.getWidth())] != 0 && imageP[xx + (yy * image.getWidth())] != transparente.getRGB()) {
                    imageP[xx + (yy * image.getWidth())] = color.getRGB();
                }
            }
        }
        newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
        return (newImage);
    }

    public static void applySombreamento
            (Graphics g,
             BufferedImage sprite,
             int tileSize,
             int x,
             int y,
             int sombreamento,
             int sombras,
             Runnable selfDraw,
             boolean condicao,
             Runnable otherEffect
            ) {
        applySombreamento(g, sprite, tileSize, x, y, sombreamento, sombras, selfDraw, false);
        if (condicao) {
            otherEffect.run();
        }
    }

    public static void applySombreamento
            (Graphics g,
             BufferedImage sprite,
             int tileSize,
             int x,
             int y,
             int sombreamento,
             int sombras,
             Runnable selfDraw,
             boolean noAr
            ) {
        desenharSombrasChao(g, sprite, x, y, tileSize, sombras, noAr);
        selfDraw.run();
        desenharSombrasObjeto(g, sprite, x, y, tileSize, sombreamento);
    }

    /*
     * Desenha as sombras geradas pelo objetos
     * */
    private static void desenharSombrasChao(Graphics g, BufferedImage sprite, int x, int y, int tileSize, int sombras, boolean noAr) {
        BufferedImage converted = noAr ?
                sombreamento(sprite, new Color(0, 0, 0, sombras)) :
                inverterV(ImageUtils.sombreamento(sprite, new Color(0, 0, 0, sombras)));
        g.drawImage(converted, x, y + tileSize, tileSize, tileSize / 2, null);
    }

    /*
     * Desenha as sombras de outros objetos no atual
     * */
    private static void desenharSombrasObjeto(Graphics g, BufferedImage sprite, int x, int y, int tileSize, int sombreamento) {
        g.drawImage(
                sombreamento(sprite, new Color(0, 0, 0, sombreamento)), x, y, tileSize, tileSize, null
        );
    }
}
