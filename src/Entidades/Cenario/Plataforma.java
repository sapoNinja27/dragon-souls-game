package Entidades.Cenario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entidades.Entidade;
import Menu.MascaraHitBox;
import World.Camera;

public class Plataforma extends Entidade {
	private final BufferedImage img;

	public Plataforma(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 1;
		adicionarMascara(new MascaraHitBox(0, 0, 64, 3));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
		g.setColor(Color.red);
		g.drawRect(
				this.getX() - Camera.x + mascaras.get(0).getPosicaoX(),
				this.getY() - Camera.y + mascaras.get(0).getPosicaoY(),
				mascaras.get(0).getAutura(),
				mascaras.get(0).getLargura());
	}
}