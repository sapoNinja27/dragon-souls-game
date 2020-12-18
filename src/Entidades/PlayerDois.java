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

public class PlayerDois extends Entity{
	public boolean caindo, subindo,podepular,completou_pulo,saiu_do_chao,caiu_no_chao, atacando,dash;
	public int px;
	boolean combat;
	public boolean right,up,left,down,parado,parando;
	public int right_dir = 0,left_dir = 1;
	public int dir = right_dir;
	public double speed = 3.5;
	public boolean parede;
	boolean jaParou;
	public boolean visivel;
	public int index=0;
	private int pos=0;
	public String personagem="Tai";
	public int mov_das_cena=0;
	public int camx=0,camy=0;
	public boolean camL,camR,camU;
	private int framesMoved = 0,maxFramesMoved = 9,indexMoved = 4, maxIndexMoved=12;
	private int framesParan = 0,maxFramesParan = 15;
	private int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	private int framesPulo = 0,maxFramesPulo =15,indexPul = 13,maxIndexPul = 15;
	private int framesCai = 0,maxFramesCai = 15,indexCai = 16,maxIndexCai = 17;
	private int framesCai2 = 0,maxFramesCai2 = 15;
	private int indexAtk=0, frames=0;
	private int framesAtkT = 0,maxFramesAtkT = 9,indexAtkT =27,maxIndexAtkT = 31;
	private int framesAtkD = 0,maxFramesAtkD = 9,indexAtkD=25,maxIndexAtkD = 28;
	private int framesAtkA = 0,maxFramesAtkA = 9,indexAtkA =24,maxIndexAtkA = 28;
	private int framesAtkS = 0,maxFramesAtkS = 9,indexAtkS =24,maxIndexAtkS = 28;
	private int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	public boolean moved = false;
	private BufferedImage[] rightTai;
	private BufferedImage[] leftTai;
	private BufferedImage[] rightSander;
	private BufferedImage[] leftSander;
	private BufferedImage[] rightAce;
	private BufferedImage[] leftAce;
	private BufferedImage[] rightDemon;
	private BufferedImage[] leftDemon;
	private BufferedImage[] direcao;
	
	public double life = 100,maxlife=100;

	public PlayerDois(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightTai = new BufferedImage[50];
		leftTai = new BufferedImage[50];
		rightSander= new BufferedImage[35];
		leftSander= new BufferedImage[35];
		rightAce= new BufferedImage[35];
		leftAce= new BufferedImage[35];
		rightDemon= new BufferedImage[35];
		leftDemon= new BufferedImage[35];
		direcao= new BufferedImage[35];
		//respirando
				for(int i =0; i < 4; i++){
					rightTai[i] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
//					rightDemon[i] = Game.demonTai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
//					rightAce[i] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
//					rightSander[i] =Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE*2);
				}
				//correndo
				for(int i =0; i < 9; i++){
					rightTai[i+4] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*1, Game.TILE_SIZE, Game.TILE_SIZE);
				}
				//pulando
				for(int i =0; i < 6; i++){
					rightTai[i+13] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*2, Game.TILE_SIZE, Game.TILE_SIZE);
				}
				//dash
				for(int i =0; i < 5; i++){
					rightTai[i+19] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*3, Game.TILE_SIZE, Game.TILE_SIZE);
				}
//				//parado soco
				for(int i =0; i < 4; i++){
					rightTai[i+24] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*4, Game.TILE_SIZE, Game.TILE_SIZE);
				}
//				//socos
				for(int i =0; i < 6; i++){
					rightTai[i+28] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*5, Game.TILE_SIZE, Game.TILE_SIZE);
				}
//				//hb1
				for(int i =0; i < 9; i++){
					rightTai[i+34] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*6, Game.TILE_SIZE, Game.TILE_SIZE);
				}
