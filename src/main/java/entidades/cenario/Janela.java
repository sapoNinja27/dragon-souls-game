package entidades.cenario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entidades.Entidade;
import entidades.mascaras.MascaraHitBox;
import world.Camera;

public class Janela extends Entidade {
	private final BufferedImage img;

	public Janela(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		depth = 1;
		adicionarMascara(new MascaraHitBox(-25, -20, 46, 80));
		this.img = img;
	}

	public void render(Graphics g) {
//        g.drawImage(img, this.getX() - Camera.x - Configuracao.TILE_SIZE,
//                this.getY() - Camera.y - Configuracao.TILE_SIZE, Configuracao.TILE_SIZE * 2,
//                Configuracao.TILE_SIZE * 2, null);
	}
}