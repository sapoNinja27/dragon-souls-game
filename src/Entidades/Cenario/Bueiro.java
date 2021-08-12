package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entidades.Entity;
import Main.Game;
import Menu.Loading;
import World.Camera;

public class Bueiro extends Entity {
	private boolean emFrente;
	private BufferedImage[] img;
	private int frames = 0;
	private float op = 0.1f;

	public Bueiro(int x, int y, BufferedImage[] img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 3;
		setMask(0, 17, -30, 32, 40);
	}

	public void tick() {
		if (this.distanciaX((int) x, Game.player.getX()) < 50 && this.distanciaY((int) y, Game.player.getY()) < 100) {
			if (!Game.player.getDentro()) {
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
	}

	public void checkCollision() {
		if (Entity.isColidding(Game.player, this, 0, 0)) {
			emFrente = true;
			if (Game.clicked) {
				Game.clicked = false;
				Loading.start();
				Game.player.setY(getY() + 898);
				Game.player2.setY(getY() + 898);
				Loading.stop();
			}
		} else {
			emFrente = false;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 500) {
			if (!Game.player.getDentro()) {
				if (!emFrente) {
					g.drawImage(img[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
				} else {
					g.drawImage(img[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
					g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 20));
					g.setColor(Color.white);
					g.drawString("Esgotos", this.getX() - Camera.x - 10, this.getY() - Camera.y + 30);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}
			}
		}
	}
}