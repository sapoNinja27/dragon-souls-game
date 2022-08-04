package Graficos;

import Configuration.Configuracoes;
import Entidades.Players.Ace;
import Entidades.Players.Player;
import Entidades.Players.Tai;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UI {
	private final BufferedImage[] icone = new BufferedImage[20];
	private final BufferedImage[] hud = new BufferedImage[3];
	private Color corBarraMana, corBarraVida, corBarraManaVazia, corBarraVidaVazia;
	private Player player;

	public UI() {
		Spritesheet img = new Spritesheet("/menus/icons.png");
		for (int i = 0; i < 3; i++) {
			icone[i] = img.getSprite(i * Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
		for (int i = 0; i < 3; i++) {
			hud[i] = img.getSprite((i * 2 + 3) * Configuracoes.TILE_SIZE, 0, Configuracoes.TILE_SIZE * 2,
					Configuracoes.TILE_SIZE);
		}
	}

	public void tick(Player player) {
		this.player = player;
		if (player.isTai()) {
			corBarraVida = new Color(189, 21, 0);
			corBarraVidaVazia = new Color(45, 6, 0);
			corBarraMana = new Color(206, 126, 0);
			corBarraManaVazia = new Color(100, 57, 0);
		}
		if (player.isAce()) {
			corBarraVida = new Color(0, 152, 79);
			corBarraVidaVazia = new Color(0, 38, 17);
			corBarraMana = new Color(133, 148, 144);
			corBarraManaVazia = new Color(34, 38, 36);
		}
	}

	private void hudTai(Graphics2D g, Tai tai) {

		g.setColor(new Color(16, 16, 16));
		g.fillRect(95, 84, 101, 4);
		g.setColor(new Color(194, 194, 194));
		g.fillRect(95, 84, 101, 2);
	}

	private void hudAce(Graphics2D g, Ace ace) {
		for(int i = 1; i <= ace.getMaxNivelFoco(); i++){
			g.setColor(Color.BLACK);
			g.fillRect(79 + (i * 25), 84, 18, 8);
			if(i == ace.getMaxNivelFoco()){
				if(ace.getNivelFoco() == i){
					g.setColor(new Color(239, 239, 29));
				} else {
					g.setColor(new Color(162, 162, 17));
				}
			} else {
				if(ace.getNivelFoco() >= i){
					g.setColor(new Color(218, 218, 185));
				} else {
					g.setColor(new Color(122, 122, 101));
				}
			}
			g.fillRect(79 + (i * 25), 84, 16, 6);
		}
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
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		int indexPlayer = 0;
		/*
			Hud
		 */
		if (player.isTai()) {
			hudTai(g2, player.asTai());
			indexPlayer = 0;
		}
		if (player.isAce()) {
			hudAce(g2, player.asAce());
			indexPlayer = 2;
		}
		/*
			Barra de vida e de mana
		 */
		desenharBarra(g,95, 45, player.getVida(), player.getVidaMaxima(), corBarraVida, corBarraVidaVazia);
		desenharBarra(g,95, 63, player.getMana(), player.getManaMaxima(), corBarraMana, corBarraManaVazia);
		/*
			Icone
		 */
		g.setColor(Color.black);
		g2.fillOval(38, 34, 67, 66);
		g.setColor(corBarraMana);
		g2.fillOval(40, 35, 64, 64);
		g.drawImage(icone[indexPlayer], 40, 35, null);
	}
}