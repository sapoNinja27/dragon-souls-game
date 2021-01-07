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

public class Menu {
	Menu_de_Opcoes menOp=new Menu_de_Opcoes();
	Menu_de_Load menLo=new Menu_de_Load();
	Menu_ingame menIn=new Menu_ingame();
	int posx=0,posy=0;
	public String[] MenuPrincipal = {"Vazio","Jogar","Continuar","Opções","Sair"};
	public int currentOption=0;
	boolean load;
	boolean opcoes;
	String idioma="Portugues";
	int volume=0;
	public Color Standart;
	public Color MouseOver;
	public Color Pressed;
	public Color beje;
	boolean sfx;
	boolean mus;
	
	public boolean clicou, soltou;
	public int mx,my;
	public boolean pause = false;
	public int pontosH=0,pontosA=0;
	
	public static void applySave(String str){
		String[] spl=str.split("/");
		for(int i=0; i< spl.length; i++) {
			String[] spl2= spl[i].split(":");
			switch(spl2[0]) {
			case "level":
				World.restartGame("level"+spl2[1]+".png");
				Game.gameState="NORMAL";
				break;
			}
		}
	}
	public static String loadGame(int encode) {
		String line= "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine=null;
				BufferedReader reader=new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine=reader.readLine())!=null) {
						String[] trans=singleLine.split(":");
						char[] val=trans[1].toCharArray();
						trans[1]="";
						for(int i=0; i<val.length;i++) {
							val[i]-=encode;
							trans[1]+=val[i];
						}
						line+=trans[0];
						line+=":";
						line+=trans[1];
						line+="/";
					}
				}catch(IOException e) {
					
				}
			}catch(FileNotFoundException e) {
				
			}
		}
		return line;
	}
	public static void saveGame(String[] val1, int[] val2, int encode) {
		BufferedWriter write =null;
		try {
			write=new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i< val1.length;i++) {
			String current=val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int n=0; n<value.length;n++) {
				value[n]+=encode;
				current+=value[n];
			}
			try {
				write.write(current);
				if(i<val1.length-1) {
					write.newLine();
				}
			}catch(IOException e) {
				
			}
			try {
				write.flush();
				write.close();
			}catch(IOException e) {
				
			}
		}
	}
	
	public void tick() {
		pontosH=6;
		pontosA=6;
		posx++;
		if(posx==2976-1440) {
			posx=0;
		}
		if(!pause) {
			if(load) {
				 menLo.tick();
			}else {
				if(opcoes) {
					menOp.tick();
					
				}else {
					if(soltou) {
						soltou=false;
						if(mx>160 && mx<160+90 && my>250 && my<250+30) {
							Game.gameState = "NORMAL";
							Game.player.visivel=true;
							Game.player.Hudvisivel=true;
							Game.player.depth=7;
			//				Game.cen.CenaStart(0);
							pause = false;					
						}
						if(mx>263 && mx<263+90 && my>250 && my<250+30) {
							load=true;
						}
						if(mx>366 && mx<366+90 && my>250 && my<250+30) {
							opcoes=true;
						}
						if(mx>469 && mx<469+90 && my>250 && my<250+30) {
							System.exit(1);
						}else {
							soltou=false;
						}
							
					}else {
						if(mx>160 && mx<160+90 && my>250 && my<250+30) {
							currentOption=1;
						}else if(mx>263 && mx<263+90 && my>250 && my<250+30) {
							currentOption=2;
						}else if(mx>366 && mx<366+90 && my>250 && my<250+30) {
							currentOption=3;
						}else if(mx>469 && mx<469+90 && my>250 && my<250+30) {
							currentOption=4;
						}else {
							currentOption=0;
						}
					}
				}
			}
			
		}else {
			menIn.tick();
		}	
	}
	
	public void render(Graphics g) {
		Standart= new Color(255,0,0);
		MouseOver= new Color(200,0,0);
		Pressed= new Color(150,0,0);
		beje= new Color(247,212,212);
		Graphics2D g2 = (Graphics2D) g;
		if(pause) {
			menIn.render(g);	
		}else {
			Color beje= new Color(247,212,212);
			g.drawImage(Game.fundo.getSprite(posx, posy, 1440, 720), 0, 0,720,360, null);
			if(load) {
				menLo.render(g);
			}else {
				if(opcoes) {
					menOp.render(g);
				}else {
					//Opcoes de menu
					g.drawImage(Game.Menu.getSprite(2700, 0,400,200), 720/2-200,30, null);
					g.setColor(Color.black);
					g.fillRoundRect(720/2-203+00+00,247, 97, 37, 30, 50);
					g.fillRoundRect(720/2-203+91+12,247, 97, 37, 30, 50);
					g.fillRoundRect(720/2-203+91*2+12*2,247, 97, 37, 30, 50);
					g.fillRoundRect(720/2-203+91*3+12*3,247, 97, 37, 30, 50);
					if(MenuPrincipal[currentOption]=="Jogar"&& clicou) {
						g.setColor(Pressed);
						g.fillRoundRect(720/2-200,250, 91, 30, 30, 50);
					}else if(MenuPrincipal[currentOption]=="Jogar") {
						g.setColor(MouseOver);
						g.fillRoundRect(720/2-200,250, 91, 30, 30, 50);
					}else {
						g.setColor(Standart);
						g.fillRoundRect(720/2-200,250, 91, 30, 30, 50);
					}
					if(MenuPrincipal[currentOption]=="Continuar"&& clicou) {
						g.setColor(Pressed);
						g.fillRoundRect(720/2-200+91+12,250, 90, 30, 30, 50);
					}else if(MenuPrincipal[currentOption]=="Continuar") {
						g.setColor(MouseOver);
						g.fillRoundRect(720/2-200+91+12,250, 90, 30, 30, 50);
					}else{
						g.setColor(Standart);
						g.fillRoundRect(720/2-200+91+12,250, 90, 30, 30, 50);
					}
					if(MenuPrincipal[currentOption]=="Opções"&& clicou) {
						g.setColor(Pressed);
						g.fillRoundRect(720/2-200+91*2+12*2,250, 90, 30, 30, 50);
					}else if(MenuPrincipal[currentOption]=="Opções") {
						g.setColor(MouseOver);
						g.fillRoundRect(720/2-200+91*2+12*2,250, 90, 30, 30, 50);
					}else{
						g.setColor(Standart);
						g.fillRoundRect(720/2-200+91*2+12*2,250, 90, 30, 30, 50);
					}
					if(MenuPrincipal[currentOption]=="Sair" && clicou) {
						g.setColor(Pressed);
						g.fillRoundRect(720/2-200+91*3+12*3,250, 90, 30, 30, 50);
					}else if(MenuPrincipal[currentOption]=="Sair") {
						g.setColor(MouseOver);
						g.fillRoundRect(720/2-200+91*3+12*3,250, 90, 30, 30, 50);
					}else{
						g.setColor(Standart);
						g.fillRoundRect(720/2-200+91*3+12*3,250, 90, 30, 30, 50);
					}
					g.setColor(Color.black);
					g.setFont(new Font("arial",Font.BOLD,18));
					if(idioma=="Portugues") {
						g.drawString("Jogar",720/2-200+19, 271);
						g.drawString("Continuar ", 720/2-200+91+12+3, 271);
						g.drawString("Opções ",720/2-200+91*2+12*2+11, 271);
						g.drawString("Sair", 720/2-200+91*3+12*3+26, 271);
					}else if(idioma=="English") {
						g.drawString("Play",720/2-200+19, 271);
						g.drawString("Continue ", 720/2-200+91+12+3, 271);
						g.drawString("Options ",720/2-200+91*2+12*2+11, 271);
						g.drawString("Exit", 720/2-200+91*3+12*3+26, 271);
					}
				}
			}
		}
	}
}
