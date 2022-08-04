//package Menu;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import Main.Game;
//import World.World;
//
//public class TextosAtributos {
//		int x,y;
//		public void render(Graphics g ) {
//		Graphics2D g2 = (Graphics2D) g;
//		Color Standart= new Color(255,0,0);
//		Color MouseOver= new Color(200,0,0);
//		Color Pressed= new Color(150,0,0);
//		Color beje= new Color(247,212,212);
//		g.setColor(Color.BLACK);
//		x=-25;
//		y=10;
//		if(Game.menu.idioma=="Portugues") {
//			if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="Vazio") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 120,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 100);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Melhorar os atributos de Tai em", 485+x, 85+y);
//				g.drawString("três pontos, força, habilidade ", 485+x, 110+y);
//				g.drawString("e defesa.", 485+x, 135+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="forca1") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 90,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 70);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Força nivel 1:", 485+x, 85+y);
//				g.drawString("Aumenta o dano de ataque.", 485+x, 110+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="forca2") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 85,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 65);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Força nivel 2:", 485+x, 85+y);
//				g.drawString("Aumenta o ganho de furia.", 485+x, 110+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="forca3") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 110,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 90);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Força nivel 3:", 485+x, 85+y);
//				g.drawString("Aumenta o dano recebido por", 485+x, 110+y);
//				g.drawString("acumulo de furia.", 485+x, 135+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="habilidade1") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 90,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 70);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Habilidade nivel 1:", 485+x, 85);
//				g.drawString("Habilidades custam menos furia.", 485+x, 110);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="habilidade2") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 90,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 70);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Habilidade nivel 2:", 485+x, 85+y);
//				g.drawString("Habilidades causam mais dano. ", 485+x, 110+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="habilidade3") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 100,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 80);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Habilidade nivel 3:", 485+x, 85+y);
//				g.drawString("Habilidades deixam de custar  ", 485+x, 110+y);
//				g.drawString("furia.", 485+x, 135+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="defesa1") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 90,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 70);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Defesa nivel 1:", 485+x, 85+y);
//				g.drawString("Aumenta a defesa base. ", 485+x, 110+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="defesa2") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 110,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 90);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Defesa nivel 2:", 485+x, 85+y);
//				g.drawString("Aumenta a defesa recebida por", 485+x, 110+y);
//				g.drawString("acumulo de furia.", 485+x, 135+y);
//			}else if(Game.menu.menIn.at.Menu[Game.menu.menIn.at.currentOption]=="defesa3") {
//				g.setColor(Color.black);
//				g.fillRoundRect(470+x,50+y, 220, 100,30,30);
//				g.setColor(beje);
//				g.fillRect(480+x,60+y, 200, 80);
//				g.setFont(new Font("arial",Font.BOLD,12));
//				g.setColor(Color.black);
//				g.drawString("Defesa nivel 3:", 485+x, 85+y);
//				g.drawString("Aumenta a eficiencia de escudos.", 485+x, 110+y);
//			}
//		}else {
//
//		}
//	}
//}