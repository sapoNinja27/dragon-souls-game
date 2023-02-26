package entidades.players.tai.habilidades;

import entidades.players.Habilidade;
import enums.MovimentoPlayer;

import java.awt.image.BufferedImage;

public class PosturaSelvagem extends Habilidade {

    @Override
    public String getTitulo() {
        return "Postura selvagem";
    }

    @Override
    public String getDescricao() {
        return "Tai permite que seu lado maligno " +
                "\n" +
                "Assuma o controle." +
                "\n" +
                "\n" +
                "Durante esse período:" +
                "\n" +
                "* Recebe um novo set de habilidades" +
                "\n" +
                "* Aumenta o dano de ataque" +
                "\n" +
                "* Recebe roubo de vida baseada na " +
                "\n" +
                "vida perdida" +
                "\n" +
                "* Reduz muito a própria defesa" +
                "\n" +
                "\n" +
                "Ao final do efeito sofre uma penalidade" +
                "\n" +
                "de 15% da vida máxima, permanente." +
                "\n" +
                "\n" +
                "Ficar sem fúria encerra a transformação.";
    }

    @Override
    public String getCusto() {
        return "";
    }

    @Override
    public BufferedImage getIcone() {
        return sprite.getSprite(128 * 2, 0, 128, 128);
    }

    @Override
    public MovimentoPlayer getMovimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_SELVAGEM;
    }

    @Override
    public boolean isBasica() {
        return true;
    }
}
