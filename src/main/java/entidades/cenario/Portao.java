package entidades.cenario;

import java.awt.*;
import java.awt.image.BufferedImage;

import entidades.Mascara;
import main.DadosGame;
import entidades.Entidade;
import main.menu.graficos.Spritesheet;
import main.world.Camera;

public class Portao extends Entidade {
    private float op = 0.1f;
    private int frames = 0;
    private final BufferedImage[] img = new BufferedImage[2];

    public Portao(int x, int y, Spritesheet spt, int width, int height) {
        super(x, y, width, height);
        depth = 4;
        adicionarMascara(Mascara
                .builder()
                .x(0)
                .y(20)
                .height(40)
                .width(64)
                .build()
        );
        for (int i = 0; i < 2; i++) {
            img[i] = spt.getSprite((i) * width, 5 * height, width, height);
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

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        Graphics2D g2 = (Graphics2D) g;
        if (colidindo) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(new Font("Cambria Math", Font.PLAIN, 15));
            g.setColor(Color.blue);
            g.drawString("Area", this.getX() - Camera.x + 15, this.getY() - Camera.y - 20);
//			g.drawString(Configuracao.nextRota(), this.getX() - Camera.x, this.getY() - Camera.y);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        g.drawImage(img[0], this.getX() - Camera.x - 29, this.getY() - Camera.y + 3, width, height, null);
        g.drawImage(img[1], this.getX() - Camera.x - 29 + 64, this.getY() - Camera.y + 3, width, height, null);
        super.render(g, dadosGame);
    }
}