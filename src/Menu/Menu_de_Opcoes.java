package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Main.Game;
import World.World;

public class Menu_de_Opcoes  {
	
	String[] Menu = {"Vazio","Volume","Efeitos","Musica","Idioma","Sair"};
	int currentOption=0;
	public int mx,my;
	int x;
	int volume;
	
	public void attMouse() {
		mx=Game.menu.mx;
		my=Game.menu.my;		
	}
	
	public void tick() {
		x=20;
		attMouse();
		if(Game.menu.soltou) {
			Game.menu.soltou=false;
			if(Game.menu.pause) {
				if(mx>279 && mx<279+165 && my>314 && my<314+24) {
					Game.menu.pause=false;
					Game.gameState="MENU";
					World.restartGame("level"+1+".png");
				}
			}
			if(mx>583+20 && mx<583+20+62 && my>314 && my<314+24) {
				Game.menu.opcoes=false;
			}else if(mx>720/2+20 && mx<720/2+20+100 && my>85 && my<85+20) {
				currentOption=1;
				Game.menu.volume=volume;
				
			}else if(mx>720/2+20 && mx<720/2+25+20 && my>135 && my<135+20) {
				if(Game.menu.sfx) {
					Game.menu.sfx=false;
				}else {
					Game.menu.sfx=true;
				}
			}else if(mx>720/2-2+20 && mx<720/2-2+20+29 && my>133+50 && my<133+50+24) {
				if(Game.menu.mus) {
					Game.menu.mus=false;
				}else {
					Game.menu.mus=true;
				}
			}else if(mx>720/3+100-10+20 && mx<720/3+20+100-10+80 && my>250-15 && my<250-15+20) {
				if(Game.menu.idioma=="English") {
					Game.menu.idioma="Portugues";
				}else if(Game.menu.idioma=="Portugues") {
					Game.menu.idioma="English";
				}
			}else {
				currentOption=0;
			}
		}else {

			int x= Game.menu.volume;
			if(mx>583+20 && mx<583+20+62 && my>314 && my<314+24) {
				if(!Game.menu.pause) {
					currentOption=5;
				}
			}else if(mx>720/2+20 && mx<720/2+20+100 && my>85 && my<85+20) {
				currentOption=1;
				volume=mx-720/2-20;
			}else if(mx>720/2+20 && mx<720/2+20+25 && my>135 && my<135+20) {
				currentOption=2;
			}else if(mx>720/2+20-2 && mx<720/2-2+20+29 && my>133+50 && my<133+50+24) {
				currentOption=3;
			}else if(mx>720/3+100+20+20-10 && mx<720/3+100+20+20-10+80 && my>250-15 && my<250-15+20) {
				currentOption=4;
			}else {
				if(mx>279 && mx<279+165 && my>314 && my<314+24) {
					currentOption=5;
				}else {
				 	currentOption=0;
				}
				Game.menu.volume=x;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRoundRect(720/3+20-40,65, 260+20, 200+20, 30, 50);
		g.setColor(Game.menu.menIn.beje);
		g.fillRoundRect(720/3+20-30,75, 260, 200, 30, 50);
		
		
		//volume
		g.setColor(Color.black);
		g.fillRect(720/2+20,85, 99, 20);
		
		if(Menu[currentOption]=="Volume") {
			g.setColor(Game.menu.menIn.MouseOver);
			g.fillRect(720/2+20,85, volume, 20);
		}else {
			g.setColor(Game.menu.menIn.Standart);
			g.fillRect(720/2+20,85, Game.menu.volume, 20);
		}
		
		
		g.setColor(Color.black);
		
		if(!Game.menu.pause) {
			g.fillRoundRect(580+20,312, 68, 28, 30, 50);
			if(Menu[currentOption]=="Sair"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(583+20,314, 62, 24, 30, 50);
			}else if(Menu[currentOption]=="Sair") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(583+20,314, 62, 24, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.Standart);
				g.fillRoundRect(583+20,314, 62, 24, 30, 50);
			}
		}else {
			g.fillRoundRect(580-80-100-124,312, 170, 28, 30, 50);
			if(Menu[currentOption]=="Sair"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(583-80-100-124,314, 165, 24, 30, 50);
			}else if(Menu[currentOption]=="Sair") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(583-80-100-124,314, 165, 24, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.Standart);
				g.fillRoundRect(583-80-100-124,314, 165, 24, 30, 50);
			}
		}
		g.setColor(Color.black);
		g.fillRoundRect(720/2+20-2,133, 29, 24, 30, 50);
		if(Game.menu.sfx) {
			if(Menu[currentOption]=="Efeitos"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}else if(Menu[currentOption]=="Efeitos") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.Standart);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}
		}else {
			if(Menu[currentOption]=="Efeitos"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}else if(Menu[currentOption]=="Efeitos") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,135, 25, 20, 30, 50);
			}
		}
		g.setColor(Color.black);
		if(!Game.menu.sfx) {
			g.drawString("On",720/3+20+124, 150);
		}else {
			g.drawString("Off",720/3+20+124, 150);
		}
		g.setColor(Color.black);
		g.fillRoundRect(720/2-2+20,133+50, 29, 24, 30, 50);
		if(Game.menu.mus) {
			if(Menu[currentOption]=="Musica"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}else if(Menu[currentOption]=="Musica") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.Standart);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}
		}else {
			if(Menu[currentOption]=="Musica"&& Game.menu.clicou) {
				g.setColor(Game.menu.menIn.Pressed);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}else if(Menu[currentOption]=="Musica") {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}else {
				g.setColor(Game.menu.menIn.MouseOver);
				g.fillRoundRect(720/2+20,185, 25, 20, 30, 50);
			}
		}
		g.setColor(Color.black);
		if(!Game.menu.mus) {
			g.drawString("On",720/3+20+124, 200);
		}else {
			g.drawString("Off",720/3+20+124, 200);
		}
		if(Menu[currentOption]=="Idioma"&& Game.menu.clicou) {
			g.setColor(Game.menu.menIn.Pressed);
			g.drawString(Game.menu.idioma,720/3+20+100+20, 250);
		}else if(Menu[currentOption]=="Idioma") {
			g.setColor(Game.menu.menIn.MouseOver);
			g.drawString(Game.menu.idioma,720/3+20+100+20, 250);
		}else {
			g.setColor(Game.menu.menIn.Standart);
			g.drawString(Game.menu.idioma,720/3+20+100+20, 250);
		}
		g.setColor(Color.black);
		if(Game.menu.idioma=="Portugues") {
			g.drawString("Volume:",720/3+20, 100);
			g.drawString("Efeitos Sonoros:", 720/3+20, 150);
			g.drawString("Musica:",720/3+20, 200);
			g.drawString("Idioma:",720/3+20, 250);
			g.setColor(Color.black);
			if(!Game.menu.pause) {
				g.drawString("Voltar", 618, 330);
			}else {
				g.drawString("Sair para o menu principal", 599-82-105-124, 330);
			}
		}else {
			g.drawString("Volume:",720/3+20, 100);
			g.drawString("Sound Efects:", 720/3+20, 150);
			g.drawString("Music:",720/3+20, 200);
			g.drawString("Language:",720/3+20, 250);
			g.setColor(Color.black);
			if(!Game.menu.pause) {
				g.drawString("Back", 619, 330);
			}else {
				g.drawString("Exit to the main menu", 599-82-105-109, 330);
			}
		}
			
		}
}
