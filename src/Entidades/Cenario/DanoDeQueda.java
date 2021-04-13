package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.Tile;

public class DanoDeQueda extends Entity{

	public DanoDeQueda(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		setMask(0,0,0,64,64);
		checkCollision();
	}
	public void checkCollision(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof DanoDeQueda) {
				if(Entity.isColidding(Game.player, atual,0,0)) {
						teleportar(Game.portaTerraco.get(Game.player.lastPorta).getX()-40,
								Game.portaTerraco.get(Game.player.lastPorta).getY(),
								Game.portaTerraco.get(Game.player.lastPorta).getX()-40,
								Game.portaTerraco.get(Game.player.lastPorta).getY(),
								Game.player.dir);
						Game.player.caindo=false;
						Game.player.parado=true;
						Game.player.life--;
						Game.Ambiente="Terraço";
					
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(this.getX() - Camera.x,this.getY() - Camera.y-10,250,250);	
		
		}
						
	
}