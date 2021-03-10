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

public class Janela extends Entity{
	private BufferedImage janela;
	public Janela(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
			janela=Game.cenario.getSprite((4)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(janela,this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
		
			
			
			
			
			
			

			
			
			
			
			
		}
						
	
}