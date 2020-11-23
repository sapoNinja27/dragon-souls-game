package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import World.Camera;

public class Menu {
	public String[] options = {"novo jogo","carregar jogo","sair"};
	public String[] Menus = {"Habilidades","Atributos","Personagens","Opções"};
	public String[] Habilidades = {"Vazio","Back","Next","Tempestade de Socos1","Tempestade de Socos2","Tempestade de Socos3",
			"Bloquear1","Bloquear2","Bloquear3","Fortalecer1","Fortalecer2","Fortalecer3","Voltar"};
	public int currentOption=0;
	public int maxOption = Habilidades.length - 1;
	
	int opcoesx;
	int opcoesy;
	int atributosx;
	int atributosy;
	int tmpsocosx1[] = { 54, 89, 123, 90, 54}; 
	int tmpsocosy1[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox1[] = { 54, 89, 123, 90, 54}; 
	int bloqueioy1[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx1[] = { 54, 89, 123, 90, 54}; 
	int forty1[] = { 119+161, 119+161, 151+161, 185+161, 185+161 }; 
	
	int tmpsocosx2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int tmpsocosy2[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int bloqueioy2[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx2[] = { 54+80, 89+80, 123+80, 90+80, 54+80}; 
	int forty2[] = { 119+161, 119+161, 151+161, 185+161, 185+161 }; 
	
	int tmpsocosx3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int tmpsocosy3[] = { 119, 119, 151, 185, 185 }; 
	int bloqueiox3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int bloqueioy3[] = { 119+80, 119+80, 152+80, 185+81, 185+81}; 
	int fortx3[] = { 54+160, 89+160, 123+160, 90+160, 54+160}; 
	int forty3[] = { 119+161, 119+161, 151+161, 185+161, 185+161 }; 
	public int pontosH=0,pontosA=0;
	public boolean up,down,enter;
	BufferedImage fundo[]=new BufferedImage[3];
	BufferedImage icons[]=new BufferedImage[90];
	BufferedImage txt;
	public int mx,my;
	public boolean pause = false;
	
	
	public void tick() {
		//colisoes
		if(mx>54 && mx<123 && my>119 && my<185) {
			currentOption=3;
		}else if(mx>54 && mx<123 && my>119+80 && my<185+81) {
			currentOption=6;
		}else if(mx>54 && mx<123 && my>119+161 && my<185+161) {
			currentOption=9;
		}else if(mx>45 && mx<70+45 && my>10 && my<30+10){
			currentOption=1;
		}else if(mx>620 && mx<75+620 && my>10 && my<30+10){
			currentOption=2;
		}else{
			currentOption=0;
		}
		
		for(int i=0;i<3;i++) {
			fundo[i]=Game.Menu.getSprite(0+(900*i), 0, 900, 900);
		}
		for(int i=0;i<6;i++) {
			icons[i]=Game.Menu.getSprite(900*5+133*i, 0, 133, 130);
			icons[i+6]=Game.Menu.getSprite(900*5+133*i, 129, 133, 130);
			icons[i+12]=Game.Menu.getSprite(900*5+133*i, 129*2, 133, 130);
		}
		txt=Game.Menu.getSprite(0+(900*3), 0, 900, 900);
		
		
//		if(up) {
//			up = false;
//			currentOption--;
//			if(currentOption < 0)
//				currentOption = maxOption;
//		}
//		if(down) {
//			down = false;
//			currentOption++;
//			if(currentOption > maxOption)
//				currentOption = 0;
//		}
		
		if(enter) {
			enter = false;
			if(options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				
				Game.gameState = "NORMAL";
				Game.player.visivel=true;
				Game.player.Hudvisivel=true;
				Game.player.depth=7;
//				Game.cen.CenaStart(0);
				pause = false;
			}else if(options[currentOption] == "sair") {
				System.exit(1);
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
			g.setFont(new Font("arial",Font.BOLD,12));
			g.setColor(Color.black);
			g.drawString("<= Opções", 50, 30);
			g.drawString("Atributos =>", 625,30);
			
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
				g.fillPolygon(tmpsocosx2, tmpsocosy2, 5);
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
				//voltar nao tem
			}
			
			
			
			
	        
	        
	        
	        
	        
	        
			if(Menus[0]=="Habilidades") {
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
				if(Habilidades[currentOption]=="Vazio" || Habilidades[currentOption]=="Next" || Habilidades[currentOption]=="Back") {
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
					g.drawString("Aumenta o alcance da habilidade", 485, 110);
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
					g.fillRect(470,50, 220, 140);
					g.setColor(Color.WHITE);
					g.fillRect(480,60, 200, 120);
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
				}
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("Habilidades:", 50, 70);
				g.setColor(Color.black);
				g.setFont(new Font("arial",Font.BOLD,15));
				g.drawString("Pontos disponiveis: "+pontosH, 50, 100);
			}
			
			
			
			
			
			
			
		}else {
			//Opcoes de menu
			g.setColor(Color.white);
			g.setFont(new Font("arial",Font.BOLD,24));
			g.drawString("Novo jogo", (Game.WIDTH*Game.SCALE) / 2 - 500, 160);
			g.drawString("Carregar jogo", (Game.WIDTH*Game.SCALE) / 2 - 500, 200);
			g.drawString("Sair", (Game.WIDTH*Game.SCALE) / 2 - 500, 240);
			
			if(options[currentOption] == "novo jogo") {
				g.drawString(">", (Game.WIDTH*Game.SCALE) / 2- 520, 160);
			}else if(options[currentOption] == "carregar jogo") {
				g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 520, 200);
			}else if(options[currentOption] == "sair") {
				g.drawString(">", (Game.WIDTH*Game.SCALE) / 2 - 520, 240);
			}
		}
		
	}
	
}
