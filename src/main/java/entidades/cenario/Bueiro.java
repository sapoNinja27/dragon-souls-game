package entidades.cenario;

import main.DadosGame;
import entidades.Mascara;
import main.enums.TipoFonte;
import main.enums.TipoMascara;
import main.utils.FonteUtils;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bueiro extends Plataforma {
    private final BufferedImage[] img;
    private int frames = 0;
    private float op = 0.1f;

    public Bueiro(int x, int y, BufferedImage[] img) {
        super(x, y, null);
        this.img = img;
        depth = 3;
        adicionarMascara(Mascara.builder()
                .tipoMascara(TipoMascara.HITBOX)
                .x(17)
                .y(-15)
                .altura(40)
                .largura(32)
                .build());
    }

    @Override
    public void tick(DadosGame dadosGame) {
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
        int posX = getX() - Camera.x;
        int posY = getY() - Camera.y;
        if (colidindo) {
            g.drawImage(img[1], posX, posY, null);
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(FonteUtils.CrimsonText(TipoFonte.REGULAR, 20));
            g.setColor(Color.white);
            g.drawString("Esgotos", posX + 60, posY + 55);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else {
            g.drawImage(img[0], posX, posY, null);
        }
        super.render(g, dadosGame);
    }
}