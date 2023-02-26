package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaDefensiva extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura defensiva";
    }

    @Override
    public String getDescricao() {
        return "Tai assume uma postura defensiva," +
                "\n" +
                "ignorando todo dano à sua frente" +
                "\n" +
                "brevemente.";
    }

    @Override
    public String getCusto() {
        return "Recebe 10% do dano defendido como fúria";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(128, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_DEFENSIVA;
    }

    @Override
    public boolean isBasica() {
        return true;
    }
}
