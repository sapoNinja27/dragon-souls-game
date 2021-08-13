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
	public void render(Graphics g) {

		if (Game.player.Hudvisivel) {

			Graphics2D g2 = (Graphics2D) g;
			Rectangle barlife = new Rectangle(100, 55, (int) Game.player.maxlife*2, 11);
			Rectangle barstamina = new Rectangle(100, 66, (int)Game.player.stamina*2, 11);
			g.setColor(Color.black);
			g2.fill(barlife);
			g2.fill(barstamina);
			g.setColor(Color.red);
			g.fillRect(100, 55, (int) ((Game.player.life*2)), 11);
			g.setColor(Color.orange);
			g.fillRect(100, 66, (int) ((Game.player.stamina*2) ), 11);
			g.setColor(Color.black);
			g2.draw(barlife);
			g2.draw(barstamina);

			g.setColor(Color.white);
//			g.fillOval(43, 35, 60, 60);

			g.drawImage(icon[Game.player.getId()], 40, 35, null);
			g.setColor(Color.black);
//			g2.drawOval(41, 33, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}

	}

}