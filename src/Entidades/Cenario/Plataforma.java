package Entidades.Cenario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entity;
import Main.Game;
import World.Camera;

public class Plataforma extends Entity {
	private BufferedImage img;

	public Plataforma(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 1;
		setMask(0, 0, -3, 64, 3);
	}

	public void render(Graphics g) {
		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 100) {
			if (!Game.player.getDentro()) {
				g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
	}
}
