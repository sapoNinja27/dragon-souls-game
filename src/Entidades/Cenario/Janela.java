package Entidades.Cenario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Configuration.Configuracoes;
import Entidades.Entidade;
import Menu.MascaraHitBox;
import World.Camera;

public class Janela extends Entidade {
	private final BufferedImage img;

	public Janela(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		depth = 1;
		adicionarMascara(new MascaraHitBox(-25, -20, 46, 80));
		this.img = img;
	}

	public void render(Graphics g) {
        g.drawImage(img, this.getX() - Camera.x - Configuracoes.TILE_SIZE,
                this.getY() - Camera.y - Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE * 2,
                Configuracoes.TILE_SIZE * 2, null);
	}
}