package entidades.cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import configuracoes.Configuracao;
import entidades.Entidade;
import graficos.Spritesheet;
import entidades.mascaras.MascaraHitBox;
import world.Camera;

public class EscadaEsgoto extends Entidade {
	private final BufferedImage[] img = new BufferedImage[2];
	private float op = 0.1f;
	private int frames = 0;

	public EscadaEsgoto(int x, int y, Spritesheet spt) {
		super(x, y, 0, 0);
		depth = 0;
		adicionarMascara(new MascaraHitBox(18, 0, 31, 40));
		img[0] = spt.getSprite(0, (6) * Configuracao.TILE_SIZE,
				Configuracao.TILE_SIZE, Configuracao.TILE_SIZE);
		img[1] = spt.getSprite(0, (6) * Configuracao.TILE_SIZE,
				Configuracao.TILE_SIZE, Configuracao.TILE_SIZE - 3);
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
        for (int i = 0; i < 7; i++) {
            g.drawImage(img[1], this.getX() - Camera.x - 27, this.getY() - Camera.y - Configuracao.TILE_SIZE- (Configuracao.TILE_SIZE * i),
                    Configuracao.TILE_SIZE * 2, Configuracao.TILE_SIZE, null);
        }
        g.drawImage(img[0], this.getX() - Camera.x - 27, this.getY() - Camera.y, Configuracao.TILE_SIZE * 2,
                Configuracao.TILE_SIZE, null);
        if (colidindo) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(new Font("Cambria Math", Font.PLAIN, 20));
            g.setColor(Color.white);
            g.drawString("Cidade", this.getX() - Camera.x + 5, this.getY() - Camera.y - 20);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
	}

}