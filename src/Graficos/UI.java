package Graficos;

import Configuration.Configuracoes;
import Entidades.Players.Ace;
import Entidades.Players.Player;
import Entidades.Players.Tai;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UI {
	private final BufferedImage[] icone = new BufferedImage[20];
	private final BufferedImage[] hud = new BufferedImage[3];
	int frames = 0;
	int i = 0;
	private Color corBarraMana, corBarraVida, corBarraManaVazia, corBarraVidaVazia;
	private Player player;

	public UI(Player player) {
		this.player = player;
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
	}

	private void hudTai(Graphics2D g, Tai tai) {
		corBarraVida = Color.red;
		corBarraMana = Color.orange;

		Rectangle defense = new Rectangle(101, 41, 0, 2);
		g.setColor(Color.black);
		g.fillRect(100, 40, 103, 4);
		g.setColor(Color.LIGHT_GRAY);
		g.fill(defense);
	}

	private void hudAce(Graphics2D g, Ace ace) {
		corBarraVida = new Color(0, 152, 79);
		corBarraVidaVazia = new Color(0, 38, 17);
		corBarraMana = new Color(133, 148, 144);
		corBarraManaVazia = new Color(34, 38, 36);

		for(int i = 1; i <= ace.getMaxNivelFoco(); i++){
			g.setColor(Color.BLACK);
			g.fillRect(92 + (i * 13), 79, 12, 5);
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
			g.fillRect(92 + (i * 13), 79, 10, 3);
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

		/*
			Hud
		 */
		if (player instanceof Tai) {
			hudTai(g2, player.asTai());
		} else if (player instanceof Ace) {
			hudAce(g2, player.asAce());
		}
		/*
			Barra de vida
		 */
		desenharBarra(g,95, 45, player.getVida(), player.getVidaMaxima(), corBarraVida, corBarraVidaVazia);
		/*
			Barra de mana
		 */
		desenharBarra(g,95, 63, player.getMana(), player.getManaMaxima(), corBarraMana, corBarraManaVazia);
		/*
			Icone
		 */
		g.setColor(Color.black);
		g2.fillOval(38, 34, 67, 66);
		g.setColor(corBarraMana);
		g2.fillOval(40, 35, 64, 64);
		g.drawImage(icone[2], 40, 35, null);

	}

}