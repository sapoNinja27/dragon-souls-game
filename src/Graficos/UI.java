package Graficos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class UI {
	private BufferedImage icon[]=new BufferedImage[5];
	public void render(Graphics g) {
		
		
		if(Game.player.Hudvisivel) {
			for(int i=0;i<5;i++) {
//				icon[i]=Game.spritesheet.getSprite(i*Game.TILE_SIZE,18*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE);
			}
			Graphics2D g2 = (Graphics2D) g;
			Rectangle barlife= new Rectangle(100,55,(int)Game.player.totalife,11);
			Rectangle barspecial= new Rectangle(67,94,11,120);
			Rectangle barstamina= new Rectangle(100,66,120,11);
			g.setColor(Color.black);
			g.fillRect(100,55,(int)Game.player.totalife,11);
			g.fillRect(67,94,11,120);
			g.fillRect(100,66,120,11);
			g.setColor(Color.red);
			g.fillRect(100,55,(int)((Game.player.life/Game.player.maxlife)*120),11);
			g.setColor(Color.orange);
			g.fillRect(67,215-(int)((Game.player.special/Game.player.maxspecial)*120),11,(int)((Game.player.special/Game.player.maxspecial)*120));
			g.setColor(Color.green);
			g.fillRect(100,66,(int)((Game.player.stamina/Game.player.maxstamina)*120),11);
			g.setColor(Color.black);
			g2.draw(barspecial);
			g2.draw(barlife);
			g2.draw(barstamina);
			
	//		g.setColor(Color.black);
	//		g.fillRect(90,47,90,11);
	//		g.setColor(Color.red);
	//		g.fillRect(90,47,(int)((Game.player.life/Game.player.maxlife)*90),11);
	//		
	//		g.setColor(Color.black);
	//		g.fillRect(90,67,90,11);
	//		g.setColor(Color.green);
	//		g.fillRect(90,67,(int)((Game.player.life/Game.player.maxlife)*90),11);
			
	//		g.drawImage(Game.spritesheet.getSprite(1*Game.TILE_SIZE,3*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
	//				
	//				84 ,0,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
	//		g.drawImage(Game.spritesheet.getSprite(1*Game.TILE_SIZE,3*Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE),
	//				
	//				84 ,20,Game.TILE_SIZE*2,Game.TILE_SIZE*2,null);
			g.setColor(Color.black);
			g.fillOval(41,33,Game.TILE_SIZE,Game.TILE_SIZE);
			g.setColor(Color.white);
			g.fillOval(43,35,60,60);
			
			if(!Game.player.transformado) {
				if(Game.player.special> (Game.player.maxspecial/2)) {
					if(Game.player.life> (Game.player.maxlife/2)) {
						g.drawImage(icon[2],25 ,18,Game.TILE_SIZE+35,Game.TILE_SIZE+35,null);
					}else {
						g.drawImage(icon[3],25 ,18,Game.TILE_SIZE+35,Game.TILE_SIZE+35,null);
					}	
				}else {
					if(Game.player.life> (Game.player.maxlife/2)) {
						g.drawImage(icon[0],25 ,18,Game.TILE_SIZE+35,Game.TILE_SIZE+35,null);
					}else {
						g.drawImage(icon[1],25 ,18,Game.TILE_SIZE+35,Game.TILE_SIZE+35,null);
					}
				}
			}else {
				g.drawImage(icon[4],25 ,18,Game.TILE_SIZE+35,Game.TILE_SIZE+35,null);
			}
		}
		
		
		
		
		
	}
	
	
}
