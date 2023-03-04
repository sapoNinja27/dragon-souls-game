package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaOfensiva extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura ofensiva";
    }

    @Override
    public String getDescricao() {
        return "Tai assume uma postura ofensiva," +
                "\n" +
                "fortalecendo brevemente suas" +
                "\n" +
                "resistências a ataques pelas costas." +
                "\n" +
                "\n" +
                "Enquanto isso, ele ataca a sua frente" +
                "\n" +
                "causando dano e afastando inimigos.";
    }

    @Override
    public String getCusto() {
        return "Recebe 30 de fúria";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(0, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_OFENSIVA;
    }

    @Override
    public boolean isBasica() {
        return true;
    }
}
