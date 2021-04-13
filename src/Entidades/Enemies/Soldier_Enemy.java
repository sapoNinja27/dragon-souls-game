package Entidades.Enemies;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.World;

public class Soldier_Enemy extends Enemy{
	
	private double speed = 0.4;
	
	private int maskx = 8,masky = 8,maskw = 10,maskh = 10;
	
	private int frames = 0,maxFrames = 20,index = 0,maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 10;
	
	private boolean isDamaged = false;
	private int damageFrames = 10,damageCurrent = 0;

	public Soldier_Enemy(int x, int y, int width, int height, BufferedImage[] sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		this.sprites[0] = sprite[0];
		this.sprites[1] = sprite[1];
	}

	public void tick(){	
	}
	
	
	public void render(Graphics g) {
		if(!isDamaged)
			g.drawImage(sprites[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
		
	}
	
	
}
