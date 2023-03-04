package entidades.cenario;

import main.DadosGame;
import entidades.Entidade;
import graficos.Spritesheet;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EscadaEsgoto extends Entidade {
    private final BufferedImage[] img = new BufferedImage[2];
    private float op = 0.1f;
    private int frames = 0;

    public EscadaEsgoto(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0);
        int tileSize = dadosGame.getTileSize();
        depth = 0;
//        adicionarMascara(new MascaraHitBox(18, 0, 31, 40));
//        img[0] = spt.getSprite(0, 6 * tileSize, tileSize, tileSize);
//        img[1] = spt.getSprite(0, 6 * tileSize, tileSize, tileSize - 3);
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
        int tileSize = dadosGame.getTileSize();
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 7; i++) {
            g.drawImage(img[1], this.getX() - Camera.x - 27, this.getY() - Camera.y - tileSize - (tileSize * i), tileSize * 2, tileSize, null);
        }
        g.drawImage(img[0], this.getX() - Camera.x - 27, this.getY() - Camera.y, tileSize * 2, tileSize, null);
        if (colidindo) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
            g.setFont(new Font("Cambria Math", Font.PLAIN, 20));
            g.setColor(Color.white);
            g.drawString("Cidade", this.getX() - Camera.x + 5, this.getY() - Camera.y - 20);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        super.render(g, dadosGame);
    }

}