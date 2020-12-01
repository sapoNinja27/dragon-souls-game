package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.World;

public class Player2 extends Entity{
	
	private double speed = 0.4;
	
	private int maskx = 8,masky = 8,maskw = 10,maskh = 10;
	
	private int frames = 0,maxFrames = 20,index = 0,maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 10;
	
	private boolean isDamaged = false;
	private int damageFrames = 10,damageCurrent = 0;

	public Player2(int x, int y, int width, int height, BufferedImage[] sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		this.sprites[0] = sprite[0];
		this.sprites[1] = sprite[1];
	}
	public void tick() {
		if(isColiddingWithPlayer() == false){
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())){
			x+=speed;
		}else if((int)x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())) {
			x-=speed;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))){
			y+=speed;
		}else if((int)y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))){
			y-=speed;
		}else{
			//Estamos colidindo
			if(Game.rand.nextInt(100) < 10){
				Game.player.life-=Game.rand.nextInt(3);
				Game.player.isDamaged = true;
				//System.out.println("Vida: "+ Game.player.life);
			}
			
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
	}
	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
	
	
	public boolean isColiddingWithPlayer(){
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	public void render(Graphics g) {
		if(!isDamaged)
			g.drawImage(sprites[index], this.getX() - Camera.x,this.getY() - Camera.y,null);
		
	}
	
	
}
