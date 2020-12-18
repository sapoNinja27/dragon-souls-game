package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Pupkin_Boss extends Entity {
	public static int index;
	int indexF;
	public static boolean fumaça;
	int framesFumacinha;
	public static boolean queimou;
	public static boolean parado;
	private int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	static int px,py;
	public static boolean aceso[]=new boolean[4];
	public static int right_dir = 0,left_dir = 1;
	public static int dir = right_dir;
	BufferedImage img[]= new BufferedImage[19];
	BufferedImage fumacinha[]= new BufferedImage[5];
	private BufferedImage[] right= new BufferedImage[19];
	private BufferedImage[] left= new BufferedImage[19];
	BufferedImage fogo[]= new BufferedImage[19];
	private BufferedImage[] Fright= new BufferedImage[19];
	private BufferedImage[] Fleft= new BufferedImage[19];
	public Pupkin_Boss(int x, int y, int width, int height, BufferedImage[] sprite) {
		super(x, y, width, height, null);
		
	}
	public static void setPos(int x, int y) {
		px=x;
		py=y;
	}
	public void tick() {
//		frames++;
//		if(frames==10) {
//			frames=0;
//			index++;
//			if(index==14) {
//				index=7;
//			}
//		}
		
//		depth=7;
//		for(int i=0;i<5;i++) {
//			fumacinha[i]=Game.spritesheet.getSprite(Game.TILE_SIZE*5+(16+16*i), 18*Game.TILE_SIZE, 16, 16);
//		}
//		for(int i=0; i<19;i++) {
//			right[i]=Game.spritesheet.getSprite(Game.TILE_SIZE*(8+3+i), 10*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE*2);
//			Fright[i]=Game.spritesheet.getSprite(Game.TILE_SIZE*(8+i), (10+6)*Game.TILE_SIZE, Game.TILE_SIZE*2, Game.TILE_SIZE);
//		}
//		if(left[0]==null && left[18]==null) {
//			for(int i=0;i<18;i++) {
//				left[i]=inverter(right[i]);
//				Fleft[i]=inverter(Fright[i]);
//			}
//		}
//		framesFumacinha++;
//		if(framesFumacinha>=10) {
//			indexF++;
//			framesFumacinha=0;
//			if(indexF==3) {
//				indexF=0;
//			}
//		}
//		if(dir == left_dir) {
//			for(int i=0;i<18;i++) {
//				img[i]=left[i];
//				fogo[i]=Fleft[i];
//			}
//		}else if(dir == right_dir) {
//				for(int i=0;i<18;i++) {
//					img[i]=(right[i]);
//					fogo[i]=(Fright[i]);
//			}
//		}
//
//		
//		if(parado) {
//			if(queimou) {
//				index=indexParado+12;
//			}else {
//				index=indexParado;
//			}
//		}
//		
//		if(parado) {
//			framesParado++;
//			if(framesParado==maxFramesParado) {
//				framesParado=0;
//				indexParado++;
//				if(indexParado==maxIndexParado) {
//					indexParado=0;
//				}
//			}
//		}
	}
	public void render(Graphics g) {
//		g.drawImage(img[index], this.getX()- Camera.x+px,this.getY()+py - Camera.y-Game.TILE_SIZE+4, null);
//		if(fumaça) {
//			g.drawImage(fumacinha[indexF], this.getX()- Camera.x+px,this.getY()+py - Camera.y-Game.TILE_SIZE+4, null);
//		}
//		if(aceso[0]) {
//			g.drawImage(fogo[0], this.getX()- Camera.x+px+15,this.getY()+py - Camera.y+15,5,5, null);
//		}
//		if(aceso[1]) {
//			g.drawImage(fogo[0], this.getX()- Camera.x+px-30,this.getY()+py - Camera.y+5,60,40, null);
//		}
//		if(aceso[2]) {
//			g.drawImage(fogo[0], this.getX()- Camera.x+px-90,this.getY()+py - Camera.y+3,64*2,64, null);
//		}
//		if(aceso[3]) {
//			g.drawImage(fogo[0], this.getX()- Camera.x+px-205,this.getY()+py - Camera.y+5,64*4,64, null);
//		}
	}
}