//				//h2
//				for(int i =0; i < 4; i++){
//					rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*7, Game.TILE_SIZE, Game.TILE_SIZE);
//				}
//				//h3
//				for(int i =0; i < 4; i++){
//					rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*8, Game.TILE_SIZE, Game.TILE_SIZE);
//				}
				//??
		
		
		
	}
	public void attsprite(){
		if(leftTai[0]==null && leftTai[34]==null) {
			for(int i=0;i<35;i++) {
				leftTai[i]=inverter(rightTai[i]);
			}
		}
		if(leftDemon[0]==null && leftDemon[34]==null) {
			for(int i=0;i<35;i++) {
				leftDemon[i]=inverter(rightDemon[i]);
			}
		}
		if(leftAce[0]==null && leftAce[34]==null) {
			for(int i=0;i<35;i++) {
				leftAce[i]=inverter(rightAce[i]);
			}
		}
		if(leftSander[0]==null && leftSander[34]==null) {
			for(int i=0;i<35;i++) {
				leftSander[i]=inverter(rightSander[i]);
			}
		}
	}
	public boolean isColiddingWithPlayer(){
		Rectangle player2 = new Rectangle(this.getX() + maskx[0],this.getY() + masky[0],maskw[0],maskh[0]);
		Rectangle player = new Rectangle(Game.player.getX()+ Game.player.maskx[0],Game.player.getY()+ Game.player.masky[0],Game.player.maskw[0],Game.player.maskh[0]);
		
		return player2.intersects(player);
	}
	public boolean safeZone(){
		Rectangle player2 = new Rectangle(this.getX() + maskx[1],this.getY() + masky[1],maskw[1],maskh[1]);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),maskw[0],maskh[0]);
		
		return player2.intersects(player);
	}
	public void tick() {
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
		setMask0(-50,11,150,52);
		setMask1(0,11,50,52);
		if(Game.player.dir==left_dir) {
			if(Game.player.getX()<getX()) {
				dir=left_dir;
				if(Game.player.getX()<getX() && !isColiddingWithPlayer()) {
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
				if(Game.player.getX()>getX() && !isColiddingWithPlayer()) {
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
		depth=9;
		personagem="Demon Tai";
		if(personagem=="Tai") {
			Tai();	
		}else if(personagem=="Demon Tai") {
			Demon();
		}else if(personagem=="Ace") {
			Ace();
		}else if(personagem=="Sander") {
			Sander();
		}
		attsprite();
		setHitbox();
		anim();
		cameraRoll();
		movedX();
//		movedY();
		checkCollisionLifePack();
//		projetil();
		lifesistem();
	}
	public void Tai() {
		if(dir == left_dir) {
			if( index== 30 || index == 29) {
				pos=-9;
			}else if(index==28) {
				pos=-1;
			}else {
				pos=0;
			}
			for(int i=0;i<35;i++) {
				direcao[i]=leftTai[i];
			}
		}else if(dir == right_dir) {
			if( index== 30 || index == 29) {
				pos=+9;
			}else if(index==28) {
				pos=+1;
			}else {
				pos=0;
			}
			for(int i=0;i<35;i++) {
				direcao[i]=(rightTai[i]);
			}
		}
		
		if(atacando) {
			indexAtk=indexAtkT;
			framesAtkT++;
			if(framesAtkT == maxFramesAtkT) {
				framesAtkT = 0;
				indexAtkT++;
				if(indexAtkT == maxIndexAtkT) {
					indexAtkT = 26;
					atacando=false;
					parado=true;
					combat=true;
				}
			}
		}
	}
	public void Demon() {
		if(dir == left_dir) {
			if(index==27) {
				pos=-9;
			}else if(index ==26){
				pos=-8;
			}else {
				pos=0;
			}
			for(int i=0;i<35;i++) {
				direcao[i]=leftDemon[i];
			}
		}else if(dir == right_dir) {
			if(index==27) {
				pos=+9;
			}else if(index ==26){
				pos=+8;
			}else {
				pos=0;
			}
			for(int i=0;i<35;i++) {
				direcao[i]=(rightDemon[i]);
			}
		}
		if(atacando) {
			indexAtk=indexAtkD;
			framesAtkD++;
			if(framesAtkD == maxFramesAtkD) {
				framesAtkD = 0;
				indexAtkD++;
				if(indexAtkD == maxIndexAtkD) {
					indexAtkD = 25;
					parado=true;
					atacando=false;
				}
			}
		}
	}
	public void Ace() {
		if(dir == left_dir) {
			for(int i=0;i<35;i++) {
				direcao[i]=leftAce[i];
			}
		}else if(dir == right_dir) {
			for(int i=0;i<35;i++) {
				direcao[i]=(rightAce[i]);
			}
		}
	}
	public void Sander() {
		
	}
	public void anim() {
		if(right) {
			dir=right_dir;
		}else if(left) {
			dir=left_dir;
		}
		if(!isFreeY() && moved && !dash ) {
			index=indexMoved;
		}else if(caiu_no_chao) {
			index=index;
		}else if(parado) {
			if(combat) {
				index=indexParado+24;
				frames++;
				if(frames>=200) {
					frames=0;
					combat=false;
				}
			}else {
				index=indexParado;
			}
		}else if(atacando) {
			index=indexAtk;
		}else if(dash) {
			index=indexDash;
		}else if(subindo) {
			index=indexPul;
		}else if(caindo) {
			index=indexCai;
		}else if(moved){
			index=indexMoved;
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
					if(!moved) {
						parando=true;
					}
					
				}
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
				jaParou=true;
			}
		}
	}
	public void setHitbox() {
		
		
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
			indexAtk = 24;
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
		g.setColor(Color.red);
		g.drawRect(getX()- Camera.x+maskx[0], getY()- Camera.y+masky[0], maskw[0], maskh[0]);
		g.drawRect(getX()- Camera.x+maskx[1], getY()- Camera.y+masky[1], maskw[1], maskh[1]);
		
		if(visivel) {
			if(index==32) {
				g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y+5, null);
			}else {
				g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);	
			}
		}
		
		
		
		
	}

}
