package menu;

import main.DadosGame;
import main.enums.TipoMenu;
import graficos.Spritesheet;

import java.awt.*;

import static main.utils.ImageUtils.draw;
import static main.utils.ImageUtils.fill;

public class Menu {
    protected final Color bordaMenu;
    private final Color fundoMenu;

    protected Spritesheet fundo = new Spritesheet("/menus/background.png");

    int posx = 0;

    public Menu() {
        bordaMenu = new Color(173, 8, 0);
        fundoMenu = new Color(173, 8, 0, 34);
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

    public void tick(DadosGame dadosGame) {
        atualizarFundo();
    }

    public void render(Graphics g, DadosGame dadosGame) {
        backgroundTela(g, dadosGame);
    }

    private void backgroundTela(Graphics g, DadosGame dadosGame) {
        Graphics2D g2 = (Graphics2D) g;
        int alfa = 0;
        if(!dadosGame.getEstadoMenu().equals(TipoMenu.INICIAL)){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
            alfa = 95;
        }
        g.drawImage(fundo.getSprite(posx, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight()), 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        g.setColor(new Color(56, 0, 0, alfa));
//        g.fillRect(0, 0, dadosGame.getScaleWidth(), dadosGame.getScaleHeight());
    }
}
