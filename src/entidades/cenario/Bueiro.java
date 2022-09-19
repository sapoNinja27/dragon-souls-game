package entidades.cenario;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entidades.Entidade;
import entidades.mascaras.MascaraHitBox;
import world.Camera;

public class Bueiro extends Entidade {
	private final BufferedImage[] img;
	private int frames = 0;
	private float op = 0.1f;

	public Bueiro(int x, int y, BufferedImage[] img) {
		super(x, y, 0, 0);
		this.img = img;
		depth = 3;
		adicionarMascara(new MascaraHitBox(17, -30, 32, 40));
	}

	@Override
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
		if (!colidindo) {
			g.drawImage(img[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else {
			g.drawImage(img[1], this.getX() - Camera.x, this.getY() - Camera.y, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
			g.setFont(new Font("Cambria Math", Font.PLAIN, 20));
			g.setColor(Color.white);
			g.drawString("Esgotos", this.getX() - Camera.x - 10, this.getY() - Camera.y + 30);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		}
}