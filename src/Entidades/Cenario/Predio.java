package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;

public class Predio extends Entity{
	Porta porta;
	BufferedImage paredes[]= new BufferedImage[4];
	public Predio(int x, int y, int width, int height, Color cor, String estilo) {
		super(x, y,width,height);
		this.width=width;
		this.height=height;
		porta=new Porta(x+100, y, cor, 0);
		for(int i = 0;i<paredes.length;i++) {
			paredes[i]=Game.cenario.getSprite(2*Game.TILE_SIZE,(5+i)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
	}
	public void tick() {
		porta.tick();
	}
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillOval(20, 20, width, height);
		g.drawImage(paredes[0],this.getX()-Camera.x+100,this.getY()-Camera.y-Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE,null);
		porta.render(g);
	}
}
