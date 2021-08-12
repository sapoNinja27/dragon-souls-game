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

public class PortaFora extends Entity {
	public boolean emFrente;
	private int frames = 0;
	private float op = 0.f;
	private BufferedImage[] porta;
	boolean ativa;

	public PortaFora(int id, int x, int y, BufferedImage[] porta) {
		super(x, y, 0, 0);
		this.porta = porta;
		depth = 10;
		setMask(0, 0 - 25, -20, 46, 80);
	}

	public void checkCollision() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			emFrente = true;
		} else {
			emFrente = false;
		}
	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			checkCollision();
			if (emFrente) {
				frames++;
				if (frames >= 10) {
					if (op < 0.7f) {
						op += 0.1f;
					}
				}
			} else {
				frames = 0;
				op = 0.1f;
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			if (ativa) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
				if (!emFrente) {
					g.drawImage(porta[0], this.getX() - Camera.x - Configuracoes.TILE_SIZE,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE * 2, null);
				} else {
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
					g.drawImage(porta[1], this.getX() - Camera.x - Configuracoes.TILE_SIZE,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE * 2, null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 20));
					g.setColor(new Color(0, 15, 100));
					g.drawString("Sair", this.getX() - Camera.x - 17, this.getY() - Camera.y - 65);
				}
			}
		}
	}
}