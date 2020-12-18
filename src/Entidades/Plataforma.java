package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Plataforma extends Entity{
	public Plataforma(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void tick() {
		maskx[0]= 0;
		masky[0]= 0;
		maskw[0]= Game.TILE_SIZE;
		maskh[0]=3;
		
		maskx[1]= 0;
		masky[1]= 0;
		maskw[1]= 3;
		maskh[1]=Game.TILE_SIZE;
		
		maskx[2]= Game.TILE_SIZE-4;
		masky[2]= 0;
		maskw[2]= 3;
		maskh[2]=Game.TILE_SIZE;
		
		maskx[3]= 0;
		masky[3]= Game.TILE_SIZE-4;
		maskw[3]= Game.TILE_SIZE;
		maskh[3]=3;
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		g.setColor(Color.RED);
		
		g.drawImage(Game.cenario.getSprite(1*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
				this.getX() - Camera.x,this.getY() - Camera.y+3, null);
//		g2.draw(rect);
//		g.setColor(Color.YELLOW);
//		g2.draw(rect2);
//		g.setColor(Color.BLUE);
//		g2.draw(rect3);
//		g.setColor(Color.GREEN);
//		g2.draw(rect4);
	}
}
