package entidades.cenario.objetosluminosos;

import configuracoes.DadosGame;
import entidades.mascaras.MascaraHitBox;
import graficos.Spritesheet;
import world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PosteLuz extends ObjetoLuminoso {
    private final BufferedImage[] lampada = new BufferedImage[3];
    private float op = 0.1f;
    private int frames = 0;

    public PosteLuz(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0);
        int tileSize = dadosGame.getTileSize();
        adicionarMascara(new MascaraHitBox(-47, -30, 32, 40));
        lampada[0] = spt.getSprite((3) * tileSize, tileSize,
                tileSize, tileSize / 2);
        lampada[1] = spt.getSprite((3) * tileSize, tileSize + 31,
                tileSize, tileSize / 2 - 31);
        lampada[2] = spt.getSprite((3) * tileSize, tileSize + 31,
                tileSize, tileSize / 2);

    }

    public void tick(DadosGame dadosGame) {
        if (!dadosGame.isDia()) {
            depth = 7;
        } else {
            depth = 4;
        }
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

    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize();
        Graphics2D g2 = (Graphics2D) g;
        Random rand = new Random();
        g.drawImage(lampada[0], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize * 3 + 7, tileSize * 2,
                tileSize, null);
        g.drawImage(lampada[1], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize * 2 + 5, tileSize * 2,
                tileSize, null);
        g.drawImage(lampada[2], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize + 3, tileSize * 2,
                tileSize, null);
        if (!dadosGame.isDia()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g.setColor(Color.white);
            int[] parteComum = new int[]{
                    this.getY() - Camera.y - tileSize * 3 + 63,
                    this.getY() - Camera.y - tileSize * 3 + 58,
                    this.getY() - Camera.y - tileSize * 3 + 55,
                    this.getY() - Camera.y - tileSize * 3 + 55,
                    this.getY() - Camera.y - tileSize * 3 + 200,
                    this.getY() - Camera.y - tileSize * 3 + 197
            };
            g.fillPolygon(
                    new int[]{
                            this.getX() - Camera.x - tileSize + 3 + 50,
                            this.getX() - Camera.x - tileSize + 5 + 50,
                            this.getX() - Camera.x - tileSize + 8 + 50,
                            this.getX() - Camera.x - tileSize + 18 + 50,
                            this.getX() - Camera.x - tileSize + 40 + 50,
                            this.getX() - Camera.x - tileSize - 40 + 50
                    }, parteComum,
                    6);
            int num = 87 + 50;
            g.fillPolygon(
                    new int[]{
                            this.getX() - Camera.x - tileSize - 3 + num,
                            this.getX() - Camera.x - tileSize - 5 + num,
                            this.getX() - Camera.x - tileSize - 8 + num,
                            this.getX() - Camera.x - tileSize - 18 + num,
                            this.getX() - Camera.x - tileSize - 40 + num,
                            this.getX() - Camera.x - tileSize + 40 + num
                    }, parteComum,
                    6);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(this.getX() - Camera.x + 40 + rand.nextInt(25) + (10),
                    this.getY() - Camera.y + rand.nextInt(20) - 140, 3, 3);
            g.fillOval(this.getX() - Camera.x + 40 + rand.nextInt(25) - 50,
                    this.getY() - Camera.y + rand.nextInt(20) - 140, 3, 3);
        }
    }
}