package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;
import enums.TipoAmbiente;

public class LimiteDeCenarioAbismo extends Entity {

	public LimiteDeCenarioAbismo(int x, int y, int width, int height) {
		super(x, y, width, height);
		setMask(0, 0, 0, 64, 64);
	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			if (!Game.player.getDentro()) {
				checkCollision();
			}
		}
	}

	public void checkCollision() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof LimiteDeCenarioAbismo) {
				if (Entity.isColidding(Game.player, atual, 0, 0)) {

					Game.player.caindo = false;
					Game.player.parado = true;
					Game.player.vida--;
					Configuracoes.local = TipoAmbiente.TELHADO;

				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y - 10, 250, 250);
	}

}