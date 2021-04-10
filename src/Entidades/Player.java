package Entidades;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graficos.Spritesheet;
import Main.Game;
import World.Camera;
import World.World;

public class Player extends Entity {
	public int px;	
	public int cont=0,maxCont=15,verif=0,maxVerif=2;
	public int pos=0;
	public int mov_das_cena=0;
	public int camx=0,camy=0;
	protected double x;
	protected double y;
	boolean jaParou;
	public boolean camL,camR,camU;
	public int framesMoved = 0,maxFramesMoved = 9,indexMoved = 4, maxIndexMoved=12;
	public int framesParan = 0,maxFramesParan = 15;
	public int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	public int framesPulo = 0,maxFramesPulo =15,indexPul = 13,maxIndexPul = 15;
	public int framesCai = 0,maxFramesCai = 15,indexCai = 16,maxIndexCai = 17;
	public int framesCai2 = 0,maxFramesCai2 = 15;
	public int framesAtk= 0;
	public int maxFramesAtk = 6;
	
	public int indexAtk =27;
	public int maxIndexAtk = 33;
	public int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	public boolean caindo, subindo,podepular,completou_pulo,saiu_do_chao,caiu_no_chao, atacando,dash,dashS,dashS2,transformado;
	public boolean combat;
	public boolean right,up,left,down,parado,parando;
	public int right_dir = 0,left_dir = 1;
	public int dir = right_dir;
	public int index=0;
	public int frames=0;
	public boolean Hudvisivel;
	public double speed=5;
	public boolean visivel;
	public String personagem="Tai";
	public boolean moved = false;
	public boolean isDamaged = false;
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100,stamina = 100,maxstamina=100;

	public boolean H1[]=new boolean[3];
	public boolean H2[]=new boolean[3];
	public boolean H3[]=new boolean[3];
	public boolean A1[]=new boolean[3];
	public boolean A2[]=new boolean[3];
	public boolean A3[]=new boolean[3];
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		
	}
	public  void trocaPersonagem(String character){
		int x1=Game.player.getX();
		int y1=Game.player.getY();
		int x2=Game.player2.getX();
		int y2=Game.player2.getY();
		
		Game.entities.remove(Game.player);
		Game.entities.remove(Game.player2);
		if(character.equals("Tai")) {
			Game.player = new Tai(x2,y2,Game.TILE_SIZE,Game.TILE_SIZE,Game.tai.getSprite(32, 0,Game.TILE_SIZE,Game.TILE_SIZE));

			Game.player2 = new Ace(x1,y1,Game.TILE_SIZE,Game.TILE_SIZE,Game.ace.getSprite(32, 0,Game.TILE_SIZE,Game.TILE_SIZE));
			Game.player.personagem="Tai";
		}else if(character.equals("Ace")) {
			Game.player = new Ace(x2,y2,Game.TILE_SIZE,Game.TILE_SIZE,Game.ace.getSprite(32, 0,Game.TILE_SIZE,Game.TILE_SIZE));
			Game.player2 = new Tai(x1,y1,Game.TILE_SIZE,Game.TILE_SIZE,Game.ace.getSprite(32, 0,Game.TILE_SIZE,Game.TILE_SIZE));
			Game.player.personagem="Ace";
		}
		Game.player.Hudvisivel=true;
		Game.entities.add(Game.player);
		Game.entities.add(Game.player2);
		Game.player.parado=true;
	}
	public void updateCamera() {
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2)+camx+250,0,World.WIDTH*Game.TILE_SIZE - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() -(Game.HEIGHT/2)-53,0,World.HEIGHT*Game.TILE_SIZE - Game.HEIGHT);
	}
	public String isFreeX(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			
			if(atual instanceof Cenario_Interagivel) {
				if(Entity.isColidding(this,  atual,0,2)) {
					Cenario_Interagivel at2= (Cenario_Interagivel)atual;
					if(at2.tipo=="parede_invisivel") {
						return "direita";
					}
				}else if(Entity.isColidding(this,  atual,0,1)) {
					Cenario_Interagivel at2= (Cenario_Interagivel)atual;
					if(at2.tipo=="parede_invisivel") {
						return "esquerda";
					}
				}else if(Entity.isColidding(this,  atual,0,0)) {
					return "cima";
				}
				
			}
		}
		return "livre";
	}
	public boolean isFreeY(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Plataforma) {
				if(Entity.isColidding(this,  atual,0,0)) {
					return false;
				}
				
			}
