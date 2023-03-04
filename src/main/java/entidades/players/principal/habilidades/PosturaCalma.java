package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaCalma extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura calma";
    }

    @Override
    public String getDescricao() {
        return "Tai assume uma postura calma," +
                "\n" +
                "recebendo regenera��o de vida" +
                "\n" +
                "por um curto per�odo.";
    }

    @Override
    public String getCusto() {
        return "Consome 30% da f�ria atual";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(128 * 5, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_CALMA;
    }

    @Override
    public boolean isBasica() {
        return false;
    }
}
