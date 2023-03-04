package entidades.cenario;

import main.DadosGame;
import entidades.Entidade;
import entidades.Mascara;
import main.enums.TipoMascara;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Plataforma extends Entidade {
	private final BufferedImage img;

	public Plataforma(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 1;
		adicionarMascara(Mascara.builder()
				.tipoMascara(TipoMascara.HITBOX)
				.x(0)
				.y(0)
				.altura(3)
				.largura(64)
				.build());
	}

	@Override
	public void render(Graphics g, DadosGame dadosGame) {
		g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
		super.render(g, dadosGame);
	}
}