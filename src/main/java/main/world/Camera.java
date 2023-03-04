package main.world;

import main.DadosGame;
import entidades.players.principal.Player;

public class Camera {

    public static int x = 0;
    public static int y = 0;

    private static int clamp(int atual, int max) {
        if (atual < 0) {
            atual = 0;
        }

        if (atual > max) {
            atual = max;
        }

        return atual;
    }
    public static void updateCamera(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        x = clamp(
                250 + player.getX() - (dadosGame.getWidth() / 2),
                dadosGame.getTileSize(dadosGame.getWordWidth()) - dadosGame.getWidth()
        );
        y = clamp(
                player.getY() - (dadosGame.getHeight() / 2) - 53,
                dadosGame.getTileSize(dadosGame.getWordHeight()) - dadosGame.getHeight()
        );
    }

}
