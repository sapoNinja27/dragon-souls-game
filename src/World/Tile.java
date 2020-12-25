package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.cenario.getSprite(2*Game.TILE_SIZE,1*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
