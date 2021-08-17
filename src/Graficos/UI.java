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
	private BufferedImage hud[] = new BufferedImage[3];
	private Color colorAtual, corLife;

	public UI(Spritesheet img) {
		for (int i = 0; i < 3; i++) {
			icon[i] = img.getSprite(i * Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
		for (int i = 0; i < 3; i++) {
			hud[i] = img.getSprite((i*2+3) * Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE*2, Configuracoes.TILE_SIZE);
		}
	}

	public void tick() {

	}

	private void hudTai(Graphics2D g) {
		int px = 11;
		int py =2;
		Game.player.furia=100;
		Game.player.defesa=100;
		g.setColor(Color.black);
		g.fillRoundRect(108+px, 64 + py, 54, 8, 15, 5);
		g.setColor(Color.orange);
		g.fillRoundRect(110+px, 66 + py, (int) (Game.player.furia /2), 4, 15, 5);

		Rectangle defense = new Rectangle(101, 39 + py, (int) Game.player.defesa  - Game.player.defesaAdicional, 2);
		g.setColor(Color.black);
		g.fillRect(100, 38 + py, 103, 4);
		g.setColor(Color.LIGHT_GRAY);
		g.fill(defense);
		corLife = Color.red;
		colorAtual = Color.orange;
	}

	private void hudAce(Graphics2D g) {
		corLife = Color.blue;
		colorAtual = Color.LIGHT_GRAY;
		g.setColor(Color.white);
		g.fillOval(115, 65, 10, 10);
		g.fillOval(126, 65, 10, 10);
		g.fillOval(137, 65, 10, 10);
		g.fillOval(148, 65, 10, 10);
		g.setColor(Color.MAGENTA);
		g.fillOval(159, 65, 10, 10);
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (Game.player.Hudvisivel) {
			g.drawImage(hud[Game.player.getId()], 90, 40, null);
			if (Game.player.getId() == 0) {
				hudTai(g2);
			} else if (Game.player.getId() == 2) {
				hudAce(g2);
			}
			/*
			 * Barra de vida
			 */
			int px = 11;
			int py =2;
			g.setColor(Color.black);
			g.fillRoundRect(108 + px, 52 + py, 54, 9, 15, 5);
			g.setColor(corLife);
			g.fillRoundRect(110 + px, 54 + py, (int) (Game.player.vida / 2 - Game.player.vidaAdicional), 5, 15, 5);

			g.setColor(Color.black);
			g2.fillOval(38, 34, 67, 66);
			g.setColor(colorAtual);
			g2.fillOval(40, 35, 64, 64);
			g.drawImage(icon[Game.player.getId()], 40, 35, null);
		}

	}

}