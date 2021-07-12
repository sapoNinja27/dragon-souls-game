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

public class Sander extends Player{
	private double x,y;
	private BufferedImage[] rightTai;
	private BufferedImage[] leftTai;
	private BufferedImage[] direcao;
	private BufferedImage[] sombra;
	public int framesDash = 0,maxFramesDash = 11,indexDash = 19,maxIndexDash = 20;
	public int framesDashS = 0,maxFramesDashS2 = 15,maxFramesDashS = 4,indexDashS = 20,maxIndexDashS = 23;
	public double life = 100,maxlife=100, totalife=120,special = 0,maxspecial=100,stamina = 100,maxstamina=100;
	
	
	public Sander(int x, int y) {
		super(x, y);
		rightTai = new BufferedImage[50];
		leftTai = new BufferedImage[50];
		direcao= new BufferedImage[50];
		sombra= new BufferedImage[50];
		//respirando
		for(int i =0; i < 4; i++){
			rightTai[i] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*0, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//correndo
		for(int i =0; i < 9; i++){
			rightTai[i+4] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*1, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//pulando
		for(int i =0; i < 6; i++){
			rightTai[i+13] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*2, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		//dash
		for(int i =0; i < 5; i++){
			rightTai[i+19] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*3, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//parado soco
		for(int i =0; i < 4; i++){
			rightTai[i+24] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*4, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//socos
		for(int i =0; i < 6; i++){
			rightTai[i+28] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*5, Game.TILE_SIZE, Game.TILE_SIZE);
		}
//		//hb1
		for(int i =0; i < 9; i++){
			rightTai[i+34] =   Game.sander.getSprite(Game.TILE_SIZE*i, Game.TILE_SIZE*6, Game.TILE_SIZE, Game.TILE_SIZE);
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
	public void render(Graphics g) {
		Sombras(g,direcao);
		g.drawImage(direcao[index], this.getX()+pos - Camera.x+mov_das_cena,this.getY() - Camera.y, null);
		CharEscuro(g,direcao);
		
		
		
//		Color c=Color.green;
//		g.setColor(c);
//		g.fillOval(200, 200, 50, 50);
//		int red= c.getRed();
//		int green= c.getGreen();
//		int blue= c.getBlue();
//		float[] hsbvals = new float[3];
//		Color.RGBtoHSB(red, green, blue, hsbvals);
//		System.out.println(hsbvals[0]);
//		g.setColor(new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2])));
//		g.fillOval(250, 200, 50, 50);
	}

}
