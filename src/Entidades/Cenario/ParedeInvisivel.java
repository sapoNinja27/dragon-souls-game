package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;

public class ParedeInvisivel extends Entity {
	private int dist;
	private int larg = 0;

	public ParedeInvisivel(int x, int y, int dist) {
		super(x, y, 0, 0);
		this.dist = dist;
	}

	public void tick() {

		setMask(0, 0 + larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);
		setMask(1, dist * Configuracoes.TILE_SIZE - 2 - larg, 0, 1, Configuracoes.HEIGHT * 11 + 8);

		checkCollision();
	}

	void setLarguraParede(int larg) {
		this.larg = larg;
	}

	public void checkCollision() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			Game.player.setX(this.getX() - 15 + larg);
			Game.player.parado = true;
		}
		if (Entity.isColidding(Game.player, this, 0, 1)) {
			Game.player.setX(this.getX() + (dist * Configuracoes.TILE_SIZE) - 47 - larg);
			Game.player.parado = true;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(this.getX() - Camera.x + maskx[0], this.getY() - Camera.y + masky[0], maskw[0], maskh[0]);
		g.drawRect(this.getX() - Camera.x + maskx[1], this.getY() - Camera.y + masky[1], maskw[1], maskh[1]);

	}

}