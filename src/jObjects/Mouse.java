package jObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Entidades.Entity;

public class Mouse {
	

	private static int x, y;
	public static boolean pressed, released, hover;
	
	
	public static int getX() {
		return x;
	}

	public static void setCordinates(int nx, int ny) {
		x = nx;
		y = ny;
	}

	public static int getY() {
		return y;
	}
//	public static void Render(Graphics g) {
//		g.setColor(Color.red);
//		Rectangle e1Mask = new Rectangle(getX()+5,getY()+3,6,9);
//		Graphics2D g2= (Graphics2D)g;
//		g2.draw(e1Mask);
//	}
//	public static boolean isColidding(Entity e, int vet){
//		
//		Rectangle e1Mask = new Rectangle(getX()+5,getY()+3,6,9);
//		Rectangle e2Mask = new Rectangle(e.getX() + e.maskx[vet],e.getY()+e.masky[vet],e.maskw[vet],e.maskh[vet]);
//		
//		return e1Mask.intersects(e2Mask);
//	}
}
