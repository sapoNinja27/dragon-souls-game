package main.entidades.cenario;

import main.entidades.Entidade;
import main.DadosGame;
import main.utils.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fundo extends Entidade {

    private final BufferedImage fundo;

    public Fundo(int x, int y, int width, int height) {
        super(x, y, width, height, 0, 0, 1);
        Spritesheet spritesheet = new Spritesheet("/bg_test_2.png");
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
