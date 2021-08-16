package Entidades.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;

public class Enemy extends Entity{
	
	private double speed = 0.4;
	
	private int frames = 0,maxFrames = 20,index = 0,maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 10;
	
	private boolean isDamaged = false;
	private int damageFrames = 10,damageCurrent = 0;

	public Enemy(int x, int y) {
		super(x, y, 0,0);
		sprites = new BufferedImage[2];
	}

	public void tick(){	
//		tick2();
	}
	public void tick2() {
		if(isColiddingWithPlayer() == false){
		if((int)x < Game.player.getX() 
				&& !isColidding((int)(x+speed), this.getY())){
			x+=speed;
		}
		else if((int)x > Game.player.getX() 
				&& !isColidding((int)(x-speed), this.getY())) {
			x-=speed;
		}
		
		if((int)y < Game.player.getY()  &&
				!isColidding(this.getX(), (int)(y+speed))){
			y+=speed;
		}
		else if((int)y > Game.player.getY() &&
				!isColidding(this.getX(), (int)(y-speed))) {
			y-=speed;
		}
		}else{
			
		}
		
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
			
			
			if(life <= 0) {
				destroySelf();
				return;
			}
			
			if(isDamaged) {
				this.damageCurrent++;
				if(this.damageCurrent == this.damageFrames) {
					this.damageCurrent = 0;
					this.isDamaged = false;
				}
			}
	}
	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
}
