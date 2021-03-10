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

public class Porta extends Entity{
	public boolean emFrente;
	private BufferedImage[] porta1;
	public Porta(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		porta1=new BufferedImage[2];
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
		for(int i=0;i <2 ; i++) {
			porta1[i]=Game.cenario.getSprite((i+2)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
		

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
		
		if(!emFrente) {
			g.drawImage(porta1[0],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
		}else {
			g.drawImage(porta1[1],this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
		}
		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}