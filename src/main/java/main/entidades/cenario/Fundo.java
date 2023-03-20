package main.entidades.cenario;

import main.entidades.Entidade;
import main.DadosGame;
import main.entidades.players.Player;
import main.enums.DirecaoPlayer;
import main.utils.Spritesheet;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.enums.MovimentoPlayer.PARANDO;
import static main.utils.ImageUtils.aplicarColoracao;

public class Fundo extends Entidade {

    private final BufferedImage[] fundo = new BufferedImage[3];
    private final int wordWidth;

    public Fundo(int x, int y, int width, int height, int wordWidth) {
        super(x, y, width, height, 0, 3, 1);
        fundo[2] = new Spritesheet("/bg_test_2.png").getSprite();
        fundo[0] = aplicarColoracao(new Spritesheet("/cenario/fundos/esgoto.png").getSprite(), new Color(57, 73, 41, 140), Color.black, false);
        this.wordWidth = wordWidth;
    }

    @Override
    public void tick(DadosGame dadosGame) {
        Player player = dadosGame.getPlayer();
        if (!player.getGerenciadorMovimentos().noAr()) {
            y = player.getY();
        }
        super.tick(dadosGame);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int initialX = getX() - Camera.x;
        drawClose(g, initialX, getY() - dadosGame.getTileSize(5.5) - Camera.y, fundo[dadosGame.getLocal().getIndex()]);
        super.render(g, dadosGame);
    }

    private void drawClose(Graphics g, int x, int y, BufferedImage spriteAtual) {
        for (int i = 0; i < wordWidth / width + 1; i++) {
            g.drawImage(spriteAtual, x + (i * width), y, width, height, null);
        }
    }
}
