package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Main.Game;
import World.Camera;

public class EscadaEsgoto extends Entity {
	public boolean emFrente;
	private BufferedImage img[] = new BufferedImage[2];
	private float op = 0.1f;
	private int frames = 0;

	public EscadaEsgoto(int x, int y) {
		super(x, y, 0, 0);
		depth = 0;
		setMask(0, 18, 0, 31, 40);
		img[0] = Game.cenario.getSprite(0 * Configuracoes.TILE_SIZE, (6) * Configuracoes.TILE_SIZE,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		img[1] = Game.cenario.getSprite(0 * Configuracoes.TILE_SIZE, (6) * Configuracoes.TILE_SIZE,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE - 3);
	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 500 && this.distanciaY((int) y, Game.player.getY()) < 500) {
			checkCollision();
			if (emFrente) {
				frames++;
				if (frames >= 10) {
					if (op < 0.9f) {
						op += 0.1f;
					}
				}

			} else {
				frames = 0;
				op = 0.1f;
			}
		}

	}

	public void checkCollision() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			emFrente = true;
			if (Game.clicked) {
				Game.clicked = false;
				Game.player.setY(getY() - 960);
				Game.player2.setY(getY() - 960);
			}
		} else {
			emFrente = false;
		}
	}

	public void render(Graphics g) {
		if (this.distanciaX((int) x, Game.player.getX()) < 500 && this.distanciaY((int) y, Game.player.getY()) < 500) {
			Graphics2D g2 = (Graphics2D) g;
			for (int i = 0; i < 7; i++) {
				g.drawImage(img[1], this.getX() - Camera.x - 27, this.getY() - Camera.y - (60 * i),
						Configuracoes.TILE_SIZE * 2, Configuracoes.TILE_SIZE, null);
			}
			g.drawImage(img[0], this.getX() - Camera.x - 27, this.getY() - Camera.y, Configuracoes.TILE_SIZE * 2,
					Configuracoes.TILE_SIZE, null);
			if (emFrente) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
				g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 20));
				g.setColor(Color.white);
				g.drawString("Cidade", this.getX() - Camera.x + 5, this.getY() - Camera.y - 20);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
			g.setColor(Color.red);
			g.drawRect(this.getX() - Camera.x + maskx[0], this.getY() - Camera.y + masky[0], maskw[0], maskh[0]);

		}
	}

}