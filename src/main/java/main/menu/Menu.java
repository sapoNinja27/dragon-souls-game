package main.menu;

import jObjects.Mouse.Mouse;
import main.DadosGame;
import main.enums.TipoMenu;
import main.menu.graficos.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.utils.ImageUtils.draw;
import static main.utils.ImageUtils.fill;

public class Menu {
    protected final Color bordaMenu;
    protected final Color fundoMenu;

    protected BufferedImage fundo;

    int posx = 0;

    public Menu() {
        bordaMenu = new Color(173, 8, 0);
        fundoMenu = new Color(173, 8, 0, 34);
        Spritesheet spritesheet = new Spritesheet("/menus/background.png");
        fundo = spritesheet.getSprite();
    }

    private void atualizarFundo() {
        posx++;
        if (posx + 1440 == 2976) {
            posx = 0;
        }
    }

    protected void desenharFundoMenu(int x, int y, Graphics g) {
        g.setColor(fundoMenu);
        g.fillRect(x, y + 30, 1000, 535);
    }

    protected void desenharBordaFundoMenu(int x, int y, Graphics g) {
        g.setColor(bordaMenu);
        g.drawRect(x, y + 30, 1000, 535);
    }

    protected void desenharLayoutDescricao(int x, int y, Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize(3);
        fill(g, x + 1050, y + 30, new Color(41, 161, 236), 300, tileSize);
        draw(g, x + 1050, y + 30, bordaMenu, 300, 535);
        fill(g, x + 1050, y + 30, fundoMenu, 300, 535);
        draw(g, x + 1050, y + 30 + tileSize, bordaMenu, 300, 50);
        fill(g, x + 1050, y + 30 + tileSize, fundoMenu, 300, 50);
    }

    public void tick(DadosGame dadosGame, Mouse mouse) {
        atualizarFundo();
    }

    public void render(Graphics g, DadosGame dadosGame) {
        backgroundTela(g, dadosGame);
    }

    private void backgroundTela(Graphics g, DadosGame dadosGame) {
        Graphics2D g2 = (Graphics2D) g;
        if (!dadosGame.getEstadoMenu().equals(TipoMenu.INICIAL)) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        }
        g.drawImage(fundo, posx, 0, null);
        g.drawImage(fundo, posx - fundo.getWidth(), 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
