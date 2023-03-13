package main.entidades.players.habilidades;

import main.enums.MovimentoPlayer;
import main.utils.Spritesheet;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PosturaCalma extends Habilidade {

    public PosturaCalma(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica(), KeyEvent.VK_3);
    }

    private static String titulo() {
        return "Postura calma";
    }

    private static String descricao() {
        return "Tai assume uma postura calma," +
                "\n" +
                "recebendo regeneração de vida" +
                "\n" +
                "por um curto período.";
    }

    private static String custo() {
        return "Consome 30% da fúria atual";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(128 * 5, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_CALMA;
    }

    private static boolean basica() {
        return false;
    }
}
