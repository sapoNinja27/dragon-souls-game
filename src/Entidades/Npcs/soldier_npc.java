package Entidades.Npcs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;

public class soldier_npc extends Entity {
	public static int index;
	public static boolean parado;
	private int framesParado = 0,maxFramesParado = 17,indexParado = 0, maxIndexParado=4;
	static int px,py;
	public static int right_dir = 0,left_dir = 1;
	public static int dir = right_dir;
	BufferedImage img[]= new BufferedImage[21];
	private BufferedImage[] right= new BufferedImage[21];
	private BufferedImage[] left= new BufferedImage[21];
	public soldier_npc(int x, int y, int width, int height, BufferedImage[] sprite) {
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
		
//		depth=5;
//		for(int i=0; i<21;i++) {
//			right[i]=Game.spritesheet.getSprite(Game.TILE_SIZE*(11+i), 12*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE*2);
//		}
//		if(left[0]==null && left[20]==null) {
//			for(int i=0;i<21;i++) {
//				left[i]=inverter(right[i]);
//			}
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
//		if(parado) {
//			index=indexParado;
//		}
//		if(dir == left_dir) {
//			for(int i=0;i<21;i++) {
//				img[i]=left[i];
//			}
//		}else if(dir == right_dir) {
//				for(int i=0;i<21;i++) {
//				img[i]=(right[i]);
//			}
//		}
	}
	public void render(Graphics g) {
//		if(index<4 || index>8) {
//			g.drawImage(img[index], this.getX()- Camera.x+px,this.getY()+py - Camera.y-Game.TILE_SIZE+4, null);
//		}else {
//			if(dir==left_dir) {
//				g.drawImage(img[index], this.getX()- Camera.x+px-10,this.getY()+py - Camera.y-Game.TILE_SIZE+4, null);
//			}else if(dir==right_dir) {
//				g.drawImage(img[index], this.getX()- Camera.x+px+10,this.getY()+py - Camera.y-Game.TILE_SIZE+4, null);
//			}
//		}
	}
}
