package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Cenario extends Entity{
	String tipo;
	public Cenario(int x, int y, int width, int height,String tipo, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.tipo=tipo;
	}
	public void render(Graphics g) {

		if(tipo == "janela") {
//			g.drawImage(Game.spritesheet.getSprite(2*Game.TILE_SIZE,11*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
//					this.getX()- Camera.x+100,this.getY() - Camera.y+90,Game.TILE_SIZE/2,Game.TILE_SIZE/2, null);
			
			
		}
						
	}
}
