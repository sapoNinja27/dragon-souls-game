package Menu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {

    public static BufferedImage inverter(BufferedImage image) {
        if(image!=null) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            int[] imageP = ((DataBufferInt)newImage.getAlphaRaster().getDataBuffer()).getData();
            for(int xx = image.getTileWidth(); xx > 0; xx--) {
                for(int yy = 0; yy < image.getHeight(); yy++) {
                    int x2=(xx-image.getWidth())*(-1);
                    Color color=new Color(image.getRGB(x2, yy),true);
                    imageP[xx-1+(yy*image.getWidth())]=color.hashCode();
                }
            }
            return newImage;
        }
        return null;

    }

    public static List<BufferedImage> inverter(List<BufferedImage> imageList) {
        List<BufferedImage> invertidas = new ArrayList<>();
        for (BufferedImage image: imageList) {
            invertidas.add(inverter(image));
        }
        return invertidas;
    }

    public static BufferedImage sombreamento(BufferedImage image) {
        return sombreamentoColorido(image, new Color(0,0,0, 0));
    }

    public static BufferedImage sombreamentoColorido(BufferedImage image, Color color) {
        if(image!=null) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            int[] imageP= new int[image.getWidth()*image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
            for(int xx = 0; xx < newImage.getWidth(); xx++) {
                for(int yy = 0; yy < newImage.getHeight(); yy++) {
                    if(imageP[xx+(yy*image.getWidth())] != 0) {
                        imageP[xx+(yy*image.getWidth())] = color.getRGB();
                    }
                }
            }
            newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
            return (newImage);
        }
        return null;
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
