package Entidades.Cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Configuration.Configuracoes;
import Entidades.Entidade;
import Graficos.Spritesheet;
import Menu.MascaraHitBox;
import World.Camera;

public class Portao extends Entidade {
	private float op = 0.1f;
	private int frames = 0;
	private final BufferedImage[] img = new BufferedImage[2];

	public Portao(int x, int y, Spritesheet spt) {
		super(x, y, 0, 0);
		depth = 4;
		adicionarMascara(new MascaraHitBox(0, 20, 64, 40));
		for (int i = 0; i < 2; i++) {
			img[i] = spt.getSprite((i) * Configuracoes.TILE_SIZE, 5 * Configuracoes.TILE_SIZE,
					Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE);
		}
	}

	public void tick() {
		if (colidindo) {
			frames++;
			if (frames >= 10) {
				if (op < 0.9f) {
					op += 0.1f;
				}
			}

		} else {
			frames = 0;
			op = 0.1f;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (colidindo) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setFont(new Font("Cambria Math", Font.PLAIN, 15));
			g.setColor(Color.blue);
			g.drawString("Area", this.getX() - Camera.x + 15, this.getY() - Camera.y - 20);
			g.drawString(Configuracoes.nextRota(), this.getX() - Camera.x, this.getY() - Camera.y);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		g.drawImage(img[0], this.getX() - Camera.x - 29, this.getY() - Camera.y + 3, Configuracoes.TILE_SIZE,
				Configuracoes.TILE_SIZE, null);
		g.drawImage(img[1], this.getX() - Camera.x - 29 + 64, this.getY() - Camera.y + 3,
				Configuracoes.TILE_SIZE, Configuracoes.TILE_SIZE, null);
		g.setColor(Color.red);
		g.drawRect(
				this.getX() - Camera.x + mascaras.get(0).getPosicaoX(),
				this.getY() - Camera.y + mascaras.get(0).getPosicaoY(),
				mascaras.get(0).getAutura(),
				mascaras.get(0).getLargura());
	}
}