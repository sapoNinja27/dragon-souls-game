package Entidades.Players;

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
	private BufferedImage[] sombra;
	public int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100,stamina = 100,maxstamina=100;
	
	
	public Tai(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightTai = new BufferedImage[50];
		leftTai = new BufferedImage[50];
		direcao= new BufferedImage[50];
		sombra= new BufferedImage[50];
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
		for(int i=0;i<35;i++) {
			sombra[i]=Sombra(direcao[i]);
		}
		if(atacando) {
			if(dir == left_dir) {
				if( indexAtk== 30 || indexAtk == 29 || indexAtk==31) {
					pos=-9;
				}else if(indexAtk==28 || indexAtk==32 ) {
					pos=-1;
				}else {
					pos=0;
				}
				for(int i=0;i<35;i++) {
					direcao[i]=leftTai[i];
				}
			}else if(dir == right_dir) {
				if( indexAtk== 30 || indexAtk == 29 || indexAtk==31) {
					pos=+9;
				}else if(indexAtk==28 || indexAtk==32 ) {
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
		if(leftTai[0]==null && leftTai[34]==null) {
			for(int i=0;i<35;i++) {
				leftTai[i]=inverter(rightTai[i]);
			}
		}
		
	}
	public void tick() {
			attsprite();
			setHitbox();
			anim();
			cameraRoll();
			dash();
			movedY();
			lifesistem();
			if(this==Game.player) {
				updateCamera(); 
				nBot();
				checkCollisionLifePack();
			}else {
				bot();
//				longeDemais();
			}
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
		}else if(!isFreeY() && moved && !dash && !dashS ){
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
				if(this==Game.player2) {
					jaParou=true;
				}
			}
		}
	}
	
	
	void dash() {
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
	}
	

	
	public void render(Graphics g) {
		Sombras(g,direcao);
		g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		CharEscuro(g,direcao);
//		g.setColor(Color.red);
//		g.drawRect(getX()- Camera.x+maskx[2], getY()- Camera.y+masky[2], maskw[2], maskh[2]);
		
		
		
		
	}

}
