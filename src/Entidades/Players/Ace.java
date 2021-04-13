package Entidades.Players;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Ace extends Player{
	
	private BufferedImage[] rightAce;
	private BufferedImage[] leftAce;
	private BufferedImage[] direcao;
	private BufferedImage playerDamage;
	public int framesDash = 0,maxFramesDash = 5,indexDash = 19,maxIndexDash = 24;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100
			,stamina = 100,maxstamina=100;

	public Ace(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightAce= new BufferedImage[35];
		leftAce= new BufferedImage[35];
		direcao= new BufferedImage[35];
		//respirando
		for(int i =0; i < 4; i++){
			rightAce[i] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//correndo
		for(int i =0; i < 9; i++){
			rightAce[i+4] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*1, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//pulando
		for(int i =0; i < 6; i++){
			rightAce[i+13] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*2, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//dash
		for(int i =0; i < 5; i++){
			rightAce[i+19] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*3, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//parado soco
		for(int i =0; i < 4; i++){
			rightAce[i+24] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*4, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//socos
		for(int i =0; i < 6; i++){
			rightAce[i+28] =   Game.ace.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*5, Game.TILE_SIZE, Game.TILE_SIZE);
		}
////		//hb1
//		for(int i =0; i < 9; i++){
//			rightTai[i+34] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*6, Game.TILE_SIZE, Game.TILE_SIZE);
//		}
////		//h2
////		for(int i =0; i < 4; i++){
////			rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*7, Game.TILE_SIZE, Game.TILE_SIZE);
////		}
////		//h3
////		for(int i =0; i < 4; i++){
////			rightTai[i+37] =   Game.tai.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*8, Game.TILE_SIZE, Game.TILE_SIZE);
////		}
//		//??
		
		
		
	}
	public void attsprite(){
		if(atacando) {
			if(dir == left_dir) {
				if( indexAtk== 29 || indexAtk == 30 ) {
					pos=-7;
				}else if(indexAtk==31 ) {
					pos=-4;
				}else {
					pos=0;
				}
			}else if(dir == right_dir) {
				if( indexAtk== 29 || indexAtk == 30 ) {
					pos=8;
				}else if(indexAtk==31 ) {
					pos=2;
				}else {
					pos=0;
				}
			}
		}else {
			pos=0;
		}
		if(leftAce[0]==null && leftAce[34]==null) {
			for(int i=0;i<35;i++) {
				leftAce[i]=inverter(rightAce[i]);
			}
		}
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
	public void tick() {
			attsprite();
			setHitbox();
			anim();
			cameraRoll();
			movedY();
			dash();
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
					framesDash++;
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
					setX(getX()+15);
				}
			}else {
				if(isFreeX()!="direita") {
					setX(getX()-15);
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
	}

}
