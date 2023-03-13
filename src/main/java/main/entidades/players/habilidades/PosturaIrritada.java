package main.entidades.players.habilidades;

import main.enums.MovimentoPlayer;
import main.utils.Spritesheet;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PosturaIrritada extends Habilidade {

    public PosturaIrritada(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica(), KeyEvent.VK_4);
    }

    private static String titulo() {
        return "Postura irada";
    }

    private static String descricao() {
        return "Tai urra, afastando inimigos próximos" +
                "\n" +
                "e roubando brevemente as" +
                "\n" +
                "resistências deles.";
    }

    private static String custo() {
        return "Custa 20 de fúria";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(128 * 3, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_IRADA;
    }

    private static boolean basica() {
        return false;
    }
}