//			if(atual instanceof 	Cenario_Interagivel) {
//				if(Entity.isColidding(this, atual,2,0)) {
//					Cenario_Interagivel at2= (Cenario_Interagivel)atual;
//					if(at2.tipo=="prateleira") {
//						return false;
//					}
//				}
//				
//			}
//			if(atual instanceof 	Cenario_Interagivel) {
//				if(Entity.isColidding(this, atual,0,0)) {
//					Cenario_Interagivel at2= (Cenario_Interagivel)atual;
//					if(at2.tipo=="parede_invisivel") {
//						return false;
//					}
//				}
//				
//			}
		}
		return true;
	}
	public void cameraRoll() {
		
		if(camU) {
			
		}
		if(camL) {
			
			if(camx<=-300) {
				camL=false;
			}else {
				camx-=5;
			}
		}else if(camR) {
			
			if(camx>=300) {
				camR=false;
			}else {
				camx+=5;
			}
		}
	}
	
	

	
	public void checkCollisionLifePack(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
//				if(Entity.isColidding(this, atual)) {
//					life+=10;
//					if(life > 100)
//						life = 100;
//					Game.entities.remove(atual);
//				}
			}
		}
	}
	
	public void lifesistem() {
		if(life<=0) {
			//Game over!
			life = 0;
			Game.gameState = "GAME_OVER";
		}
	}
	public void nBot() {
		depth=6;
		speed=6;
		if(atacando) {
			framesAtk++;
			if(framesAtk ==maxFramesAtk) {
				framesAtk = 0;
				indexAtk++;
				if(indexAtk == maxIndexAtk) {
					indexAtk = 26;
					atacando=false;
					parado=true;
					combat=true;
				}
			}
		}
	}
	public void CharEscuro(Graphics g, BufferedImage[] direcao) {
		Graphics2D g2 = (Graphics2D) g;
		float op=0.6f;
		if(Game.dia) {
			op=0f;
		}else {
			for(int i = 0; i < Game.entities.size(); i++){
				Entity atual = Game.entities.get(i);
				if(atual instanceof PosteLuz) {
					if(distanciaX(getX(),atual.getX())<150) {
						int[] dist={0,1,2,3,4,5,6,7,8,9,10,11,12};
						float[] opac={0.1f,0.1f,0.2f,0.2f,0.3f,0.3f,0.4f,0.4f,0.5f,0.5f,0.6f,0.6f};
						
						for(int c=0;c<11;c++) {
							if((int)distanciaX(getX(),atual.getX())/10==dist[c]) {
								op=opac[c];
							}
						}
					}
				}
			}
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,op));
		g.drawImage(Sombra(direcao[index]), this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	public void Sombras(Graphics g, BufferedImage[] direcao) {
		Graphics2D g2 = (Graphics2D) g;
		float op=0.1f;
		if(Game.dia) {
			op=0.5f;
		}else {
			for(int i = 0; i < Game.entities.size(); i++){
				Entity atual = Game.entities.get(i);
				if(atual instanceof PosteLuz) {
					if(distanciaX(getX(),atual.getX())<150) {
						int[] dist={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
						float[] opac={0.8f,0.8f,0.7f,0.7f,0.6f,0.6f,0.5f,0.5f,0.4f,0.4f,0.3f,0.3f,0.2f,0.2f,0.1f,0.1f};
						for(int c=0;c<15;c++) {
							if((int)distanciaX(getX(),atual.getX())/10==dist[c]) {
								op=opac[c];
							}
						}
					}
				}
			}
			if(this==Game.player2) {
				g.setColor(Color.black);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
				g.fillRect(Game.player.getX()-Camera.x-800, Game.player.getY()-Camera.y-300, 2000, 1200);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			}
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,op));
		if(!subindo && !caindo) {
			if(Game.dia) {
				g.drawImage(inverterV(Sombra(direcao[index])), this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y+Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE/2, null);
			}else {
				g.drawImage(inverterV(Sombra(direcao[index])), this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y+Game.TILE_SIZE+7,Game.TILE_SIZE,Game.TILE_SIZE/2, null);
			}
			
		}else {
			g.drawImage(Sombra(direcao[index]), this.getX()+pos - Camera.x+mov_das_cena+10,this.getY() - Camera.y+Game.TILE_SIZE,Game.TILE_SIZE,Game.TILE_SIZE/2, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		
	}
	
public void movedY() {
		
		if(up &&podepular){
			subindo=true;
		}else {
			subindo=false;
			//cainimation=true;
		}
		if(!isFreeY()) {
			podepular=true;
		}

		if(subindo) {
			caiu_no_chao=false;
			saiu_do_chao=true;
			framesPulo++;
			if(framesPulo == maxFramesPulo) {
				framesPulo = 0;
				if(indexPul!=maxIndexPul) {
					indexPul++;
				}
				if(indexPul==maxIndexPul) {
					subindo=false;
					podepular=false;
					completou_pulo=true;
					
				}
			}
			setY(getY()-4);
		}
		if(down){
			//cair da plat
		}
		
		if(isFreeY() && !subindo) {
			caindo=true;
		}else {
			
			caindo=false; 
			
		}
		if(!isFreeY() && saiu_do_chao) {
			caiu_no_chao=true;
		}
		
		if(caindo) {
			framesCai++;
			if(framesCai == maxFramesCai) {
				framesCai = 0;
				if(indexCai!=maxIndexCai) {
					indexCai++;
				}
				if(indexCai==maxIndexCai) {
					caindo=false;
				}
			}
			setY(getY()+4);
		}
		if(caiu_no_chao ) {
			indexCai=16;
			indexPul=13;
			saiu_do_chao=false;
			index=18;
			framesCai2++;
			if(framesCai2 == maxFramesCai2) {
				framesCai2 = 0;
				parado=true;
				caiu_no_chao=false;
			}
		}
		
	}
	public void bot() {
		depth=5;
//		if(distanciaX(Game.player.getX(),Game.player2.getX())<100 && Game.player.up) {
//			up=true;
//			parado=false;
//		}else {
//			up=false;
//			
//		}
		
		if(distanciaX(Game.player.getX(),Game.player2.getX())<500) {
			if(Game.player.moved==true) {
				if(distanciaX(Game.player.getX(),Game.player2.getX())/20>4) {
					speed=distanciaX(Game.player.getX(),Game.player2.getX())/25;
				}
			}else {
				if(distanciaX(Game.player.getX(),Game.player2.getX())/25>4) {
					speed=distanciaX(Game.player.getX(),Game.player2.getX())/30;
				}
			}
		}else {
			if(Game.player.getX()<getX()) {
				setX(Game.player.getX()-20);
			}else if(Game.player.getX()>getX()) {
				setX(Game.player.getX()+20);
			}
		}
		
		setMask(0,-50,11,150,52);
		if(Game.player.dash) {
			parado=false;
			dash=true;
			jaParou=false;
		}else {
			dash=false;
			if(!moved) {
				if(!jaParou) {
					parando=true;
				}
			}
			
		}
		if(Game.player.dir==left_dir) {
			if(Game.player.getX()<getX()) {
				dir=left_dir;
				if(Game.player.getX()<getX() && distanciaX(Game.player.getX(),Game.player2.getX())>70) {
					parado=false;
					left=true;
					right=false;
					moved=true;
					jaParou=false;
				}else {
					if(!jaParou) {
						left=false;
						moved=false;
						parando=true;
					}
				}
			}else {
				dir=right_dir;
			}
		}else if(Game.player.dir==right_dir) {
			if(Game.player.getX()>getX()) {
				dir=right_dir;
				if(Game.player.getX()>getX() && distanciaX(Game.player.getX(),Game.player2.getX())>70) {
					parado=false;
					right=true;
					left=false;
					moved=true;
					jaParou=false;
				}else {
					if(!jaParou) {
						right=false;
						moved=false;
						parando=true;
					}
				}
			}else {
				dir=left_dir;
			}
			
		}
		visivel=true;
	}
	public void movedX() {
		if(right && isFreeX()!="esquerda") {
			moved = true;
			dir = right_dir;
			correr(xDouble()+speed);
		}
		if(left && isFreeX()!="direita") {
			moved = true;
			dir = left_dir;
			correr(xDouble()-speed);
		}
		if(parado) {
			framesParado++;
			if(framesParado==maxFramesParado) {
				framesParado=0;
				indexParado++;
				if(indexParado==maxIndexParado) {
					indexParado=0;
				}
			}
		}
		if(moved) {
			framesMoved++;
			if(framesMoved>=6) {
				framesMoved=0;
				indexMoved++;
				if(indexMoved==maxIndexMoved) {
					indexMoved=4;
				}
				
			}
		}
		
		
		
		
		
	}
	public boolean isColiddingWithPlayer(){
		Rectangle player2 = new Rectangle(Game.player2.getX() + Game.player2.maskx[0],Game.player2.getY() + 
				Game.player2.masky[0],Game.player2.maskw[0],Game.player2.maskh[0]);
		
		Rectangle player = new Rectangle(Game.player.getX()+ Game.player.maskx[0],
				Game.player.getY()+ Game.player.masky[0],Game.player.maskw[0],Game.player.maskh[0]);
		
		return player2.intersects(player);
	}
	public boolean safeZone(){
		Rectangle player2 = new Rectangle(this.getX() + maskx[1],this.getY() + masky[1],maskw[1],maskh[1]);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),maskw[0],maskh[0]);
		
		return player2.intersects(player);
	}
}
