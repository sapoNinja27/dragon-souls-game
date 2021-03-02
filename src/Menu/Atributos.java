package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Game;

public class Atributos {
	int posx=0,posy=0;
	public String[] Menu = {"Vazio","A11","A12","A13","A21","A22","A23","A31","A32","A33"};
	public int currentOption=0;
	String golpe;
	confirmacao con=new confirmacao();
	TextosAtributos txt= new TextosAtributos();
	
	int nivel;
	
	int h1x1[] = { 52, 92, 125, 92, 52}; 
	int h1y1[] = { 117, 117, 152, 187, 187 }; 
	int h2x1[]  = { 52, 92, 125, 92, 52};
	int h2y1[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int h3x1[]  = { 52, 92, 125, 92, 52};
	int h3y1[] = { 117+160, 117+160, 152+160, 187+160, 187+160 }; 
	
	int h1x2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int h1y2[] = { 117, 117, 152, 187, 187 };
	int h2x2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int h2y2[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int h3x2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int h3y2[] = { 117+160, 117+160, 152+160, 187+160, 187+160 };
	
	int h1x3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int h1y3[] = { 117, 117, 152, 187, 187 };
	int h2x3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int h2y3[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int h3x3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int h3y3[] = { 117+160, 117+160, 152+160, 187+160, 187+160 };
	BufferedImage icons[]=new BufferedImage[27];
	public int mx,my;
	public void attMouse() {
		mx=Game.menu.mx;
		my=Game.menu.my;		
	}
	public void tick() {
		attMouse();
		
		for(int i=0;i<3;i++) {
			icons[i+0]=Game.Menu.getSprite(133*i+900, 130*0, 133, 130);
			icons[i+3]=Game.Menu.getSprite(133*i+900, 130*1, 133, 130);
			icons[i+6]=Game.Menu.getSprite(133*i+900, 130*2, 133, 130);
			
			icons[i+9]=Game.Menu.getSprite(399+133*i+900, 130*0, 133, 130);
			icons[i+12]=Game.Menu.getSprite(399+133*i+900, 130*1, 133, 130);
			icons[i+15]=Game.Menu.getSprite(399+133*i+900, 130*2, 133, 130);
			
			icons[i+18]=Game.Menu.getSprite(133*i+900, 390, 133, 130);
			icons[i+21]=Game.Menu.getSprite(133*i+900, 390+130, 133, 130);
			icons[i+24]=Game.Menu.getSprite(133*i+900, 390+130*2, 133, 130);
		}
		con.tick();

		if(Game.menu.soltou) {
			Game.menu.soltou=false;
			if(mx>54 && mx<123 && my>119 && my<185) {
				//socos tempestade nv 1
				currentOption=1;
				if(!Game.player.A1[0]) {
					if(Game.menu.pontosA>0) {
						golpe="A1";
						nivel=1;
						con.confirmar(golpe, nivel, "Atributo",11);

					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.A1[0]) {
				//socos tempestade 2
				currentOption=2;
				if(!Game.player.A1[1]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A1";
						nivel=2;
						con.confirmar(golpe, nivel, "Atributo",12);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.A1[1]) {
				//socos tempestade 3
				currentOption=3;
				if(!Game.player.A1[2]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A1";
						nivel=3;
						con.confirmar(golpe, nivel, "Atributo",13);
					}
				}
			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
				//bloquear 1
				currentOption=4;
				if(!Game.player.A2[0]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A2";
						nivel=1;
						con.confirmar(golpe, nivel, "Atributo",21);
					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.A2[0]) {
				//bloquear 2
				currentOption=5;
				if(!Game.player.A2[1]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A2";
						nivel=2;
						con.confirmar(golpe, nivel, "Atributo",22);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.A2[1]) {
				//bloquear 3
				currentOption=6;
				if(!Game.player.A2[2]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A2";
						nivel=3;
						con.confirmar(golpe, nivel, "Atributo",23);
					}
				}
			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
				//h3alecer 1 
				currentOption=7;
				if(!Game.player.A3[0]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A3";
						nivel=1;
						con.confirmar(golpe, nivel, "Atributo",31);
					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.A3[0]) {
				//h3alecer 2
				currentOption=8;
				if(!Game.player.A3[1]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A3";
						nivel=2;
						con.confirmar(golpe, nivel, "Atributo",32);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.A3[1]) {
				//h3alecer  3
				currentOption=9;
				if(!Game.player.A3[2]) {
					if(Game.menu.pontosA>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="A3";
						nivel=3;
						con.confirmar(golpe, nivel, "Atributo",33);
					}
				}
			}else {
				currentOption=0;
			}
			
		}else {
			if(mx>54 && mx<123 && my>119 && my<185) {
				//socos tempestade 1
				currentOption=1;
			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.A1[0]) {
				//socos tempestade 2
				currentOption=2;
			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.A1[1]) {
				//socos tempestade 3
				currentOption=3;
			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
				//bloquear1
				currentOption=4;
			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.A2[0]) {
				//bloquear2
				currentOption=5;
			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.A2[1]) {
				//bloquear3
				currentOption=6;
			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
				//h3alecer 1
				currentOption=7;
			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.A3[0]) {
				//h3alecer 2
				currentOption=8;
			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.A3[1]) {
				//h3alecer 3
				currentOption=9;
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
		g.setColor(Color.BLACK); 
		if(!Game.player.A1[0]) {
			if(Menu[currentOption]=="A11" && Game.menu.clicou || con.bot==11) {
				g.fillPolygon(h1x1, h1y1, 5);
				g.drawImage(icons[2], 55,120,133/2, 130/2, null);
			}else if(Menu[currentOption]=="A11") {
				g.fillPolygon(h1x1, h1y1, 5);
				g.drawImage(icons[1], 55,120,133/2, 130/2, null);
			}else{
				g.fillPolygon(h1x1, h1y1, 5);
				g.drawImage(icons[0], 55,120,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(h1x1, h1y1, 5);
			g.drawImage(icons[2], 55,120,133/2, 130/2, null);
			if(!Game.player.A1[1]) {
				if(Menu[currentOption]=="A12" && Game.menu.clicou || con.bot==12) {
					g.fillPolygon(h1x2, h1y2, 5);
					g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
				}else if(Menu[currentOption]=="A12") {
					g.fillPolygon(h1x2, h1y2, 5);
					g.drawImage(icons[4], 55+80,120,133/2, 130/2, null);
				}else{
					g.fillPolygon(h1x2, h1y2, 5);
					g.drawImage(icons[3], 55+80,120,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(h1x2, h1y2, 5);
				g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
				if(!Game.player.A1[2]) {
					if(Menu[currentOption]=="Tempestade de Socos3" && Game.menu.clicou|| con.bot==13) {
						g.fillPolygon(h1x3, h1y3, 5);
						g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
					}else if(Menu[currentOption]=="Tempestade de Socos3") {
						g.fillPolygon(h1x3, h1y3, 5);
						g.drawImage(icons[7], 55+160,120,133/2, 130/2, null);
					}else{
						g.fillPolygon(h1x3, h1y3, 5);
						g.drawImage(icons[6], 55+160,120,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(h1x3, h1y3, 5);
					g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
				}
			}
		}
		if(!Game.player.A2[0]) {
			if(Menu[currentOption]=="A21" && Game.menu.clicou|| con.bot==21) {
				g.fillPolygon(h2x1, h2y1, 5);
				g.drawImage(icons[11], 55,200,133/2, 130/2, null);
			}else if(Menu[currentOption]=="A21") {
				g.fillPolygon(h2x1, h2y1, 5);
				g.drawImage(icons[10], 55,200,133/2, 130/2, null);
			}else {
				g.fillPolygon(h2x1, h2y1, 5);
				g.drawImage(icons[9], 55,200,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(h2x1, h2y1, 5);
			g.drawImage(icons[11], 55,200,133/2, 130/2, null);
			if(!Game.player.A2[1]) {
				if(Menu[currentOption]=="A22" && Game.menu.clicou|| con.bot==22) {
					g.fillPolygon(h2x2, h2y2, 5);
					g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
				}else if(Menu[currentOption]=="Bloquear2") {
					g.fillPolygon(h2x2, h2y2, 5);
					g.drawImage(icons[13], 55+80,200,133/2, 130/2, null);
				}else {
					g.fillPolygon(h2x2, h2y2, 5);
					g.drawImage(icons[12], 55+80,200,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(h2x2, h2y2, 5);
				g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
				if(!Game.player.A2[2]) {
					if(Menu[currentOption]=="A23" && Game.menu.clicou|| con.bot==23) {
						g.fillPolygon(h2x3, h2y3, 5);
						g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
					}else if(Menu[currentOption]=="Bloquear3") {
						g.fillPolygon(h2x3, h2y3, 5);
						g.drawImage(icons[16], 55+160,200,133/2, 130/2, null);
					}else {
						g.fillPolygon(h2x3, h2y3, 5);
						g.drawImage(icons[15], 55+160,200,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(h2x3, h2y3, 5);
					g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
				}
			}
		}
		
		
		
		
		if(!Game.player.A3[0]) {
			if(Menu[currentOption]=="A31" && Game.menu.clicou|| con.bot==31) {
				g.fillPolygon(h3x1, h3y1, 5);
				g.drawImage(icons[20], 55,280,133/2, 130/2, null);
			}else if(Menu[currentOption]=="A31") {
				g.fillPolygon(h3x1, h3y1, 5);
				g.drawImage(icons[19], 55,280,133/2, 130/2, null);
			}else {
				g.fillPolygon(h3x1, h3y1, 5);
				g.drawImage(icons[18], 55,280,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(h3x1, h3y1, 5);
			g.drawImage(icons[20], 55,280,133/2, 130/2, null);
			if(!Game.player.A3[1]) {
				if(Menu[currentOption]=="A32" && Game.menu.clicou|| con.bot==32) {
					g.fillPolygon(h3x2, h3y2, 5);
					g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
				}else if(Menu[currentOption]=="A32") {
					g.fillPolygon(h3x2, h3y2, 5);
					g.drawImage(icons[22], 55+80,280,133/2, 130/2, null);
				}else {
					g.fillPolygon(h3x2, h3y2, 5);
					g.drawImage(icons[21], 55+80,280,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(h3x2, h3y2, 5);
				g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
				if(!Game.player.A3[2]) {
					if(Menu[currentOption]=="A33" && Game.menu.clicou|| con.bot==33) {
						g.fillPolygon(h3x3, h3y3, 5);
						g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
					}else if(Menu[currentOption]=="A33") {
						g.fillPolygon(h3x3, h3y3, 5);
						g.drawImage(icons[25], 55+160,280,133/2, 130/2, null);
					}else {
						g.fillPolygon(h3x3, h3y3, 5);
						g.drawImage(icons[24], 55+160,280,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(h3x3, h3y3, 5);
					g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
				}
			}
		}
		con.render(g);
		
		txt.render(g);
		
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("Pontos disponiveis: "+Game.menu.pontosA, 50, 100);
			
	}
}
