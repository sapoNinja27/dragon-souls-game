package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Habilidades {
	int posx=0,posy=0;
	public String[] Menu = {"Vazio","Tempestade de Socos1","Tempestade de Socos2","Tempestade de Socos3",
			"Bloquear1","Bloquear2","Bloquear3","Fortalecer1","Fortalecer2","Fortalecer3"};
	public int currentOption=0;
	String golpe;
	confirmacao con=new confirmacao();
	TextosHablidades txt= new TextosHablidades();
	
	int nivel;
	int tmpsocosx1[] = { 52, 92, 125, 92, 52}; 
	int tmpsocosy1[] = { 117, 117, 152, 187, 187 }; 
	int bloqueiox1[]  = { 52, 92, 125, 92, 52};
	int bloqueioy1[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int fortx1[]  = { 52, 92, 125, 92, 52};
	int forty1[] = { 117+160, 117+160, 152+160, 187+160, 187+160 }; 
	
	int tmpsocosx2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int tmpsocosy2[] = { 117, 117, 152, 187, 187 };
	int bloqueiox2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int bloqueioy2[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int fortx2[] = { 52+80, 92+80, 125+80, 92+80, 52+80}; 
	int forty2[] = { 117+160, 117+160, 152+160, 187+160, 187+160 };
	
	int tmpsocosx3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int tmpsocosy3[] = { 117, 117, 152, 187, 187 };
	int bloqueiox3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int bloqueioy3[] = { 117+80, 117+80, 152+80, 187+81, 187+81}; 
	int fortx3[] = { 52+160, 92+160, 125+160, 92+160, 52+160}; 
	int forty3[] = { 117+160, 117+160, 152+160, 187+160, 187+160 };
	BufferedImage icons[]=new BufferedImage[27];
	public int mx,my;
	public void attMouse() {
		mx=Game.menu.mx;
		my=Game.menu.my;		
	}
	public void tick() {
		attMouse();
		
		for(int i=0;i<3;i++) {
			icons[i+0]=Game.Menu.getSprite(133*i, 130*0, 133, 130);
			icons[i+3]=Game.Menu.getSprite(133*i, 130*1, 133, 130);
			icons[i+6]=Game.Menu.getSprite(133*i, 130*2, 133, 130);
			
			icons[i+9]=Game.Menu.getSprite(399+133*i, 130*0, 133, 130);
			icons[i+12]=Game.Menu.getSprite(399+133*i, 130*1, 133, 130);
			icons[i+15]=Game.Menu.getSprite(399+133*i, 130*2, 133, 130);
			
			icons[i+18]=Game.Menu.getSprite(133*i, 390, 133, 130);
			icons[i+21]=Game.Menu.getSprite(133*i, 390+130, 133, 130);
			icons[i+24]=Game.Menu.getSprite(133*i, 390+130*2, 133, 130);
		}
		con.tick();

		if(Game.menu.soltou) {
			Game.menu.soltou=false;
			if(mx>54 && mx<123 && my>119 && my<185) {
				//socos tempestade nv 1
				currentOption=1;
				if(!Game.player.tmpSocos[0]) {
					if(Game.menu.pontosH>0) {
						golpe="tempestade de socos";
						nivel=1;
						con.confirmar(golpe, nivel, "Habilidade",11);

					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.tmpSocos[0]) {
				//socos tempestade 2
				currentOption=2;
				if(!Game.player.tmpSocos[1]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="tempestade de socos";
						nivel=2;
						con.confirmar(golpe, nivel, "Habilidade",12);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.tmpSocos[1]) {
				//socos tempestade 3
				currentOption=3;
				if(!Game.player.tmpSocos[2]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="tempestade de socos";
						nivel=3;
						con.confirmar(golpe, nivel, "Habilidade",13);
					}
				}
			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
				//bloquear 1
				currentOption=4;
				if(!Game.player.bloqueio[0]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="bloquear";
						nivel=1;
						con.confirmar(golpe, nivel, "Habilidade",21);
					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.bloqueio[0]) {
				//bloquear 2
				currentOption=5;
				if(!Game.player.bloqueio[1]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="bloquear";
						nivel=2;
						con.confirmar(golpe, nivel, "Habilidade",22);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.bloqueio[1]) {
				//bloquear 3
				currentOption=6;
				if(!Game.player.bloqueio[2]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="bloquear";
						nivel=3;
						con.confirmar(golpe, nivel, "Habilidade",23);
					}
				}
			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
				//fortalecer 1 
				currentOption=7;
				if(!Game.player.socoForte[0]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="fortalecer";
						nivel=1;
						con.confirmar(golpe, nivel, "Habilidade",31);
					}
				}
			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.socoForte[0]) {
				//fortalecer 2
				currentOption=8;
				if(!Game.player.socoForte[1]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="fortalecer";
						nivel=2;
						con.confirmar(golpe, nivel, "Habilidade",32);
					}
				}
			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.socoForte[1]) {
				//fortalecer  3
				currentOption=9;
				if(!Game.player.socoForte[2]) {
					if(Game.menu.pontosH>0) {
						Game.menu.menIn.confirmacao=true;
						golpe="fortalecer";
						nivel=3;
						con.confirmar(golpe, nivel, "Habilidade",33);
					}
				}
			}else {
				currentOption=0;
			}
			
		}else {
			if(mx>54 && mx<123 && my>119 && my<185) {
				//socos tempestade 1
				currentOption=1;
			}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.tmpSocos[0]) {
				//socos tempestade 2
				currentOption=2;
			}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.tmpSocos[1]) {
				//socos tempestade 3
				currentOption=3;
			}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
				//bloquear1
				currentOption=4;
			}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.bloqueio[0]) {
				//bloquear2
				currentOption=5;
			}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.bloqueio[1]) {
				//bloquear3
				currentOption=6;
			}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
				//fortalecer 1
				currentOption=7;
			}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.socoForte[0]) {
				//fortalecer 2
				currentOption=8;
			}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.socoForte[1]) {
				//fortalecer 3
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
		if(!Game.player.tmpSocos[0]) {
			if(Menu[currentOption]=="Tempestade de Socos1" && Game.menu.clicou || con.bot==11) {
				g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
				g.drawImage(icons[2], 55,120,133/2, 130/2, null);
			}else if(Menu[currentOption]=="Tempestade de Socos1") {
				g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
				g.drawImage(icons[1], 55,120,133/2, 130/2, null);
			}else{
				g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
				g.drawImage(icons[0], 55,120,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
			g.drawImage(icons[2], 55,120,133/2, 130/2, null);
			if(!Game.player.tmpSocos[1]) {
				if(Menu[currentOption]=="Tempestade de Socos2" && Game.menu.clicou || con.bot==12) {
					g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
					g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
				}else if(Menu[currentOption]=="Tempestade de Socos2") {
					g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
					g.drawImage(icons[4], 55+80,120,133/2, 130/2, null);
				}else{
					g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
					g.drawImage(icons[3], 55+80,120,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
				g.drawImage(icons[5], 55+80,120,133/2, 130/2, null);
				if(!Game.player.tmpSocos[2]) {
					if(Menu[currentOption]=="Tempestade de Socos3" && Game.menu.clicou|| con.bot==13) {
						g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
						g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
					}else if(Menu[currentOption]=="Tempestade de Socos3") {
						g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
						g.drawImage(icons[7], 55+160,120,133/2, 130/2, null);
					}else{
						g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
						g.drawImage(icons[6], 55+160,120,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
					g.drawImage(icons[8], 55+160,120,133/2, 130/2, null);
				}
			}
		}
		if(!Game.player.bloqueio[0]) {
			if(Menu[currentOption]=="Bloquear1" && Game.menu.clicou|| con.bot==21) {
				g.fillPolygon(bloqueiox1, bloqueioy1, 5);
				g.drawImage(icons[11], 55,200,133/2, 130/2, null);
			}else if(Menu[currentOption]=="Bloquear1") {
				g.fillPolygon(bloqueiox1, bloqueioy1, 5);
				g.drawImage(icons[10], 55,200,133/2, 130/2, null);
			}else {
				g.fillPolygon(bloqueiox1, bloqueioy1, 5);
				g.drawImage(icons[9], 55,200,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(bloqueiox1, bloqueioy1, 5);
			g.drawImage(icons[11], 55,200,133/2, 130/2, null);
			if(!Game.player.bloqueio[1]) {
				if(Menu[currentOption]=="Bloquear2" && Game.menu.clicou|| con.bot==22) {
					g.fillPolygon(bloqueiox2, bloqueioy2, 5);
					g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
				}else if(Menu[currentOption]=="Bloquear2") {
					g.fillPolygon(bloqueiox2, bloqueioy2, 5);
					g.drawImage(icons[13], 55+80,200,133/2, 130/2, null);
				}else {
					g.fillPolygon(bloqueiox2, bloqueioy2, 5);
					g.drawImage(icons[12], 55+80,200,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(bloqueiox2, bloqueioy2, 5);
				g.drawImage(icons[14], 55+80,200,133/2, 130/2, null);
				if(!Game.player.bloqueio[2]) {
					if(Menu[currentOption]=="Bloquear3" && Game.menu.clicou|| con.bot==23) {
						g.fillPolygon(bloqueiox3, bloqueioy3, 5);
						g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
					}else if(Menu[currentOption]=="Bloquear3") {
						g.fillPolygon(bloqueiox3, bloqueioy3, 5);
						g.drawImage(icons[16], 55+160,200,133/2, 130/2, null);
					}else {
						g.fillPolygon(bloqueiox3, bloqueioy3, 5);
						g.drawImage(icons[15], 55+160,200,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(bloqueiox3, bloqueioy3, 5);
					g.drawImage(icons[17], 55+160,200,133/2, 130/2, null);
				}
			}
		}
		
		
		
		
		if(!Game.player.socoForte[0]) {
			if(Menu[currentOption]=="Fortalecer1" && Game.menu.clicou|| con.bot==31) {
				g.fillPolygon(fortx1, forty1, 5);
				g.drawImage(icons[20], 55,280,133/2, 130/2, null);
			}else if(Menu[currentOption]=="Fortalecer1") {
				g.fillPolygon(fortx1, forty1, 5);
				g.drawImage(icons[19], 55,280,133/2, 130/2, null);
			}else {
				g.fillPolygon(fortx1, forty1, 5);
				g.drawImage(icons[18], 55,280,133/2, 130/2, null);
			}
		}else {
			g.fillPolygon(fortx1, forty1, 5);
			g.drawImage(icons[20], 55,280,133/2, 130/2, null);
			if(!Game.player.socoForte[1]) {
				if(Menu[currentOption]=="Fortalecer2" && Game.menu.clicou|| con.bot==32) {
					g.fillPolygon(fortx2, forty2, 5);
					g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
				}else if(Menu[currentOption]=="Fortalecer2") {
					g.fillPolygon(fortx2, forty2, 5);
					g.drawImage(icons[22], 55+80,280,133/2, 130/2, null);
				}else {
					g.fillPolygon(fortx2, forty2, 5);
					g.drawImage(icons[21], 55+80,280,133/2, 130/2, null);
				}
			}else {
				g.fillPolygon(fortx2, forty2, 5);
				g.drawImage(icons[23], 55+80,280,133/2, 130/2, null);
				if(!Game.player.socoForte[2]) {
					if(Menu[currentOption]=="Fortalecer3" && Game.menu.clicou|| con.bot==33) {
						g.fillPolygon(fortx3, forty3, 5);
						g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
					}else if(Menu[currentOption]=="Fortalecer3") {
						g.fillPolygon(fortx3, forty3, 5);
						g.drawImage(icons[25], 55+160,280,133/2, 130/2, null);
					}else {
						g.fillPolygon(fortx3, forty3, 5);
						g.drawImage(icons[24], 55+160,280,133/2, 130/2, null);
					}
				}else {
					g.fillPolygon(fortx3, forty3, 5);
					g.drawImage(icons[26], 55+160,280,133/2, 130/2, null);
				}
			}
		}
		con.render(g);
		
		txt.render(g);
		
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("Pontos disponiveis: "+Game.menu.pontosH, 50, 100);
			
	}
}
