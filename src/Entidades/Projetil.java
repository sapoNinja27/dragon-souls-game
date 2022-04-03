package Entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import World.Camera;

public class Projetil extends Entidade {

	private double dx;
	private double dy;
	private double spd = 4;
	
	private int life = 30,curLife = 0;
	
	
	public Projetil(int x, int y, int width, int height, BufferedImage sprite,double dx,double dy) {
		super(x, y, width, height);
		this.dx = dx;
		this.dy = dy;
	}
	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curLife++;
		if(curLife == life) {
//			Game.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillOval(this.getX() - Camera.x,this.getY() - Camera.y,width,height);
	}
	
}
