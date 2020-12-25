package Entidades;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Cenario_Interagivel extends Entity{
	String tipo;
	public Cenario_Interagivel(int x, int y, int width, int height,String tipo, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.tipo=tipo;
	}
	public void tick() {
		
		if(tipo=="prateleira") {
			depth=4;
		}else if(tipo=="lampada") {
			depth=5;
		}

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
		
		
		
		if(tipo=="prateleira") {
			
			g.drawImage(Game.cenario.getSprite(2*Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
					this.getX()- Camera.x,this.getY() - Camera.y-16,Game.TILE_SIZE,Game.TILE_SIZE+20, null);
			
			g.drawImage(Game.cenario.getSprite(3*Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
					this.getX()- Camera.x+Game.TILE_SIZE,this.getY() - Camera.y-16,Game.TILE_SIZE,Game.TILE_SIZE+20, null);
			
			g.drawImage(Game.cenario.getSprite(4*Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
					this.getX()- Camera.x+Game.TILE_SIZE+Game.TILE_SIZE,this.getY() - Camera.y-16,Game.TILE_SIZE,Game.TILE_SIZE+20, null);
		
			
			
	
	
		}else if(tipo=="lampada") {
		
			g.setColor(Color.black);
			
			g.fillRect(this.getX() - Camera.x,this.getY() - Camera.y-102-90,(int)(Game.TILE_SIZE),Game.TILE_SIZE+Game.TILE_SIZE+Game.TILE_SIZE);
			g.fillRect(this.getX() - Camera.x+Game.TILE_SIZE,this.getY() - Camera.y-102-90,(int)(Game.TILE_SIZE),Game.TILE_SIZE+Game.TILE_SIZE+Game.TILE_SIZE);
			g.fillRect(this.getX() - Camera.x+Game.TILE_SIZE+Game.TILE_SIZE,this.getY() - Camera.y-102-90,(int)(Game.TILE_SIZE),Game.TILE_SIZE+Game.TILE_SIZE+Game.TILE_SIZE);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.setColor(Color.white);
			g.fillOval(this.getX() - Camera.x-1
					,this.getY() - Camera.y+10,Game.TILE_SIZE,Game.TILE_SIZE-10);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			
			
			g.drawImage(Game.cenario.getSprite(Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
					this.getX() - Camera.x
					,this.getY() - Camera.y-2,(int)(Game.TILE_SIZE),Game.TILE_SIZE, null);
			


		}else if(tipo=="porta") {
			g.drawImage(Game.cenario.getSprite(Game.TILE_SIZE,0*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
					this.getX() - Camera.x
					,this.getY() - Camera.y-2,(int)(Game.TILE_SIZE),Game.TILE_SIZE, null);
		}
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}