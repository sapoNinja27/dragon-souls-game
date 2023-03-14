package main.entidades.cenario.estaticos;

import main.DadosGame;
import main.entidades.Entidade;
import main.entidades.Mascara;
import main.entidades.players.Player;
import main.enums.TipoMascara;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.nonNull;
import static main.enums.MovimentoPlayer.CAINDO;
import static main.enums.MovimentoPlayer.POUSANDO;

public class Plataforma extends Entidade {
	private final BufferedImage img;

	public Plataforma(int x, int y, BufferedImage img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 1;
		adicionarMascara(Mascara.builder()
				.alias("plataforma")
				.x(0)
				.y(0)
				.height(3)
				.width(64)
				.build());
	}

	@Override
	public void tick(DadosGame dadosGame){
		Player player = dadosGame.getPlayer();
		if (colidindoComPlayer(player)) {
			player.getGerenciadorMovimentos().setarAnimacao(POUSANDO);
			player.setY(y - dadosGame.getTileSize());
		}
	}

	@Override
	public void render(Graphics g, DadosGame dadosGame) {
		g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
		super.render(g, dadosGame);
	}
}