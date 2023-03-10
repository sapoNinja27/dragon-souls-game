package main.interfaces.menu;

import main.enums.TipoFonte;
import main.interfaces.mouse.Mouse;
import main.DadosGame;
import main.enums.TipoMenu;
import main.utils.Fontes;
import main.utils.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.util.Objects.nonNull;
import static main.utils.ImageUtils.draw;
import static main.utils.ImageUtils.fill;

public class Menu {
    protected final Color bordaMenu;
    protected final Color fundoMenu;
    protected final int x = 60;
    protected final int y = 100;
    private final int width;
    private final int height;
    protected BufferedImage fundo;
    protected BufferedImage[] img;
    private int posx = 0;
    private final String titulo;

    public Menu(String titulo, int width, int height) {
        bordaMenu = new Color(173, 8, 0);
        fundoMenu = new Color(173, 8, 0, 34);
        this.width = width;
        this.height = height;
        this.titulo = titulo;
        Spritesheet spritesheet = new Spritesheet("/menus/background.png");
        this.fundo = spritesheet.getSprite();
    }

    private void atualizarFundo() {
        posx++;
        if (posx + 1440 == 2976) {
            posx = 0;
        }
    }

    protected void desenharFundoMenu(int x, int y, int width, int height, Graphics g) {
        g.setColor(fundoMenu);
        g.fillRect(x, y + 30, width, height);
    }

    protected void desenharBordaFundoMenu(int x, int y, int width, int height, Graphics g) {
        g.setColor(bordaMenu);
        g.drawRect(x, y + 30, width, height);
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
        g.setColor(Color.WHITE);
        Font font = Fontes.CrimsonText(TipoFonte.REGULAR, 35);
        g.setFont(font);
        g.drawString(titulo, x, y - 40);
        desenharBordaFundoMenu(x, y, width, height, g);
        desenharFundoMenu(x, y, width, height, g);
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
