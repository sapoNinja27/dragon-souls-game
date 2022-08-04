package Entidades.Npcs;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entidade;

public class roux_npc extends Entidade {
	public static int index;
	public static boolean parado;
	private int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	static int px,py;
	public static int right_dir = 0,left_dir = 1;
	public static int dir = right_dir;
	BufferedImage img[]= new BufferedImage[18];
	private BufferedImage[] right= new BufferedImage[19];
	private BufferedImage[] left= new BufferedImage[19];
	public roux_npc(int x, int y, int width, int height) {
		super(x, y, width, height);
		
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
		
//		depth=5;
//		for(int i=0; i<11;i++) {
//			right[i]=Game.spritesheet.getSprite(Game.TILE_SIZE*(i), 13*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
//		}
//		if(left[0]==null && left[18]==null) {
//			for(int i=0;i<18;i++) {
//				left[i]=inverter(right[i]);
//			}
//		}
//		if(parado) {
//			index=indexParado;
//		}
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
//		if(dir == left_dir) {
//			for(int i=0;i<18;i++) {
//				img[i]=left[i];
//			}
//		}else if(dir == right_dir) {
//				for(int i=0;i<18;i++) {
//				img[i]=(right[i]);
//			}
//		}
	}
	public void render(Graphics g) {
//		g.drawImage(img[index], this.getX()- Camera.x+px,this.getY()+py - Camera.y, null);
	}
}


