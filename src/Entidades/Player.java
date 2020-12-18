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
	public boolean caindo, subindo,podepular,completou_pulo,saiu_do_chao,caiu_no_chao, atacando,dash,dashS,dashS2,transformado;
	public int px;
	public boolean combat;
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
	private int indexAtk=0;
	public int frames=0;
	public int framesAtkT = 0;
	private int maxFramesAtkT = 6;
	private int indexAtkT =27;
	private int maxIndexAtkT = 33;
	private int framesAtkD = 0,maxFramesAtkD = 9,indexAtkD=25,maxIndexAtkD = 28;
	private int framesAtkA = 0,maxFramesAtkA = 9,indexAtkA =24,maxIndexAtkA = 28;
	private int framesAtkS = 0,maxFramesAtkS = 9,indexAtkS =24,maxIndexAtkS = 28;
	private int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	private int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
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
	
	private BufferedImage playerDamage;
	
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public boolean shoot = false,mouseShoot = false;
	
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100
			,stamina = 100,maxstamina=100;
	public int mx,my;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
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
//			rightDemon[i] = Game.demonTai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
//			rightAce[i] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
//			rightSander[i] =Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE*2);
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
//		//parado soco
		for(int i =0; i < 4; i++){
			rightTai[i+24] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*4, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//socos
		for(int i =0; i < 6; i++){
			rightTai[i+28] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*5, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//hb1
		for(int i =0; i < 9; i++){
			rightTai[i+34] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*6, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//h2
//		for(int i =0; i < 4; i++){
//			rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*7, Game.TILE_SIZE, Game.TILE_SIZE);
//		}
//		//h3
//		for(int i =0; i < 4; i++){
//			rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*8, Game.TILE_SIZE, Game.TILE_SIZE);
//		}
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
	public void tick() {
		personagem="Tai";
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
		movedY();
		checkCollisionLifePack();
//		projetil();
		lifesistem();
		updateCamera(); 
	}
	public void Tai() {
		if(atacando) {
			if(dir == left_dir) {
				if( indexAtkT== 30 || indexAtkT == 29 || indexAtkT==31) {
					pos=-9;
				}else if(indexAtkT==28 || indexAtkT==32 ) {
					pos=-1;
				}else {
					pos=0;
				}
				for(int i=0;i<35;i++) {
					direcao[i]=leftTai[i];
				}
			}else if(dir == right_dir) {
				if( indexAtkT== 30 || indexAtkT == 29 || indexAtkT==31) {
					pos=+9;
				}else if(indexAtkT==28 || indexAtkT==32 ) {
					pos=+1;
				}else {
					pos=0;
				}
				for(int i=0;i<35;i++) {
					direcao[i]=(rightTai[i]);
				}
			}
		}else {
			pos=0;
		}
		if(dir == left_dir) {
			for(int i=0;i<35;i++) {
				direcao[i]=leftTai[i];
			}
		}else if(dir == right_dir) {
			for(int i=0;i<35;i++) {
				direcao[i]=(rightTai[i]);
			}
		}
		
		if(atacando) {
			indexAtk=indexAtkT;
			framesAtkT++;
			if(framesAtkT ==maxFramesAtkT) {
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
		if(!isFreeY() && moved && !dash && !dashS) {
			index=indexMoved;
		}else if(caiu_no_chao) {
			index=index;
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
		}else if(atacando ) {
			index=indexAtk;
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
		if(dashS) {
			dash=false;
			indexDash = 19;
			framesDash = 0;
			framesDashS++;
			if(framesDashS == maxFramesDashS) {
				framesDashS = 0;
				indexDashS++;
				if(indexDashS == maxIndexDashS) {
					indexDashS = 23;
					dashS=false;
					dashS2=true;
				}
			}
		}
		if(dashS2) {
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
				}else {
					setMask1(20,20,30,10);
				}
			}else {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(-15,20,30,10);
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
				}else {
					setMask1(20,20,30,10);
				}
			}else {
				if(atacando) {
					if(indexAtk!=24 ) {
						setMask1(-5,20,30,10);
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

//		g.drawImage(direcao[30], this.getX()+9 - Camera.x+mov_das_cena,this.getY() - Camera.y, null);	
//		g.drawImage(direcao[29], this.getX()+9 - Camera.x+mov_das_cena,this.getY() - Camera.y, null);	
//		g.drawImage(direcao[28], this.getX()+1 - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
//		g.drawImage(direcao[27], this.getX()+0 - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		if(visivel) {
			g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		}
		
		
		
		
	}

}
