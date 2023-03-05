package entidades.cenario.iluminacao;

import main.DadosGame;
import entidades.Entidade;
import entidades.Mascara;
import main.enums.TipoMascara;
import main.menu.graficos.Spritesheet;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Poste extends Entidade {
    private final BufferedImage[] lampada = new BufferedImage[3];

    public Poste(int x, int y, Spritesheet spt, DadosGame dadosGame) {
        super(x, y, 0, 0);
        int tileSize = dadosGame.getTileSize();
        depth = 13;
        adicionarMascara(
                Mascara.builder()
                        .tipoMascara(TipoMascara.HURTBOX)
                        .x(25)
                        .y(-100)
                        .altura(100)
                        .largura(10)
                        .build()
        );
        lampada[0] = spt.getSprite((3) * tileSize, tileSize,
                tileSize, tileSize / 2);
        lampada[1] = spt.getSprite((3) * tileSize, tileSize + 31,
                tileSize, tileSize / 2 - 31);
        lampada[2] = spt.getSprite((3) * tileSize, tileSize + 31,
                tileSize, tileSize / 2);

    }

    public void render(Graphics g, DadosGame dadosGame) {
        int tileSize = dadosGame.getTileSize();
        g.drawImage(lampada[0], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize * 3 + 7, tileSize * 2,
                tileSize, null);
        g.drawImage(lampada[1], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize * 2 + 5, tileSize * 2,
                tileSize, null);
        g.drawImage(lampada[2], this.getX() - Camera.x - tileSize + 30,
                this.getY() - Camera.y - tileSize + 3, tileSize * 2,
                tileSize, null);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(this.getX() - Camera.x + 40 + rand.nextInt(25) + (10),
                this.getY() - Camera.y + rand.nextInt(20) - 140, 3, 3);
        g.fillOval(this.getX() - Camera.x + 40 + rand.nextInt(25) - 50,
                this.getY() - Camera.y + rand.nextInt(20) - 140, 3, 3);
        super.render(g, dadosGame);
    }
}