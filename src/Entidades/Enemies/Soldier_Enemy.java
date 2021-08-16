package Entidades.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;
import World.World;

public class Soldier_Enemy extends Enemy{
	
	private double speed = 0.4;
	
	private int frames = 0,maxFrames = 20,index = 0,maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 10;
	
	private boolean isDamaged = false;

	public Soldier_Enemy(int x, int y) {
		super(x, y);
		sprites = new BufferedImage[2];
	}

	public void tick(){	
		setMask(0, 20, 11, 20, 52);
		//tick2();
	}
	
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle barstamina = new Rectangle(this.getX() - Camera.x , this.getY() - Camera.y, 20, 64);
		g2.setColor(Color.orange);
		g2.fill(barstamina);
		g.setColor(Color.BLUE);
		g.drawRect(getX()- Camera.x+maskx[0], getY()- Camera.y+masky[0], maskw[0], maskh[0]);	
		
	}
	
	
}
