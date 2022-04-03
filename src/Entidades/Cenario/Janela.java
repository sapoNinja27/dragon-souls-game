//package Entidades.Cenario;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//import Configuration.Configuracoes;
//import Entidades.Entidade;
//import Main.Game;
//import World.Camera;
//
//public class Janela extends Entidade {
//	private BufferedImage img;
//
//	public Janela(int x, int y, BufferedImage img) {
//		super(x, y, 0, 0);
//		depth = 1;
//		adicionarMascara(0, 0 - 25, -20, 46, 80);
//		this.img = img;
//	}
//
//	public void render(Graphics g) {
//		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
//			if (!Game.player.getDentro()) {
//				g.drawImage(img, this.getX() - Camera.x - Configuracoes.TILE_SIZE,
//						this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
//						Configuracoes.TILE_SIZE * 2, null);
//			}
//		}
//	}
//}