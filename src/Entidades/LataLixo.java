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

public class LataLixo extends Entity{
	public boolean emFrente;
	private final int[] index= {Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2)};
	private final int[] pos= {Game.rand.nextInt(2),Game.rand.nextInt(2),Game.rand.nextInt(2)};
	private BufferedImage[] lata;
	public LataLixo(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		lata=new BufferedImage[2];
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
		for(int i=0;i <2 ; i++) {
			
			lata[i]=Game.cenario.getSprite((4+i)*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			
		}

	}
	public void render(Graphics g) {
		for(int i=0;i<3;i++) {
			if(pos[i]==1) {
				g.drawImage(lata[index[i]],this.getX()-Camera.x+20+(-i*20),this.getY()-Camera.y+7,Game.TILE_SIZE,Game.TILE_SIZE,null);
			}else{
				g.drawImage(inverter(lata[index[i]]),this.getX()-Camera.x+20+(-i*20),this.getY()-Camera.y+7,Game.TILE_SIZE,Game.TILE_SIZE,null);
			}
		}
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}