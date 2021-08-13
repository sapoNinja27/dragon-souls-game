package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entity;
import Graficos.Spritesheet;
import Main.Game;
import World.Camera;

public class PosteLuz extends Entity {
	public boolean emFrente;
	private BufferedImage lampada[] = new BufferedImage[3];
	private float op = 0.1f;
	private int frames = 0;

	public PosteLuz(int x, int y, Spritesheet spt) {
		super(x, y, 0, 0);
		setMask(0, -47, -30, 32, 40);
		lampada[0] = spt.getSprite((3) * Configuracoes.TILE_SIZE, (1) * Configuracoes.TILE_SIZE,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE / 2);
		lampada[1] = spt.getSprite((3) * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE + 31,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE / 2 - 31);
		lampada[2] = spt.getSprite((3) * Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE + 31,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE / 2);

	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
			if (!Game.player.getDentro()) {
				if (!Configuracoes.dia) {
					depth = 7;
				} else {
					depth = 4;
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

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 200) {
			if (!Game.player.getDentro()) {
				if (Configuracoes.dia) {
					g.drawImage(lampada[0], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 7, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
					g.drawImage(lampada[1], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE * 2 + 5, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
					g.drawImage(lampada[2], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE + 3, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
				} else {
					g.drawImage(lampada[0], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 7, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
					g.drawImage(lampada[1], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE * 2 + 5, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
					g.drawImage(lampada[2], this.getX() - Camera.x - Configuracoes.TILE_SIZE + 30,
							this.getY() - Camera.y - Configuracoes.TILE_SIZE + 3, Configuracoes.TILE_SIZE * 2,
							Configuracoes.TILE_SIZE, null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
					g.setColor(Color.white);
					g.fillPolygon(
							new int[] { this.getX() - Camera.x - Configuracoes.TILE_SIZE + 3 + 50,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE + 5 + 50,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE + 8 + 50,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE + 18 + 50,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE + 40 + 50,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE - 40 + 50 },
							new int[] { this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 63,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 58,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 55,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 55,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 200,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 197 },
							6);
					int num = 87 + 50;
					g.fillPolygon(
							new int[] { this.getX() - Camera.x - Configuracoes.TILE_SIZE - 3 + num,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE - 5 + num,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE - 8 + num,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE - 18 + num,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE - 40 + num,
									this.getX() - Camera.x - Configuracoes.TILE_SIZE + 40 + num },
							new int[] { this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 63,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 58,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 55,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 55,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 200,
									this.getY() - Camera.y - Configuracoes.TILE_SIZE * 3 + 197 },
							6);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(this.getX() - Camera.x + 40 + Game.rand.nextInt(25) + (10),
							this.getY() - Camera.y + Game.rand.nextInt(20) - 140, 3, 3);
					g.fillOval(this.getX() - Camera.x + 40 + Game.rand.nextInt(25) - 50,
							this.getY() - Camera.y + Game.rand.nextInt(20) - 140, 3, 3);
				}
			}
		}
	}
}