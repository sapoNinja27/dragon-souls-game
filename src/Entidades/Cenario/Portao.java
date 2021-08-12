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
import World.World;
import enums.TipoAmbiente;

public class Portao extends Entity {
	private boolean emFrente;
	private float op = 0.1f;
	private int frames = 0;
	private BufferedImage[] porta1;

	public Portao(int x, int y) {
		super(x, y, 0, 0);
		porta1 = new BufferedImage[2];
		depth = 4;
		setMask(0, 0, 20, 64, 40);
		for (int i = 0; i < 2; i++) {
			porta1[i] = Game.cenario.getSprite((0 + i) * Configuracoes.TILE_SIZE, 1 * Configuracoes.TILE_SIZE,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			if (!Game.player.getDentro()) {
				if (Configuracoes.local == TipoAmbiente.RUA) {
					checkCollisionPortao();
				}
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
	}

	public void checkCollisionPortao() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			emFrente = true;
			if (Game.clicked) {
				Game.clicked = false;
				Configuracoes.p1 = Game.player;
				Configuracoes.p2 = Game.player2;
				World.changeArea();
			}
		} else {
			emFrente = false;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			if (!Game.player.getDentro()) {
				if (emFrente) {
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
					g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 15));
					g.setColor(Color.blue);
					g.drawString("Area", this.getX() - Camera.x + 15, this.getY() - Camera.y - 20);
					g.drawString(Configuracoes.nextRota(), this.getX() - Camera.x, this.getY() - Camera.y);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}
				g.drawImage(
						Game.cenario.getSprite(0, 5 * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE,
								Configuracoes.TILE_SIZE),
						this.getX() - Camera.x - 29, this.getY() - Camera.y + 3, Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE, null);
				g.drawImage(
						Game.cenario.getSprite(1 * Configuracoes.TILE_SIZE, 5 * Configuracoes.TILE_SIZE,
								Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE),
						this.getX() - Camera.x - 29 + 64, this.getY() - Camera.y + 3, Configuracoes.TILE_SIZE,
						Configuracoes.TILE_SIZE, null);
			}
			g.setColor(Color.red);
			g.drawRect(this.getX() - Camera.x + maskx[0], this.getY() - Camera.y + masky[0], maskw[0], maskh[0]);
		}
	}
}