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

public class Tai extends Player{
	private double x,y;
	private BufferedImage[] rightTai;
	private BufferedImage[] leftTai;
	private BufferedImage[] direcao;
	
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100,stamina = 100,maxstamina=100;
	
	
	public Tai(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightTai = new BufferedImage[50];
		leftTai = new BufferedImage[50];
		direcao= new BufferedImage[35];
		//respirando
		for(int i =0; i < 4; i++){
			rightTai[i] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
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
		if(leftTai[0]==null && leftTai[34]==null) {
			for(int i=0;i<35;i++) {
				leftTai[i]=inverter(rightTai[i]);
			}
		}
		
	}
	public void tick() {
		if(Game.player.personagem=="Tai"){
			updateCamera(); 
		}
			attsprite();
			setHitbox();
			anim();
			cameraRoll();
			movedX();
			movedY();
			checkCollisionLifePack();
			checkCollisionPorta();
			lifesistem();
		
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
					setX(getX()-1);
				}
			}else {
				if(isFreeX()!="esquerda") {
					setX(getX()+1);
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
			setY(getY()-speed);
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
			setY(getY()+speed);
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
			setX(getX()+speed);
		}
		if(left && isFreeX()!="direita") {
			moved = true;
			dir = left_dir;
			setX(getX()-speed);
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
					setX(getX()+8);
				}
			}else {
				if(isFreeX()!="direita") {
					setX(getX()-8);
				}
			}
		}
		if(!transformado) {
			if(dashS) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						setX(getX()+6);
					}
				}else {
					if(isFreeX()!="direita") {
						setX(getX()-6);
					}
				}
			}
			if(dashS2) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						setX(getX()+4);
					}
				}else {
					if(isFreeX()!="direita") {
						setX(getX()-4);
					}
				}
			}
		}else {
			if(dashS) {
				if(dir==right_dir) {
					if(isFreeX()!="esquerda") {
						setX(getX()+3);
					}
				}else {
					if(isFreeX()!="direita") {
						setX(getX()-3);
					}
				}
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
		g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		
		
		
	}

}
