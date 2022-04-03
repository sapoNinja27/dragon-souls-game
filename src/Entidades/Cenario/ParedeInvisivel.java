//package Entidades.Cenario;
//
//import java.awt.Color;
//import java.awt.Graphics;
//
//import Configuration.Configuracoes;
//import Entidades.Entidade;
//import Main.Game;
//import World.Camera;
//
//public class ParedeInvisivel extends Entidade {
//	private int dist;
//	private int larg = 0;
//
//	public ParedeInvisivel(int x, int y, int dist) {
//		super(x, y, 0, 0);
//		this.dist = dist;
//		adicionarMascara(0, 0 + larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);
//		adicionarMascara(1, dist * Configuracoes.TILE_SIZE - 2 - larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);
//	}
//
//	public void tick() {
//		checkCollision();
//	}
//
//	void setLarguraParede(int larg) {
//		this.larg = larg;
//		adicionarMascara(0, 0 + larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);
//		adicionarMascara(1, dist * Configuracoes.TILE_SIZE - 2 - larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);
//	}
//
//	public void checkCollision() {
//		if (Entidade.corpoColidindo(Game.player, this, 0, 0)) {
//			Game.player.setX(this.getX() - 15 + larg);
//			Game.player.parado = true;
//		}
//		if (Entidade.corpoColidindo(Game.player, this, 0, 1)) {
//			Game.player.setX(this.getX() + (dist * Configuracoes.TILE_SIZE) - 47 - larg);
//			Game.player.parado = true;
//		}
//	}
//
//	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.drawRect(this.getX() - Camera.x + mascaracaX[0], this.getY() - Camera.y + mascaraY[0], mascaraAltura[0], mascaraLargura[0]);
//		g.drawRect(this.getX() - Camera.x + mascaracaX[1], this.getY() - Camera.y + mascaraY[1], mascaraAltura[1], mascaraLargura[1]);
//	}
//
//}