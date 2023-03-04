package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaFirme extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura firme";
    }

    @Override
    public String getDescricao() {
        return "Tai assume uma postura firme," +
                "\n" +
                "aumentando o equilibrio brevemente." +
                "\n" +
                "\n" +
                "Durante o periodo todos os ataques" +
                "\n" +
                "empurram alvos ao contato.";
    }

    @Override
    public String getCusto() {
        return "Custa 10 de fúria";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(128 * 4, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_FIRME;
    }

    @Override
    public boolean isBasica() {
        return false;
    }
}
