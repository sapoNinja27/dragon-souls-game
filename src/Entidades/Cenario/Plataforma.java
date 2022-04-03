package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entidade;
import Main.Game;
import Menu.MascaraHitBox;
import World.Camera;

public class Plataforma extends Entidade {
	private final BufferedImage img;

	public Plataforma(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 1;
		adicionarMascara(new MascaraHitBox("padrao", 0, 0, 64, 3));
	}

	public void render(Graphics g) {
//		if (this.distanciaX((int) x, Game.player.getX()) < 1000 && this.distanciaY((int) y, Game.player.getY()) < 150) {
//			if (!Game.player.getDentro()) {
//				g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
//			}
//		}
		g.setColor(Color.red);
		g.drawRect(this.getX() - Camera.x + mascaras.get(0).getPosicaoX(), this.getY() - Camera.y + mascaras.get(0).getPosicaoY(), mascaras.get(0).getAutura(), mascaras.get(0).getLargura());
	}
}