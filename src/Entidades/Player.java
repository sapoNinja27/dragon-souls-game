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

public class Player extends Entity{
	public boolean caindo, subindo,podepular,completou_pulo,saiu_do_chao,caiu_no_chao, atacando,dash,dashS,dashS2,transformado,
	atacando2,atack2;
	public int px;
	public boolean tmpSocos[]=new boolean[3];
	public boolean bloqueio[]=new boolean[3];
	public boolean socoForte[]=new boolean[3];
	public boolean forca[]=new boolean[3];
	public boolean habilidade[]=new boolean[3];
	public boolean defesa[]=new boolean[3];
	public boolean right,up,left,down,parado,parando;
	public int right_dir = 0,left_dir = 1;
	public int dir = right_dir;
	public double speed = 4;
	public boolean parede;
	public boolean visivel;
	public boolean Hudvisivel;
	public int index=0;
	private int cont=0,maxCont=15,verif=0,maxVerif=2;
	private int pos=0;
	public int mov_das_cena=0;
	public int camx=0,camy=0;
	public boolean camL,camR,camU;
	private int framesMoved = 0,maxFramesMoved = 9,indexMoved = 4, maxIndexMoved=12;
	private int framesParan = 0,maxFramesParan = 15;
	private int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	private int framesPulo = 0,maxFramesPulo =15,indexPul = 13,maxIndexPul = 15;
	private int framesCai = 0,maxFramesCai = 15,indexCai = 16,maxIndexCai = 17;
	private int framesCai2 = 0,maxFramesCai2 = 15;
	private int framesAtk = 0,maxFramesAtk = 9,indexAtk =24,maxIndexAtk = 28;
	private int framesAtk2 = 0,maxFramesAtk2 = 9,indexAtk2 =27,maxIndexAtk2 = 29;
	private int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	private int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	public boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] direcao;
	
	private BufferedImage playerDamage;
	
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public boolean shoot = false,mouseShoot = false;
	
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100
			,stamina = 100,maxstamina=100;
	public int mx,my;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[33*4];
		leftPlayer = new BufferedImage[33*4];
		direcao = new BufferedImage[33*4];
		
		
		
		
		for(int i =0; i < 33; i++){
			rightPlayer[i] = Game.spritesheet.getSprite(Game.TILE_SIZE*i, 0, Game.TILE_SIZE, Game.TILE_SIZE);
			//sessao redeye
			rightPlayer[i+33] = Game.spritesheet.getSprite(Game.TILE_SIZE*i,Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
			//machucados
			rightPlayer[i+66] = Game.spritesheet.getSprite(Game.TILE_SIZE*i, 2*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
			//demon
			rightPlayer[i+99] = Game.spritesheet.getSprite(Game.TILE_SIZE*i, 3*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		
		
		
	}
	
	public void tick() {
		special=00;
		if(special>maxspecial) {
			special=maxspecial;
		}
		if(atacando || atacando2) {
			if(special>maxspecial/2) {
				special+=0.3;
				//dano=15;
			}else {
				special+=0.1;
				//dano=2;
			}
		}
		if(stamina<maxstamina) {
			stamina+=0.09;
		}
		if(special>0) {
			special-=0.03;
		}
		cameraRoll();
		setHitbox();
		movedX();
		movedY();
		anim();
		checkCollisionLifePack();
		projetil();
		lifesistem();
		updateCamera();
	}
	public void setHitbox() {
		//hitbox padrao
		setMask0(20,11,20,52);
		setMask2(11,60,40,3);
		//ataques melle
		if(special>maxspecial/2) {
			if(dir==right_dir) {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(50,20,30,10);
					}
				}else if(atacando2) {
					if(indexAtk2!=27 ) {
						setMask1(55,0,10,30);
					}
				}else {
					setMask1(20,20,30,10);
				}
			}else {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(-15,20,30,10);
					}
				}else if(atacando2) {
					if(indexAtk2!=27 ) {
						setMask1(-4,0,10,30);
					}
				}else {
					setMask1(20,20,30,10);
				}
			}
		}else {
			if(dir==right_dir) {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(40,20,30,10);
					}
				}else if(atacando2) {
					if(indexAtk2!=27 ) {
						setMask1(45,0,10,30);
					}
				}else {
					setMask1(20,20,30,10);
				}
			}else {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(-5,20,30,10);
					}
				}else if(atacando2) {
					if(indexAtk2!=27 ) {
						setMask1(6,0,10,30);
					}
				}else {
					setMask1(20,20,30,10);
				}
			}
		}
		
		
		
		
	}
	public void projetil() {
		
		if(shoot) {
			shoot = false;
			
			
			int dx = 0;
			int px = 0;
			int py = 6;
			if(dir == right_dir) {
				px = 18;
				dx = 1;
			}else {
				px = -8;
				dx = -1;
			}
			
			BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,3,3,null,dx,0);
			Game.bullets.add(bullet);
			
		}
	}
	public void lifesistem() {
		if(life<=0) {
			//Game over!
			life = 0;
			Game.gameState = "GAME_OVER";
		}
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
			y-=speed;
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
//		if(tanoar) {
//			if(!isFreeY()) {
//				cainimation=true;
//				tanoar=false;
//			}
//		}
		
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
			y+=speed;
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
	public void movedX() {
		if(right && isFreeX()!="esquerda") {
			moved = true;
			dir = right_dir;
			x+=speed;
		}
		if(left && isFreeX()!="direita") {
			moved = true;
			dir = left_dir;
			x-=speed;
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
			if(framesMoved==maxFramesMoved) {
				framesMoved=0;
				indexMoved++;
				if(indexMoved==maxIndexMoved) {
					indexMoved=4;
				}
				
			}
		}
		if(moved) {
			atacando=false;
			atacando2=false;
			indexAtk = 24;
			indexAtk2 = 27;
		}
		if(dash) {
			if(dir==right_dir) {
				if(isFreeX()!="esquerda") {
					x+=8;
				}
			}else {
				if(isFreeX()!="direita") {
					x-=8;
				}
			}
		}
		if(!transformado) {
			if(dashS) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						x+=6;
					}
				}else {
					if(isFreeX()!="direita") {
						x-=6;
					}
				}
			}
			if(dashS2) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						x+=4;
					}
				}else {
					if(isFreeX()!="direita") {
						x-=4;
					}
				}
			}
		}else {
			if(dashS) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						x+=3;
					}
				}else {
					if(isFreeX()!="direita") {
						x-=3;
					}
				}
			}
			
		}
		
		
		
	}
	public void anim() {
		if(right) {
			dir=right_dir;
		}else if(left) {
			dir=left_dir;
		}
		if(!isFreeY() && moved) {
			index=indexMoved;
		}else if(caiu_no_chao) {
			index=index;
		}else if(parado) {
			if(!transformado) {
				index=indexParado;
			}else {
				index=indexParado+99;
			}
		}else if(atacando) {
			index=indexAtk;
		}else if(atacando2) {
			index=indexAtk2;
		}else if(dash) {
			index=indexDash;
		}else if(dashS) {
			index=indexDashS;
		}else if(dashS2) {
			index=indexDashS;
		}else if(subindo) {
			index=indexPul;
		}else if(caindo) {
			index=indexCai;
		}else if(moved){
			index=indexMoved;
		}
		
		if(atacando) {
			framesAtk++;
			if(framesAtk == maxFramesAtk) {
				framesAtk = 0;
				indexAtk++;
				if(indexAtk == maxIndexAtk) {
					indexAtk = 24;
					if(transformado) {
						index=2;
					}else {
						index=24;
					}
					atacando=false;
					if(atack2) {
						atacando2=true;
						atack2=false;
					}
				}
			}
		}
		
		if(dash) {
			parando=false;
			framesDash++;
			if(framesDash == maxFramesDash) {
				framesDash = 0;
				indexDash++;
				if(indexDash == maxIndexDash) {
					indexDash = 19;
					dash=false;

					stamina-=15;
					if(!moved) {
						parando=true;
					}
					
				}
			}
		}
		if(dashS) {
			dash=false;
			indexDash = 19;
			framesDash = 0;
			framesDashS++;
			if(framesDashS == maxFramesDashS) {
				framesDashS = 0;
				indexDashS++;
				if(indexDashS == maxIndexDashS) {
					if(transformado) {
						indexDashS = 24;
					}else {
						indexDashS = 23;
					}
					dashS=false;
					dashS2=true;
				}
			}
		}
		if(dashS2) {
			stamina-=1;
			dash=false;
			framesDashS++;
			if(framesDashS == maxFramesDashS2) {
				framesDashS = 0;
				indexDashS=19;
					dashS2=false;
					parado=true;
				
				}
		}
		if(parando ) {
			index=12;
			framesParan++;
			if(dir==left_dir) {
				if(isFreeX()!="direita") {
					x--;
				}
			}else {
				if(isFreeX()!="esquerda") {
					x++;
				}
			}
			if(framesParan == maxFramesParan) {
				framesParan = 0;
				parado=true;
				parando=false;
			}
		}
		
		
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
	
	public void updateCamera() {
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2)+camx,0,World.WIDTH*Game.TILE_SIZE - Game.WIDTH);
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

	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect= new Rectangle(this.getX() - Camera.x+maskx[0],this.getY() - Camera.y+masky[0],maskw[0],maskh[0]);
		Rectangle rect2= new Rectangle(this.getX() - Camera.x+maskx[1],this.getY() - Camera.y+masky[1],maskw[1],maskh[1]);
		Rectangle rect3= new Rectangle(this.getX() - Camera.x+maskx[2],this.getY() - Camera.y+masky[2],maskw[2],maskh[2]);
