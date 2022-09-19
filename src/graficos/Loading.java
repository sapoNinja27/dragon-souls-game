package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import configuracoes.Configuracao;
import utils.ImageUtils;

public class Loading {
	private static boolean iniciando;
	private static boolean carregando;
	private static boolean finalizando;
	private static final BufferedImage[] pixels = new BufferedImage[7];
	private static final Spritesheet loading = new Spritesheet("/loading.png");
	private static int frames = 0, index = 0;

	public static void tick() {
		if (iniciando) {
			frames++;
			if (frames > 10) {
				index++;
				frames = 0;
				if (index == 3) {
					index = 0;
					iniciando = false;
					carregando = true;
				}
			}
		}
		if (carregando) {
			frames++;
			if (frames > 10) {
				index++;
				frames = 0;
				if (index == 3) {
					index = 0;
				}
			}
		}
		if (finalizando) {
			frames++;
			if (frames > 10) {
				index++;
				frames = 0;
				if (index == 7) {
					index = 0;
					carregando = false;
					finalizando = false;
				}
			}
		}
	}
	public static boolean isLoading() {
		return carregando;
	}
	public static void start() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = loading.getSprite(i * Configuracao.TILE_SIZE, 535, Configuracao.TILE_SIZE,
					Configuracao.TILE_SIZE);
		}
		iniciando = true;
	}

	public static void stop() {
		finalizando = true;
	}

	public static void render(Graphics g) {
		if (carregando || iniciando) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 1500, 1000);
			g.setFont(new Font("", Font.PLAIN, 20));
			g.drawImage(ImageUtils.inverter(pixels[index]), 95, 535, Configuracao.TILE_SIZE * 2,
					Configuracao.TILE_SIZE * 2, null);
		}
	}

}