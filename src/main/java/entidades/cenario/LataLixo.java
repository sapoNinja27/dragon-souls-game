package entidades.cenario;

import configuracoes.DadosGame;
import entidades.Entidade;
import entidades.mascaras.MascaraHitBox;
import graficos.Spritesheet;
import utils.ImageUtils;
import world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LataLixo extends Entidade {
    private final Random random = new Random();
    private final int[] index = {random.nextInt(2), random.nextInt(2), random.nextInt(2)};
    private final int[] pos = {random.nextInt(2), random.nextInt(2), random.nextInt(2)};
    private final BufferedImage[] lata = new BufferedImage[2];

    public LataLixo(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0);
        int tileSize = dadosGame.getTileSize();
        depth = 1;
        adicionarMascara(new MascaraHitBox(-25, -20, 46, 80));
        for (int i = 0; i < 2; i++) {
            lata[i] = spt.getSprite((4 + i) * tileSize, tileSize, tileSize, tileSize);
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize();
        for (int i = 0; i < 3; i++) {
            if (pos[i] == 1) {
                g.drawImage(lata[index[i]], this.getX() - Camera.x + 20 + (-i * 20), this.getY() - Camera.y + 7, tileSize, tileSize, null);
            } else {
                g.drawImage(ImageUtils.inverter(lata[index[i]]), this.getX() - Camera.x + 20 + (-i * 20),
                        this.getY() - Camera.y + 7, tileSize, tileSize, null);
            }

        }
        if (dadosGame.isDia()) {
            for (int i = 0; i < 3; i++) {
                if (index[i] == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(this.getX() - Camera.x + 40 + random.nextInt(25) + (-i * 20),
                            this.getY() - Camera.y + 7 + random.nextInt(20), 3, 3);
                }
            }
        }
    }
}