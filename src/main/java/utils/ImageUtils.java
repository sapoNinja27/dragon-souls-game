package utils;

import configuracoes.Configuracao;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.nonNull;

public class ImageUtils {

    private static final HashMap<BufferedImage, HashMap<Color, BufferedImage>> cacheSombra = new HashMap<>();
    public static BufferedImage inverter(BufferedImage image) {
        if(image!=null) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            int[] imageP = ((DataBufferInt)newImage.getAlphaRaster().getDataBuffer()).getData();
            for(int xx = image.getTileWidth(); xx > 0; xx--) {
                for(int yy = 0; yy < image.getHeight(); yy++) {
                    int x2 = (xx - image.getWidth()) * (-1);
                    Color color=new Color(image.getRGB(x2, yy),true);
                    imageP[xx-1+(yy*image.getWidth())]=color.hashCode();
                }
            }
            return newImage;
        }
        return null;

    }

    public static void drawPolygon(Color color, Graphics g, Polygon polygon) {
        g.setColor(color);
        g.fillPolygon(polygon);
    }
    public static List<BufferedImage> inverter(List<BufferedImage> imageList) {
        List<BufferedImage> invertidas = new ArrayList<>();
        for (BufferedImage image: imageList) {
            invertidas.add(inverter(image));
        }
        return invertidas;
    }

    public static BufferedImage sombreamento(BufferedImage image) {
        return sombreamento(image, new Color(0,0,0));
    }

    public static BufferedImage sombreamento(BufferedImage image, Color color) {
        if(nonNull(cacheSombra.get(image)) && nonNull(cacheSombra.get(image).get(color))){
            return cacheSombra.get(image).get(color);
        }

        if(image!=null) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            int[] imageP= new int[image.getWidth()*image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
            for(int xx = 0; xx < newImage.getWidth(); xx++) {
                for(int yy = 0; yy < newImage.getHeight(); yy++) {
                    Color transparente = new Color(255, 255, 255,0);
                    if(imageP[xx+(yy*image.getWidth())] != 0 && imageP[xx+(yy*image.getWidth())] != transparente.getRGB()) {
                        imageP[xx+(yy*image.getWidth())] = color.getRGB();
                    }
                }
            }
            newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
            HashMap<Color, BufferedImage> cache = new HashMap<>();
            cache.put(color, newImage);
            cacheSombra.put(image, cache);
            return (newImage);
        }
        return null;
    }

    public static void draw(Graphics g, BufferedImage image, int x, int y){
        draw(g, image, x, y, Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
    }

    public static void draw(Graphics g, String texto, int x, int y, Color color, int font){
        g.setColor(color);
        g.setFont(new Font("arial", Font.BOLD, font));
        g.drawString(texto, x, y);
    }

    public static void draw(Graphics g, int x, int y, Color color, int w, int h){
        g.setColor(color);
        g.drawRect(x, y, w, h);
    }

    public static void fill(Graphics g, int x, int y, Color color, int w, int h){
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public static void draw(Graphics g, BufferedImage image, int x, int y, int w, int h){
        g.drawImage(image, x, y, w, h, null);
    }

    public static BufferedImage inverterV(BufferedImage image) {
        if(image!=null) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            int[] imageP=((DataBufferInt)newImage.getAlphaRaster().getDataBuffer()).getData();
            for(int xx=0; xx<image.getTileWidth(); xx++) {
                for(int yy = image.getHeight(); yy >0; yy--) {
                    int y2=(yy-image.getHeight())*(-1);
                    Color color=new Color(image.getRGB(xx, y2),true);
                    if(xx+(yy*image.getWidth())-1>=4096) {
                        continue;
                    }
                    imageP[xx+(yy*image.getWidth())-1]=color.hashCode();
                }
            }
            return newImage;
        }
        return null;
    }
}