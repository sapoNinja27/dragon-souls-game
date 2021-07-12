package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graficos.Spritesheet;
import Main.Game;
import World.Camera;

public class Chama extends Entity{
	private BufferedImage sprite[];
	private int frames=0,index=0,maxIndex=15;
	private Spritesheet chama;
	public Chama(int x, int y) {
		super(x, y, 0,0);
		sprite= new BufferedImage[16];
		chama=new Spritesheet("/fogo.png");
		for(int i = 0;i<4;i++) {
			sprite[i]=chama.getSprite((i)*Game.TILE_SIZE,(0)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			sprite[i+4]=chama.getSprite((i)*Game.TILE_SIZE,(1)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			sprite[i+8]=chama.getSprite((i)*Game.TILE_SIZE,(2)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			sprite[i+12]=chama.getSprite((i)*Game.TILE_SIZE,(3)*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
		}
	}
	public void tick(){
		frames++;
		if(frames>=7) {
			frames=0;
			index++;
			if(index==maxIndex) {
				index=0;
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(sprite[index],this.getX(),this.getY(),Game.TILE_SIZE,Game.TILE_SIZE,null);
	}
}
