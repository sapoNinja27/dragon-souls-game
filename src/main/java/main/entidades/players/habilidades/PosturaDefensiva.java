package main.entidades.players.habilidades;

import main.enums.MovimentoPlayer;
import main.utils.Spritesheet;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PosturaDefensiva extends Habilidade {

    public PosturaDefensiva(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica(), KeyEvent.VK_2);
    }

    private static String titulo() {
        return "Postura defensiva";
    }

    private static String descricao() {
        return "Tai assume uma postura defensiva," +
                "\n" +
                "ignorando todo dano à sua frente" +
                "\n" +
                "brevemente.";
    }

    private static String custo() {
        return "Recebe 10% do dano defendido como fúria";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(128, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_DEFENSIVA;
    }

    private static boolean basica() {
        return false;
    }
}
