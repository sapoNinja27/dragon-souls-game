package entidades.players.principal.habilidades;

import main.enums.MovimentoPlayer;
import main.menu.graficos.Spritesheet;

import java.awt.image.BufferedImage;

public class PosturaFirme extends Habilidade {

    public PosturaFirme(int x, int y, int width, int heigth) {
        super(x, y, width, heigth, titulo(), descricao(), custo(), icone(), movimentoPlayer(), basica());
    }

    private static String titulo() {
        return "Postura firme";
    }

    private static String descricao() {
        return "Tai assume uma postura firme," +
                "\n" +
                "aumentando o equilibrio brevemente." +
                "\n" +
                "\n" +
                "Durante o periodo todos os ataques" +
                "\n" +
                "empurram alvos ao contato.";
    }

    private static String custo() {
        return "Custa 10 de fúria";
    }

    private static BufferedImage icone() {
        Spritesheet sprite = new Spritesheet("/menus/Menu.png");
        return sprite.getSprite(128 * 4, 0, 128, 128);
    }

    private static MovimentoPlayer movimentoPlayer() {
        return MovimentoPlayer.HABILIDADE_POSTURA_FIRME;
    }

    private static boolean basica() {
        return false;
    }
}
