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
		g.setColor(Color.red);
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
		Rectangle rect4= new Rectangle(this.getX() - Camera.x+maskx[3],this.getY() - Camera.y+masky[3],maskw[3],maskh[3]);
		Rectangle rect5= new Rectangle(this.getX() - Camera.x+maskx[4],this.getY() - Camera.y+masky[4],maskw[4],maskh[4]);
		g.drawRect(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);	
		}
						
	
}