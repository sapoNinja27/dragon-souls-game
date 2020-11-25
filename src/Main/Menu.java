package Main;

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

import World.World;

public class Menu {
	int posx=0,posy=0;
	public String[] MenuPrincipal = {"Vazio","Jogar","Continuar","Opções","Sair","Volume","Efeitos","Musica","Idioma","Slot1","Slot2","Slot3"};
	public String[] Menus = {"Habilidades","Atributos","Personagens","Opções"};
	public String[] Habilidades = {"Vazio","Back","Next","Tempestade de Socos1","Tempestade de Socos2","Tempestade de Socos3",
			"Bloquear1","Bloquear2","Bloquear3","Fortalecer1","Fortalecer2","Fortalecer3","Voltar","Sim","Não"};
	public int currentOption=0;
	int menus=0;
	String slot1="Vazio.",slot2="Vazio.",slot3="Vazio.";
	boolean load;
	public static boolean saveExists=false;
	public static boolean saveGame=false;
	String idioma="Portugues";
	int volume=0,volume2=0;
	boolean sfx;
	boolean mus;
	boolean confirmacao;
	public int maxOption = Habilidades.length - 1;
	public boolean clicou, soltou;
	String golpe;
	int nivel;
	boolean opcoes;
	int tmpsocosx1[] = { 54, 89, 123, 90, 54}; 
	int tmpsocosy1[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox1[] = { 54, 89, 123, 90, 54}; 
	int bloqueioy1[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx1[] = { 54, 89, 123, 90, 54}; 
	int forty1[] = { 119+160, 119+160, 151+160, 185+160, 185+160 }; 
	
	int tmpsocosx2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int tmpsocosy2[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int bloqueioy2[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int forty2[] = { 119+160, 119+160, 151+160, 185+160, 185+160 }; 
	
	int tmpsocosx3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int tmpsocosy3[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int bloqueioy3[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int forty3[] = { 119+160, 119+160, 151+160, 185+160, 185+160 }; 
	public int pontosH=0,pontosA=0;
	public boolean up,down,enter;
	BufferedImage fundo[]=new BufferedImage[3];
	BufferedImage icons[]=new BufferedImage[90];
	BufferedImage txt;
	public int mx,my, mcx,mcy;
	public boolean pause = false;
	
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
		File file=new File("save.txt");
		if(file.exists()) {
			saveExists=true;
		}else {
			saveExists=false;
		}
		for(int i=0;i<3;i++) {
			fundo[i]=Game.Menu.getSprite(0+(900*i), 0, 900, 900);
		}
		for(int i=0;i<6;i++) {
			icons[i+0]=Game.Menu.getSprite(900*5+133*i, 130*0, 133, 130);
			icons[i+6]=Game.Menu.getSprite(900*5+133*i, 130*1, 133, 130);
			icons[i+12]=Game.Menu.getSprite(900*5+133*i, 130*2, 133, 130);
			icons[i+18]=Game.Menu.getSprite(900*5+133*i, 130*3, 133, 130);
			icons[i+24]=Game.Menu.getSprite(900*5+133*i, 130*4, 133, 130);
			icons[i+30]=Game.Menu.getSprite(900*5+133*i, 130*5, 133, 130);
		}
		txt=Game.Menu.getSprite(0+(900*3), 0, 900, 900);
		pontosH=6;
		pontosA=6;
		if(!pause) {
			if(load) {
				//pra dele tar file=new File("save.txt"); file.delete();
				
				slot1=String.valueOf(file);
				if(soltou) {
					soltou=false;
					if(mx>720/3 && mx<720/3+100 && my>135 && my<135+20) {
						if(file.exists()) {
							String saver=loadGame(0);
							applySave(saver);
						}
					}else if(mx>720/3 && mx<720/3+100 && my>185 && my<185+20) {
						
					}else if(mx>720/3 && mx<720/3+100 && my>235 && my<125+20) {
						
					}else if(mx>583 && mx<583+62 && my>314 && my<314+24) {
						load=false;
					}else  {
						currentOption=0;
					}
				}else {
					if(mx>720/3 && mx<720/3+100 && my>135 && my<135+20) {
						currentOption=9;
					}else if(mx>720/3 && mx<720/3+100 && my>185 && my<185+20) {
						currentOption=10;
					}else if(mx>720/3 && mx<720/3+100 && my>235 && my<235+20) {
						currentOption=11;
					}else  if(mx>583 && mx<583+62 && my>314 && my<314+24) {
						currentOption=4;
					}else {
						currentOption=0;
					}
				}
			}else {
				if(opcoes) {
					if(soltou) {
						soltou=false;
						if(mx>583 && mx<583+62 && my>314 && my<314+24) {
							opcoes=false;
						}else if(mx>720/2 && mx<720/2+100 && my>85 && my<85+20) {
							currentOption=5;
							volume=volume2;
							
						}else if(mx>720/2 && mx<720/2+25 && my>135 && my<135+20) {
							if(sfx) {
								sfx=false;
							}else {
								sfx=true;
							}
						}else if(mx>720/2-2 && mx<720/2-2+29 && my>133+50 && my<133+50+24) {
							if(mus) {
								mus=false;
							}else {
								mus=true;
							}
						}else if(mx>720/3+100-10 && mx<720/3+100-10+80 && my>250-15 && my<250-15+20) {
							if(idioma=="English") {
								idioma="Portugues";
							}else if(idioma=="Portugues") {
								idioma="English";
							}
						}else {
							currentOption=0;
						}
					}else {

						int x= volume;
						if(mx>583 && mx<583+62 && my>314 && my<314+24) {
							currentOption=4;
						}else if(mx>720/2 && mx<720/2+100 && my>85 && my<85+20) {
							currentOption=5;
							volume2=mx-720/2;
							
						}else if(mx>720/2 && mx<720/2+25 && my>135 && my<135+20) {
							currentOption=6;
						}else if(mx>720/2-2 && mx<720/2-2+29 && my>133+50 && my<133+50+24) {
							currentOption=7;
						}else if(mx>720/3+100-10 && mx<720/3+100-10+80 && my>250-15 && my<250-15+20) {
							currentOption=8;
						}else {
							currentOption=0;
							volume=x;
						}
					}
					
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
			if(Menus[menus]=="Habilidades") {
				if(confirmacao) {
					if(clicou) {
						clicou=false;
						if(mcx>230 && mcx<230+40 && mcy>220 && mcy<220+20) {
							currentOption=13;
							pontosH--;
							if(golpe=="tempestade de socos") {
								Game.player.tmpSocos[nivel-1]=true;
								confirmacao=false;
							}else if(golpe=="bloquear") {
								Game.player.bloqueio[nivel-1]=true;
								confirmacao=false;
							}else if(golpe=="fortalecer") {
								Game.player.socoForte[nivel-1]=true;
								confirmacao=false;
							}
						}else if(mcx>370 && mcx<370+40 && mcy>220 && mcy<220+20) {
							confirmacao=false;
						}
					}else {
						if(mx>230 && mx<230+40 && my>220 && my<220+20) {
							currentOption=13;
						}else if(mx>370 && mx<370+40 && my>220 && my<220+20) {
							currentOption=14;
						}else {
							currentOption=0;
						}
					}
				}else {
					if(clicou) {
						clicou=false;
						if(mcx>54 && mcx<123 && mcy>119 && mcy<185) {
							//socos tempestade nv 1
							currentOption=3;
							if(!Game.player.tmpSocos[0]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="tempestade de socos";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119 && mcy<185 && Game.player.tmpSocos[0]) {
							//socos tempestade 2
							currentOption=4;
							if(!Game.player.tmpSocos[1]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="tempestade de socos";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119 && mcy<185 && Game.player.tmpSocos[1]) {
							//socos tempestade 3
							currentOption=5;
							if(!Game.player.tmpSocos[2]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="tempestade de socos";
									nivel=3;
								}
							}
						}else if(mcx>54 && mcx<123 && mcy>119+80 && mcy<185+81) {
							//bloquear 1
							currentOption=6;
							if(!Game.player.bloqueio[0]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="bloquear";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119+80 && mcy<185+81 && Game.player.bloqueio[0]) {
							//bloquear 2
							currentOption=7;
							if(!Game.player.bloqueio[1]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="bloquear";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119+80 && mcy<185+81 && Game.player.bloqueio[1]) {
							//bloquear 3
							currentOption=8;
							if(!Game.player.bloqueio[2]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="bloquear";
									nivel=3;
								}
							}
						}else if(mcx>54 && mcx<123 && mcy>119+161 && mcy<185+161) {
							//fortalecer 1 
							currentOption=9;
							if(!Game.player.socoForte[0]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="fortalecer";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119+161 && mcy<185+161 && Game.player.socoForte[0]) {
							//fortalecer 2
							currentOption=10;
							if(!Game.player.socoForte[1]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="fortalecer";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119+161 && mcy<185+161 && Game.player.socoForte[1]) {
							//fortalecer  3
							currentOption=11;
							if(!Game.player.socoForte[2]) {
								if(pontosH>0) {
									confirmacao=true;
									golpe="fortalecer";
									nivel=3;
								}
							}
						}else if(mcx>45 && mcx<70+45 && mcy>10 && mcy<30+10){
							//opçoes
							currentOption=1;
							menus=3;
						}else if(mcx>620 && mcx<75+620 && mcy>10 && mcy<30+10){
							//atributos
							menus=1;
							currentOption=2;
						}else if(mcx>615 && mcx<43+615 && mcy>315 && mcy<315+20){
							pause=false;
							Game.gameState = "NORMAL";
						}else {
							currentOption=0;
						}
					}else {
						if(mx>54 && mx<123 && my>119 && my<185) {
							//socos tempestade 1
							currentOption=3;
						}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.tmpSocos[0]) {
							//socos tempestade 2
							currentOption=4;
						}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.tmpSocos[1]) {
							//socos tempestade 3
							currentOption=5;
						}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
							//bloquear1
							currentOption=6;
						}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.bloqueio[0]) {
							//bloquear2
							currentOption=7;
						}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.bloqueio[1]) {
							//bloquear3
							currentOption=8;
						}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
							//fortalecer 1
							currentOption=9;
						}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.socoForte[0]) {
							//fortalecer 2
							currentOption=10;
						}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.socoForte[1]) {
							//fortalecer 3
							currentOption=11;
						}else if(mx>45 && mx<70+45 && my>10 && my<30+10){
							//opçoes
							currentOption=1;
						}else if(mx>620 && mx<75+620 && my>10 && my<30+10){
							//atributos
							currentOption=2;
						}else if(mx>615 && mx<43+615 && my>315 && my<315+20){
							currentOption=12;
						}else {
							currentOption=0;
						}
					}
				}
				
			}else if(Menus[menus]=="Atributos") {
				if(confirmacao) {
					if(clicou) {
						clicou=false;
						if(mcx>230 && mcx<230+40 && mcy>220 && mcy<220+20) {
							currentOption=13;
							pontosA--;
							if(golpe=="força") {
								Game.player.forca[nivel-1]=true;
								confirmacao=false;
							}else if(golpe=="habilidade") {
								Game.player.habilidade[nivel-1]=true;
								confirmacao=false;
							}else if(golpe=="defesa") {
								Game.player.defesa[nivel-1]=true;
								confirmacao=false;
							}
						}else if(mcx>370 && mcx<370+40 && mcy>220 && mcy<220+20) {
							confirmacao=false;
						}
					}else {
						if(mx>230 && mx<230+40 && my>220 && my<220+20) {
							currentOption=13;
						}else if(mx>370 && mx<370+40 && my>220 && my<220+20) {
							currentOption=14;
						}else {
							currentOption=0;
						}
					}
				}else {
					if(clicou) {
						clicou=false;
						if(mcx>54 && mcx<123 && mcy>119 && mcy<185) {
							//soco nv 1
							currentOption=3;
							if(!Game.player.forca[0]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="força";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119 && mcy<185 && Game.player.forca[0]) {
							//soco nv 2
							currentOption=4;
							if(!Game.player.forca[1]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="força";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119 && mcy<185 && Game.player.forca[1]) {
							//ssoco nv 3
							currentOption=5;
							if(!Game.player.forca[2]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="força";
									nivel=3;
								}
							}
						}else if(mcx>54 && mcx<123 && mcy>119+80 && mcy<185+81) {
							//habilidades
							currentOption=6;
							if(!Game.player.habilidade[0]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="habilidade";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119+80 && mcy<185+81 && Game.player.habilidade[0]) {
							//HABILIDADE 2
							currentOption=7;
							if(!Game.player.habilidade[1]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="habilidade";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119+80 && mcy<185+81 && Game.player.habilidade[1]) {
							//habilida de 3
							currentOption=8;
							if(!Game.player.habilidade[2]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="habilidade";
									nivel=3;
								}
							}
						}else if(mcx>54 && mcx<123 && mcy>119+161 && mcy<185+161) {
							//defesa 1 
							currentOption=9;
							if(!Game.player.defesa[0]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="defesa";
									nivel=1;
								}
							}
						}else if(mcx>54+80 && mcx<123+80 && mcy>119+161 && mcy<185+161 && Game.player.defesa[0]) {
							//defesa 2
							currentOption=10;
							if(!Game.player.defesa[1]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="defesa";
									nivel=2;
								}
							}
						}else if(mcx>54+160 && mcx<123+160 && mcy>119+161 && mcy<185+161 && Game.player.defesa[1]) {
							//defesa 3
							currentOption=11;
							if(!Game.player.defesa[2]) {
								if(pontosA>0) {
									confirmacao=true;
									golpe="defesa";
									nivel=3;
								}
							}
						}else if(mcx>45 && mcx<90+45 && mcy>10 && mcy<30+10){
							//habilidade
							currentOption=1;
							menus=0;
						}else if(mcx>580 && mcx<580+110 && mcy>10 && mcy<30+10){
							//apersonafgens
							menus=2;
							currentOption=2;
						}else if(mcx>615 && mcx<43+615 && mcy>315 && mcy<315+20){
							pause=false;
							Game.gameState = "NORMAL";
						}else {
							currentOption=0;
						}
					}else {
						if(mx>54 && mx<123 && my>119 && my<185) {
							//força
							currentOption=3;
						}else if(mx>54+80 && mx<123+80 && my>119 && my<185 && Game.player.forca[0]) {
							//força
							currentOption=4;
						}else if(mx>54+160 && mx<123+160 && my>119 && my<185 && Game.player.forca[1]) {
							///força
							currentOption=5;
						}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
							//habilidade
							currentOption=6;
						}else if(mx>54+80 && mx<123+80 && my>119+80 && my<185+81 && Game.player.habilidade[0]) {
							//habilidade
							currentOption=7;
						}else if(mx>54+160 && mx<123+160 && my>119+80 && my<185+81 && Game.player.habilidade[1]) {
							//habilidade
							currentOption=8;
						}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
							//defesa
							currentOption=9;
						}else if(mx>54+80 && mx<123+80 && my>119+161 && my<185+161 && Game.player.defesa[0]) {
							//defesa
							currentOption=10;
						}else if(mx>54+160 && mx<123+160 && my>119+161 && my<185+161 && Game.player.defesa[1]) {
							//defesa
							currentOption=11;
						}else if(mx>45 && mx<90+45 && my>10 && my<30+10){
							currentOption=1;
						}else if(mx>580 && mx<110+580 && my>10 && my<30+10){
							currentOption=2;
						}else if(mx>615 && mx<43+615 && my>315 && my<315+20){
							currentOption=12;
						}else {
							currentOption=0;
						}
					}
				}
			}else if(Menus[menus]=="Personagens") {
				if(clicou) {
					clicou=false;
					if(mcx>45 && mcx<90+45 && mcy>10 && mcy<30+10){
						//atributos
						currentOption=1;
						menus=1;
					}else if(mcx>580 && mcx<90+580 && mcy>10 && mcy<30+10){
						//opçoes
						menus=3;
						currentOption=2;
					}else{
						currentOption=0;
					}
				}else {
					if(mx>45 && mx<90+45 && my>10 && my<30+10){
						//atributos
						currentOption=1;
					}else if(mx>580 && mx<90+580 && my>10 && my<30+10){
						//opçoes
						currentOption=2;
					}else{
						currentOption=0;
					}
				}
			}else if(Menus[menus]=="Opções") {
				if(clicou) {
					clicou=false;
					if(mcx>45 && mcx<100+45 && mcy>10 && mcy<30+10){
						//personagens
						currentOption=1;
						menus=2;
					}else if(mcx>580 && mcx<100+580 && mcy>10 && mcy<30+10){
						//habilidades
						menus=0;
						currentOption=2;
					}else{
						currentOption=0;
					}
				}else {
					if(mx>45 && mx<100+45 && my>10 && my<30+10){
						//personagens
						currentOption=1;
					}else if(mx>580 && mx<100+580 && my>10 && my<30+10){
						//habilidades
						currentOption=2;
					}else{
						currentOption=0;
					}
				}
			}
		}	
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(new Color(0,0,0,100));
		//g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		// x coordinates of vertices 
		if(pause) {
			g.drawImage(fundo[0], 0,0,900,900/2, null);
			
			
			
			
			
			
			
	        
	        
	        
	        
	        
	        
			if(Menus[menus]=="Habilidades") {
				g.setColor(Color.red); 
				if(currentOption==0) {
					//vazio
				}else if(currentOption==1) {
					g.drawRect(45, 10, 70, 30);
				}else if(currentOption==2) {
					g.drawRect(620, 10, 75, 30);
				}else if(currentOption==3) {
					g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
				}else if(currentOption==4) {
					g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
				}else if(currentOption==5) {
					g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
				}else if(currentOption==6) {
					g.fillPolygon(bloqueiox1, bloqueioy1, 5);
				}else if(currentOption==7) {
					g.fillPolygon(bloqueiox2, bloqueioy2, 5);
				}else if(currentOption==8) {
					g.fillPolygon(bloqueiox3, bloqueioy3, 5);
				}else if(currentOption==9) {
					g.fillPolygon(fortx1, forty1, 5);
				}else if(currentOption==10) {
					g.fillPolygon(fortx2, forty2, 5);
				}else if(currentOption==11) {
					g.fillPolygon(fortx3, forty3, 5);
				}else if(currentOption==12) {
					g.drawRect(615, 315, 43, 20);
				}
				if(!Game.player.tmpSocos[0]) {
					g.drawImage(icons[0], 55,120,133/2, 130/2, null);
				}else {
					g.drawImage(icons[3], 55,120,133/2, 130/2, null);
					if(!Game.player.tmpSocos[1]) {
						g.drawImage(icons[1], 55+80,120,133/2, 130/2, null);
					}else {
						g.drawImage(icons[4], 55+80,120,133/2, 130/2, null);
						if(!Game.player.tmpSocos[2]) {
							g.drawImage(icons[2], 55+160,120,133/2, 130/2, null);
						}else {
							g.drawImage(icons[5], 55+160,120,133/2, 130/2, null);
						}
					}
				}
				if(!Game.player.bloqueio[0]) {
					g.drawImage(icons[6], 55,200,133/2, 130/2, null);
				}else {
					g.drawImage(icons[9], 55,200,133/2, 130/2, null);
					if(!Game.player.bloqueio[1]) {
						g.drawImage(icons[7], 55+80,200,133/2, 130/2, null);
					}else {
						g.drawImage(icons[10], 55+80,200,133/2, 130/2, null);
						if(!Game.player.bloqueio[2]) {
							g.drawImage(icons[8], 55+160,200,133/2, 130/2, null);
						}else {
							g.drawImage(icons[11], 55+160,200,133/2, 130/2, null);
						}
					}
				}
				if(!Game.player.socoForte[0]) {
					g.drawImage(icons[12], 55,280,133/2, 130/2, null);
				}else {
					g.drawImage(icons[15], 55,280,133/2, 130/2, null);
					if(!Game.player.socoForte[1]) {
						g.drawImage(icons[13], 55+80,280,133/2, 130/2, null);
					}else {
						g.drawImage(icons[16], 55+80,280,133/2, 130/2, null);
						if(!Game.player.socoForte[2]) {
							g.drawImage(icons[14], 55+160,280,133/2, 130/2, null);
						}else {
							g.drawImage(icons[17], 55+160,280,133/2, 130/2, null);
						}
					}
				}
				if((Habilidades[currentOption]=="Vazio" || Habilidades[currentOption]=="Next" || Habilidades[currentOption]=="Back"
						|| Habilidades[currentOption]=="Voltar") && !confirmacao) {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 185);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 165);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Tai se enfurece em combate com", 485, 85);
					g.drawString("inimigos, recebendo resistencias", 485, 110);
					g.drawString("e dano de ataque por acumulo", 485, 135);
					g.drawString("de furia.", 485, 160);
					g.drawString("Furia também pode ser usada", 485, 185);
					g.drawString("para usar habilidades.", 485, 210);
				}else if(Habilidades[currentOption]=="Tempestade de Socos1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 185);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 165);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Tempestade de socos nivel 1:", 485, 85);
					g.drawString("Tai dispara uma rajada de socos", 485, 110);
					g.drawString("a sua frente, causando dano a", 485, 135);
					g.drawString("todos os inimigos nessa area.", 485, 160);
					g.drawString("Custa furia para ser usado.", 485, 185);
					g.drawString("Presione ??para usar.", 485, 210);
				}else if(Habilidades[currentOption]=="Tempestade de Socos2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 85);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 65);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Tempestade de socos nivel 2:", 485, 85);
					g.drawString("Aumenta o alcance da habilidade.", 485, 110);
				}else if(Habilidades[currentOption]=="Tempestade de Socos3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 160);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 140);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Tempestade de socos nivel 3:", 485, 85);
					g.drawString("Apos usar essa habilidade, Tai", 485, 110);
					g.drawString("recebe um escudo baseado no ", 485, 135);
					g.drawString("dano causado.", 485, 160);
					g.drawString("O escudo dura 5 segundos.", 485, 185);
				}else if(Habilidades[currentOption]=="Bloquear1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 185);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 165);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Bloquear nivel 1:", 485, 85);
					g.drawString("Tai ganha a habilidade de repelir", 485, 110);
					g.drawString("um ataque inimigo.", 485, 135);
					g.drawString("Custa furia apenas se usado", 485, 160);
					g.drawString("contra um golpe inimigo.", 485, 185);
					g.drawString("Para usar pressione |R| .", 485, 210);
				}else if(Habilidades[currentOption]=="Bloquear2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 185);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 165);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Bloquear nivel 2:", 485, 85);
					g.drawString("Tai reune sua furia e lança um ", 485, 110);
					g.drawString("golpe em área que expulsa os", 485, 135);
					g.drawString("inimigos proximos.", 485, 160);
					g.drawString("Aumenta o custo em furia.", 485, 185);
					g.drawString("Perde a habilidade de bloquear.", 485, 210);
				}else if(Habilidades[currentOption]=="Bloquear3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 185);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 165);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Bloquear nivel 3:", 485, 85);
					g.drawString("Tai eleva sua furia ao maximo ", 485, 110);
					g.drawString("aumentando o dano de repelir.", 485, 135);
					g.drawString("Destroi o escudo equipado e", 485, 160);
					g.drawString("se cura no valor restante do", 485, 185);
					g.drawString("escudo.", 485, 210);
				}else if(Habilidades[currentOption]=="Fortalecer1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 225);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 205);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Fortalecer nivel 1:", 485, 85);
					g.drawString("Tai fortalece o seu proximo golpe ", 485, 110);
					g.drawString("fazendo causar o dobro de dano.", 485, 135);
					g.drawString("Custa furia para ser usado.", 485, 160);
					g.drawString("Se a barra de furia estiver cheia", 485, 185);
					g.drawString("fortalecer é automaticamente", 485, 210);
					g.drawString("usado.", 485, 235);
					g.drawString("Para usar pressione |?|.", 485, 260);
				}else if(Habilidades[currentOption]=="Fortalecer2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 160);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 140);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Fortalecer nivel 2:", 485, 85);
					g.drawString("Atacar usando fortalecer agora", 485, 110);
					g.drawString("gera um escudo baseado no ", 485, 135);
					g.drawString("dano causado.", 485, 160);
					g.drawString("O escudo dura 2 segundos.", 485, 185);
				}else if(Habilidades[currentOption]=="Fortalecer3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 205);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 185);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Fortalecer nivel 3:", 485, 85);
					g.drawString("Fortalecer deixa de custar furia.", 485, 110);
					g.drawString("Tai usa passivamente os", 485, 135);
					g.drawString("atributos de fortalecer a cada 3", 485, 160);
					g.drawString("ataques.", 485, 185);
					g.drawString("Tai recebe menos dano de", 485, 210);
					g.drawString("oponentes que não esta atacando.", 485, 235);
				}else if(confirmacao) {
					if(golpe=="tempestade de socos") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 185);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 165);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Tempestade de socos nivel 1:", 485, 85);
							g.drawString("Tai dispara uma rajada de socos", 485, 110);
							g.drawString("a sua frente, causando dano a", 485, 135);
							g.drawString("todos os inimigos nessa area.", 485, 160);
							g.drawString("Custa furia para ser usado.", 485, 185);
							g.drawString("Presione ??para usar.", 485, 210);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 85);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 65);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Tempestade de socos nivel 2:", 485, 85);
							g.drawString("Aumenta o alcance da habilidade.", 485, 110);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 160);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 140);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Tempestade de socos nivel 3:", 485, 85);
							g.drawString("Apos usar essa habilidade, Tai", 485, 110);
							g.drawString("recebe um escudo baseado no ", 485, 135);
							g.drawString("dano causado.", 485, 160);
							g.drawString("O escudo dura 5 segundos.", 485, 185);
						}
						
					}else if(golpe=="bloquear") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 185);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 165);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Bloquear nivel 1:", 485, 85);
							g.drawString("Tai ganha a habilidade de repelir", 485, 110);
							g.drawString("um ataque inimigo.", 485, 135);
							g.drawString("Custa furia apenas se usado", 485, 160);
							g.drawString("contra um golpe inimigo.", 485, 185);
							g.drawString("Para usar pressione |R| .", 485, 210);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 185);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 165);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Bloquear nivel 2:", 485, 85);
							g.drawString("Tai reune sua furia e lança um ", 485, 110);
							g.drawString("golpe em área que expulsa os", 485, 135);
							g.drawString("inimigos proximos.", 485, 160);
							g.drawString("Aumenta o custo em furia.", 485, 185);
							g.drawString("Perde a habilidade de bloquear.", 485, 210);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 185);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 165);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Bloquear nivel 3:", 485, 85);
							g.drawString("Tai eleva sua furia ao maximo ", 485, 110);
							g.drawString("aumentando o dano de repelir.", 485, 135);
							g.drawString("Destroi o escudo equipado e", 485, 160);
							g.drawString("se cura no valor restante do", 485, 185);
							g.drawString("escudo.", 485, 210);
						}
					}else if(golpe=="fortalecer") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 225);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 205);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Fortalecer nivel 1:", 485, 85);
							g.drawString("Tai fortalece o seu proximo golpe ", 485, 110);
							g.drawString("fazendo causar o dobro de dano.", 485, 135);
							g.drawString("Custa furia para ser usado.", 485, 160);
							g.drawString("Se a barra de furia estiver cheia", 485, 185);
							g.drawString("fortalecer é automaticamente", 485, 210);
							g.drawString("usado.", 485, 235);
							g.drawString("Para usar pressione |?|.", 485, 260);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 160);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 140);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Fortalecer nivel 2:", 485, 85);
							g.drawString("Atacar usando fortalecer agora", 485, 110);
							g.drawString("gera um escudo baseado no ", 485, 135);
							g.drawString("dano causado.", 485, 160);
							g.drawString("O escudo dura 2 segundos.", 485, 185);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 205);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 185);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Fortalecer nivel 3:", 485, 85);
							g.drawString("Fortalecer deixa de custar furia.", 485, 110);
							g.drawString("Tai usa passivamente os", 485, 135);
							g.drawString("atributos de fortalecer a cada 3", 485, 160);
							g.drawString("ataques.", 485, 185);
							g.drawString("Tai recebe menos dano de", 485, 210);
							g.drawString("oponentes que não esta atacando.", 485, 235);
						}
					}
				}
				
				

				g.setFont(new Font("arial",Font.BOLD,12));
				g.setColor(Color.black);
				g.drawString("<= Opções", 50, 30);
				g.drawString("Atributos =>", 625,30);
				
				g.drawString("Sair", 625,330);
				
				
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("Habilidades:", 50, 70);
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,15));
				g.drawString("Pontos disponiveis: "+pontosH, 50, 100);
				if(confirmacao) {
					g.setColor(Color.red);
					g.fillRect(200,140, 260, 120);
					g.setColor(Color.WHITE);
					g.fillRect(210,150, 240, 100);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Gastar 1 ponto de habilidade para ", 215, 165);
					g.drawString("melhorar "+golpe+" para", 215, 185);
					g.drawString("o nivel "+nivel+"? (Não pode se desfeito)", 215, 205);
					g.drawString("Sim", 240, 235);
					g.drawString("Não", 380, 235);
					g.setColor(Color.red);
					if(currentOption==13) {
						g.drawRect(230, 220, 40, 20);
					}else if(currentOption==14) {
						g.drawRect(370, 220, 40, 20);
					}
					
				}
			}else if(Menus[menus]=="Atributos") {
				
				g.setColor(Color.red); 
				if(currentOption==0) {
					//vazio
				}else if(currentOption==1) {
					g.drawRect(45, 10, 90, 30);
				}else if(currentOption==2) {
					g.drawRect(580, 10, 110, 30);
				}else if(currentOption==3) {
					g.fillPolygon(tmpsocosx1, tmpsocosy1, 5);
				}else if(currentOption==4) {
					g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
				}else if(currentOption==5) {
					g.fillPolygon(tmpsocosx3, tmpsocosy3, 5);
				}else if(currentOption==6) {
					g.fillPolygon(bloqueiox1, bloqueioy1, 5);
				}else if(currentOption==7) {
					g.fillPolygon(bloqueiox2, bloqueioy2, 5);
				}else if(currentOption==8) {
					g.fillPolygon(bloqueiox3, bloqueioy3, 5);
				}else if(currentOption==9) {
					g.fillPolygon(fortx1, forty1, 5);
				}else if(currentOption==10) {
					g.fillPolygon(fortx2, forty2, 5);
				}else if(currentOption==11) {
					g.fillPolygon(fortx3, forty3, 5);
				}else if(currentOption==12) {
					g.drawRect(615, 315, 43, 20);
				}
				if(!Game.player.forca[0]) {
					g.drawImage(icons[18], 55,120,133/2, 130/2, null);
				}else {
					g.drawImage(icons[21], 55,120,133/2, 130/2, null);
					if(!Game.player.forca[1]) {
						g.drawImage(icons[19], 55+80,120,133/2, 130/2, null);
					}else {
						g.drawImage(icons[22], 55+80,120,133/2, 130/2, null);
						if(!Game.player.forca[2]) {
							g.drawImage(icons[20], 55+160,120,133/2, 130/2, null);
						}else {
							g.drawImage(icons[23], 55+160,120,133/2, 130/2, null);
						}
					}
				}
				if(!Game.player.habilidade[0]) {
					g.drawImage(icons[24], 55,200,133/2, 130/2, null);
				}else {
					g.drawImage(icons[27], 55,200,133/2, 130/2, null);
					if(!Game.player.habilidade[1]) {
						g.drawImage(icons[25], 55+80,200,133/2, 130/2, null);
					}else {
						g.drawImage(icons[28], 55+80,200,133/2, 130/2, null);
						if(!Game.player.habilidade[2]) {
							g.drawImage(icons[26], 55+160,200,133/2, 130/2, null);
						}else {
							g.drawImage(icons[29], 55+160,200,133/2, 130/2, null);
						}
					}
				}
				if(!Game.player.defesa[0]) {
					g.drawImage(icons[30], 55,280,133/2, 130/2, null);
				}else {
					g.drawImage(icons[33], 55,280,133/2, 130/2, null);
					if(!Game.player.defesa[1]) {
						g.drawImage(icons[31], 55+80,280,133/2, 130/2, null);
					}else {
						g.drawImage(icons[34], 55+80,280,133/2, 130/2, null);
						if(!Game.player.defesa[2]) {
							g.drawImage(icons[32], 55+160,280,133/2, 130/2, null);
						}else {
							g.drawImage(icons[35], 55+160,280,133/2, 130/2, null);
						}
					}
				}
				if((Habilidades[currentOption]=="Vazio" || Habilidades[currentOption]=="Next" || Habilidades[currentOption]=="Back"
						|| Habilidades[currentOption]=="Voltar") && !confirmacao) {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 120);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 100);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Melhorar os atributos de Tai em", 485, 85);
					g.drawString("três pontos, força, habilidade ", 485, 110);
					g.drawString("e defesa.", 485, 135);
				}else if(Habilidades[currentOption]=="Tempestade de Socos1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 90);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 70);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Força nivel 1:", 485, 85);
					g.drawString("Aumenta o dano de ataque.", 485, 110);
				}else if(Habilidades[currentOption]=="Tempestade de Socos2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 85);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 65);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Força nivel 2:", 485, 85);
					g.drawString("Aumenta o ganho de furia.", 485, 110);
				}else if(Habilidades[currentOption]=="Tempestade de Socos3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 110);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 90);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Força nivel 3:", 485, 85);
					g.drawString("Aumenta o dano recebido por", 485, 110);
					g.drawString("acumulo de furia.", 485, 135);
				}else if(Habilidades[currentOption]=="Bloquear1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 90);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 70);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Habilidade nivel 1:", 485, 85);
					g.drawString("Habilidades custam menos furia.", 485, 110);
				}else if(Habilidades[currentOption]=="Bloquear2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 90);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 70);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Habilidade nivel 2:", 485, 85);
					g.drawString("Habilidades causam mais dano. ", 485, 110);
				}else if(Habilidades[currentOption]=="Bloquear3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 100);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 80);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Habilidade nivel 3:", 485, 85);
					g.drawString("Habilidades deixam de custar  ", 485, 110);
					g.drawString("furia.", 485, 135);
				}else if(Habilidades[currentOption]=="Fortalecer1") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 90);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 70);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Defesa nivel 1:", 485, 85);
					g.drawString("Aumenta a defesa base. ", 485, 110);
				}else if(Habilidades[currentOption]=="Fortalecer2") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 110);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 90);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Defesa nivel 2:", 485, 85);
					g.drawString("Aumenta a defesa recebida por", 485, 110);
					g.drawString("acumulo de furia.", 485, 135);
				}else if(Habilidades[currentOption]=="Fortalecer3") {
					g.setColor(Color.black);
					g.fillRect(470,50, 220, 100);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 80);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Defesa nivel 3:", 485, 85);
					g.drawString("Aumenta a eficiencia de escudos.", 485, 110);
				}else if(confirmacao) {
					if(golpe=="força") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 90);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 70);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Força nivel 1:", 485, 85);
							g.drawString("Aumenta o dano de ataque.", 485, 110);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 85);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 65);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Força nivel 2:", 485, 85);
							g.drawString("Aumenta o ganho de furia.", 485, 110);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 110);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 90);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Força nivel 3:", 485, 85);
							g.drawString("Aumenta o dano recebido por", 485, 110);
							g.drawString("acumulo de furia.", 485, 135);
						}
						
					}else if(golpe=="habilidade") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 90);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 70);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Habilidade nivel 1:", 485, 85);
							g.drawString("Habilidades custam menos furia.", 485, 110);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 90);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 70);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Habilidade nivel 2:", 485, 85);
							g.drawString("Habilidades causam mais dano. ", 485, 110);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 100);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 80);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Habilidade nivel 3:", 485, 85);
							g.drawString("Habilidades deixam de custar  ", 485, 110);
							g.drawString("furia.", 485, 135);
						}
					}else if(golpe=="defesa") {
						if(nivel==1) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 90);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 70);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Defesa nivel 1:", 485, 85);
							g.drawString("Aumenta a defesa base. ", 485, 110);
						}else if(nivel==2) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 110);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 90);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Defesa nivel 2:", 485, 85);
							g.drawString("Aumenta a defesa recebida por", 485, 110);
							g.drawString("acumulo de furia.", 485, 135);
						}else if(nivel==3) {
							g.setColor(Color.black);
							g.fillRect(470,50, 220, 100);
							g.setColor(Color.WHITE);
							g.fillRect(480,60, 200, 80);
							g.setFont(new Font("arial",Font.BOLD,12));
							g.setColor(Color.black);
							g.drawString("Defesa nivel 3:", 485, 85);
							g.drawString("Aumenta a eficiencia de escudos.", 485, 110);						}
					}
				}
				
				
				
				g.setFont(new Font("arial",Font.BOLD,12));
				g.setColor(Color.black);
				g.drawString("<= Habilidades", 50, 30);
				g.drawString("Personagens =>", 590,30);
				
				g.drawString("Sair", 625,330);
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("Atributos:", 50, 70);
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,15));
				g.drawString("Pontos disponiveis: "+pontosA, 50, 100);
				if(confirmacao) {
					g.setColor(Color.red);
					g.fillRect(200,140, 260, 120);
					g.setColor(Color.WHITE);
					g.fillRect(210,150, 240, 100);
					g.setFont(new Font("arial",Font.BOLD,12));
					g.setColor(Color.black);
					g.drawString("Gastar 1 ponto de atributo para ", 215, 165);
					g.drawString("melhorar "+golpe+" para o nivel "+nivel+"?", 215, 185);
					g.drawString("(Não pode se desfeito)", 215, 205);
					g.drawString("Sim", 240, 235);
					g.drawString("Não", 380, 235);
					g.setColor(Color.red);
					if(currentOption==13) {
						g.drawRect(230, 220, 40, 20);
					}else if(currentOption==14) {
						g.drawRect(370, 220, 40, 20);
					}
					
				}
			}else if(Menus[menus]=="Personagens") {
				
				g.setColor(Color.red); 
				if(currentOption==0) {
					//vazio
				}else if(currentOption==1) {
					g.drawRect(45, 10, 90, 30);
				}else if(currentOption==2) {
					g.drawRect(580, 10, 90, 30);
				}
				
				g.setFont(new Font("arial",Font.BOLD,12));
				g.setColor(Color.black);
				g.drawString("<= Atributos", 50, 30);
				g.drawString("Opções =>", 590,30);
				
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("Personagens:", 50, 70);
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,15));
			}else if(Menus[menus]=="Opções") {
				
				g.setColor(Color.red); 
				if(currentOption==0) {
					//vazio
				}else if(currentOption==1) {
					g.drawRect(45, 10, 100, 30);
				}else if(currentOption==2) {
					g.drawRect(580, 10, 100, 30);
				}
				
				g.setFont(new Font("arial",Font.BOLD,12));
				g.setColor(Color.black);
				g.drawString("<= Personagens", 50, 30);
				g.drawString("Habilidades =>", 590,30);
				
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("Opções:", 50, 70);
				g.setColor(Color.black);

				g.setFont(new Font("arial",Font.BOLD,15));
				g.drawString("Continuar", 250,130);
				g.drawString("Reiniciar do ultimo checkpoint", 250,160);
				g.drawString("Reiniciar nivel", 250,190);
				g.drawString("Idioma", 250,220);
				g.drawString("Sair para o menu principal", 250,250);
				
			}
			
			
			
			
			
			
			
		}else {
			posx++;
			if(posx==2976-1440) {
				posx=0;
			}
			Color Standart= new Color(255,0,0);
			Color MouseOver= new Color(200,0,0);
			Color Pressed= new Color(150,0,0);
			Color beje= new Color(247,212,212);
			g.drawImage(Game.fundo.getSprite(posx, posy, 1440, 720), 0, 0,720,360, null);
			if(load) {
				g.setColor(Color.black);
				g.fillRoundRect(720/3-40,65, 260+20, 200+20, 30, 50);
				g.setColor(beje);
				g.fillRoundRect(720/3-30,75, 260, 200, 30, 50);
				
				
				
				
				
				
				
				g.setColor(Color.black);
				g.fillRoundRect(580,312, 68, 28, 30, 50);
				
				if(MenuPrincipal[currentOption]=="Sair"&& clicou) {
					g.setColor(Pressed);
					g.fillRoundRect(583,314, 62, 24, 30, 50);
				}else if(MenuPrincipal[currentOption]=="Sair") {
					g.setColor(MouseOver);
					g.fillRoundRect(583,314, 62, 24, 30, 50);
				}else {
					g.setColor(Standart);
					g.fillRoundRect(583,314, 62, 24, 30, 50);
				}
				
				g.setColor(Color.black);
				if(idioma=="Portugues") {
					g.drawString("Carregar Jogo",720/3+55, 95);
					g.setColor(Color.black);
					g.drawString("Voltar", 599, 330);
					if(slot1=="Empty.") {
						slot1="Vazio.";
					}
					if(slot2=="Empty.") {
						slot2="Vazio.";
					}
					if(slot3=="Empty.") {
						slot3="Vazio.";
					}
				}else {
					if(slot1=="Vazio.") {
						slot1="Empty.";
					}
					if(slot2=="Vazio.") {
						slot2="Empty.";
					}
					if(slot3=="Vazio.") {
						slot3="Empty.";
					}
					g.drawString("Load Game",720/3+55, 95);
					g.setColor(Color.black);
					g.drawString("Back", 599, 330);
				}
				
				if(MenuPrincipal[currentOption]=="Slot1"&& clicou) {
					g.setColor(Pressed);
					g.drawString(slot1, 720/3, 150);
				}else if(MenuPrincipal[currentOption]=="Slot1") {
					g.setColor(MouseOver);
					g.drawString(slot1, 720/3, 150);
				}else {
					g.setColor(Standart);
					g.drawString(slot1, 720/3, 150);
				}
				if(MenuPrincipal[currentOption]=="Slot2"&& clicou) {
					g.setColor(Pressed);
					g.drawString(slot2,720/3, 200);
				}else if(MenuPrincipal[currentOption]=="Slot2") {
					g.setColor(MouseOver);
					g.drawString(slot2,720/3, 200);
				}else {
					g.setColor(Standart);
					g.drawString(slot2,720/3, 200);
				}
				if(MenuPrincipal[currentOption]=="Slot3"&& clicou) {
					g.setColor(Pressed);
					g.drawString(slot3,720/3, 250);
				}else if(MenuPrincipal[currentOption]=="Slot3") {
					g.setColor(MouseOver);
					g.drawString(slot3,720/3, 250);
				}else {
					g.setColor(Standart);
					g.drawString(slot3,720/3, 250);
				}
			}else {
				if(opcoes) {
					g.setColor(Color.black);
					g.fillRoundRect(720/3-40,65, 260+20, 200+20, 30, 50);
					g.setColor(beje);
					g.fillRoundRect(720/3-30,75, 260, 200, 30, 50);
					
					
					//volume
					g.setColor(Color.black);
					g.fillRect(720/2,85, 99, 20);
					
					if(MenuPrincipal[currentOption]=="Volume") {
						g.setColor(MouseOver);
						g.fillRect(720/2,85, volume2, 20);
					}else {
						g.setColor(Standart);
						g.fillRect(720/2,85, volume, 20);
					}
					
					
					g.setColor(Color.black);
					g.fillRoundRect(580,312, 68, 28, 30, 50);
					
					if(MenuPrincipal[currentOption]=="Sair"&& clicou) {
						g.setColor(Pressed);
						g.fillRoundRect(583,314, 62, 24, 30, 50);
					}else if(MenuPrincipal[currentOption]=="Sair") {
						g.setColor(MouseOver);
						g.fillRoundRect(583,314, 62, 24, 30, 50);
					}else {
						g.setColor(Standart);
						g.fillRoundRect(583,314, 62, 24, 30, 50);
					}
					g.setColor(Color.black);
					g.fillRoundRect(720/2-2,133, 29, 24, 30, 50);
					if(sfx) {
						if(MenuPrincipal[currentOption]=="Efeitos"&& clicou) {
							g.setColor(Pressed);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}else if(MenuPrincipal[currentOption]=="Efeitos") {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}else {
							g.setColor(Standart);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}
					}else {
						if(MenuPrincipal[currentOption]=="Efeitos"&& clicou) {
							g.setColor(Pressed);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}else if(MenuPrincipal[currentOption]=="Efeitos") {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}else {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,135, 25, 20, 30, 50);
						}
					}
					g.setColor(Color.black);
					if(!sfx) {
						g.drawString("On",720/3+124, 150);
					}else {
						g.drawString("Off",720/3+124, 150);
					}
					g.setColor(Color.black);
					g.fillRoundRect(720/2-2,133+50, 29, 24, 30, 50);
					if(mus) {
						if(MenuPrincipal[currentOption]=="Musica"&& clicou) {
							g.setColor(Pressed);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}else if(MenuPrincipal[currentOption]=="Musica") {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}else {
							g.setColor(Standart);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}
					}else {
						if(MenuPrincipal[currentOption]=="Musica"&& clicou) {
							g.setColor(Pressed);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}else if(MenuPrincipal[currentOption]=="Musica") {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}else {
							g.setColor(MouseOver);
							g.fillRoundRect(720/2,185, 25, 20, 30, 50);
						}
					}
					g.setColor(Color.black);
					if(!mus) {
						g.drawString("On",720/3+124, 200);
					}else {
						g.drawString("Off",720/3+124, 200);
					}
					if(MenuPrincipal[currentOption]=="Idioma"&& clicou) {
						g.setColor(Pressed);
						g.drawString(idioma,720/3+100+20, 250);
					}else if(MenuPrincipal[currentOption]=="Idioma") {
						g.setColor(MouseOver);
						g.drawString(idioma,720/3+100+20, 250);
					}else {
						g.setColor(Standart);
						g.drawString(idioma,720/3+100+20, 250);
					}
					g.setColor(Color.black);
					if(idioma=="Portugues") {
						g.drawString("Volume:",720/3, 100);
						g.drawString("Efeitos Sonoros:", 720/3, 150);
						g.drawString("Musica:",720/3, 200);
						g.drawString("Idioma:",720/3, 250);
						g.setColor(Color.black);
						g.drawString("Voltar", 599, 330);
					}else {
						g.drawString("Volume:",720/3, 100);
						g.drawString("Sound Efects:", 720/3, 150);
						g.drawString("Music:",720/3, 200);
						g.drawString("Language:",720/3, 250);
						g.setColor(Color.black);
						g.drawString("Back", 599, 330);
					}
					
					
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
