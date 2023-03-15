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

    //TODO unificar as plataformas em uma classe só
    //TODO construtor passar tipo, no tipo vem a imagem da plataforma
    public Plataforma(int x, int y, BufferedImage img, int depht) {
        super(x, y, 0, 0, depht, 0, 1);
        this.img = img;
        adicionarMascara(Mascara.builder()
                .alias("plataforma")
                .x(0)
                .y(0)
                .height(3)
                .width(64)
                .build());
    }

    @Override
    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (colidindoComPlayer(player)) {
            player.getGerenciadorMovimentos().setarAnimacao(POUSANDO);
            player.move(player.getX(), y - dadosGame.getTileSize());
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.drawImage(img, this.getX() - Camera.x, this.getY() - Camera.y, null);
        super.render(g, dadosGame);
    }
}