package World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.cenario.getSprite(0*Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	public static BufferedImage fillBack(BufferedImage image, int code) {
		if(image!=null) {
			BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
			int[] imageP= new int[image.getWidth()*image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			for(int xx=0; xx<newImage.getWidth(); xx++) {
				for(int yy = 0; yy < newImage.getHeight(); yy++) {
					if(imageP[xx+(yy*image.getWidth())]==0xFFFFFF) {
						Color c= new Color(code);
						imageP[xx+(yy*image.getWidth())]=c.getRGB();
					}
				}
			}
			newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			return (newImage);
		}
		return null;
		
	}
	public static BufferedImage colorir(BufferedImage image, Color c) {
		if(image!=null) {
			BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
			int[] imageP= new int[image.getWidth()*image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			for(int xx=0; xx<newImage.getWidth(); xx++) {
				for(int yy = 0; yy < newImage.getHeight(); yy++) {
					if(imageP[xx+(yy*image.getWidth())]!=0xFFFFFF) {
						if(imageP[xx+(yy*image.getWidth())]!=0xFF81C6C6 
								&& imageP[xx+(yy*image.getWidth())]!=0xFF4BC1C1 
								&&imageP[xx+(yy*image.getWidth())]!=0xFF5E9191 
								&&imageP[xx+(yy*image.getWidth())]!=0xFF7DC1C1
								&&imageP[xx+(yy*image.getWidth())]!=0xFF3F1111
								&&imageP[xx+(yy*image.getWidth())]!=0xFF000000) {
							imageP[xx+(yy*image.getWidth())]=imageP[xx+(yy*image.getWidth())]+c.getRGB();
						}
					}
				}
			}
			newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			return (newImage);
		}
		return null;
		
	}
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
