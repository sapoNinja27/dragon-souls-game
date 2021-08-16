package Graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Main.Game;

public class UI {
	private BufferedImage icon[] = new BufferedImage[20];

	public UI(Spritesheet img) {
		for (int i = 0; i < 20; i++) {
			icon[i] = img.getSprite(i * Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
	}

	public void tick() {

	}

	private void hudTai(Graphics2D g) {
		Rectangle barstamina = new Rectangle(110, 66, (int) Game.player.furia * 2, 11);
		g.setColor(Color.orange);
		g.fill(barstamina);
		Rectangle defense = new Rectangle(110, 79, (int) Game.player.defesa * 2 - Game.player.defesaAdicional, 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fill(defense);
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (Game.player.Hudvisivel) {
			if (Game.player.getId() == 0) {
				hudTai(g2);
			}
			Rectangle barlife = new Rectangle(110, 54, (int) (Game.player.vida * 2 - Game.player.vidaAdicional), 11);
			g.setColor(Color.red);
			g2.fill(barlife);
			g.drawImage(icon[Game.player.getId()], 40, 35, null);
		}

	}

}