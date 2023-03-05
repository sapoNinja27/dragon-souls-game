package entidades.cenario;

import entidades.Entidade;
import main.DadosGame;
import main.menu.graficos.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fundo extends Entidade {

    private final BufferedImage fundo;

    public Fundo(int x, int y, int width, int height) {
        super(x, y, width, height);
        depth = 0;
        Spritesheet spritesheet = new Spritesheet("/bgtest.jpg");
        fundo = spritesheet.getSprite();
    }

    @Override
    public void tick(DadosGame dadosGame) {
        super.tick(dadosGame);
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        g.drawImage(fundo, this.getX(), this.getY(), dadosGame.getWidth(), dadosGame.getHeight(), null);
        super.render(g, dadosGame);
    }
}
