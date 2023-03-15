package main.entidades.players;

import main.entidades.players.habilidades.Habilidade;
import main.utils.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class UI {
    private final Color corBarraMana = new Color(206, 126, 0);
    private final Color corBarraVida = new Color(189, 21, 0);
    private final Color corBarraManaVazia = new Color(100, 57, 0);
    private final Color corBarraVidaVazia = new Color(45, 6, 0);
    private final BufferedImage icone;
    private final BufferedImage hudBg;

    public UI() {
        Spritesheet sprites = new Spritesheet("/menus/icons.png");
        icone = sprites.getSprite(0, 0, 64, 64);
        hudBg = new Spritesheet("/menus/hud_bg.png").getSprite();
    }

    private int posHudHelp = 0;

    public void render(Graphics g, Player player) {
        g.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		/*
			Hud
		 */
        if (player.isShowHudHelp()) {
            posHudHelp -= 7;
            if (posHudHelp <= 0) {
                posHudHelp = 0;
            }
        } else {
            posHudHelp += 7;
            if (posHudHelp >= 220) {
                posHudHelp = 220;
            }
        }
        drawHud(g, player, posHudHelp);
		/*
			Barra de vida e de mana
		 */
        desenharBarra(g, 95, 45, (int) player.getVida(), player.getVidaMaxima(), corBarraVida, corBarraVidaVazia);
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

    final int[] frames = {0, 0, 0, 0, 0, 0};

    public void drawHud(Graphics g, Player player, int posX) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(posX + 545, 41 + 10, 32 * 6, 45, 10, 10);
        g.fillRoundRect(posX + 500, 15, 32 * 3, 45, 10, 10);
        List<Habilidade> basicas = player.getHabilidades().stream().filter(habilidade -> !habilidade.isTransformacao()).collect(Collectors.toList());
        List<Habilidade> avancadas = player.getHabilidades().stream().filter(Habilidade::isTransformacao).collect(Collectors.toList());
        for (int i = 0; i < basicas.size(); i++) {
            drawHabilidade(posX + 550 + i * 40, 45 + 10, g, i, basicas.get(i), player.getGerenciadorMovimentos().inCoolDown(basicas.get(i).getMovimentoPlayer()));
        }
        drawHabilidade(posX + 510, 45 - 25, g, 4, avancadas.get(0), player.getGerenciadorMovimentos().inCoolDown(avancadas.get(0).getMovimentoPlayer()));
        drawHabilidade(posX + 510 + 40, 45 - 25, g, 4, avancadas.get(1), player.getGerenciadorMovimentos().inCoolDown(avancadas.get(1).getMovimentoPlayer()));
        //        g.setColor(new Color(16, 16, 16));
//        g.fillRect(95, 84, 101, 4);
//        g.setColor(new Color(194, 194, 194));
//        g.fillRect(95, 84, (player.getDefesa() * 100) / 257, 2);
    }


    public void drawHabilidade(int x, int y, Graphics g, int index, Habilidade habilidade, boolean inCooldown) {
        //TODO cd reduc
        int coolDown = (int) habilidade.getMovimentoPlayer().getInitialCooldown();
        if (inCooldown) {
            frames[index]--;
            if (frames[index] <= 0) {
                frames[index] = (coolDown + 1) * 60;
            }
        } else {
            frames[index] = 0;
        }
        int size = 32;
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf((char) habilidade.getActionButton()), x + 35, y + 35);
        g.drawImage(habilidade.getIcone(), x, y, size, size, null);
        g.setColor(new Color(0, 0, 0, 147));
        int height = Math.max((int) (((double) (frames[index] / 60 * 100 / coolDown) / 100) * size), 0);
        g.fillRect(x, y + size - height, size, height);
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