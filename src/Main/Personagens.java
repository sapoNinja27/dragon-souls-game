package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Personagens {
	int mx,my;
	String[] Menu = {"Vazio","tai","sander","ace","rouxie","cooper","iron","lead","titanium","gold","silver","light","kenai","alex","oldman","mestre"};
	int currentOption=0;
	BufferedImage icons[]=new BufferedImage[15];
	public void tick() {
		mx=Game.menu.mx;
		my=Game.menu.my;
		for(int i=0;i<15;i++) {
			icons[i]=(Game.icones.getSprite(i*64, 0, Game.TILE_SIZE, Game.TILE_SIZE));
		}
		if(Game.menu.soltou) {
			Game.menu.soltou=false;
		}
		if(Game.menu.soltou) {
			
		}else {
			if(mx>100 && mx<100+ 64 && my>80 && my< 80+64) {
				currentOption=1;
			}else if(mx>100+100 && mx<100+100+ 64 && my>80 && my< 80+64) {
				currentOption=2;
			}else if(mx>100+100*2 && mx<100+100*2+ 64 && my>80 && my< 80+64) {
				currentOption=3;
			}else if(mx>100+100*3 && mx<100+100*3+ 64 && my>80 && my< 80+64) {
				currentOption=4;
			}else if(mx>100+100*4 && mx<100+100*4+ 64 && my>80 && my< 80+64) {
				currentOption=5;
			}else if(mx>100 && mx<100+ 64 && my>80+80 && my< 80+80+64) {
				currentOption=6;
			}else if(mx>100+100 && mx<100+100+ 64 && my>80+80 && my< 80+80+64) {
				currentOption=7;
			}else if(mx>100+100*2 && mx<100+100*2+ 64 && my>80+80 && my< 80+80+64) {
				currentOption=8;
			}else if(mx>100+100*3 && mx<100+100*3+ 64 && my>80+80 && my< 80+80+64) {
				currentOption=9;
			}else if(mx>100+100*4 && mx<100+100*4+ 64 && my>80+80 && my< 80+80+64) {
				currentOption=10;
			}else if(mx>100 && mx<100+ 64 && my>80+80+80 && my< 80+80+80+64) {
				currentOption=11;
			}else if(mx>100+100 && mx<100+100+ 64 && my>80+80+80 && my< 80+80+80+64) {
				currentOption=12;
			}else if(mx>100+100*2 && mx<100+100*2+ 64 && my>80+80+80 && my< 80+80+80+64) {
				currentOption=13;
			}else if(mx>100+100*3 && mx<100+100*3+ 64 && my>80+80+80 && my< 80+80+80+64) {
				currentOption=14;
			}else if(mx>100+100*4 && mx<100+100*4+ 64 && my>80+80+80 && my< 80+80+80+64) {
				currentOption=15;
			}else {
				currentOption=0;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color Standart= new Color(255,0,0);
		Color MouseOver= new Color(200,0,0);
		Color Pressed= new Color(150,0,0);
		Color beje= new Color(247,212,212);
		
		
		
		for(int i=0;i<5;i++) {
			g.setColor(Color.black);

			g.fillRoundRect(95+100*i,75+160,64+10,64+10,30,30);
			g.fillRoundRect(95+100*i,75,64+10,64+10,30,30);
			g.fillRoundRect(95+100*i,75+80,64+10,64+10,30,30);
			g.setColor(beje);
			g.fillRect(100+100*i,240,64,64);
			g.fillRect(100+100*i,80,64,64);
			g.fillRect(100+100*i,160,64,64);
			g.drawImage(icons[i], 100+100*i,80,null);
			g.drawImage(icons[i+5], 100+100*i, 160,null);
			g.drawImage(icons[i+10], 100+100*i, 240,null);
		}
		
		
		g.setColor(Color.black);
		if(Menu[currentOption]=="tai" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="tai") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="sander" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="sander") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="ace" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="ace") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="rouxie" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="rouxie") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="cooper" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="cooper") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100+100,80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="iron" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100,80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="iron") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="lead" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="lead") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="titanium" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="titanium") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="gold" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="gold") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="silver" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="silver") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100+100,+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		
		if(Menu[currentOption]=="light" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="light") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="kenai" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="kenai") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="alex" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="alex") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="oldman" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="oldman") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		if(Menu[currentOption]=="mestre" && Game.menu.clicou) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(100+100+100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}else if(Menu[currentOption]=="mestre") {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g.fillRect(100+100+100+100+100,80+80+80,64,64);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		
	}	
	
}
