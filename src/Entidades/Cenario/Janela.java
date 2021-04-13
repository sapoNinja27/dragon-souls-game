package Entidades.Cenario;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;

public class Janela extends Entity{
	private BufferedImage janela;
	public Janela(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void setCor(Color cor) {
		janela=Tile.colorir(Game.cenario.getSprite((4)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),cor);
		
	}
	public void tick() {
		depth=1;
		setMask(0,0-25,-20,46,80);
		

	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(janela,this.getX()-Camera.x-Game.TILE_SIZE,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
	}
						
	
}