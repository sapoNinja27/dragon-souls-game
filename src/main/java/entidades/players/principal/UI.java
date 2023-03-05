package entidades.players.principal;

import entidades.players.principal.Player;
import main.menu.graficos.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    private final Color corBarraMana = new Color(206, 126, 0);
    private final Color corBarraVida = new Color(189, 21, 0);
    private final Color corBarraManaVazia = new Color(100, 57, 0);
    private final Color corBarraVidaVazia = new Color(45, 6, 0);
    private final BufferedImage icone;

    public UI() {
        Spritesheet sprites = new Spritesheet("/menus/icons.png");
        icone = sprites.getSprite(0, 0, 64, 64);
    }

    public void render(Graphics g, Player player) {
        g.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		/*
			Hud
		 */
        drawHud(g);
		/*
			Barra de vida e de mana
		 */
        desenharBarra(g, 95, 45, player.getVida(), player.getVidaMaxima(), corBarraVida, corBarraVidaVazia);
        desenharBarra(g, 95, 63, player.getMana(), player.getManaMaxima(), corBarraMana, corBarraManaVazia);
		/*
			Icone
		 */
        g.setColor(Color.black);
        g.fillOval(38, 34, 67, 66);
        g.setColor(corBarraMana);
        g.fillOval(40, 35, 64, 64);
        g.drawImage(icone, 40, 35, null);
    }

    public void drawHud(Graphics g) {
        g.setColor(new Color(16, 16, 16));
        g.fillRect(95, 84, 101, 4);
        g.setColor(new Color(194, 194, 194));
        g.fillRect(95, 84, 101, 2);
    }

    private void desenharBarra(Graphics g, int x, int y, int atual, int max, Color corBarra, Color corBarraVazia) {
        g.setColor(Color.black);
        g.drawRect(x, y, 101, 16);
        g.setColor(corBarraVazia);
        g.fillRect(x + 1, y + 1, 100, 15);
        g.setColor(corBarra);
        g.fillRect(x + 1, y + 1, 100 * atual / max, 15);
    }
}