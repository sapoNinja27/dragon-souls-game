package Graficos;

import Entidades.Players.Player;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UI {
	private Color corBarraMana, corBarraVida, corBarraManaVazia, corBarraVidaVazia;
	private Player player;
	public UI() {}

	public void atualizarPlayer(Player player){
		this.player = player;
	}
	public void tick() {
		corBarraVida = player.getCoresSet().get("corBarraVida");
		corBarraVidaVazia = player.getCoresSet().get("corBarraVidaVazia");
		corBarraMana = player.getCoresSet().get("corBarraMana");
		corBarraManaVazia = player.getCoresSet().get("corBarraManaVazia");
	}

	private void desenharBarra(Graphics g, int x, int y, int atual, int max, Color corBarra, Color corBarraVazia){
		g.setColor(Color.black);
		g.drawRect(x, y, 101, 16);
		g.setColor(corBarraVazia);
		g.fillRect(x + 1, y + 1, 100, 15);
		g.setColor(corBarra);
		g.fillRect(x + 1, y + 1, 100 * atual / max, 15);
		g.setColor(Color.white);
		List<String> chars = Arrays.asList(String.valueOf(atual).split(""));
		Collections.reverse(chars);
		for (int i = 0; i < chars.size(); i++) {
			String casa = chars.get(i);
			g.drawString(casa, x + 40 - (7 * i),  y + 12);
		}
		g.drawString("|", x + 50, y + 12);
		g.drawString(String.valueOf(max), x + 55, y + 12);
	}

	public void render(Graphics g) {
		g.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		/*
			Hud
		 */
		player.drawHud(g);
		/*
			Barra de vida e de mana
		 */
		desenharBarra(g,95, 45, player.getVida(), player.getVidaMaxima(), corBarraVida, corBarraVidaVazia);
		desenharBarra(g,95, 63, player.getMana(), player.getManaMaxima(), corBarraMana, corBarraManaVazia);
		/*
			Icone
		 */
		g.setColor(Color.black);
		g.fillOval(38, 34, 67, 66);
		g.setColor(corBarraMana);
		g.fillOval(40, 35, 64, 64);
		g.drawImage(player.getIcone(), 40, 35, null);
	}
}