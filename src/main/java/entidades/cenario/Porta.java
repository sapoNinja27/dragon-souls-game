package entidades.cenario;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entidades.Entidade;

public class Porta extends Entidade {
	public boolean emFrente;
	private int frames = 0;
	private float op = 0.f;
	private BufferedImage[] porta;
	private int id;
	private boolean dentro;
	private String acao;

	public Porta(int x, int y, BufferedImage[] porta) {
		super(x, y, 0, 0);
		this.porta = porta;
		depth = 1;
//		adicionarMascara(new MascaraHitBox(- 25, -20, 46, 80));
	}

	public void tick() {
        if (emFrente) {
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
//		if (!colidindo) {
//			g.drawImage(porta[0], this.getX() - Camera.x - Configuracao.TILE_SIZE,
//					this.getY() - Camera.y - Configuracao.TILE_SIZE, Configuracao.TILE_SIZE * 2,
//					Configuracao.TILE_SIZE * 2, null);
//		} else {
//			g.drawImage(porta[1], this.getX() - Camera.x - Configuracao.TILE_SIZE,
//					this.getY() - Camera.y - Configuracao.TILE_SIZE, Configuracao.TILE_SIZE * 2,
//					Configuracao.TILE_SIZE * 2, null);
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
//			g.setFont(new Font("Cambria Math", Font.ROMAN_BASELINE, 20));
//			g.setColor(Color.blue);
//			g.drawString(acao, this.getX() - Camera.x - 27, this.getY() - Camera.y - 65);
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//		}

	}
}