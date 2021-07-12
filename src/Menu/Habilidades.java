package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entidades.ArvoreHabilidade;
import Entidades.Chama;
import Main.Game;

public class Habilidades {
	private Chama chama=new Chama(20,20);
	private ArvoreHabilidade h=new ArvoreHabilidade();
	int posx=0,posy=0;
	public String[] Menu = {"Vazio","Tempestade de Socos1","Tempestade de Socos2","Tempestade de Socos3",
			"Bloquear1","Bloquear2","Bloquear3","Fortalecer1","Fortalecer2","Fortalecer3"};
	public int currentOption=0;
	String golpe;
	confirmacao con=new confirmacao();
	TextosHablidades txt= new TextosHablidades();
	
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
		chama.tick();
		
//		for(int i=0;i<3;i++) {
//			icons[i+0]=Game.Menu.getSprite(133*i, 130*0, 133, 130);
//			icons[i+3]=Game.Menu.getSprite(133*i, 130*1, 133, 130);
//			icons[i+6]=Game.Menu.getSprite(133*i, 130*2, 133, 130);
//			
//			icons[i+9]=Game.Menu.getSprite(399+133*i, 130*0, 133, 130);
//			icons[i+12]=Game.Menu.getSprite(399+133*i, 130*1, 133, 130);
//			icons[i+15]=Game.Menu.getSprite(399+133*i, 130*2, 133, 130);
//			
//			icons[i+18]=Game.Menu.getSprite(133*i, 390, 133, 130);
//			icons[i+21]=Game.Menu.getSprite(133*i, 390+130, 133, 130);
//			icons[i+24]=Game.Menu.getSprite(133*i, 390+130*2, 133, 130);
//		}
//		con.tick();
//
//		if(Game.menu.soltou) {
//			Game.menu.soltou=false;
//			if(mx>54 && mx<123 && my>119 && my<185) {
//				//socos tempestade nv 1
//				currentOption=1;
//				if(!Game.player.H1[0]) {
//					if(Game.menu.pontosH>0) {
//						golpe="tempestade de socos";
//						nivel=1;
//						con.confirmar(golpe, nivel, "Habilidade",11);
//
//					}
//				}
//			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.H1[0]) {
//				//socos tempestade 2
//				currentOption=2;
//				if(!Game.player.H1[1]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="tempestade de socos";
//						nivel=2;
//						con.confirmar(golpe, nivel, "Habilidade",12);
//					}
//				}
//			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.H1[1]) {
//				//socos tempestade 3
//				currentOption=3;
//				if(!Game.player.H1[2]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="tempestade de socos";
//						nivel=3;
//						con.confirmar(golpe, nivel, "Habilidade",13);
//					}
//				}
//			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
//				//bloquear 1
//				currentOption=4;
//				if(!Game.player.H2[0]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="bloquear";
//						nivel=1;
//						con.confirmar(golpe, nivel, "Habilidade",21);
//					}
//				}
//			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.H2[0]) {
//				//bloquear 2
//				currentOption=5;
//				if(!Game.player.H2[1]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="bloquear";
//						nivel=2;
//						con.confirmar(golpe, nivel, "Habilidade",22);
//					}
//				}
//			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.H2[1]) {
//				//bloquear 3
//				currentOption=6;
//				if(!Game.player.H2[2]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="bloquear";
//						nivel=3;
//						con.confirmar(golpe, nivel, "Habilidade",23);
//					}
//				}
//			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
//				//fortalecer 1 
//				currentOption=7;
//				if(!Game.player.H3[0]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="fortalecer";
//						nivel=1;
//						con.confirmar(golpe, nivel, "Habilidade",31);
//					}
//				}
//			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.H3[0]) {
//				//fortalecer 2
//				currentOption=8;
//				if(!Game.player.H3[1]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="fortalecer";
//						nivel=2;
//						con.confirmar(golpe, nivel, "Habilidade",32);
//					}
//				}
//			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.H3[1]) {
//				//fortalecer  3
//				currentOption=9;
//				if(!Game.player.H3[2]) {
//					if(Game.menu.pontosH>0) {
//						Game.menu.menIn.confirmacao=true;
//						golpe="fortalecer";
//						nivel=3;
//						con.confirmar(golpe, nivel, "Habilidade",33);
//					}
//				}
//			}else {
//				currentOption=0;
//			}
//			
//		}else {
//			if(mx>54 && mx<123 && my>119 && my<185) {
//				//socos tempestade 1
//				currentOption=1;
//			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.H1[0]) {
//				//socos tempestade 2
//				currentOption=2;
//			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.H1[1]) {
//				//socos tempestade 3
//				currentOption=3;
//			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
//				//bloquear1
//				currentOption=4;
//			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.H2[0]) {
//				//bloquear2
//				currentOption=5;
//			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.H2[1]) {
//				//bloquear3
//				currentOption=6;
//			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
//				//fortalecer 1
//				currentOption=7;
//			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.H3[0]) {
//				//fortalecer 2
//				currentOption=8;
//			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.H3[1]) {
//				//fortalecer 3
//				currentOption=9;
//			}else {
//				currentOption=0;
//			}
//		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		g.setColor(Color.BLACK); 
//		if(!Game.player.H1[0]) {
//			if(Menu[currentOption]=="Tempestade de Socos1" && Game.menu.clicou || con.bot==11) {
//				g.fillPolygon(h1x1, h1y1, 5);
//				g.drawImage(icons[2], 55,120,133/2, 130/2, null);
//			}else if(Menu[currentOption]=="Tempestade de Socos1") {
//				g.fillPolygon(h1x1, h1y1, 5);
//				g.drawImage(icons[1], 55,120,133/2, 130/2, null);
//			}else{
//				g.fillPolygon(h1x1, h1y1, 5);
//				g.drawImage(icons[0], 55,120,133/2, 130/2, null);
//			}
//		}else {
//			g.fillPolygon(h1x1, h1y1, 5);
//			g.drawImage(icons[2], 55,120,133/2, 130/2, null);
//			if(!Game.player.H1[1]) {
//				if(Menu[currentOption]=="Tempestade de Socos2" && Game.menu.clicou || con.bot==12) {
//					g.fillPolygon(h1x2, h1y2, 5);
//					g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
//				}else if(Menu[currentOption]=="Tempestade de Socos2") {
//					g.fillPolygon(h1x2, h1y2, 5);
//					g.drawImage(icons[4], 55+80,120,133/2, 130/2, null);
//				}else{
//					g.fillPolygon(h1x2, h1y2, 5);
//					g.drawImage(icons[3], 55+80,120,133/2, 130/2, null);
//				}
//			}else {
//				g.fillPolygon(h1x2, h1y2, 5);
//				g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
//				if(!Game.player.H1[2]) {
//					if(Menu[currentOption]=="Tempestade de Socos3" && Game.menu.clicou|| con.bot==13) {
//						g.fillPolygon(h1x3, h1y3, 5);
//						g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
//					}else if(Menu[currentOption]=="Tempestade de Socos3") {
//						g.fillPolygon(h1x3, h1y3, 5);
//						g.drawImage(icons[7], 55+160,120,133/2, 130/2, null);
//					}else{
//						g.fillPolygon(h1x3, h1y3, 5);
//						g.drawImage(icons[6], 55+160,120,133/2, 130/2, null);
//					}
//				}else {
//					g.fillPolygon(h1x3, h1y3, 5);
//					g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
//				}
//			}
//		}
//		if(!Game.player.H2[0]) {
//			if(Menu[currentOption]=="Bloquear1" && Game.menu.clicou|| con.bot==21) {
//				g.fillPolygon(h2x1, h2y1, 5);
//				g.drawImage(icons[11], 55,200,133/2, 130/2, null);
//			}else if(Menu[currentOption]=="Bloquear1") {
//				g.fillPolygon(h2x1, h2y1, 5);
//				g.drawImage(icons[10], 55,200,133/2, 130/2, null);
//			}else {
//				g.fillPolygon(h2x1, h2y1, 5);
//				g.drawImage(icons[9], 55,200,133/2, 130/2, null);
//			}
//		}else {
//			g.fillPolygon(h2x1, h2y1, 5);
//			g.drawImage(icons[11], 55,200,133/2, 130/2, null);
//			if(!Game.player.H2[1]) {
//				if(Menu[currentOption]=="Bloquear2" && Game.menu.clicou|| con.bot==22) {
//					g.fillPolygon(h2x2, h2y2, 5);
//					g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
//				}else if(Menu[currentOption]=="Bloquear2") {
//					g.fillPolygon(h2x2, h2y2, 5);
//					g.drawImage(icons[13], 55+80,200,133/2, 130/2, null);
//				}else {
//					g.fillPolygon(h2x2, h2y2, 5);
//					g.drawImage(icons[12], 55+80,200,133/2, 130/2, null);
//				}
//			}else {
//				g.fillPolygon(h2x2, h2y2, 5);
//				g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
//				if(!Game.player.H2[2]) {
//					if(Menu[currentOption]=="Bloquear3" && Game.menu.clicou|| con.bot==23) {
//						g.fillPolygon(h2x3, h2y3, 5);
//						g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
//					}else if(Menu[currentOption]=="Bloquear3") {
//						g.fillPolygon(h2x3, h2y3, 5);
//						g.drawImage(icons[16], 55+160,200,133/2, 130/2, null);
//					}else {
//						g.fillPolygon(h2x3, h2y3, 5);
//						g.drawImage(icons[15], 55+160,200,133/2, 130/2, null);
//					}
//				}else {
//					g.fillPolygon(h2x3, h2y3, 5);
//					g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
//				}
//			}
//		}
//		
//		
//		
//		
//		if(!Game.player.H3[0]) {
//			if(Menu[currentOption]=="Fortalecer1" && Game.menu.clicou|| con.bot==31) {
//				g.fillPolygon(h3x1, h3y1, 5);
//				g.drawImage(icons[20], 55,280,133/2, 130/2, null);
//			}else if(Menu[currentOption]=="Fortalecer1") {
//				g.fillPolygon(h3x1, h3y1, 5);
//				g.drawImage(icons[19], 55,280,133/2, 130/2, null);
//			}else {
//				g.fillPolygon(h3x1, h3y1, 5);
//				g.drawImage(icons[18], 55,280,133/2, 130/2, null);
//			}
//		}else {
//			g.fillPolygon(h3x1, h3y1, 5);
//			g.drawImage(icons[20], 55,280,133/2, 130/2, null);
//			if(!Game.player.H3[1]) {
//				if(Menu[currentOption]=="Fortalecer2" && Game.menu.clicou|| con.bot==32) {
//					g.fillPolygon(h3x2, h3y2, 5);
//					g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
//				}else if(Menu[currentOption]=="Fortalecer2") {
//					g.fillPolygon(h3x2, h3y2, 5);
//					g.drawImage(icons[22], 55+80,280,133/2, 130/2, null);
//				}else {
//					g.fillPolygon(h3x2, h3y2, 5);
//					g.drawImage(icons[21], 55+80,280,133/2, 130/2, null);
//				}
//			}else {
//				g.fillPolygon(h3x2, h3y2, 5);
//				g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
//				if(!Game.player.H3[2]) {
//					if(Menu[currentOption]=="Fortalecer3" && Game.menu.clicou|| con.bot==33) {
//						g.fillPolygon(h3x3, h3y3, 5);
//						g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
//					}else if(Menu[currentOption]=="Fortalecer3") {
//						g.fillPolygon(h3x3, h3y3, 5);
//						g.drawImage(icons[25], 55+160,280,133/2, 130/2, null);
//					}else {
//						g.fillPolygon(h3x3, h3y3, 5);
//						g.drawImage(icons[24], 55+160,280,133/2, 130/2, null);
//					}
//				}else {
//					g.fillPolygon(h3x3, h3y3, 5);
//					g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
//				}
//			}
//		}
//		con.render(g);
		
		txt.render(g);
//		chama.render(g);
		h.render(g2);
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,15));
//		g.drawString("Pontos disponiveis: "+Game.menu.pontosH, 50, 100);
//			g.fillRect(50, 60, 360, 280);
	}
}
