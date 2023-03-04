package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaIrritada extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura irada";
    }

    @Override
    public String getDescricao() {
        return "Tai urra, afastando inimigos pr�ximos" +
                "\n" +
                "e roubando brevemente as" +
                "\n" +
                "resist�ncias deles.";
    }

    @Override
    public String getCusto() {
        return "Custa 20 de f�ria";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(128 * 3, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_IRADA;
    }

    @Override
    public boolean isBasica() {
        return false;
    }
}
