package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Comparator;



import World.Camera;

public class Entity {
	
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	public boolean debug = false;
	
	private BufferedImage sprite;
	public int depth;
	public int maskx[],masky[],maskh[],maskw[];
	
	public Entity(int x,int y,int width,int height,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		maskx = new int[5];
		masky = new int[5];
		maskw = new int[5];
		maskh = new int[5];
	}
	public static Comparator<Entity> nodeSorter= new Comparator<Entity>() {
		@Override
		public int compare(Entity n0, Entity n1) {
			if(n1.depth<n0.depth) {
				return +1;
			}
			if(n1.depth>n0.depth) {
				return -1;
			}
			return 0;
		}
	};
	public void setMask(int vet,int maskx,int masky,int mwidth,int mheight){
		this.maskx[vet] = maskx;
		this.masky [vet]= masky;
		this.maskw [vet]= mwidth;
		this.maskh[vet] = mheight;
	}
	public void correr(double x) {
		this.x = x;
	}
	public double xDouble() {
		return this.x;
	}
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick(){
		
	}
	public double distanciaX(int x1, int x2) {
		return Math.abs(x1-x2);
	}
	public double distanciaY(int y1, int y2) {
		return Math.abs(y1-y2);
	}
	public double calculateDistance(int x1,int y1,int x2, int y2) {
		return Math.sqrt(x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
	}
	public static BufferedImage inverter(BufferedImage image) {
		if(image!=null) {
			BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
			int[] imageP=((DataBufferInt)newImage.getAlphaRaster().getDataBuffer()).getData();
			for(int xx=image.getTileWidth(); xx>0; xx--) {
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
	public static BufferedImage Sombra(BufferedImage image) {
		if(image!=null) {
			BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
			int[] imageP= new int[image.getWidth()*image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			for(int xx=0; xx<newImage.getWidth(); xx++) {
				for(int yy = 0; yy < newImage.getHeight(); yy++) {
					if(imageP[xx+(yy*image.getWidth())]!=0xFFFFFF) {
						imageP[xx+(yy*image.getWidth())]=0xFF000000;
					}
				}
			}
			newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), imageP, 0, image.getWidth());
			return newImage;
		}
		return null;
		
	}
	public static boolean isColidding(Entity e1,Entity e2, int vet1, int vet2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx[vet1],e1.getY()+e1.masky[vet1],e1.maskw[vet1],e1.maskh[vet1]);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx[vet2],e2.getY()+e2.masky[vet2],e2.maskw[vet2],e2.maskh[vet2]);
		
		return e1Mask.intersects(e2Mask);
	}
	
	
	public void render(Graphics g) {
//		g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y,null);
	}
	
}
