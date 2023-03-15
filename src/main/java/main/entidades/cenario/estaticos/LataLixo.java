package main.entidades.cenario.estaticos;

import main.DadosGame;
import main.entidades.Entidade;
import main.utils.Spritesheet;
import main.utils.ImageUtils;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LataLixo extends Entidade {
    private final Random random = new Random();
    private final int[] index = {random.nextInt(2), random.nextInt(2), random.nextInt(2)};
    private final int[] pos = {random.nextInt(2), random.nextInt(2), random.nextInt(2)};
    private final BufferedImage[] lata = new BufferedImage[2];

    public LataLixo(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0, 10, 0, 1);
        int tileSize = dadosGame.getTileSize();
//        adicionarMascara(new MascaraHitBox(-25, -20, 46, 80));
        for (int i = 0; i < 2; i++) {
            lata[i] = spt.getSprite((4 + i) * tileSize, tileSize, tileSize, tileSize);
        }
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        for (int i = 0; i < 3; i++) {
            final int ni = i;
            final int x = this.getX() - Camera.x + 20 + (-i * 20);
            final int y = this.getY() - Camera.y + 7;
            int tileSize = dadosGame.getTileSize();
            ImageUtils.applySombreamento(
                    g,
                    imageAtualizada(ni),
                    tileSize,
                    x,
                    y,
                    sombreamento,
                    sombras,
                    () -> g.drawImage(imageAtualizada(ni),
                            x,
                            y,
                            tileSize, tileSize, null),
                    dadosGame.isDia(),
                    () -> drawMoscas(g)
            );
        }
        super.render(g, dadosGame);
    }

    private BufferedImage imageAtualizada(int i) {
        return pos[i] == 1 ? lata[index[i]] : ImageUtils.inverter(lata[index[i]]);
    }

    private void drawMoscas(Graphics g) {
        for (int i = 0; i < 3; i++) {
            if (index[i] == 0) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(this.getX() - Camera.x + 40 + random.nextInt(25) + (-i * 20),
                        this.getY() - Camera.y + 7 + random.nextInt(20), 3, 3);
            }
        }
    }
}