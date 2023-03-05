package entidades.cenario.objetoscommovimento;

import main.DadosGame;
import main.enums.TipoAmbiente;
import main.menu.graficos.Spritesheet;
import main.utils.ImageUtils;
import main.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Transito extends ObjetosComMovimento {
    private final BufferedImage img;

    public Transito(int x, int y, DadosGame dadosGame, Spritesheet spt) {
        super(x, y);
        int tileSize = dadosGame.getTileSize();
        Random random = new Random();
        img = ImageUtils.inverter(
                spt.getSprite((random.nextInt(2)) * tileSize, (7 + random.nextInt(2)) * tileSize, tileSize, tileSize)
        );
    }

    @Override
    public void tick(DadosGame dadosGame) {
        depth = 20;
        x += speed + 5;
    }

    @Override
    public void render(Graphics g, DadosGame dadosGame) {
        Graphics2D g2 = (Graphics2D) g;
        TipoAmbiente local = dadosGame.getLocal();
        int tileSize = dadosGame.getTileSize();
        int posX = this.getX() - Camera.x;
        int posY = this.getY() - Camera.y;
        if (TipoAmbiente.PERIMETRO_SUPERIOR.equals(local)) {
            if (dadosGame.isDia()) {
                g.drawImage(img, posX + 20 + (20), posY - 100, tileSize * 3, tileSize * 3, null);
            } else {
                g.drawImage(img, posX + 20 + (20), posY - 100, tileSize * 3, tileSize * 3, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g.drawImage(ImageUtils.sombreamento(img), posX + 20 + (20), posY - 100, tileSize * 3, tileSize * 3, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                g.setColor(Color.yellow);
                g.fillPolygon(
                        ImageUtils.createPolygon(
                                posX - tileSize + 3 + 300, posY - tileSize * 3 + 220,
                                posX - tileSize + 5 + 295, posY - tileSize * 3 + 230,
                                posX - tileSize + 8 + 300, posY - tileSize * 3 + 240,
                                posX - tileSize + 40 + 500, posY - tileSize * 3 + 260,
                                posX - tileSize + 40 + 500, posY - tileSize * 3 + 230,
                                posX - tileSize - 40 + 500 + 70, posY - tileSize * 3 + 200
                        )
                );
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        }
        super.render(g, dadosGame);
    }
}