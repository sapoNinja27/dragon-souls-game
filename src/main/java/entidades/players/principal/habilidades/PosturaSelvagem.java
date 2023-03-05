package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;
import main.menu.graficos.Spritesheet;

import java.awt.image.BufferedImage;

public class PosturaSelvagem extends Habilidade {

    public PosturaSelvagem(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica());
    }

    private static String titulo() {
        return "Postura selvagem";
    }

    private static String descricao() {
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

    private static String custo() {
        return "";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(128 * 2, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_SELVAGEM;
    }

    private static boolean basica() {
        return true;
    }
}
