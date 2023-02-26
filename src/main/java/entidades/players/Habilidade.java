package entidades.players;

import configuracoes.DadosGame;
import graficos.Spritesheet;
import interfaces.HabilidadesCommons;
import interfaces.MenuCommons;
import jObjects.Mouse.Mouse;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Habilidade implements HabilidadesCommons, MenuCommons {
    private int x, y;
    protected Spritesheet sprite = new Spritesheet("/menus/Menu.png");
    private int nivel;
    private int melhoria;
    private final List<BufferedImage> filhas = new ArrayList<>();

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMelhoria() {
        return melhoria;
    }

    public void setMelhoria(int melhoria) {
        this.melhoria = melhoria;
    }

    public boolean isOver() {
        if (Mouse.isOver(getShape(x, y))) {
            Mouse.over = true;
            return true;
        } else {
            Mouse.over = false;
            return false;
        }
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g, DadosGame dadosGame) {
        g.fillRect(Mouse.getX(), Mouse.getY(), 1, 1);
        g.drawImage(getIcone(), x, y, dadosGame.getTileSize(), dadosGame.getTileSize(), null);

        ImageUtils.drawPolygon(getBlur(), g, getShape(x, y));
    }

    private Polygon getShape(int x, int y) {
        return new Polygon(
                new int[]
                        {
                                x,
                                x + 18,
                                x + 44,
                                x + 64,
                                x + 64,
                                x + 44,
                                x + 18,
                                x
                        },
                new int[]
                        {
                                y + 18,
                                y,
                                y,
                                y + 18,
                                y + 44,
                                y + 64,
                                y + 64,
                                y + 44
                        },
                8);
    }

    private Color getBlur() {
        if (isOver()) {
            return new Color(0, 0, 0, 60);
        } else {
            return new Color(0, 0, 0, 0);
        }
    }
}
