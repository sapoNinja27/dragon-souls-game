//package Entidades.Cenario;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//import Entidades.Entidade;
//import Main.Game;
//import World.Camera;
//
//public class CanoEsgoto extends Entidade {
//	private int index;
//	private int ciclo;
//	private BufferedImage[] img;
//	private int frames = 0;
//	private int[] rand=new int[3];
//	public CanoEsgoto(int x, int y, BufferedImage[] img) {
//		super(x, y, 0, 0);
//		this.img = img;
//		depth = 3;
//		for(int i=0;i<3;i++) {
//			rand[i]=Game.rand.nextInt(300);
//		}
//		adicionarMascara(0, 17, -30, 32, 40);
//	}
//
//	public void tick() {
//		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 500) {
//			if (!Game.player.getDentro()) {
//				frames++;
//				if (index == 1) {
//					if (frames >= rand[ciclo] + 50) {
//						frames = 0;
//						index = 0;
//						ciclo++;
//						if(ciclo>2) {
//							ciclo=0;
//						}
//					}
//				}
//				if (index == 0) {
//					if (frames >= rand[ciclo] + 200) {
//						frames = 0;
//						index = 1;
//						ciclo++;
//						if(ciclo>2) {
//							ciclo=0;
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public void render(Graphics g) {
//		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 500) {
//			if (!Game.player.getDentro()) {
//				g.drawImage(img[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
//			}
//		}
//	}
//}