//		g.setColor(Color.RED);
//		g2.draw(rect);
//		g.setColor(Color.BLUE);
//		g2.draw(rect2);
//		g.setColor(Color.green);
//		g2.draw(rect3);
		//g.drawImage(Game.fundo.getSprite(0, 0,1000, 750), this.getX() - Camera.x-(1000/2),this.getY() - Camera.y-(750/2), null);
		
		if(leftPlayer[0]==null && leftPlayer[33*4-1]==null) {
			for(int i=0;i<33*4;i++) {
				leftPlayer[i]=inverter(rightPlayer[i]);
			}
		}
		
		if(dir == left_dir) {
			if(index==26 || index==27) {
				pos=-9;
			}else{
				pos=0;
			}
			for(int i=0;i<33*4;i++) {
				direcao[i]=leftPlayer[i];
			}
		}else if(dir == right_dir) {
			if(index==26 || index==27) {
				pos=+9;
			}else{
				pos=0;
			}
			for(int i=0;i<33*4;i++) {
				direcao[i]=(rightPlayer[i]);
			}
		}
		for(int i=0;i<4;i++) {
			//g.drawImage(direcao[24+i],i*80,0, null);
		}
//		g.drawImage(direcao[index+1], this.getX()+50 - Camera.x,this.getY() - Camera.y, null);
//		g.drawImage(direcao[index+2], this.getX()+51 - Camera.x,this.getY() - Camera.y, null);
//		g.drawImage(direcao[index+3], this.getX()+60 - Camera.x,this.getY() - Camera.y, null);
//		g.drawImage(direcao[index+4], this.getX()+60 - Camera.x,this.getY() - Camera.y, null);

		
		if(visivel) {
			if(index==32 || index==99+30) {
				g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y+5, null);
			}else {
				g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);				
			}
		}
		
		
		
		
		
		
//		if(!isDamaged) {
//			
//		}else {
//			g.drawImage(playerDamage, this.getX()-Camera.x, this.getY() - Camera.y,null);
//			
//		}
	}

}
