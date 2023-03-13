package main.entidades.players.habilidades;

import main.enums.MovimentoPlayer;
import main.utils.Spritesheet;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PosturaOfensiva extends Habilidade {

    public PosturaOfensiva(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica(), KeyEvent.VK_1);
    }

    private static String titulo() {
        return "Postura ofensiva";
    }

    private static String descricao() {
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

    private static String custo() {
        return "Recebe 30 de fúria";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(0, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_OFENSIVA;
    }

    private static boolean basica() {
        return false;
    }
}
